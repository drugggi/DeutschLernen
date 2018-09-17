package com.example.laakso.deutschlernen;

import android.content.Intent;
import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ListView;

import java.util.ArrayList;

public class MyStoredWordsActivity extends AppCompatActivity {

    private ListView wordSubjectsListView;
    private Button startButton;
    private CheckBox hardModeCheckBox;
    private StoredWordsAdapter subjectsAdapter;
    private ArrayList<String[]> wordSubjects;
    private boolean[] selectedItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_stored_words);

       /*
        wordSubjects.add(getResources().getStringArray(R.array.tag1) );
        wordSubjects.add(getResources().getStringArray(R.array.tag2) );
        wordSubjects.add(getResources().getStringArray(R.array.tag3) );
        wordSubjects.add(getResources().getStringArray(R.array.tag4) );
*/
        String[] test;

        String[] learningSubjects = getResources().getStringArray(R.array.my_stored_subjects);
        // wordSubjects = getResources().getStringArray
        startButton = (Button) findViewById(R.id.startButton);
        hardModeCheckBox = (CheckBox) findViewById(R.id.hardModeCheckBox);

        wordSubjectsListView = (ListView) findViewById(R.id.wordSubjectsListView);
        subjectsAdapter = new StoredWordsAdapter(this, learningSubjects);
        wordSubjectsListView.setAdapter(subjectsAdapter);

        selectedItems = new boolean[subjectsAdapter.getCount() ];
        Log.d("boolean","array size: " + selectedItems.length ) ;

        /*
        wordSubjectsListView = (ListView) findViewById(R.id.wordSubjectsListView);
        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.learning_listview, R.id.textview , learningSubjects);
        wordSubjectsListView.setAdapter(adapter);
*/

        wordSubjectsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                subjectsAdapter.itemClicked(i);
                subjectsAdapter.notifyDataSetChanged();
                if (selectedItems[i]) {
                    selectedItems[i] = false;
                }
                else {
                    selectedItems[i] = true;
                }
            }
        });

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (hardModeCheckBox.isChecked() ) {



                }
                else {


                    Intent myWordsQuestionsIntent = new Intent(view.getContext(), MyStoredWordsQuestionsEasyActivity.class);

                    myWordsQuestionsIntent.putExtra("com.finn.laakso.deutschlernen.SELECTEDSUBJECTS",selectedItems);

                    startActivity(myWordsQuestionsIntent);
                }
            }
        });
    }




}
