package com.projmanager.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
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
import com.projmanager.repo.ParentTaskRepository;
import com.projmanager.repo.ProjectRepository;
import com.projmanager.repo.TaskRepository;
import com.projmanager.repo.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class TaskServiceTest {

	MockMvc mockMvc;
	@Mock
	ProjectRepository projectRepository;

	@Mock
	UserRepository userRepository;
	@Mock
	TaskRepository taskRepository;
	@Mock
	ParentTaskRepository parentTaskRepository;

	@Mock
	ProjectService projectService;

	@Mock
	UserService userService;

	@InjectMocks
	TaskService taskService;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(taskService).build();
	}

	@Test
	public void test() throws Exception {
		Project project = new Project();
		project.setProject("AppleProj");
		project.setProjectId(606L);
		project.setPriority("2");
		project.setProjectId(606L);
		project.setStatus("C");

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

		List<Task> taskList = new ArrayList<Task>();
		taskList.add(task);

		List<Task> taskListempty = new ArrayList<Task>();

		ParentTask pt = new ParentTask("ptask");
		pt.setParentTask("ptask_name");
		pt.setParentTaskId(606L);

		List<ParentTask> ptaskList = new ArrayList<ParentTask>();
		ptaskList.add(pt);

		User user = new User("fname_jim", "lname_cook", "emp_id_2020");
		user.setUserId(606L);

		when(taskRepository.findAll()).thenReturn(taskList);
		when(parentTaskRepository.findById(606L)).thenReturn(null);

		assertEquals(taskList, taskService.getAllTask());
		assertEquals(taskListempty, taskService.getAllParentTask());
	}

}
