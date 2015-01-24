package com.jkenoyer.ktrack.commands;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jkenoyer on 1/24/15.
 */
public class GetWithdrawelReasonsCommand {

    public List<String> get() {
        ArrayList<String> list = new ArrayList<String>();

        list.add("Video Game");
        list.add("Fun times");
        list.add("Other");

        return list;
    }
}
