package com.projmanager.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.projmanager.entity.User;
import com.projmanager.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserControllerTest {
	
	private MockMvc mockMvc;
	
	@Mock
	UserService userService;
	
	@InjectMocks
	UserController userController;


    @Before
    public void setUp() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .build();
    }
	
	@Test
	public void test() throws Exception {
		User user=new User("fname_jim","lname_cook","emp_id_2020");
		user.setUserId(606L);
		when(userService.getUser(606L)).thenReturn(user);
		when(userService.getUserByProjectId("606")).thenReturn(user);
		
	 
		
		String json = "{\n" +
                "  \"firstName\": \"ygb\",\n" +
                "  \"lastName\": \"geo\"\n" +
                "  \"empId\": \"224258\"\n" +
                "}";
		
		  mockMvc.perform(get("/users/user/1011111")
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());
		  
			
		  mockMvc.perform(get("/users/user")
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());
		  
		  mockMvc.perform(get("/users/user")
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());
		  
		  mockMvc.perform(get("/users/user/projectid/606")
	                .accept(MediaType.APPLICATION_JSON))
	                .andExpect(status().isOk());
		  
		  mockMvc.perform(post("/users/adduser")
	                .accept(MediaType.APPLICATION_JSON)
	                .content(json)).andReturn();
		  userController.addUser(user);
		  
		  mockMvc.perform(post("/users/deleteuser")
	                .accept(MediaType.APPLICATION_JSON)
	                .content(json)).andReturn();
		  userController.deleteUser(user);
		  
		  mockMvc.perform(post("/users/edituser")
	                .accept(MediaType.APPLICATION_JSON)
	                .content(json)).andReturn();
		  
		  userController.updateUser(user);
	}

}
