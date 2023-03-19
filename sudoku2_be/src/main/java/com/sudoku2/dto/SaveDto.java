package com.sudoku2.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

public class SaveDto {
	
	private int userId;
	
	private int puzzleId;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdAt;
	
	private int elapsedSeconds;
	
	private int numMistakes;
	
	public int getNumMistakes() {
		return numMistakes;
	}

	public void setNumMistakes(int numMistakes) {
		this.numMistakes = numMistakes;
	}

	private String state;

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getPuzzleId() {
		return puzzleId;
	}

	public void setPuzzleId(int puzzleId) {
		this.puzzleId = puzzleId;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public int getElapsedSeconds() {
		return elapsedSeconds;
	}

	public void setElapsedSeconds(int elapsedSeconds) {
		this.elapsedSeconds = elapsedSeconds;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	
	
}
