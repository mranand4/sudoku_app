package com.sudoku2.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class SolvedStats {
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@ManyToOne
	@JoinColumn(name = "puzzle_id")
	private Puzzle puzzle;
	
	private int elapsedSeconds;
	
	private int numMistakes;
	
	private LocalDateTime createdAt;
	
	

}
