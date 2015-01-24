package com.jkenoyer.ktrack.commands;

import com.google.gson.Gson;
import com.jkenoyer.ktrack.database.Db;
import com.jkenoyer.ktrack.model.Account;
import com.jkenoyer.ktrack.model.CurrentAccount;
import com.jkenoyer.ktrack.model.Transaction;
import com.jkenoyer.ktrack.model.TransactionType;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.util.JSON;

import java.util.Date;
import java.util.List;

/**
 */
public class SaveTransactionCommand {

    private final DB db;

    public SaveTransactionCommand() {
        this.db = new Db().get();
    }

    public void save(Account account, TransactionType transactionType, String reason, int amount) {

        int accountAmount = account.getAmount();
        accountAmount = accountAmount + amount;
        account.setAmount(accountAmount);

        Transaction transaction = new Transaction();
        transaction.setAmount(amount);
        transaction.setDate(new Date());
        transaction.setDescription(reason);
        transaction.setEmailName(account.getEmail());
        transaction.setTransactionType(transactionType);

        saveTransaction(transaction);

        saveAccount(account);

        updateCurrentAccount(account);
    }

    private void saveTransaction(Transaction transaction) {
        Gson gson = new Gson();

        DBCollection transactions = this.db.getCollection("Transactions");
        transactions.insert(new BasicDBObject("Transaction", JSON.parse(gson.toJson(transaction))));
    }

    private void saveAccount(Account account) {
        BasicDBObject newDocument = new BasicDBObject();
        newDocument.append("$set", new BasicDBObject().append("Account.amount", account.getAmount()));

        BasicDBObject searchQuery = new BasicDBObject().append("Account.email", account.getEmail());

        DBCollection accounts = this.db.getCollection("Accounts");
        accounts.update(searchQuery, newDocument);
    }

    private void updateCurrentAccount(Account account) {
        List<Account> kids = CurrentAccount.getKids();

        for(Account kid : kids) {
            if(kid.getEmail().equals(account.getEmail())) {
                kid.setAmount(account.getAmount());
            }
        }
    }
}
