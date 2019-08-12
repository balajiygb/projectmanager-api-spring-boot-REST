package com.projmanager.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projmanager.entity.ParentTask;
import com.projmanager.entity.Task;
import com.projmanager.repo.ParentTaskRepository;
import com.projmanager.repo.TaskRepository;
import com.projmanager.service.TaskService;
import com.projmanager.service.UserService;

@RestController
@RequestMapping("/tasks")
public class TaskController {
	
	private final Logger log = LoggerFactory.getLogger(TaskController.class);

	@Autowired
	TaskRepository taskRepository;
	@Autowired
	ParentTaskRepository parentTaskRepository;

	@Autowired
	UserService userService;
	@Autowired
	TaskService taskService;

	@RequestMapping(value = "/addtask", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	public void addTask(@RequestBody Task task)throws Exception {
		log.info("adding task");
		taskService.addTask(task);
	}

	@RequestMapping("/task")
	public List<Task> getAllTask()throws Exception {
		return taskService.getAllTask();
		
	}

	@RequestMapping("/parenttask")
	public List<ParentTask> getAllParentTask() throws Exception{
		return taskService.getAllParentTask();
	}

	@RequestMapping("/viewtask/projectid/{id}")
	public List<Task> getAllTaskByProjId(@PathVariable(value = "id") Long id) throws Exception{
		return taskService.getAllTaskByProjId(id);
	}

	@RequestMapping("/task/{id}")
	public Task getUserbyId(@PathVariable(value = "id") Long id) throws Exception{
		return taskRepository.findById(id).get();
	}

}
