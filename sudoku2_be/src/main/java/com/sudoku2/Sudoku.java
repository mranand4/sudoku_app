package com.sudoku2;

import org.springframework.stereotype.Service;

public class Sudoku {
	
	private String board;
	private String solution;
	private String difficulty;
	
	public String getBoard() {
		return board;
	}
	public void setBoard(String board) {
		this.board = board;
	}
	public String getSolution() {
		return solution;
	}
	public void setSolution(String solution) {
		this.solution = solution;
	}
	public String getDifficulty() {
		return difficulty;
	}
	public void setDifficulty(String difficulty) {
		this.difficulty = difficulty;
	}
	
	

}
