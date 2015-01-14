package com.ktrack.jkenoyer.ktrack.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by jkenoyer on 1/11/15.
 */
public class Account implements Serializable {

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getFamilyRole() {
        return familyRole;
    }

    public void setFamilyRole(String familyRole) {
        this.familyRole = familyRole;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    private String name;

    private String password;

    private String familyRole;

    private String family;

    private int amount;

}
