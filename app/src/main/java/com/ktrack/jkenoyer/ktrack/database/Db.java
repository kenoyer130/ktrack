package com.ktrack.jkenoyer.ktrack.database;

import com.ktrack.jkenoyer.ktrack.R;
import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.Properties;

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

        MongoCredential credential = MongoCredential.createMongoCRCredential(Ctx.getText(R.string.dbUser), "ktracker", Ctx.getText(R.string.dbPassword).toCharArray());
        MongoClient mongoClient = null;
        try {
            mongoClient = new MongoClient(new ServerAddress(Ctx.getText(R.string.dbServer), Integer.parseInt(Ctx.getText(R.string.dbPort))), Arrays.asList(credential));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        db = mongoClient.getDB("ktracker");
    }

}
