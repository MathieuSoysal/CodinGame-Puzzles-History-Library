package io.github.mathieusoysal;

import com.mongodb.client.MongoClient;

import io.github.mathieusoysal.dao.DatedPuzzlesDao;

/**
 * This class is used to access the CodinGame's puzzles statistics history.
 */
public class CodinGameHistory extends DatedPuzzlesDao {

    /**
     * Creates a new instance of {@link CodinGameHistory}.
     * 
     * @param mongoClient  the {@link MongoClient} used to access the database.
     * @param databaseName the name of the database.
     */
    public CodinGameHistory(MongoClient mongoClient, String databaseName) {
        super(mongoClient, databaseName);
    }

}
