package com.jkenoyer.ktrack.database;

import com.jkenoyer.ktrack.Properties;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by jkenoyer on 1/11/15.
 */
public class Password {


    public Password() {
    }

    public String hash(String password) {

        password = password + Properties.password_salt;

        MessageDigest sha = null;
        try {
            sha = MessageDigest.getInstance("SHA-1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        byte[] hash = sha.digest(password.getBytes());

        return hash.toString();
    }
}
