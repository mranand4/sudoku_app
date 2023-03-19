package com.sudoku2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sudoku2.model.User;
import com.sudoku2.model.UserRepository;
import com.sudoku2.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@SuppressWarnings("deprecation")
	@GetMapping("{id}")
	public User getUser(@PathVariable int id) {
		return userRepository.getById(id);
	}
	
	@GetMapping("/ok")
	public String ok() {
		return "ok :)";
	}

}
