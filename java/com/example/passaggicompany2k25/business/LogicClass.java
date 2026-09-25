package com.example.passaggicompany2k25.business;

import android.content.Context;
import com.example.passaggicompany2k25.bean.Letter;
import com.example.passaggicompany2k25.commons.Comms;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class LogicClass {

    private Context context;

    public LogicClass(Context context) {
        this.context = context;
    }

    public List<Letter> load() {
        List<Letter> data = loadFromAssets();
        return data;
    }

    public List<Letter> loadFromAssets() {
        List<Letter> list = new ArrayList<>();
        List<String> lines = new ArrayList<>();
        for (String lettername : Comms.LETTERNAMES) {
            List<String> temp = readFromAssets(context,lettername);
            lines.addAll(temp);
        }
        for (String line : lines) {
            list.add(new Letter(line));
        }
        return list;
    }

    public List<String> readFromAssets(Context context, String filename) {
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
