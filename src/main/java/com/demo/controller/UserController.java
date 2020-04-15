package com.demo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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

	@RequestMapping("/welcome")
	public String Welcome(HttpServletRequest request) {
		
		return "welcome";
	}

	@RequestMapping("/register")
	public String Registration(HttpServletRequest request, Model model) {
		model.addAttribute("user", new User());
		
		return "register";
	}

	@PostMapping("/save-user")
	public String registerUser(@ModelAttribute User user, BindingResult bindingResult, HttpServletRequest request) {
		userServices.saveMyUser(user);
		return "welcome";
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
		if (userServices.findByUsernameAndPassword(user.getUserName(), user.getPassWord()) != null) {
			return "login";
		} else {
			request.setAttribute("error", "Invalid Username or Password");
			return "login";

		}
	}
}
