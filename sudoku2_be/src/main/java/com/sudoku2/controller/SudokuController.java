package com.sudoku2.controller;

import java.sql.Timestamp;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sudoku2.dto.BookmarkDto;
import com.sudoku2.model.Bookmark;
import com.sudoku2.model.BookmarkRepository;
import com.sudoku2.model.Puzzle;
import com.sudoku2.model.User;
import com.sudoku2.service.PuzzleService;
import com.sudoku2.utils.DateUtils;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/sudoku")
public class SudokuController {

	@Autowired
	PuzzleService puzzleService;

	@Autowired
	BookmarkRepository bookmarkRepository;

	private static final String LEVEL_EASY = "easy";
	private static final String LEVEL_MEDIUM = "medium";
	private static final String LEVEL_HARD = "hard";
	private static final String LEVEL_RANDOM = "random";

	@GetMapping("/{code}")
	public Puzzle getSudoku(@PathVariable String code) {
		if (code == null)
			return null;

		code = code.toLowerCase().trim();

		// https://stackoverflow.com/a/34253764
		if (code.chars().allMatch(Character::isDigit)) {
			return puzzleService.getPuzzleById(Integer.valueOf(code));
		}

		if (code.equals(LEVEL_EASY) || code.equals(LEVEL_MEDIUM) || code.equals(LEVEL_HARD)) {
			return puzzleService.getPuzzle(code.charAt(0));
		}

		if (code.equals(LEVEL_RANDOM)) {
			char[] codes = { 'e', 'm', 'h' };
//			/https://www.baeldung.com/java-generating-random-numbers-in-range
			return puzzleService.getPuzzle(codes[new Random().nextInt(codes.length - 0)]);
		}

		return null;
	}

	@PostMapping("/generate/{count}")
	public String generatePuzzles(@PathVariable int count) {
		return puzzleService.addPuzzles(count);
	}

	@PostMapping("bookmark")
	public ResponseEntity<String> bookmark(@RequestBody BookmarkDto bookmarkDto) {
		try {

			System.out.println(bookmarkDto);
			
			Bookmark bookmark = new Bookmark(new Puzzle(bookmarkDto.getPuzzleId()), new User(bookmarkDto.getUserId()),
					bookmarkDto.getCreatedAt());
			bookmarkRepository.save(bookmark);
			return new ResponseEntity<>("Bookmarked !", HttpStatus.OK);
			
		} catch (Exception e) {
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

}
