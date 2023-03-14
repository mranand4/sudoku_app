package com.sudoku2.dto;

import java.time.LocalDateTime ;


import com.fasterxml.jackson.annotation.JsonFormat;

public class BookmarkDto {
	
	private int userId;
	
	private int puzzleId;
	
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
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	@Override
	public String toString() {
		return "BookmarkDto [userId=" + userId + ", puzzleId=" + puzzleId + ", createdAt=" + createdAt + "]";
	}
	
	
	
	
}
