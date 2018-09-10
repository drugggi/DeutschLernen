package com.example.laakso.deutschlernen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MyStoredWordsActivity extends AppCompatActivity {

    ListView wordSubjectsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_stored_words);

        String[] learningSubjects = getResources().getStringArray(R.array.learning_subjects);

        wordSubjectsListView = (ListView) findViewById(R.id.wordSubjectsListView);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.learning_listview, R.id.textview , learningSubjects);
        wordSubjectsListView.setAdapter(adapter);
    }
}
