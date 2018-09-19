package com.example.laakso.deutschlernen;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class WordQuestions {


    private ArrayList<ArrayList<String>> allQuestionWords;

    private int QandAindex;
    private Random rng;

    int[] correctAmount;
    int totalCorrect;

    public WordQuestions(Context ctx, boolean[] includes) {

        ArrayList<String> tempQuestionLines = new ArrayList<>();
        for (TypedArray item : ResourceHelper.getMultiTypedArray(ctx, "tag", includes)) {

            for ( int j = 0 ; j < item.length() ; j++ ) {

                tempQuestionLines.add(item.getString(j) );
            }

        }
        correctAmount = new int[tempQuestionLines.size() ];

      allQuestionWords = new ArrayList<>();
        String[] parts;
        for ( int i = 0 ; i < tempQuestionLines.size() ; i++ ) {

            correctAmount[i] = 0;

            ArrayList tempQuestionWords = new ArrayList();

            tempQuestionLines.set(i, tempQuestionLines.get(i).replaceAll("//s+",""));

            Log.d("tempquestlines",tempQuestionLines.get(i) );

            //parts = questions[i].split(";");
            parts = tempQuestionLines.get(i).split(";");
            if (parts.length == 2) {

                // For some reason there empty character at the end of String which have to be removed ( char ' ' )
                parts[0] = parts[0].substring(0,(parts[0].length() - 1) );
                tempQuestionWords.add(parts[0]);
                Log.d("part[0]",parts[0]);
            }
            else {
                tempQuestionWords.add("Error");
                parts = new String[] {"Error","Error , Error"};
            }

            parts = parts[1].split(",");

            for ( int j = 0 ; j < parts.length ; j++ ) {
                tempQuestionWords.add(parts[j]);
            }

            allQuestionWords.add(tempQuestionWords);
        }


        rng = new Random();
    }

    public String newQuestion() {

        QandAindex = rng.nextInt(allQuestionWords.size() );

        if (totalCorrect == allQuestionWords.size() * 100) {
            return "You already know everything!";
        }

        // We won't ask words that has 100% correct answers already
        while(correctAmount[QandAindex] == 100) {
            QandAindex++;

            // preventing out of bounds exception
            if (QandAindex == allQuestionWords.size() ) {
                QandAindex = 0;
            }
        }

        StringBuilder questionBuilder = new StringBuilder();

        for (int i = 1 ; i < allQuestionWords.get(QandAindex).size() ; i++) {
            questionBuilder.append(allQuestionWords.get(QandAindex).get(i) );
            questionBuilder.append(",");
        }

        return questionBuilder.toString();
    }

    public void getCorrectAmount() {
        Log.d("Correct amount",": " + totalCorrect);
    }

    private int calculateCorrectAmount(String answer) {
        int howCorrect;
        String correctAnswer = allQuestionWords.get(QandAindex).get(0);

        if(answer.equals(correctAnswer)) {
            return 100;
        }
        else {
            return rng.nextInt(100);
        }

    }

    public int checkForRightAnswer(String answer) {

        int howCorrect = calculateCorrectAmount(answer);

        totalCorrect = totalCorrect - correctAmount[QandAindex] + howCorrect;
        correctAmount[QandAindex] = howCorrect;

        return howCorrect;
    }

    public String getCorrectAnswer() {

        String correctAnswer = allQuestionWords.get(QandAindex).get(0);

        StringBuilder correctAnswerBuilder = new StringBuilder(correctAnswer);

        correctAnswerBuilder.append(" = ");
        int j;
        for (j = 1 ; j < allQuestionWords.get(QandAindex).size() -1 ; j++) {
            correctAnswerBuilder.append(allQuestionWords.get(QandAindex).get(j) );
            correctAnswerBuilder.append(",");
        }
        correctAnswerBuilder.append(allQuestionWords.get(QandAindex).get(j) );

        return correctAnswerBuilder.toString();
    }

    public String getTotalCorrectAmount() {



        int holeNumber = totalCorrect / 100 ;
        int leftOver = totalCorrect % 100;

        StringBuilder TCABuilder = new StringBuilder();
        TCABuilder.append(holeNumber).append(".").append(leftOver).append(" / ");
        TCABuilder.append(allQuestionWords.size()).append(" correct so far");

        return TCABuilder.toString();

    }

}
