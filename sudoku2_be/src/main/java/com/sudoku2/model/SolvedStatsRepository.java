package com.sudoku2.model;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SolvedStatsRepository extends CrudRepository<SolvedStats, UserPuzzleCompositeId> {
	
	
	/**
	 * https://www.baeldung.com/spring-data-derived-queries
	 * @param puzzle
	 * @return
	 */
	public List<SolvedStats> findByPuzzle(Puzzle puzzle);

}
