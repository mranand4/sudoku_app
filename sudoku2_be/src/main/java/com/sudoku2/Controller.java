package com.sudoku2;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	
	@GetMapping("/{count}")
	public List<Sudoku> getBoard(@PathVariable int count) {
		List<Sudoku> list = new ArrayList<>();
		for(int i=0; i<count; i++) {
			list.add(new SudokuBoard().generateBoard());
		}
		return list;
	}

}
