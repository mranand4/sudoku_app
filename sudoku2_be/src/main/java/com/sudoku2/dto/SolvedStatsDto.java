package com.sudoku2.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sudoku2.model.Puzzle;
import com.sudoku2.model.User;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class SolvedStatsDto {
	
	private int userId;
	
	private int puzzleId;
	
	private int elapsedSeconds;
	
	private int numMistakes;
	
	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime createdAt;


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

	public int getElapsedSeconds() {
		return elapsedSeconds;
	}

	public void setElapsedSeconds(int elapsedSeconds) {
		this.elapsedSeconds = elapsedSeconds;
	}

	public int getNumMistakes() {
		return numMistakes;
	}

	public void setNumMistakes(int numMistakes) {
		this.numMistakes = numMistakes;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	@Override
	public String toString() {
		return "SolvedStatsDto [userId=" + userId + ", puzzleId=" + puzzleId + ", elapsedSeconds=" + elapsedSeconds
				+ ", numMistakes=" + numMistakes + ", createdAt=" + createdAt + "]";
	}
	
	
	
}
