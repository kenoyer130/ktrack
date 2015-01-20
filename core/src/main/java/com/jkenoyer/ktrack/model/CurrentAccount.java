package com.jkenoyer.ktrack.model;

/**
 * Created by jkenoyer on 1/19/15.
 */
public class CurrentAccount {
    private static Account account;

    public static Account getAccount() {
        return  account;
    }

    public static void setAccount(Account a) {
        account = a;
    }
}
