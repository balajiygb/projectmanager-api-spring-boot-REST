package com.projmanager.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projmanager.entity.User;
import com.projmanager.repo.UserRepository;

@Service
public class UserService {
	private final Logger log = LoggerFactory.getLogger(UserService.class);

	@Autowired
	UserRepository userRepository;

	public List<User> getAllUser() {
		// userRepository.findAll(); ---- gives all user from DB
		List<User> users = new ArrayList<User>();
		// java 8 for each loop
		try {
			userRepository.findAll().forEach(users::add);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return users;
	}

	public User getUser(Long userId) {
		User user = new User();
		try {
			user = userRepository.findById(userId).get();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return user;
	}

	public User getUserByProjectId(String userId) {

		// userRepository.findAll(); ---- gives all user from DB
		List<User> users = new ArrayList<User>();
		User user = new User();
		try {
			userRepository.findAll().forEach(users::add);
			for (User temp : users) {
				if (temp.getProjectId().toString().equalsIgnoreCase(userId)) {
					return temp;
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());

		}

		return user;
	}

	public void addUser(User user) {
		log.debug("User ID to add /update ; ", user.getUserId());
		try {
			userRepository.save(user);
		} catch (Exception e) {
			log.error(e.getMessage());
		}
	}

	public void deleteUser(User user) {
		log.debug("User ID to delete ; ", user.getUserId());
		try {
			userRepository.deleteById(user.getUserId());
		} catch (Exception e) {
			log.error(e.getMessage());
		}

	}

}
