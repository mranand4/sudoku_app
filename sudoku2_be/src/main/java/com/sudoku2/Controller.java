package com.sudoku2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sudoku2.model.Puzzle;
import com.sudoku2.service.PuzzleService;

@RestController
public class Controller {
	
	@Autowired
	PuzzleService puzzleService;
	
	@GetMapping("/api/sudoku/generate/{count}")
	public String generatePuzzles(@PathVariable int count) {
		return puzzleService.addPuzzles(count);
	}
	
	@GetMapping("/api/sudoku/{difficulty}")
	public Puzzle getPuzzle(@PathVariable char difficulty) {
		return puzzleService.getPuzzle(difficulty);
	}

}
