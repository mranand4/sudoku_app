package com.sudoku2.dto;

import jakarta.annotation.Nonnull;

public class RegisterDto {
	
	@Nonnull
	private String email;
	
	@Nonnull
	private String password;
	private String name;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "RegisterDto [email=" + email + ", password=" + password + ", name=" + name + "]";
	}
	
}
