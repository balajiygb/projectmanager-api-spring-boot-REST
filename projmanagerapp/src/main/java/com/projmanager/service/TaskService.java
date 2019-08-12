package com.projmanager.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.projmanager.entity.ParentTask;
import com.projmanager.entity.Task;
import com.projmanager.entity.User;
import com.projmanager.repo.ParentTaskRepository;
import com.projmanager.repo.TaskRepository;

@Service
public class TaskService {
	
	private final Logger log = LoggerFactory.getLogger(TaskService.class);


	@Autowired
	TaskRepository taskRepository;

	@Autowired
	ParentTaskRepository parentTaskRepository;

	@Autowired
	UserService userService;

	public void addTask(@RequestBody Task task) {
		try {
			taskRepository.save(task);
			Long task_id = task.getTaskId();
			if (task.getParentFlag().equalsIgnoreCase("Y")) {
				// Add parent task table
				ParentTask parentTask = new ParentTask();
				parentTask.setParentTask(task.getTask());
				parentTaskRepository.save(parentTask);
			}
			// update task id in user table
			Long userid = task.getUserid();
			User user = userService.getUser(userid);
			user.setTaskId(task_id);
			userService.addUser(user);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}
	
	public List<Task> getAllTask() throws Exception{
		List<Task> tasks = new ArrayList<Task>();
		// java 8 for each loop
		taskRepository.findAll().forEach(tasks::add);
		return tasks;
	}
	
	public List<ParentTask> getAllParentTask()throws Exception {
		List<ParentTask> parenttasks = new ArrayList<ParentTask>();
		parentTaskRepository.findAll().forEach(parenttasks::add);
		return parenttasks;
	}
	
	public List<Task> getAllTaskByProjId(Long id)throws Exception {
		List<Task> taskList = new ArrayList<Task>();
		List<Task> tasks = getAllTask();
		for (Task temp : tasks) {
			if (temp.getProjectId() == id) {
				if (temp.getParentId() != null) {
					try {
						ParentTask pt = parentTaskRepository.findById(temp.getParentId()).get();
						temp.setParentTaskName(pt.getParentTask());
					} catch (Exception e) {
						 e.printStackTrace();
					}
				}
				taskList.add(temp);
			}
		}
		return taskList;
	}

}
