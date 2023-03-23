package com.sudoku2.controller;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sudoku2.dto.LoginDto;
import com.sudoku2.dto.LoginResponseDto;
import com.sudoku2.dto.RegisterDto;
import com.sudoku2.model.Role;
import com.sudoku2.model.RoleRepository;
import com.sudoku2.model.User;
import com.sudoku2.model.UserRepository;
import com.sudoku2.security.JwtGenerator;
import com.sudoku2.security.SecurityConstants;

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
	
	@Autowired
	JwtGenerator jwtGenerator;

	@PostMapping("register")
	public ResponseEntity<String> register(@Valid @RequestBody RegisterDto registerDto) {
		if (userRepository.existsByEmail(registerDto.getEmail())) {
			return new ResponseEntity<>("A user with this email already exists.", HttpStatus.BAD_REQUEST);
		}
		
		User user = new User();
		
		user.setUsername(registerDto.getEmail());
		user.setPassword(passwordEncoder.encode((registerDto.getPassword())));
		user.setName(registerDto.getName());

		Role roles = roleRepository.findByName("user").get();
		user.setRoles(Collections.singletonList(roles));

		userRepository.save(user);

		return new ResponseEntity<>("User registered success!", HttpStatus.OK);
	}

	@PostMapping("login")
	@ResponseBody
	public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto) {

		try {
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getEmail(), loginDto.getPassword()));
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			int id = userRepository.findByEmail(loginDto.getEmail()).get().getId();
			
			String token = jwtGenerator.generateToken(authentication);
			
			return new ResponseEntity<>(new LoginResponseDto(id,  token), HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}
}