package com.jkenoyer.ktrack.database;

/**
 * Created by jkenoyer on 1/21/15.
 */
public interface Callback<T> {
    public void onResult(T result);
}

