package com.example.laakso.deutschlernen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

public class MyStoredWordsActivity extends AppCompatActivity {

    ListView wordSubjectsListView;
    Button startButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_stored_words);

        String[] learningSubjects = getResources().getStringArray(R.array.my_stored_subjects);
        startButton = (Button) findViewById(R.id.startButton);

        wordSubjectsListView = (ListView) findViewById(R.id.wordSubjectsListView);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.learning_listview, R.id.textview , learningSubjects);
        wordSubjectsListView.setAdapter(adapter);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MyStoredWordsActivity.this,"started",Toast.LENGTH_SHORT).show();

                Intent myWordsQuestionsIntent = new Intent(view.getContext(), MyStoredWordsQuestionsActivity.class);
                startActivity(myWordsQuestionsIntent);
            }
        });
    }
}
