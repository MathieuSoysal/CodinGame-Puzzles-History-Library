
package io.github.mathieusoysal.dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

import org.bson.types.ObjectId;

abstract class AbstractDao {

    protected final String CODINGAME_DATABASE;
    protected MongoDatabase db;
    protected MongoClient mongoClient;

    protected AbstractDao(MongoClient mongoClient, String databaseName) {
        this.mongoClient = mongoClient;
        CODINGAME_DATABASE = databaseName;
        this.db = this.mongoClient.getDatabase(CODINGAME_DATABASE);
    }

    public ObjectId generateObjectId() {
        return new ObjectId();
    }
}