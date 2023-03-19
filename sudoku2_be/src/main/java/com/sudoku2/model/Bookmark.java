package com.sudoku2.model;

import java.time.LocalDateTime ;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Embeddable;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;

/**
 * https://www.jpa-buddy.com/blog/the-ultimate-guide-on-composite-ids-in-jpa-entities/
 * https://stackoverflow.com/questions/31385658/jpa-how-to-make-composite-foreign-key-part-of-composite-primary-key
 * https://stackoverflow.com/questions/3585034/how-to-map-a-composite-key-with-jpa-and-hibernate
 * @author shivansh
 *
 */
@Entity
@IdClass(UserPuzzleCompositeId.class) 
public class Bookmark {
	
	@Id
	@ManyToOne
	@JoinColumn(name = "puzzle_id")
	private Puzzle puzzle;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "user_id")
	@JsonBackReference
	private User user;
	
	private LocalDateTime createdAt;
	
	public Bookmark() {
		
	}

	public Bookmark(Puzzle puzzle, User user, LocalDateTime createdAt) {
		this.puzzle = (puzzle);
		this.user = (user);
		this.createdAt = createdAt;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
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
