package com.example.laakso.deutschlernen;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;

import java.util.ArrayList;

public class QuestionsEasy extends QuestionsHard {



    public QuestionsEasy() {
        super();

    }

    public String[] getWrongAnswers(int wrongAnswerAmount) {

        String[] wrongAnswers = new String[wrongAnswerAmount];
        // int[] wrongAnswersIndices = new int[wrongAnswerAmount];

        ArrayList<Integer> answerIndices = new ArrayList<>();
        answerIndices.add(QandAindex);

        int from = allQuestionWords.size() / 2;

        if (QandAindex < allQuestionWords.size() / 2 ) {
            from = 0;

        }

        boolean foundSameIndex = false;

        for (int i = 0 ; i < wrongAnswers.length ; i++ ) {
            int rngNumber = rng.nextInt(allQuestionWords.size() / 2 ) + from;

            Log.d("rngnumber",": " + rngNumber + " from: " + from );

            for (int j = 0 ; j < answerIndices.size() ; j++) {

                if (rngNumber == answerIndices.get(j)) {

                    foundSameIndex = true;
                    break;
                }
            }

            if (foundSameIndex) {
                i--;
                foundSameIndex= false;
                continue;
            }
            answerIndices.add(rngNumber);

            // int randomAnsewrIndex = rng.nextInt(allQuestionWords.get(rngNumber).size() - 1) + 1;
            // return allQuestionWords.get(randomIndex).get(randomAnsewrIndex);
            wrongAnswers[i] = allQuestionWords.get(rngNumber).get(0);
        }


        return wrongAnswers;
    }

    public void setCorrect() {
        correctAmount[QandAindex] = 100;
        totalCorrect = 100 + totalCorrect;
    }

    @Override
    public void addQuestions(Context ctx, boolean[] includes) {

        ArrayList<String> tempQuestionLines = new ArrayList<>();
        for (TypedArray item : ResourceHelper.getMultiTypedArray(ctx, "tag", includes)) {

            for ( int j = 0 ; j < item.length() ; j++ ) {

                tempQuestionLines.add(item.getString(j) );
            }

        }

        correctAmount = new int[tempQuestionLines.size() * 2 ];

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

        int nextIndex = tempQuestionLines.size() - 1 ;
        for ( int i = 0 ; i < tempQuestionLines.size() ; i++ ) {

            correctAmount[nextIndex] = 0;
            nextIndex++;

            ArrayList tempQuestionWords = new ArrayList();

            tempQuestionLines.set(i, tempQuestionLines.get(i).replaceAll("//s+",""));

            Log.d("tempquestlines",tempQuestionLines.get(i) );

            //parts = questions[i].split(";");
            parts = tempQuestionLines.get(i).split(";");

            if (parts.length == 2) {

                // For some reason there empty character at the end of String which have to be removed ( char ' ' )
                parts[0] = parts[0].substring(0,(parts[0].length() - 1) );
                tempQuestionWords.add(parts[1]);
                tempQuestionWords.add(parts[0]);
                Log.d("part[0]",parts[0]);
            }
            else {
                tempQuestionWords.add("Error");
                parts = new String[] {"Error","Error , Error"};
            }
/*
            parts = parts[1].split(",");

            for ( int j = 0 ; j < parts.length ; j++ ) {
                tempQuestionWords.add(parts[j]);
            }

      */
            allQuestionWords.add(tempQuestionWords);
        }



    }
}
