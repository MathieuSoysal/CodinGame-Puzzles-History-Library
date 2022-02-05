
package io.github.mathieusoysal.dao;

import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;

import org.bson.types.ObjectId;

abstract class AbstractDao {

    /**
     * The database name.
     */
    protected final String CODINGAME_DATABASE;
    /**
     * The {@link MongoClient} used to access the database.
     */
    protected MongoClient mongoClient;
    /**
     * The {@link MongoDatabase} used to interact with the database.
     */
    protected MongoDatabase db;

    /**
     * Creates a new instance of {@link AbstractDao}.
     * 
     * @param mongoClient  the {@link MongoClient} used to access the database.
     * @param databaseName the name of the database.
     */
    protected AbstractDao(MongoClient mongoClient, String databaseName) {
        this.mongoClient = mongoClient;
        CODINGAME_DATABASE = databaseName;
        this.db = this.mongoClient.getDatabase(CODINGAME_DATABASE);
    }

    /**
     * Default generation of the {@link ObjectId} used to identify the document.
     * 
     * @return the {@link ObjectId} used to identify the document.
     */
    public ObjectId generateObjectId() {
        return new ObjectId();
    }
}