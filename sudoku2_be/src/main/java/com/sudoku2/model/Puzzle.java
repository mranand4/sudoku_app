package com.sudoku2.model;

import java.sql.Timestamp;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

@Entity
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
	
	@Nonnull
	@Column(length = 1)
	private char difficulty;

	@CreationTimestamp
	private Timestamp createdAt;
	
	public Puzzle() {
		
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
	

}
