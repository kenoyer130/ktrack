package com.jkenoyer.ktrack.misc;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;

/**
 * Created by jkenoyer on 1/22/15.
 */
public class CreditialStore {


    private final Context context;

    public CreditialStore(Context context) {
        this.context = context;
    }

    public boolean Exists() {
        File file = context.getFileStreamPath("data.props");
        if(file == null || !file.exists()) {
            return false;
        }

        return true;
    }

    public String read(FileInputStream fis) {

        try {

            BufferedReader reader = new BufferedReader(new InputStreamReader(fis));

            String line = null;
            String input="";

            while ((line = reader.readLine()) != null) {
                input += line;
            }

            reader.close();

            fis.close();

            return input;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public void write(FileOutputStream fos,String data) {
        try {
            fos.write(data.getBytes());
            fos.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
