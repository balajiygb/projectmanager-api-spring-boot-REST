package com.projmanager.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.projmanager.entity.Project;
import com.projmanager.entity.Task;
import com.projmanager.entity.User;
import com.projmanager.repo.ProjectRepository;

@Service
public class ProjectService {
	
	private final Logger log = LoggerFactory.getLogger(ProjectService.class);


	@Autowired
	UserService userService;
	@Autowired
	ProjectRepository projectRepository;
	@Autowired
	TaskService taskService;

	public void addProject(Project project)throws Exception {

		Project savedProject = projectRepository.save(project);
		log.info("savedProject proj-id:" + savedProject.getProjectId());
		Long proj_id = savedProject.getProjectId();
		Long userid = project.getUserId();
		User user = userService.getUser(userid);
		user.setProjectId(proj_id);
		userService.addUser(user);
	}

	public void deleteProject(Project project) throws Exception{
		projectRepository.deleteById(project.getProjectId());
	}
	
	public void deleteProjectById(Long projectId)throws Exception {
		projectRepository.deleteById(projectId);
	}

	public List<Project> getProjects()throws Exception {
		return (List<Project>) projectRepository.findAll();
	}
	
	

	public List<Project> getAllProjects() throws Exception{

		List<Project> list = (List<Project>) projectRepository.findAll();
		for (Project temp : list) {

			List<Task> tasks = taskService.getAllTaskByProjId(temp.getProjectId());
			temp.setTotalTask(tasks.size() + "");
			int taskCounter = 0;
			for (Task tempTask : tasks) {

				if (tempTask.getStatus() != null && tempTask.getStatus().equalsIgnoreCase("C")) {
					taskCounter++;
				}
				temp.setCompletedTask(taskCounter + "");
			}
			if (temp.getUserId() != null)
				temp.setUserName(userService.getUser(temp.getUserId()).getFirstName());
		}
		return list;
	}

	public void updateProject(@RequestBody Project project)throws Exception {
		projectRepository.save(project);
		Long proj_id = project.getProjectId();
		Long userid = project.getUserId();
		User user = userService.getUser(userid);
		user.setProjectId(proj_id);
		userService.addUser(user);
	}

}
