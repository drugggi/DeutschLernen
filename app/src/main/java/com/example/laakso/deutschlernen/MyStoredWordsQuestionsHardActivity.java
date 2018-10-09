package com.example.laakso.deutschlernen;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

    private QuestionsHard questions;

    Random rng;

    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putInt("totalCorrectScore",questions.getTotalCorrect() );
        outState.putIntArray("totalCorrectArray",questions.getCorrectAmount() );


        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stored_words_hard_questions);

        questionTextView = (TextView) findViewById(R.id.questionTextView);
        correctAnswerTextView = (TextView) findViewById(R.id.correctAnswersTextView);
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

        questions = new QuestionsHard();
        questions.addQuestions(MyStoredWordsQuestionsHardActivity.this, includes);

        if (savedInstanceState != null ) {
            int score = savedInstanceState.getInt("totalCorrectScore");
            int[] correctAmount = savedInstanceState.getIntArray("totalCorrectArray");

            questions.setTotalCorrect(score);
            questions.setCorrectAmount(correctAmount);
        }

        answerEditText.setText("");

        String text = questions.newQuestion();
        questionTextView.setText( text );
        correctAnswerTextView.setText(questions.getTotalCorrectAmount() );


        newQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                answerEditText.setText("");

                String text = questions.newQuestion();
                questionTextView.setText( text );

            }
        });

        answerEditText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                String answer = answerEditText.getText().toString();

                int correctAmount = questions.checkForRightAnswer(answer);


                if (correctAmount == 100) {
                    Toast.makeText(MyStoredWordsQuestionsHardActivity.this, correctAmount + "% correct!", Toast.LENGTH_SHORT).show();
                }
                else if (correctAmount >= 50) {
                    Toast.makeText(MyStoredWordsQuestionsHardActivity.this, "about " + correctAmount + "% correct", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MyStoredWordsQuestionsHardActivity.this, "only " + correctAmount + "% correct", Toast.LENGTH_SHORT).show();
                }

                questions.getCorrectAmount();

                String answerText = questions.getCorrectAnswer();
                questionTextView.setText(answerText);

                correctAnswerTextView.setText(questions.getTotalCorrectAmount() );
  /*              String correctAnswer = allQuestionWords.get(QandAindex).get(0);

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

                questionTextView.setText(correctAnswerBuilder.toString() );*/
                return true;
            }
        });


    }
}
