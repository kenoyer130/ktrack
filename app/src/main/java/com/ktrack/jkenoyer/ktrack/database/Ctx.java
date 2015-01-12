package com.ktrack.jkenoyer.ktrack.database;

import android.content.Context;
import android.content.res.Resources;

/**
 * Created by jkenoyer on 1/11/15.
 */
public class Ctx {
    private static Context ctx;

    public static void setContext(Context context) {
        ctx = context;
    }

    public static String getText(int id) {
        return ctx.getResources().getText(id).toString();
    }
}
