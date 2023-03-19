package com.sudoku2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sudoku2.dto.RegisterDto;
import com.sudoku2.model.User;
import com.sudoku2.model.UserRepository;
import com.sudoku2.service.UserService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {
	
	@Autowired
	UserService userService;
	
	@GetMapping("{id}")
	public ResponseEntity<User> getUser(@PathVariable int id) {
		User user = userService.getUserById(id);
		
		if(user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		
		return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping("{id}")
	public ResponseEntity<User> getUser(@PathVariable int id, @RequestBody RegisterDto dto) {
		User user = userService.updateUser(id, dto);
		
		if(user != null) {
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
		
		return new ResponseEntity<User>(HttpStatus.BAD_REQUEST);
	}
	
	@DeleteMapping("{id}")
	public boolean deleteUser(@PathVariable int id) {
		userService.deleteUserById(id);
		return true;
	}

}
