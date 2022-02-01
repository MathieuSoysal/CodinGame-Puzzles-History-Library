
package io.github.mathieusoysal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import org.bson.Document;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.github.mathieusoysal.dao.DatedPuzzlesDao;
import io.github.mathieusoysal.model.DatedPuzzle;
import io.github.mathieusoysal.util.MongoDBMock;

class DatedPuzzleDaoTest extends MongoDBMock {

    private DatedPuzzlesDao puzzleDao;

    @BeforeEach
    public void setUp() throws Exception {
        super.setUp();
        puzzleDao = new DatedPuzzlesDao(mongoClient, "CodinGame-stats");
    }

    @AfterEach
    public void tearDown() throws Exception {
        mongoClient.getDatabase("CodinGame-stats")
                .getCollection(DatedPuzzlesDao.PUZZLES_HISTORY_COLLECTION)
                .deleteMany(new Document());
        super.tearDown();
    }

    @Test
    void testGetPuzzlesOfDate() {
        mongoClient.getDatabase("CodinGame-stats")
                .getCollection(DatedPuzzlesDao.PUZZLES_HISTORY_COLLECTION)
                .insertMany(Arrays.asList(
                        new Document("date", new Date(new GregorianCalendar(2020 + 1900, 1, 1).getTimeInMillis()))
                                .append("puzzle", new Document("id", "1")),
                        new Document("date", new Date(new GregorianCalendar(2020 + 1900, 2, 1).getTimeInMillis()))
                                .append("puzzle", new Document("id", "2")),
                        new Document("date", new Date(new GregorianCalendar(2020 + 1900, 3, 3).getTimeInMillis()))
                                .append("puzzle", new Document("id", "3"))));

        List<DatedPuzzle> puzzles = puzzleDao.getPuzzlesOf(new GregorianCalendar(2020 + 1900, 1, 1).getTime());

        assertEquals(1, puzzles.size());
        assertEquals(1, puzzles.get(0).getPuzzle().getId());
    }

}