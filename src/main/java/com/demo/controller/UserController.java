package com.demo.controller;

import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.demo.model.User;
import com.demo.services.UserServices;

@Controller
public class UserController {

	@Autowired
	private UserServices userServices;

	@RequestMapping({"/", "/welcome", "/home"})
	public String Welcome(HttpServletRequest request) {
		return "welcome";
	}

	@RequestMapping("/register")
	public String Registration(HttpServletRequest request) {
		return "register";
	}

	@PostMapping("/save-user")
	public String registerUser(@ModelAttribute User user, BindingResult bindingResult, HttpServletRequest request) {
		userServices.saveMyUser(user);
		return "redirect:home?action=register";
	}

	@GetMapping("/show-users")
	public String showAllUsers(HttpServletRequest request) {
		request.setAttribute("users", userServices.showAllUsers());
		return "home";
	}

	@RequestMapping("/login")
	public String login(HttpServletRequest request) {
		return "login";
	}

	@RequestMapping("/login-user")
	public String loginUser(@ModelAttribute User user, HttpServletRequest request) {
		if (userServices.findByUsernameAndPassword(user.getUsername(), user.getPassword()) != null) {
			return "welcome";
		} else {
			return "login";
		}
	}

	@RequestMapping("/logout")
	public String logout(HttpServletRequest request){
		return "welcome";
	}
}
