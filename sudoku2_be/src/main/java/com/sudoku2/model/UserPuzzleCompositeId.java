package com.sudoku2.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class UserPuzzleCompositeId implements Serializable {
	
	private Puzzle puzzle;
	private User user;
	
	public UserPuzzleCompositeId() {
		
	}

	public UserPuzzleCompositeId(Puzzle puzzle, User user) {
		this.puzzle = puzzle;
		this.user = user;
	}

	public Puzzle getPuzzle() {
		return puzzle;
	}

	public void setPuzzle(Puzzle puzzle) {
		this.puzzle = puzzle;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	} 
	
}
