package com.jkenoyer.ktrack.database;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jkenoyer.ktrack.model.Account;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

/**
 * Created by jkenoyer on 1/21/15.
 */
public class AccountReader {
    private final DB db;

    public interface ReadCallback {
        public void onRead(Account account);
    }

    public AccountReader() {
        this.db = new Db().get();
    }

    public void read(BasicDBObject query, ReadCallback callback) {

        DBCollection accounts = this.db.getCollection("Accounts");

        DBCursor cursor = accounts.find(query);

        try {
            while (cursor.hasNext()) {
                Gson gson = new Gson();

                String json = cursor.next().toString();

                JsonObject obj = gson.fromJson(json, JsonObject.class);

                JsonElement element = obj.get("Account");

                Account bean = gson.fromJson(element.toString(), Account.class);

                callback.onRead(bean);
            }
        } finally {
            cursor.close();
        }
    }
}
