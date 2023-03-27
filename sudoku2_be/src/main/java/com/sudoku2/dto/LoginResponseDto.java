package com.sudoku2.dto;

public class LoginResponseDto {
	
	private int id;
	private String jwt;
	private String name;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getJwt() {
		return jwt;
	}
	public void setJwt(String jwt) {
		this.jwt = jwt;
	}
	public LoginResponseDto(int id, String name, String jwt) {
		this.id = id;
		this.name = name;
		this.jwt = jwt;
	}
	
	

}
