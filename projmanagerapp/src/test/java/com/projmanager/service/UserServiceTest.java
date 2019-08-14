package com.projmanager.service;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.projmanager.entity.User;
import com.projmanager.repo.UserRepository;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceTest {

	MockMvc mockMvc;

	@Mock
	UserRepository userRepository;

	@InjectMocks
	UserService userService;

	@Before
	public void setUp() throws Exception {
		mockMvc = MockMvcBuilders.standaloneSetup(userService).build();
	}

	@Test
	public void test() throws Exception {

		User user = new User();
		user.setUserId(606L);
		user.setProjectId(606L);
		user.setEmpId("606");
		user.setFirstName("fname");
		user.setLastName("lname");
		user.setTaskId(606L);

		List<User> userList = new ArrayList<User>();
		userList.add(user);

		Optional<User> userOpt = null;

		when(userRepository.findAll()).thenReturn(userList);

		when(userRepository.save(user)).thenReturn(null);

		when(userRepository.findById(606L)).thenReturn(userOpt);

		assertEquals(userList, userService.getAllUser());

		assertEquals(user, userService.getUserByProjectId("606"));

		userService.addUser(user);
		verify(userRepository, times(1)).save(user);

		assertEquals(null, userService.getUser(606L).getFirstName());

		userService.deleteUser(user);
		verify(userRepository, times(0)).delete(user);

	}

}
