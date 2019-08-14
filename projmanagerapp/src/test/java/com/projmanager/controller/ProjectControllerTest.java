package com.projmanager.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.projmanager.entity.Project;
import com.projmanager.entity.User;
import com.projmanager.repo.ProjectRepository;
import com.projmanager.service.ProjectService;
import com.projmanager.service.TaskService;
import com.projmanager.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProjectControllerTest {
	
private MockMvc mockMvc;
	
	@Mock
	ProjectService projectService;

	
	@Mock
	ProjectRepository projectRepository;
	
	@Mock
	UserService userService;
	
	@InjectMocks
	ProjectController projectController;
	
	 @Before
	    public void setUp() throws Exception {
	        mockMvc = MockMvcBuilders.standaloneSetup(projectController)
	                .build();
	    }

	@Test
	public void test() throws Exception {
		User user=new User("1","2","3");
		user.setUserId(606L);
		
		Project project=new Project();
		project.setProject("AppleProj");
		project.setUserId(606L);
		project.setPriority("2");
		project.setProjectId(606L);
		project.setStatus("C");
		List<Project> projectList =new ArrayList<Project>();
		projectList.add(project);
		
		String json = "{\n" +
                "  \"projectId\": \"606\",\n" +
                "  \"project\": \"applecity\"\n" +
                "  \"priority\": \"2\"\n" +
                "}";
		
		when(projectService.getProjects()).thenReturn(projectList);
	
		
		
		
		mockMvc.perform(get("/projects/project")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
		
	
		
		mockMvc.perform(get("/projects/projectwithtask")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
		
		  mockMvc.perform(post("/users/addproject")
	                .accept(MediaType.APPLICATION_JSON)
	                .content(json)).andReturn();
		  projectController.addproject(project);
		  
		  mockMvc.perform(post("/users/deleteproject")
	                .accept(MediaType.APPLICATION_JSON)
	                .content(json)).andReturn();
		  projectController.deleteProject(project);
		  
		  mockMvc.perform(post("/users/updateproject")
	                .accept(MediaType.APPLICATION_JSON)
	                .content(json)).andReturn();
		  projectController.updateProject(project);

	}

}
