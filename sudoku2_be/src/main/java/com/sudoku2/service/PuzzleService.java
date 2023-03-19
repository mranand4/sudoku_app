package com.sudoku2.service;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.qqwing.PrintStyle;
import com.qqwing.QQWing;
import com.sudoku2.dto.BookmarkDto;
import com.sudoku2.model.Bookmark;
import com.sudoku2.model.BookmarkRepository;
import com.sudoku2.model.Puzzle;
import com.sudoku2.model.PuzzleRepository;
import com.sudoku2.model.User;
import com.sudoku2.model.UserPuzzleCompositeId;

@Service
public class PuzzleService {

	@Autowired
	private PuzzleRepository puzzleRepository;

	@Autowired
	BookmarkRepository bookmarkRepository;

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

}
