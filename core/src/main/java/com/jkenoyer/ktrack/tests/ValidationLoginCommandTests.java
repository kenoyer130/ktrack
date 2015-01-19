package com.jkenoyer.ktrack.tests;

import com.jkenoyer.ktrack.commands.ValidateLoginCommand;
import com.jkenoyer.ktrack.commands.ValidationResult;

import org.junit.Assert;
import org.junit.Test;


public class ValidationLoginCommandTests {

    @Test
    public void blankEmailLoginTest() {
        // assemble
        ValidateLoginCommand cmd = new ValidateLoginCommand();

        // act
        ValidationResult result = cmd.validate("", "password");

        // assert
       Assert.assertEquals(1, result.getResults().size());
       Assert.assertEquals("Email Login cannot be blank.", result.getResults().get(0));
    }
}
