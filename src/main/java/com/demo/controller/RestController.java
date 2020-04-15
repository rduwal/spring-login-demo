package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.demo.model.User;
import com.demo.services.UserServices;

@org.springframework.web.bind.annotation.RestController
public class RestController {

	@Autowired
	private UserServices userServices;

	@GetMapping("/")
	public String Ranjan() {
		return "hi ranjan";
	}

	@GetMapping("/saveuser")
	public String saveUser(@RequestParam String username, @RequestParam String firstname, @RequestParam String lastname,
			@RequestParam int age, @RequestParam String password) {
		User user = new User(age, username, firstname, lastname, age, password);
		userServices.saveMyUser(user);
		return "User Saved";
	}

}
