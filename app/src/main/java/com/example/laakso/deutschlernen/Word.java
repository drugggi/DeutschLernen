package com.example.laakso.deutschlernen;

import android.util.Log;

import java.util.ArrayList;

public abstract class Word {
    // Erilaisia sanoja subs, verb etc
    // niistä voi pyytää sitten taas eri taivutuksia etc
    protected ArrayList<String> german;
    protected ArrayList<String> finnish;

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
                finnish.add(finnishParts[i]);
            }

        }

        else {
            finnish.add("ERROR");
            german.add("ERROR");
        }


    }
    public String getFinnishWord() {
        return finnish.get(0);
    }

    public String getFinnishWords() {

        StringBuilder words = new StringBuilder();

        for (int i = 0 ; i < finnish.size() - 1 ; i++) {
            words.append(finnish.get(i) ).append(", ");
        }
        words.append(finnish.get(finnish.size() -1 ));
        return words.toString();
    }

    public String getGermanWord(int index) {
        if (index < 0 || index >= german.size() ) {
            return "ERROR INDEX SIZE";
        }
        else {
            return german.get(index);
        }
    }


}
