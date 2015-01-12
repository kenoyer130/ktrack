package com.ktrack.jkenoyer.ktrack.misc;

import android.content.Context;

/**
 * Created by jkenoyer on 1/11/15.
 */
public class UncaughtExceptionHandler implements Thread.UncaughtExceptionHandler {

    public UncaughtExceptionHandler(Context context) {

    }

    @Override
    public void uncaughtException(Thread thread, Throwable ex) {
        String msg = ex.getMessage();

       ex.printStackTrace();
    }
}
