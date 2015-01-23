package com.jkenoyer.ktrack.tests;

import com.jkenoyer.ktrack.commands.GetAccountFamilyCommand;
import com.jkenoyer.ktrack.database.Callback;
import com.jkenoyer.ktrack.model.Account;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * Created by jkenoyer on 1/22/15.
 */
public class GetAccountFamilyCommandTests {

    @Test
    public void GetAccountFamilyResultsTest() throws InterruptedException {
        // assemble
        GetAccountFamilyCommand cmd = new GetAccountFamilyCommand();

        // act
        List<Account> results = cmd.get("test");

        Assert.assertEquals(3, results.size());
    }
}
