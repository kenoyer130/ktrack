package com.jkenoyer.ktrack.commands;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jkenoyer.ktrack.database.Db;
import com.jkenoyer.ktrack.database.Password;
import com.jkenoyer.ktrack.model.Account;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

public class AccountLoginCommand {

    private final DB db;

    public AccountLoginCommand() {
        this.db = new Db().get();
    }

    public Account Login(String emailLogin, String password) {

        DBCollection accounts = this.db.getCollection("Accounts");

        BasicDBObject query = new BasicDBObject("Account.email", emailLogin);

        DBCursor cursor = accounts.find(query);

        Account account = null;

        try {
            while (cursor.hasNext()) {
                Gson gson = new Gson();

                String json = cursor.next().toString();

                JsonObject obj = gson.fromJson(json, JsonObject.class);

                JsonElement element = obj.get("Account");

                Account bean = gson.fromJson(element.toString(), Account.class);

                String passwordCheck = new Password().hash(password);

                if(bean.getPassword().equals(passwordCheck)) {
                    account = bean;
                }
            }
        } finally {
            cursor.close();
        }

        return account;
    }
}
