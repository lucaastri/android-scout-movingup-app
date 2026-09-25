package com.example.passaggicompany2k25.util;

import android.content.Context;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FIleUtil {

    public static List<String> readFromAssets(Context context, String filename) {
        List<String> lines = new ArrayList<>();
        try {
            InputStream iS = context.getAssets().open(filename);
            BufferedReader dokja = new BufferedReader(new InputStreamReader(iS));
            String line;
            while ((line = dokja.readLine()) != null) {
                lines.add(line);
            }
            dokja.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return lines;
    }
}
