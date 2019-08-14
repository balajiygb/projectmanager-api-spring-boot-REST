package com.projmanager.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.projmanager.entity.ParentTask;
import com.projmanager.entity.Project;
import com.projmanager.entity.Task;
import com.projmanager.entity.User;
import com.projmanager.repo.ProjectRepository;
import com.projmanager.repo.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class ProjectServiceTest {

	MockMvc mockMvc;
	@Mock
	ProjectRepository projectRepository;
	@Mock
	UserRepository userRepository;

	@Mock
	TaskService taskService;

	@Mock
	UserService userService;

	@InjectMocks
	ProjectService projectService;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(projectService).build();
	}

	@Test
	public void test() throws Exception {

		Project project = new Project();
		project.setProject("AppleProj");
		project.setProjectId(606L);
		project.setPriority("2");
		project.setProjectId(606L);
		project.setStatus("C");
		project.setStartdt(new Date());
		project.setEnddt(new Date());
		project.setUserName("Jim");

		List<Project> projectList = new ArrayList<Project>();
		projectList.add(project);

		Task task = new Task();
		task.setTask("taskname");
		task.setTaskId(606L);
		task.setUserid(606L);
		task.setParentTaskName("ptask");
		task.setParentId(606L);
		task.setParentFlag("Y");
		task.setUserid(606L);
		task.setProjectId(606L);
		task.setPriority(2);
		task.setStatus("C");
		task.setStartdt(new Date());
		task.setEnddt(new Date());

		List<Task> taskList = new ArrayList<Task>();
		taskList.add(task);

		ParentTask pt = new ParentTask();
		pt.setParentTask("ptask_name");
		pt.setParentTaskId(606L);

		List<ParentTask> ptaskList = new ArrayList<ParentTask>();
		ptaskList.add(pt);

		User user = new User("fname_jim", "lname_cook", "emp_id_2020");
		user.setUserId(606L);

		when(projectRepository.findAll()).thenReturn(projectList);

		when(taskService.getAllTaskByProjId(606L)).thenReturn(taskList);

		when(userService.getUser(606L)).thenReturn(user);

		when(projectRepository.save(project)).thenReturn(project);

		assertEquals(projectList, projectService.getAllProjects());
		assertEquals(projectList, projectService.getProjects());

		projectService.deleteProject(project);
		verify(projectRepository, times(0)).delete(project);

		projectService.deleteProjectById(606L);
		verify(projectRepository, times(2)).deleteById(606L);

	}

}
