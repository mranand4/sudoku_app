package com.sudoku2.payload.request;

import java.util.Set;

import jakarta.validation.constraints.*;

public class SignupRequest {

	private String name;

	@NotBlank
	@Size(max = 15)
	private String username;

	@NotBlank
	@Email
	private String email;

	private Set<String> role;

	@NotBlank
	private String password;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getRole() {
		return this.role;
	}

	public void setRole(Set<String> role) {
		this.role = role;
	}
}