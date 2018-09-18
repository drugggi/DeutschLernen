package com.example.laakso.deutschlernen;

import android.content.res.TypedArray;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MyStoredWordsQuestionsHardActivity extends AppCompatActivity {

    private TextView questionTextView;
    private EditText answerEditText;
    private TextView correctAnswerTextView;

    private Button newQuestionButton;
    private int QandAindex;
    private ArrayList<ArrayList<String>> allQuestionWords;
    private int rightAnswerButtonIndex;
    boolean[] includes;

    Random rng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stored_words_hard_questions);

        questionTextView = (TextView) findViewById(R.id.questionTextView);
        answerEditText = (EditText) findViewById(R.id.answerEditText);
        newQuestionButton = (Button) findViewById(R.id.newQuestionButton);
        // correctAnswerTextView = (TextView) findViewById()

        rng = new Random();

        allQuestionWords = new ArrayList<>();
        ArrayList<String> tempQuestionWords;

        final String questionWord;
        final String answerWord;
        String parts[];

        Bundle extras = getIntent().getExtras();
        if(extras !=null)
        {
            includes = extras.getBooleanArray("com.finn.laakso.deutschlernen.SELECTEDSUBJECTS");
        }

        ArrayList<String> tempQuestionLines = new ArrayList<>();
        for (TypedArray item : ResourceHelper.getMultiTypedArray(MyStoredWordsQuestionsHardActivity.this, "tag", includes)) {

            for ( int j = 0 ; j < item.length() ; j++ ) {

                tempQuestionLines.add(item.getString(j) );
            }

        }

        for ( int i = 0 ; i < tempQuestionLines.size() ; i++ ) {
            tempQuestionWords = new ArrayList<>();

            // tempQuestionLines.set(i) = questions[i].replaceAll("//s+","");
            tempQuestionLines.set(i, tempQuestionLines.get(i).replaceAll("//s+",""));

            Log.d("tempquestlines",tempQuestionLines.get(i) );

            //parts = questions[i].split(";");
            parts = tempQuestionLines.get(i).split(";");
            if (parts.length == 2) {
                parts[0] = parts[0].substring(0,(parts[0].length() - 1) );
                tempQuestionWords.add(parts[0]);
                Log.d("part[0]",parts[0]);
            }
            else {
                Log.e("ERROR","parts not 2");
                continue;
            }

            parts = parts[1].split(",");

            for ( int j = 0 ; j < parts.length ; j++ ) {
                tempQuestionWords.add(parts[j]);
            }

            allQuestionWords.add(tempQuestionWords);
        }


        newQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                QandAindex = rng.nextInt(allQuestionWords.size() );

                StringBuilder questionBuilder = new StringBuilder();

                for (int i = 1 ; i < allQuestionWords.get(QandAindex).size() ; i++) {
                    questionBuilder.append(allQuestionWords.get(QandAindex).get(i) );
                    questionBuilder.append(",");
                }

                questionTextView.setText(questionBuilder.toString() );

            }
        });

        answerEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                String answer = answerEditText.getText().toString();
                String correctAnswer = allQuestionWords.get(QandAindex).get(0);

                Log.d("ansewr","'" + answer + "'");
                Log.d("correct answer","'" + correctAnswer + "'");

                if (answer.equals(correctAnswer )) {

                    Toast.makeText(MyStoredWordsQuestionsHardActivity.this,"Think that was correct",Toast.LENGTH_SHORT).show();

                } else {

                    Toast.makeText(MyStoredWordsQuestionsHardActivity.this, "was not so correct", Toast.LENGTH_SHORT).show();
                }

                StringBuilder correctAnswerBuilder = new StringBuilder(correctAnswer);

                correctAnswerBuilder.append(" = ");
                for (int j = 1 ; j < allQuestionWords.get(QandAindex).size() ; j++) {
                    correctAnswerBuilder.append(allQuestionWords.get(QandAindex).get(j) );
                    correctAnswerBuilder.append(",");
                }

                questionTextView.setText(correctAnswerBuilder.toString() );
                return false;
            }
        });


    }
}
