package com.sudoku2.controller;

import java.sql.Timestamp;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sudoku2.dto.BookmarkDto;
import com.sudoku2.dto.SaveDto;
import com.sudoku2.dto.SolvedStatsDto;
import com.sudoku2.model.Bookmark;
import com.sudoku2.model.UserPuzzleCompositeId;
import com.sudoku2.model.BookmarkRepository;
import com.sudoku2.model.Puzzle;
import com.sudoku2.model.Save;
import com.sudoku2.model.SaveRepository;
import com.sudoku2.model.SolvedStats;
import com.sudoku2.model.SolvedStatsRepository;
import com.sudoku2.model.User;
import com.sudoku2.service.PuzzleService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/sudoku")
public class SudokuController {

	@Autowired
	PuzzleService puzzleService;
	
	@Autowired
	SaveRepository saveRepository;
	
	@Autowired
	SolvedStatsRepository solvedStatsRepository;

	private static final String LEVEL_EASY = "easy";
	private static final String LEVEL_MEDIUM = "medium";
	private static final String LEVEL_HARD = "hard";
	private static final String LEVEL_RANDOM = "random";

	/**
	 * Accepts code {sudoku id, level name}
	 * Returns a puzzle if code is valid with status code 200, otherwise returns a null puzzle with status code 400
	 */
	@GetMapping("/{code}")
	public ResponseEntity<Puzzle> getSudoku(@PathVariable String code) {
		Puzzle puzzle = new Puzzle();
				
		if (code == null) {
			new ResponseEntity<Puzzle>(new Puzzle(), HttpStatus.BAD_REQUEST);
		}

		code = code.toLowerCase().trim();

		// https://stackoverflow.com/a/34253764
		if (code.chars().allMatch(Character::isDigit)) {			
			puzzle = puzzleService.getPuzzleById(Integer.valueOf(code));
			if(puzzle == null) {			
				return new ResponseEntity<Puzzle>(new Puzzle(), HttpStatus.BAD_REQUEST);
			}
		} else if (code.equals(LEVEL_EASY) || code.equals(LEVEL_MEDIUM) || code.equals(LEVEL_HARD)) {
			puzzle = puzzleService.getPuzzle(code.charAt(0));
		} else if (code.equals(LEVEL_RANDOM)) {
			char[] codes = { 'e', 'm', 'h' };
//			/https://www.baeldung.com/java-generating-random-numbers-in-range
			puzzle = puzzleService.getPuzzle(codes[new Random().nextInt(codes.length - 0)]);
		} else {
			new ResponseEntity<Puzzle>(HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity<Puzzle>(puzzle, HttpStatus.OK);
	}

	@PostMapping("/generate/{count}")
	public String generatePuzzles(@PathVariable int count) {
		return puzzleService.addPuzzles(count);
	}

	@PostMapping("bookmark")
	public ResponseEntity<String> bookmark(@RequestBody BookmarkDto dto) {
		String msg = "Can't bookmark :(";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		if(puzzleService.bookmarkPuzzle(dto)) {
			msg = "Bookmarked successfully !";
			status = HttpStatus.OK;
		}
		
		return new ResponseEntity<String>(msg, status);
	}
	
	@DeleteMapping("bookmark")
	public ResponseEntity<String> deleteBookmark(@RequestBody BookmarkDto dto) {
		String msg = "Can't delete :(";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		if(puzzleService.deleteBookmark(dto)) {
			msg = "Deleted successfully !";
			status = HttpStatus.OK;
		}
		
		return new ResponseEntity<String>(msg, status);
	}
	
	@PostMapping("save")
	public ResponseEntity<String> save(@RequestBody SaveDto dto) {
		String msg = "Can't save :(";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		if(puzzleService.savePuzzle(dto)) {
			msg = "Saved successfully !";
			status = HttpStatus.OK;
		}
		
		return new ResponseEntity<String>(msg, status);	
	}
	
	@DeleteMapping("save")
	public ResponseEntity<String> deleteSave(@RequestBody SaveDto dto) {
		String msg = "Can't delete :(";
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		if(puzzleService.savePuzzle(dto)) {
			msg = "Deleted successfully !";
			status = HttpStatus.OK;
		}
		
		return new ResponseEntity<String>(msg, status);	
	}
	
	
	@PostMapping("solved")
	public ResponseEntity<List<SolvedStats>> solved(@RequestBody SolvedStatsDto dto) {
		List<SolvedStats> statsByLevel = puzzleService.solved(dto);
		
		if(statsByLevel != null) {
			return new ResponseEntity<List<SolvedStats>>(statsByLevel, HttpStatus.OK);
		}
		
		return new ResponseEntity<List<SolvedStats>>(HttpStatus.BAD_REQUEST);
	}
}
