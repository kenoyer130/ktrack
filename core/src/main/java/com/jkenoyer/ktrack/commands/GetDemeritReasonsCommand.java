package com.jkenoyer.ktrack.commands;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jkenoyer on 1/24/15.
 */
public class GetDemeritReasonsCommand {

    public List<String> getDemeritReasons() {
        ArrayList<String> list = new ArrayList<String>();

        list.add("Backtalk");
        list.add("Fighting");
        list.add("NonCompliance");
        list.add("Other");

        return list;
    }
}
