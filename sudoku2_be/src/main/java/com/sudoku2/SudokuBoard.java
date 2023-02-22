package com.sudoku2;

import org.springframework.beans.factory.annotation.Autowired;

import com.qqwing.PrintStyle;
import com.qqwing.QQWing;

public class SudokuBoard {

	
	public Sudoku generateBoard() {
        QQWing qqWing = new QQWing();
        // Generate a puzzle
        qqWing.generatePuzzle();
        // Convert the puzzle to a one-line string
        qqWing.setPrintStyle(PrintStyle.ONE_LINE);
        // Initialize the puzzle string representation of the puzzle
        qqWing.setRecordHistory(true);
        qqWing.solve();
        
        Sudoku sudoku = new Sudoku();
        
        
        sudoku.setBoard(qqWing.getPuzzleString());
        sudoku.setSolution(qqWing.getSolutionString());
        sudoku.setDifficulty(qqWing.getDifficultyAsString());
        
        return sudoku;
	}

}
