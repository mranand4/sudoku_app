package com.sudoku2.controller;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sudoku2.model.Puzzle;
import com.sudoku2.service.PuzzleService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/sudoku")
public class SudokuController {
	
	@Autowired
	PuzzleService puzzleService;
	
	private static final String LEVEL_EASY = "easy";
	private static final String LEVEL_MEDIUM = "medium";
	private static final String LEVEL_HARD = "hard";
	private static final String LEVEL_RANDOM = "random";
	
	@GetMapping("/{code}")
	public Puzzle getSudoku(@PathVariable String code) {
		System.out.println(code);
		if(code == null) return null;
		
		code = code.toLowerCase().trim();
		
		//https://stackoverflow.com/a/34253764
		if(code.chars().allMatch( Character::isDigit )) {
			return puzzleService.getPuzzleById(Integer.valueOf(code));
		}
		
		if(code.equals(LEVEL_EASY) || code.equals(LEVEL_MEDIUM) || code.equals(LEVEL_HARD)) {
			return puzzleService.getPuzzle(code.charAt(0));
		}
		
		if(code.equals(LEVEL_RANDOM)) {
			char[] codes = {'e', 'm', 'h'};
//			/https://www.baeldung.com/java-generating-random-numbers-in-range
		    return puzzleService.getPuzzle(codes[new Random().nextInt(codes.length - 0)]);
		}
		
		return null;
	}

}
