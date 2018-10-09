package com.example.laakso.deutschlernen;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class VerbConjucateActivity extends AppCompatActivity {

    private TextView questionTextView;
    private EditText answerEditText;

    private Button newQuestionButton;

    private QuestionsVerbConjucate questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verb_conjucate);

        questionTextView = (TextView) findViewById(R.id.questionTextView);
        answerEditText = (EditText) findViewById(R.id.answerEditText);
        newQuestionButton = (Button) findViewById(R.id.newQuestionButton);

        Resources res = getResources();

        questions = new QuestionsVerbConjucate(res);

        newQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String question = questions.getNewQuestion();

                questionTextView.setText(question);

            }
        });

        answerEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                String questionAndAnswer = questionTextView.getText().toString();

                questionAndAnswer = questionAndAnswer + " = ";

                questionAndAnswer = questionAndAnswer + questions.getCorrectAnswer();

                questionTextView.setText(questionAndAnswer);

                return false;
            }
        });



    }
}
