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

            String salt = Properties.password_salt;

            String generatedPassword = null;
            try {
                // Create MessageDigest instance for MD5
                MessageDigest md = MessageDigest.getInstance("MD5");
                //Add password bytes to digest
                md.update(salt.getBytes());
                //Get the hash's bytes
                byte[] bytes = md.digest(password.getBytes());
                //This bytes[] has bytes in decimal format;
                //Convert it to hexadecimal format
                StringBuilder sb = new StringBuilder();
                for(int i=0; i< bytes.length ;i++)
                {
                    sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
                }
                //Get complete hashed password in hex format
                generatedPassword = sb.toString();
            }
            catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }
            return generatedPassword;
        }

}
