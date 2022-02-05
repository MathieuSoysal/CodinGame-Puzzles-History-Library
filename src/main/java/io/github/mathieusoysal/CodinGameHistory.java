package io.github.mathieusoysal;

import com.mongodb.client.MongoClient;

import io.github.mathieusoysal.dao.DatedPuzzlesDao;

public class CodinGameHistory extends DatedPuzzlesDao {

    public CodinGameHistory(MongoClient mongoClient, String databaseName) {
        super(mongoClient, databaseName);
    }

}
