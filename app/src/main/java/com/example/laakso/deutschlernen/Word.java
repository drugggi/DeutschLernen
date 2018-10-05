package com.example.laakso.deutschlernen;

import android.util.Log;

import java.util.ArrayList;

public abstract class Word {
    // Erilaisia sanoja subs, verb etc
    // niistä voi pyytää sitten taas eri taivutuksia etc
    private ArrayList<String> german;
    private ArrayList<String> finnish;

    public Word(String data) {
        german = new ArrayList<>();
        finnish = new ArrayList<>();

        data = data.replaceAll("//s+","");
        // data = data.replaceAll(" ","");

        String[] parts;
        parts = data.split(";");

        String[] finnishParts;
        String[] germanParts;

        if (parts.length == 2) {

            Log.d("ger parts","'" + parts[0]+"'");
            Log.d("fin parts","'" + parts[1]+"'");

            germanParts = parts[0].split(",");
            finnishParts = parts[1].split(",");

            for (int i = 0 ; i < germanParts.length ; i++) {
                german.add(germanParts[i]);
            }
            for (int i = 0 ; i < finnishParts.length ; i++) {
                finnish.add(germanParts[i]);
            }

        }

        else {
            finnish.add("ERROR");
            german.add("ERROR");
        }


    }

}
