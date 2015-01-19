package com.jkenoyer.ktrack.commands;

/**
 * Created by jkenoyer on 1/18/15.
 */
public class ValidateLoginCommand {

    public ValidationResult validate(String email, String password) {
        ValidationResult result = new ValidationResult();

        if(email == null || email.equals("")) {
            result.addResult("Email Login cannot be blank.");
        }

        if(password == null || password.equals("")) {
            result.addResult("Password cannot be blank.");
        }

        return result;
    }
}
