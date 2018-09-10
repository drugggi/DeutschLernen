package com.example.laakso.deutschlernen;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MyStoredWordsQuestionsActivity extends AppCompatActivity {

    private Button answer1Button;
    private Button answer2Button;
    private Button answer3Button;
    private Button answer4Button;
    private Button newQuestionButton;
    private TextView questionTextView;
    private int QandAindex;
    private ArrayList<ArrayList<String>> allQuestionWords;
    private int rightAnswerButtonIndex;

    Random rng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_stored_words_questions);

        String[] questions = getResources().getStringArray(R.array.test_questions);
        rng = new Random();

        allQuestionWords = new ArrayList<>();
        ArrayList<String> tempQuestionWords = new ArrayList<>();

        String questionWord;
        final String answerWord;
        String parts[];

        for ( int i = 0 ; i < questions.length ; i++ ) {
            tempQuestionWords = new ArrayList<>();

            questions[i] = questions[i].replaceAll("//s+","");

            parts = questions[i].split(";");
            if (parts.length == 2) {
                tempQuestionWords.add(parts[0]);
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

        /*
        Toast.makeText(MyStoredWordsQuestionsActivity.this,"size: " + allQuestionWords.size(),Toast.LENGTH_SHORT).show();

        for (int i = 0 ; i < allQuestionWords.size() ; i++ ) {
            Log.d("sana Saksa",allQuestionWords.get(i).get(0));
            for (int j = 1; j < allQuestionWords.get(i).size() ; j++) {
                Log.d("sana Suomi","   " + allQuestionWords.get(i).get(j));
            }
        }
*/

        answer1Button = (Button) findViewById(R.id.answer1Button);
        answer2Button = (Button) findViewById(R.id.answer2Button);
        answer3Button = (Button) findViewById(R.id.answer3Button);
        answer4Button = (Button) findViewById(R.id.answer4Button);
        newQuestionButton = (Button) findViewById(R.id.newQuestionButton);

        questionTextView = (TextView) findViewById(R.id.questionTextView);

        newQuestionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                answer1Button.setTextColor(Color.BLACK);
                answer2Button.setTextColor(Color.BLACK);
                answer3Button.setTextColor(Color.BLACK);
                answer4Button.setTextColor(Color.BLACK);

                QandAindex = rng.nextInt(allQuestionWords.size() );
                rightAnswerButtonIndex = rng.nextInt(4)+1;
                String randomWord;

                questionTextView.setText(allQuestionWords.get(QandAindex).get(0));

                if (rightAnswerButtonIndex == 1) {
                    int ansewrIndex = rng.nextInt(allQuestionWords.get(QandAindex).size() - 1) + 1;
                    String rightAnswer = allQuestionWords.get(QandAindex).get(ansewrIndex);
                    answer1Button.setText(rightAnswer);
                }
                else {
                    answer1Button.setText(getRandomWord() );
                }


                if (rightAnswerButtonIndex == 2) {
                    int ansewrIndex = rng.nextInt(allQuestionWords.get(QandAindex).size() - 1) + 1;
                    String rightAnswer = allQuestionWords.get(QandAindex).get(ansewrIndex);
                    answer2Button.setText(rightAnswer);
                }
                else {
                    answer2Button.setText(getRandomWord() );
                }

                if (rightAnswerButtonIndex == 3) {
                    int ansewrIndex = rng.nextInt(allQuestionWords.get(QandAindex).size() - 1) + 1;
                    String rightAnswer = allQuestionWords.get(QandAindex).get(ansewrIndex);
                    answer3Button.setText(rightAnswer);
                }
                else {
                    answer3Button.setText(getRandomWord() );
                }

                if (rightAnswerButtonIndex == 4) {
                    int ansewrIndex = rng.nextInt(allQuestionWords.get(QandAindex).size() - 1) + 1;
                    String rightAnswer = allQuestionWords.get(QandAindex).get(ansewrIndex);
                    answer4Button.setText(rightAnswer);
                }
                else {
                    answer4Button.setText(getRandomWord() );
                }
            }
        });

        answer1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rightAnswerButtonIndex == 1) {
                    //Toast.makeText(MyStoredWordsQuestionsActivity.this,"That's right!",Toast.LENGTH_SHORT).show();
                    answer1Button.setTextColor(Color.YELLOW);
                    answer2Button.setText("");
                    answer3Button.setText("");
                    answer4Button.setText("");
                    printRightAnswer();
                }
                else {
                   // Toast.makeText(MyStoredWordsQuestionsActivity.this,"Wrong answer.",Toast.LENGTH_SHORT).show();
                    //answer1Button.setBackgroundColor(Color.RED);
                    // answer1Button.setHighlightColor(Color.RED);
                    answer1Button.setTextColor(Color.RED);
                }
            }
        });

        answer2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rightAnswerButtonIndex == 2) {
                    //Toast.makeText(MyStoredWordsQuestionsActivity.this,"That's right!",Toast.LENGTH_SHORT).show();
                    answer1Button.setText("");
                    answer2Button.setTextColor(Color.YELLOW);
                    answer3Button.setText("");
                    answer4Button.setText("");
                    printRightAnswer();
                }
                else {
                    //Toast.makeText(MyStoredWordsQuestionsActivity.this,"Wrong answer.",Toast.LENGTH_SHORT).show();
                    answer2Button.setTextColor(Color.RED);
                }
            }
        });

        answer3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rightAnswerButtonIndex == 3) {
                    //Toast.makeText(MyStoredWordsQuestionsActivity.this,"That's right!",Toast.LENGTH_SHORT).show();
                    answer1Button.setText("");
                    answer2Button.setText("");
                    answer3Button.setTextColor(Color.YELLOW);
                    answer4Button.setText("");
                    printRightAnswer();
                }
                else {
                    //Toast.makeText(MyStoredWordsQuestionsActivity.this,"Wrong answer.",Toast.LENGTH_SHORT).show();
                    answer3Button.setTextColor(Color.RED);
                }
            }
        });


        answer4Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (rightAnswerButtonIndex == 4) {
                    //Toast.makeText(MyStoredWordsQuestionsActivity.this,"That's right!",Toast.LENGTH_SHORT).show();
                    answer1Button.setText("");
                    answer2Button.setText("");
                    answer3Button.setText("");
                    answer4Button.setTextColor(Color.YELLOW);
                    printRightAnswer();
                }
                else {
                   // Toast.makeText(MyStoredWordsQuestionsActivity.this,"Wrong answer.",Toast.LENGTH_SHORT).show();
                    answer4Button.setTextColor(Color.RED);
                }
            }
        });

    }

    private String getRandomWord() {

        int randomIndex = rng.nextInt(allQuestionWords.size());

        while (randomIndex == QandAindex) {
            randomIndex = rng.nextInt(allQuestionWords.size());
        }
        Log.d("indices",randomIndex + " rng qA " +QandAindex);

        int randomAnsewrIndex = rng.nextInt(allQuestionWords.get(randomIndex).size() - 1) + 1;
        return allQuestionWords.get(randomIndex).get(randomAnsewrIndex);
    }

    private void printRightAnswer() {
        StringBuilder answerTextBuilder = new StringBuilder(allQuestionWords.get(QandAindex).get(0) );
        answerTextBuilder.append(" = ");
        for ( int i = 1 ; i < allQuestionWords.get(QandAindex).size() ; i++) {
            answerTextBuilder.append(allQuestionWords.get(QandAindex).get(i) );
            answerTextBuilder.append(", ");
        }
        questionTextView.setText(answerTextBuilder.toString() );
    }
}
