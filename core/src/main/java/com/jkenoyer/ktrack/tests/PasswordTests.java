package com.jkenoyer.ktrack.tests;

import com.jkenoyer.ktrack.database.Password;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by jkenoyer on 1/19/15.
 */
public class PasswordTests {

    @Test
    public void passwordHashesTest() {
        // assemble
        Password password = new Password();

        // act
        String hash = password.hash("test");
        String confirmHash = password.hash("test");

        // assert
        Assert.assertEquals(hash, confirmHash);
    }
}
