package com.example.laakso.deutschlernen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ListView learningSubjectsListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String[] learningSubjects = getResources().getStringArray(R.array.learning_subjects);

        learningSubjectsListView = (ListView) findViewById(R.id.learningSubjectsListView);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.learning_listview, R.id.textview , learningSubjects);
        learningSubjectsListView.setAdapter(adapter);


        learningSubjectsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(MainActivity.this,"jes",Toast.LENGTH_SHORT).show();

                Intent myWordsIntent = new Intent(view.getContext(), MyStoredWordsActivity.class);
                startActivity(myWordsIntent);
            }
        });



    }
}
