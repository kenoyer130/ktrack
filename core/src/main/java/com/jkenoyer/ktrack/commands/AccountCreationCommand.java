package com.jkenoyer.ktrack.commands;

import com.google.gson.Gson;
import com.jkenoyer.ktrack.database.Db;
import com.jkenoyer.ktrack.database.Password;
import com.jkenoyer.ktrack.model.Account;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.util.JSON;

public class AccountCreationCommand {

    private final DB db;

    public AccountCreationCommand() {
        this.db = new Db().get();
    }

    public CreateAccountResult Create(Account account) {

        account.setPassword(new Password().hash(account.getPassword()));

        if(accountExists(account.getEmail())) {
            CreateAccountResult result = new CreateAccountResult();
            result.Success = false;
            result.error = "Account name already exists!";
            return result;
        }

        saveAccount(account);

        CreateAccountResult result = new CreateAccountResult();
        result.Success = true;
        result.account = account;

        return result;
    }

    private void saveAccount(Account account) {
        Gson gson = new Gson();

        DBCollection accounts = this.db.getCollection("Accounts");

        accounts.insert(new BasicDBObject("Account", JSON.parse(gson.toJson(account))));
    }

    private boolean accountExists(String email) {

        boolean accountExists = false;

        DBCollection accounts = this.db.getCollection("Accounts");

        BasicDBObject query = new BasicDBObject("Account.email", email);

        DBCursor cursor = accounts.find(query);

        try {
            while (cursor.hasNext()) {
                accountExists = true;
            }
        } finally {
            cursor.close();
        }


        return accountExists;
    }
}
