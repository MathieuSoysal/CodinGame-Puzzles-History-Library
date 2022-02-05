
package io.github.mathieusoysal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.util.Arrays;
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
                                                new Document("date", "2020-01-01T00:00:00.000+00:00")
                                                                .append("puzzle", new Document("id", "1")),
                                                new Document("date", "2020-02-01T00:00:00.000+00:00")
                                                                .append("puzzle", new Document("id", "2")),
                                                new Document("date", "2020-03-01T00:00:00.000+00:00")
                                                                .append("puzzle", new Document("id", "3"))));

                List<DatedPuzzle> puzzles = puzzleDao.getPuzzlesOf(LocalDate.of(2020, 1, 1));

                assertEquals(1, puzzles.size());
                assertEquals(1, puzzles.get(0).getPuzzle().getId());
        }

        @Test
        void testGetPuzzlesBetweenTwoDates() {
                mongoClient.getDatabase("CodinGame-stats")
                                .getCollection(DatedPuzzlesDao.PUZZLES_HISTORY_COLLECTION)
                                .insertMany(Arrays.asList(
                                                new Document("date", "2020-01-01T00:00:00.000+00:00")
                                                                .append("puzzle", new Document("id", "1")),
                                                new Document("date", "2020-02-01T00:00:00.000+00:00")
                                                                .append("puzzle", new Document("id", "2")),
                                                new Document("date", "2020-03-01T00:00:00.000+00:00")
                                                                .append("puzzle", new Document("id", "3"))));

                List<DatedPuzzle> puzzles = puzzleDao.getPuzzlesBetweenTwoDate(LocalDate.of(2020, 1, 1),
                                LocalDate.of(2020, 2, 1));

                assertEquals(2, puzzles.size());
                assertEquals(1, puzzles.get(0).getPuzzle().getId());
                assertEquals(2, puzzles.get(1).getPuzzle().getId());
        }

}