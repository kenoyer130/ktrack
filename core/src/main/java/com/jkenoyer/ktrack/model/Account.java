package com.jkenoyer.ktrack.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by jkenoyer on 1/11/15.
 */
public class Account implements Serializable {

    private String email;

    private String familyEmail;

    private String name;

    private String password;

    private String familyRole;

    private String family;

    private int amount;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFamilyEmail() {
        return familyEmail;
    }

    public void setFamilyEmail(String familyEmail) {
        this.familyEmail = familyEmail;
    }
}
