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

import com.projmanager.entity.Project;
import com.projmanager.repo.ProjectRepository;
import com.projmanager.service.ProjectService;

@RestController
@RequestMapping("/projects")
public class ProjectController {
	private final Logger log = LoggerFactory.getLogger(ProjectController.class);

	
	@Autowired
	ProjectRepository projectRepository;
	

	
	@Autowired
	ProjectService projectService;
	
	@RequestMapping(value="/addproject" ,method = RequestMethod.POST, produces = "application/json", consumes = "application/json") 
	public void addproject(@RequestBody Project project)throws Exception {
		log.info("Add proj : proj ID : "+project.getProjectId());
		projectService.addProject(project);
		
	}
	
	@RequestMapping(value="/updateproject" ,method = RequestMethod.POST, produces = "application/json", consumes = "application/json") 
	public void updateProject(@RequestBody Project project)throws Exception {
		projectService.updateProject(project);
	}
	
	@RequestMapping("/project") 
	public List<Project> getProjects()throws Exception {
		return projectService.getProjects();
	}
	
	@RequestMapping("/projectwithtask") 
	public List<Project> getAllProjects() throws Exception{
		return projectService.getAllProjects();
		
	}
	
	@RequestMapping("/project/{id}") 
	public Project getProjctbyId(@PathVariable(value = "id") Long id)throws Exception {
		return projectRepository.findById(id).get();
	}

	
	@RequestMapping("/deleteproject")
	public void deleteProject(@RequestBody Project project)throws Exception {
		projectService.deleteProject(project);
		
	}
	
}
