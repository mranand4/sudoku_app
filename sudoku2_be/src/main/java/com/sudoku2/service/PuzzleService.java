package com.sudoku2.service;

import java.util.List;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.qqwing.PrintStyle;
import com.qqwing.QQWing;
import com.sudoku2.dto.BookmarkDto;
import com.sudoku2.dto.SaveDto;
import com.sudoku2.dto.SolvedStatsDto;
import com.sudoku2.model.Bookmark;
import com.sudoku2.model.BookmarkRepository;
import com.sudoku2.model.Puzzle;
import com.sudoku2.model.PuzzleRepository;
import com.sudoku2.model.Save;
import com.sudoku2.model.SaveRepository;
import com.sudoku2.model.SolvedStats;
import com.sudoku2.model.SolvedStatsRepository;
import com.sudoku2.model.User;
import com.sudoku2.model.UserPuzzleCompositeId;

@Service
public class PuzzleService {

	@Autowired
	private PuzzleRepository puzzleRepository;

	@Autowired
	BookmarkRepository bookmarkRepository;
	
	@Autowired
	SaveRepository saveRepository;
	
	@Autowired
	SolvedStatsRepository solvedStatsRepository;

	private QQWing qqWing;

	public PuzzleService() {
		this.qqWing = new QQWing();
	}

	private boolean addPuzzle(Puzzle puzzle) {
		try {
			puzzleRepository.save(puzzle);
			return true;
		} catch(ConstraintViolationException e) {
			System.out.println("An error occurred while saving the data - " + e);
			return false;
		}
	}

	public String addPuzzles(int count) {
		int countGenerated = 0;

		for(int i=0; i<count; i++) {
			qqWing.generatePuzzle(); // generates a puzzle
	        qqWing.setPrintStyle(PrintStyle.ONE_LINE); // so that we get answer in single line
	        qqWing.setRecordHistory(true); // with this we'll be able to see the difficulty of the puzzle
	        qqWing.solve();

	        Puzzle puzzle = new Puzzle(qqWing.getPuzzleString(), qqWing.getSolutionString(), qqWing.getDifficultyCode());

	        if(addPuzzle(puzzle)) {
	        	countGenerated++;
	        }
		}

		return "GENERATED " + countGenerated + "/" + count + " PUZZLES !";
	}

	public Puzzle getPuzzle(char difficulty) {
		return puzzleRepository.getPuzzleByDifficulty(difficulty);
	}

	public Puzzle getPuzzleById(int id) {
		return puzzleRepository.findById(id).orElse(null);
	}

	public boolean bookmarkPuzzle(BookmarkDto dto) {
		try {
			bookmarkRepository.save(new Bookmark(new Puzzle(dto.getPuzzleId()), new User(dto.getUserId()),
					dto.getCreatedAt()));
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean deleteBookmark(BookmarkDto dto) {

		UserPuzzleCompositeId bmrk = new UserPuzzleCompositeId(
				new Puzzle(dto.getPuzzleId()),
				new User(dto.getUserId())
				);

		try {
			bookmarkRepository.deleteById(bmrk);
			return true;
		} catch(Exception e ) {
			return false;
		}
	}
	
	public boolean savePuzzle(SaveDto dto) {
		try {			
			Save save = new Save(
					new User(dto.getUserId()),
					new Puzzle(dto.getPuzzleId()), 
					dto.getState(),
					dto.getElapsedSeconds(),
					dto.getNumMistakes(),
					dto.getCreatedAt()
					);
			
			saveRepository.save(save);
			
			return true;
			
		} catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return false;
	}
	
	public boolean deleteSavedPuzzle(SaveDto dto) {
		UserPuzzleCompositeId bmrk = new UserPuzzleCompositeId(
				new Puzzle(dto.getPuzzleId()),
				new User(dto.getUserId())
				);
				
		try {
			saveRepository.deleteById(bmrk);
			return true;
		} catch(Exception e ) {
			System.out.println(e.getMessage());
		}
		
		return false;
	}
	
	/**
	 * TODO : use jsonview here
	 * @param dto
	 * @return
	 */
	public List<SolvedStats> solved(SolvedStatsDto dto) {
		
		try {
			
			SolvedStats solved = new SolvedStats(
					new User(dto.getUserId()),
					new Puzzle(dto.getPuzzleId()), 
					dto.getElapsedSeconds(),
					dto.getNumMistakes(),
					dto.getCreatedAt()
				);
			
			solvedStatsRepository.save(solved);
		
			List<SolvedStats> similarStats = solvedStatsRepository.findByPuzzle(new Puzzle(dto.getPuzzleId()));
			
			return similarStats;
			
			
		} catch (Exception e) {
			System.err.println(e);
		}	
		
		return null;
	}

}
