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

    public void get(String family, Callback<List<Account>> callback) {

        final List<Account> familyMembers = new ArrayList<Account>();

        BasicDBObject query = new BasicDBObject("Account.family", family);

        AccountReader reader = new AccountReader();

        reader.read(query, new AccountReader.ReadCallback() {

            @Override
            public void onRead(Account account) {
                familyMembers.add(account);
            }
        });

        callback.onResult(familyMembers);
    }

}
