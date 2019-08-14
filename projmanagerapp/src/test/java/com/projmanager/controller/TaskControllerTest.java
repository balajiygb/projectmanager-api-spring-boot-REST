package com.projmanager.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.projmanager.entity.ParentTask;
import com.projmanager.entity.Project;
import com.projmanager.entity.Task;
import com.projmanager.entity.User;
import com.projmanager.repo.TaskRepository;
import com.projmanager.service.TaskService;
import com.projmanager.service.UserService;
@RunWith(SpringJUnit4ClassRunner.class)
public class TaskControllerTest {
	
private MockMvc mockMvc;
	@Mock
	TaskService taskService;
	@Mock
	TaskRepository taskRepository;
	@Mock
	UserService userService;
	@InjectMocks
	TaskController taskController;
	
	 @Before
	    public void setUp() throws Exception {
	        mockMvc = MockMvcBuilders.standaloneSetup(taskController)
	                .build();
	    }

	@Test
	public void test() throws Exception {
		User user=new User("1","2","3");
		user.setUserId(606L);
		
		Task task = new Task();
		task.setTask("taskname");
		task.setTaskId(606L);
		task.setUserid(606L);
		task.setParentTaskName("ptask");
		task.setParentId(606L);
		task.setParentFlag("Y");
		task.setUserid(606L);
		task.setProjectId(606L);
		
		List<Task> taskList =new ArrayList<Task>();
		taskList.add(task);
		
		ParentTask pt=new ParentTask();
		pt.setParentTask("ptask_name");
		pt.setParentTaskId(606L);
		
		List<ParentTask> ptaskList =new ArrayList<ParentTask>();
		ptaskList.add(pt);
		
		String json = "{\n" +
                "  \"taskId\": \"606\",\n" +
                "  \"task\": \"Coding\"\n" +
                "  \"parentTaskName\": \"Analysis\"\n" +
                "}";
		
		when(taskService.getAllTask()).thenReturn(taskList);
		when(taskService.getAllParentTask()).thenReturn(ptaskList);
		
		when(taskService.getAllTaskByProjId(606L)).thenReturn(taskList);
		
		mockMvc.perform(get("/tasks/task")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
		
		mockMvc.perform(get("/tasks/parenttask")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
		
		mockMvc.perform(get("/tasks//viewtask/projectid/606")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
		
		  mockMvc.perform(post("/tasks/addtask")
	                .accept(MediaType.APPLICATION_JSON)
	                .content(json)).andReturn();
		  taskController.addTask(task);
		
		
	}

}
