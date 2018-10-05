package com.example.laakso.deutschlernen;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class VerbConjucateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verb_conjucate);

        Resources res = getResources();

        String[] personalPronounResources = res.getStringArray(R.array.personal_pronoun);
        String[] verbConjucationResources = res.getStringArray(R.array.verb_conjucation_presens);


        ArrayList<PersonalPronoun> personalPronouns = new ArrayList<>();
        PersonalPronoun tempWord;
        for (int i = 0 ; i < personalPronounResources.length ; i++) {
            tempWord = new PersonalPronoun(personalPronounResources[i]);
            personalPronouns.add(tempWord );
        }

        for (int i = 0 ; i < verbConjucationResources.length ; i++) {
            tempWord = new PersonalPronoun(verbConjucationResources[i]);
            personalPronouns.add(tempWord );
        }

    }
}
