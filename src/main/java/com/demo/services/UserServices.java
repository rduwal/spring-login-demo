package com.demo.services;

import java.util.ArrayList;
import java.util.List;


import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.demo.model.User;
import com.demo.repo.UserRepository;

@Service
@Transactional
public class UserServices {

	private final UserRepository userRepository;
	
	public UserServices(UserRepository userRepository) {
		this.userRepository=userRepository;
	}


	public List<User> showAllUsers(){
		List<User> users = new ArrayList<User>();
		for(User user : userRepository.findAll()) {
			users.add(user);
		}
		return users;
	}

	public  void saveMyUser(User user) {
		userRepository.save(user);
		
	}

	public User findByUsernameAndPassword(String username, String password) {
		return userRepository.findByUsernameAndPassword(username, password);
	}



	

	
	



}
