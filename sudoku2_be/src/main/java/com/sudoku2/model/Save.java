package com.sudoku2.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.GeneratorType;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "saved_puzzles")
@IdClass(UserPuzzleCompositeId.class) 
public class Save {

	@Id
	@ManyToOne
	@JoinColumn(name = "puzzle_id")
	private Puzzle puzzle;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User user;
	
	@NotNull
	private String state;
	
	@NotNull
	private int elapsedSeconds;
	
	@NotNull
	private LocalDateTime createdAt;
	
	@NotNull
	private int numMistakes;
	
	public Save() {
		
	}

	public Save(User user, Puzzle puzzle, String state, int elapsedSeconds, int numMistakes, LocalDateTime createdAt) {
		this.user = user;
		this.puzzle = puzzle;
		this.state = state;
		this.elapsedSeconds = elapsedSeconds;
		this.numMistakes = numMistakes;
		this.createdAt = createdAt;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Puzzle getPuzzle() {
		return puzzle;
	}

	public void setPuzzle(Puzzle puzzle) {
		this.puzzle = puzzle;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getElapsedSeconds() {
		return elapsedSeconds;
	}

	public void setElapsedSeconds(int elapsedSeconds) {
		this.elapsedSeconds = elapsedSeconds;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	
}
