package com.sudoku2.model;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface PuzzleRepository extends CrudRepository<Puzzle, Integer> {
	
	@Query("SELECT p FROM Puzzle p WHERE p.difficulty = :difficulty ORDER BY RAND() LIMIT 1")
	public Puzzle getPuzzleByDifficulty(@Param("difficulty") char difficulty);

}
