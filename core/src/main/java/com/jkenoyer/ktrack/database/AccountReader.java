package com.jkenoyer.ktrack.database;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jkenoyer.ktrack.model.Account;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jkenoyer on 1/21/15.
 */
public class AccountReader {
    private final DB db;

    public AccountReader() {
        this.db = new Db().get();
    }

    public List<Account> read(BasicDBObject query) {

        List<Account> results = new ArrayList<Account>();

        DBCollection collection = this.db.getCollection("Accounts");

        DBCursor cursor = collection.find(query);

        try {
            while (cursor.hasNext()) {
                Gson gson = new Gson();

                String json = cursor.next().toString();

                JsonObject obj = gson.fromJson(json, JsonObject.class);

                JsonElement element = obj.get("Account");

                Account bean = gson.fromJson(element.toString(), Account.class);

                results.add(bean);
            }
        } finally {
            cursor.close();
        }

        return results;
    }
}
