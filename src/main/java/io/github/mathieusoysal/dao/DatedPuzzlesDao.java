
package io.github.mathieusoysal.dao;

import java.util.Date;
import java.util.List;

import com.github.mathieusoysal.codingame_stats.puzzle.Puzzle;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;

import org.bson.Document;

import io.github.mathieusoysal.model.DatedPuzzle;

public class DatedPuzzlesDao extends AbstractDao {

    public static final String PUZZLES_HISTORY_COLLECTION = "puzzles-history";

    private MongoCollection<Document> collection;

    public DatedPuzzlesDao(MongoClient mongoClient, String databaseName) {
        super(mongoClient, databaseName);
        collection = db.getCollection(PUZZLES_HISTORY_COLLECTION);
    }

    public List<DatedPuzzle> getPuzzlesOf(Date date) {
        return null;
    }

}