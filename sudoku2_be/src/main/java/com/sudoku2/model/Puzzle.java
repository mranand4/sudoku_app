package com.sudoku2.model;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "puzzles")
public class Puzzle {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Nonnull
	@Column(length = 81, unique = true)
	private String puzzle;

	@Nonnull
	@Column(length = 81, unique = true)
	private String solution;
	
	@Column(length = 1)
	@NotNull
	private char difficulty;

	@CreationTimestamp
	@JsonIgnore
	@NotNull
	private Timestamp createdAt;
	
	public Puzzle() {
		
	}
	
	public Puzzle(int id) {
		this.id = id;
	}
	
	public Puzzle(String puzzle, String solution, char difficulty) {
		this.puzzle = puzzle;
		this.solution = solution;
		this.difficulty = difficulty;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPuzzle() {
		return puzzle;
	}

	public void setPuzzle(String puzzle) {
		this.puzzle = puzzle;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public char getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(char difficulty) {
		this.difficulty = difficulty;
	}
	
}
