package io.github.mathieusoysal.model;

import java.util.Date;

import com.github.mathieusoysal.codingame_stats.puzzle.Puzzle;

/**
 * This class represents statistics about a puzzle on a specific date.
 * 
 * @author Mathieu Soysal
 */
public class DatedPuzzle {
    private Date date;
    private Puzzle puzzle;

    /**
     * 
     * @return the date of the puzzle statistics.
     */
    public Date getDate() {
        return date;
    }

    /**
     * @return the puzzle statistics.
     */
    public Puzzle getPuzzle() {
        return puzzle;
    }
}
