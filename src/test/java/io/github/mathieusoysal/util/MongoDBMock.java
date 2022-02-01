package io.github.mathieusoysal.util;

import java.io.IOException;
import java.net.UnknownHostException;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.WriteConcern;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;

import de.flapdoodle.embed.mongo.MongodExecutable;
import de.flapdoodle.embed.mongo.MongodProcess;
import de.flapdoodle.embed.mongo.MongodStarter;
import de.flapdoodle.embed.mongo.config.ImmutableMongodConfig;
import de.flapdoodle.embed.mongo.config.MongodConfig;
import de.flapdoodle.embed.mongo.config.Net;
import de.flapdoodle.embed.mongo.distribution.Version;
import de.flapdoodle.embed.process.runtime.Network;

public abstract class MongoDBMock {
    private static final MongodStarter starter = MongodStarter.getDefaultInstance();

    private MongodExecutable mongodExe;
    private MongodProcess mongod;
    protected MongoClient mongoClient;
    private int port;

    protected void setUp() throws Exception {
        port = Network.getFreeServerPort();
        mongodExe = starter.prepare(createMongodConfig());
        mongod = mongodExe.start();
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString("mongodb://localhost:" + port + "/"))
                .retryWrites(true)
                .writeConcern(WriteConcern.MAJORITY)
                .build();
        mongoClient = MongoClients.create(settings);
    }

    protected int port() {
        return port;
    }

    private MongodConfig createMongodConfig() throws UnknownHostException, IOException {
        return createMongodConfigBuilder().build();
    }

    private ImmutableMongodConfig.Builder createMongodConfigBuilder() throws UnknownHostException, IOException {
        return MongodConfig.builder()
                .version(Version.Main.PRODUCTION)
                .net(new Net(port, Network.localhostIsIPv6()));
    }

    protected void tearDown() throws Exception {
        mongod.stop();
        mongodExe.stop();
    }

    protected MongoClient getMongo() {
        return mongoClient;
    }
}