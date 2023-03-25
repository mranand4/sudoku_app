package com.sudoku2.model;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface SolvedStatsRepository extends CrudRepository<SolvedStats, UserPuzzleCompositeId> {
	
	
	/**
	 * https://www.baeldung.com/spring-data-derived-queries
	 * @param puzzle
	 * @return
	 */
	public List<SolvedStats> findByPuzzle(Puzzle puzzle);
	
	/**
	 * https://stackoverflow.com/questions/44680729/spring-boot-jpa-filter-by-join-table
	 */
	public List<SolvedStats> findByPuzzleDifficultyEquals(char difficulty);

}
