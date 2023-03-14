package com.sudoku2.controller;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sudoku2.dto.LoginDto;
import com.sudoku2.dto.RegisterDto;
import com.sudoku2.model.Role;
import com.sudoku2.model.RoleRepository;
import com.sudoku2.model.User;
import com.sudoku2.model.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@PostMapping("register")
	public ResponseEntity<String> register(@Valid @RequestBody RegisterDto registerDto) {
		System.out.println(registerDto);
		if (userRepository.existsByEmail(registerDto.getEmail())) {
			return new ResponseEntity<>("A user with this email already exists.", HttpStatus.BAD_REQUEST);
		}

		User user = new User();
		user.setUsername(registerDto.getEmail());
		user.setPassword(passwordEncoder.encode((registerDto.getPassword())));

		Role roles = roleRepository.findByName("user").get();
		user.setRoles(Collections.singletonList(roles));

		userRepository.save(user);

		return new ResponseEntity<>("User registered success!", HttpStatus.OK);
	}

	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody LoginDto loginDto) {

		try {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			return new ResponseEntity<>("Ok", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>("Incorrect email or password", HttpStatus.BAD_REQUEST);
		}

	}
}