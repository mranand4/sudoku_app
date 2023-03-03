package com.sudoku2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.sudoku2.model.Puzzle;
import com.sudoku2.service.PuzzleService;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
public class Controller {
	
	@Autowired
	PuzzleService puzzleService;
	
	@GetMapping("/api/sudoku/generate/{count}")
	public String generatePuzzles(@PathVariable int count) {
		return puzzleService.addPuzzles(count);
	}
	
	@PostMapping("/abc")
	@ResponseBody
	public String aa(@RequestParam(required = false) String a) {
		return a;
	}
	
	@GetMapping("/api/sudoku/{difficulty}")
	public Puzzle getPuzzle(@PathVariable char difficulty) {
		return puzzleService.getPuzzle(difficulty);
	}

}
