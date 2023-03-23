package com.sudoku2.dto;

public class LoginResponseDto {
	
	private int id;
	private String jwt;
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
	public LoginResponseDto(int id, String jwt) {
		super();
		this.id = id;
		this.jwt = jwt;
	}
	
	

}
