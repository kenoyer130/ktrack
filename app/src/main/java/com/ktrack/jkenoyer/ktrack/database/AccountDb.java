package com.ktrack.jkenoyer.ktrack.database;

import com.google.gson.Gson;
import com.ktrack.jkenoyer.ktrack.model.Account;
import com.ktrack.jkenoyer.ktrack.model.FamilyRole;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.util.JSON;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by jkenoyer on 1/11/15.
 */
public class AccountDb {



    private final DB db;

    public AccountDb() {
        this.db = new Db().get();
    }

    public Account Login(String userName, String password) {

        password = new Password().hash(password);

        DBCollection accounts = this.db.getCollection("Accounts");

        BasicDBObject query = new BasicDBObject("Account.name", userName).append("Account.password", password);

        DBCursor cursor = accounts.find(query);

        Account account = null;

        try {
            while (cursor.hasNext()) {
                System.out.println(cursor.next());
            }
        } finally {
            cursor.close();
        }

        return account;
    }

    public class CreateAccountResult {
        public boolean Success;
        public String error;
        public Account account;
    }

    public CreateAccountResult CreateAccount(String userName, String familyName, String password) {

        password = new Password().hash(password);

        //todo: might be able to do in one or query
        if(accountExists(userName)) {
            CreateAccountResult result = new CreateAccountResult();
            result.Success = false;
            result.error = "Account name already exists!";
            return result;
        }

        if(familyExists(familyName)) {
            CreateAccountResult result = new CreateAccountResult();
            result.Success = false;
            result.error = "Family name already exists!";
            return result;
        }

        Account account = new Account();
        account.setFamily(familyName);
        account.setPassword(password);
        account.setName(userName);
        account.setFamilyRole(FamilyRole.Parent);

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

    private boolean accountExists(String userName) {

        boolean accountExists = false;

        DBCollection accounts = this.db.getCollection("Accounts");

        BasicDBObject query = new BasicDBObject("Account.name", userName);

        DBCursor cursor = accounts.find(query);

        Account account = null;

        try {
            while (cursor.hasNext()) {
               accountExists = true;
            }
        } finally {
            cursor.close();
        }


        return accountExists;
    }

    private boolean familyExists(String familyName) {

        boolean familyExists = false;

        DBCollection accounts = this.db.getCollection("Accounts");

        BasicDBObject query = new BasicDBObject("Account.family", familyName);

        DBCursor cursor = accounts.find(query);

        Account account = null;

        try {
            while (cursor.hasNext()) {
                familyExists = true;
            }
        } finally {
            cursor.close();
        }


        return familyExists;
    }

}
