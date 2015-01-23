package com.jkenoyer.ktrack.commands;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.jkenoyer.ktrack.database.AccountReader;
import com.jkenoyer.ktrack.database.Callback;
import com.jkenoyer.ktrack.database.Db;
import com.jkenoyer.ktrack.database.Password;
import com.jkenoyer.ktrack.model.Account;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jkenoyer on 1/21/15.
 */
public class GetAccountFamilyCommand {

    public List<Account> get(String family) {

        BasicDBObject query = new BasicDBObject("Account.family", family);

        return new AccountReader().read(query);
    }
}
