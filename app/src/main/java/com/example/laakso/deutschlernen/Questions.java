package com.example.laakso.deutschlernen;

import android.util.Log;

import java.util.Random;

public abstract class Questions {

    protected Random rng;
    protected int QandAIndex;


    public Questions() {
        rng = new Random();
    }

    abstract String getNewQuestion();


    public static int calculateCorrectAmount(String answer, String correctAnswer) {


        int answerLength = answer.length();
        int correctSize = correctAnswer.length();
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


    private static int checkTypoAmount(String a, String b) {
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

    private static int getRoughTotalScore(int answerLength, int correctSize, int howCorrect) {

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

    private static int getLongestCommonSubstring(String a, String b){
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
