package io.github.mathieusoysal.model;

import java.util.Date;

import com.github.mathieusoysal.codingame_stats.puzzle.Puzzle;

public class DatedPuzzle {
    private Date date;
    private Puzzle puzzle;

    public Date getDate() {
        return date;
    }

    public Puzzle getPuzzle() {
        return puzzle;
    }
}
