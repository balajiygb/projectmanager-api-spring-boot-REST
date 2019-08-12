package com.projmanager.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projmanager.entity.User;
import com.projmanager.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private final Logger log = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value="/adduser" ,method = RequestMethod.POST, produces = "application/json", consumes = "application/json") 
	public void addUser(@RequestBody User user) throws Exception{
			log.info("add User ..");
			userService.addUser(user);
		
	}
	
	
	@GetMapping(value="/user",produces = "application/json") 
	public List<User> getAllUser() throws Exception{
		log.info("get all users ..");
		return userService.getAllUser();
	}
	
	@GetMapping(value="/user/{id}",produces = MediaType.APPLICATION_JSON_VALUE) 
	public User getUserbyId(@PathVariable(value = "id") Long userId) throws Exception{
		log.info("get user by user id.");
		return userService.getUser(userId);
	}

	
	@RequestMapping("/user/projectid/{id}") 
	public User getUserByProjectId(@PathVariable(value = "id") String userId)throws Exception {
		log.info("get user by project id.");
		return userService.getUserByProjectId(userId);
	}
	
	@RequestMapping(value="/edituser" ,method = RequestMethod.POST, produces = "application/json", consumes = "application/json") 
	public void updateUser(@RequestBody User user)throws Exception {
		log.info("Update user");
		userService.addUser(user);
	}
	
	@RequestMapping("/deleteuser")
	public void deleteUser(@RequestBody User user)throws Exception {
		log.info("Delete user");
		userService.deleteUser(user);
		
	}

}
