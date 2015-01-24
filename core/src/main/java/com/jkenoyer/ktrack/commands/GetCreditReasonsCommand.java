package com.jkenoyer.ktrack.commands;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jkenoyer on 1/24/15.
 */
public class GetCreditReasonsCommand {

    public List<String> getCreditReasons() {
        ArrayList<String> list = new ArrayList<String>();

        list.add("Chore");
        list.add("No Complaining");
        list.add("Homework");
        list.add("Other");

        return list;
    }
}
