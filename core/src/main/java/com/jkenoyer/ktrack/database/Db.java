package com.jkenoyer.ktrack.database;

import com.jkenoyer.ktrack.Properties;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import java.net.UnknownHostException;
import java.util.Arrays;

/**
 * Created by jkenoyer on 1/11/15.
 */
public class Db {

    private static DB db;

    public DB get() {

        if(db == null) {
            initDb();
        }

        return db;
    }

    private void initDb() {

        MongoCredential credential = MongoCredential
                .createMongoCRCredential(Properties.dbUser,
                        "ktracker",
                         Properties.dbPassword.toCharArray());

        MongoClient mongoClient = null;
        try {
            mongoClient = new MongoClient(new ServerAddress(Properties.dbServer, Properties.dbPort),
                    Arrays.asList(credential));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        db = mongoClient.getDB("ktracker");
    }
}
