package com.jkenoyer.ktrack.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jkenoyer on 1/19/15.
 */
public class CurrentAccount {
    private static Account account;
    private static List<Account> familyMembers = new ArrayList<Account>();

    public static Account getAccount() {
        return  account;
    }

    public static void setAccount(Account a) {
        account = a;
    }

    public static void setFamilyMembers(List<Account> fm) {
        familyMembers = fm;
    }

    public static List<Account> getFamilyMembers() {
        return familyMembers;
    }

    public static List<Account> getKids() {

        List<Account> kids = new ArrayList<Account>();

        for(Account account : familyMembers) {
            if(account.getFamilyRole().equals(FamilyRole.Child.toString())) {
                kids.add(account);
            }
        }

        return kids;
    }
}
