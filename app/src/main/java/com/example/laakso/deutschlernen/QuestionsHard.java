package com.example.laakso.deutschlernen;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class QuestionsHard {

    protected ArrayList<ArrayList<String>> allQuestionWords;

    protected int QandAindex;
    protected Random rng;

    protected int[] correctAmount;
    protected int totalCorrect;

    public QuestionsHard() {
        rng = new Random();
    }

    public void addQuestions(Context ctx, boolean[] includes) {

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

    }


    public int[] getCorrectAmount() {
        return correctAmount;
    }

    public int getTotalCorrect() {
        return totalCorrect;
    }

    public void setCorrectAmount(int[] correctAmount) {
        this.correctAmount = correctAmount;
    }

    public void setTotalCorrect(int totalCorrect) {
        this.totalCorrect = totalCorrect;
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


    private int calculateCorrectAmount(String answer) {

        String correctAnswer = allQuestionWords.get(QandAindex).get(0);

        int answerLength = answer.length();
        int correctSize = allQuestionWords.get(QandAindex).get(0).length();
        int howCorrect = getLongestCommonSubstring(answer,correctAnswer);

        Log.d("correctness",answer + " ?= " + correctAnswer + "   howCorrect: "+ howCorrect);

        if(answer.equals(correctAnswer)) {
            return 100;
        }

        // Lets check simple typos and give higher score if we find only few of them
        else if (answerLength == correctSize) {
            int typoAmount = checkTypoAmount(answer, correctAnswer);

            if (typoAmount == 1) {
                return 90;
            }
            else if (typoAmount == 2) {
                return 75;
            }
            else {
                return getRoughTotalScore(answerLength,correctSize,howCorrect);
            }

        }

        else {

            return getRoughTotalScore(answerLength,correctSize,howCorrect);
        }

    }

    private int checkTypoAmount(String a, String b) {
        if (a.length() != b.length() ) {
            return 999;
        }

        int typo = 0;
        for (int i = 0 ; i < a.length() ; i++) {

            if (a.charAt(i) != b.charAt(i)) {
                typo++;
            }

        }
        return typo;
    }

    private int getRoughTotalScore(int answerLength, int correctSize, int howCorrect) {

        if (howCorrect <= 2) { return 0; }

        // The shorter the correct word the bigger the punishment
        // 50,33,25,20,16, 14, 12, 11, 10
        int wrongFactor = 100 / correctSize;

        // 0 is a perfect match
        // 0,1,2,3,4,5,6,7
        int answerDifference = answerLength - correctSize;
        if (answerDifference < 0) {
            answerDifference = answerDifference * -1;
        }

        int sizeDifferencePunishment = answerDifference * wrongFactor;
        int matchDifferencePunishment = (correctSize - howCorrect)*100 / correctSize;

        int totalScore = 100 - sizeDifferencePunishment - matchDifferencePunishment;


        Log.d("SDP",": " + sizeDifferencePunishment);
        Log.d("MDP",": " + matchDifferencePunishment);
        Log.d("TS",": " + totalScore);

        if (totalScore < 0) {
            return 0;
        } else if (totalScore > 99) {
            return 99;
        }
        return totalScore;
    }

    public int checkForRightAnswer(String answer) {

        int howCorrect = calculateCorrectAmount(answer);

        totalCorrect = totalCorrect - correctAmount[QandAindex] + howCorrect;
        correctAmount[QandAindex] = howCorrect;

        return howCorrect;
    }

    public String getAnswer() {
        return allQuestionWords.get(QandAindex).get(0);
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

    public static int getLongestCommonSubstring(String a, String b){
        int m = a.length();
        int n = b.length();

        int max = 0;

        int[][] dp = new int[m][n];

        for(int i=0; i<m; i++){
            for(int j=0; j<n; j++){
                if(a.charAt(i) == b.charAt(j)){
                    if(i==0 || j==0){
                        dp[i][j]=1;
                    }else{
                        dp[i][j] = dp[i-1][j-1]+1;
                    }

                    if(max < dp[i][j])
                        max = dp[i][j];
                }

            }
        }

        return max;
    }

}
