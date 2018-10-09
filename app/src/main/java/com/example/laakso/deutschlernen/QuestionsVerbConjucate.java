package com.example.laakso.deutschlernen;

import android.content.res.Resources;

import java.util.ArrayList;

public class QuestionsVerbConjucate extends Questions {

    private ArrayList<PersonalPronounWord> personalPronouns;
    private ArrayList<VerbConjucateWord> verbConjucates;

    private int verbConjucateIndex;


    public QuestionsVerbConjucate(Resources res) {
        super();

        String[] personalPronounResources = res.getStringArray(R.array.personal_pronoun);
        String[] verbConjucationResources = res.getStringArray(R.array.verb_conjucation_presens);

        personalPronouns = new ArrayList<>();
        PersonalPronounWord tempWord;
        for (int i = 0 ; i < personalPronounResources.length ; i++) {
            tempWord = new PersonalPronounWord(personalPronounResources[i]);
            personalPronouns.add(tempWord );
        }

        verbConjucates = new ArrayList<>();
        VerbConjucateWord tempWord2;
        for (int i = 0 ; i < verbConjucationResources.length ; i++) {
            tempWord2 = new VerbConjucateWord(verbConjucationResources[i]);
            verbConjucates.add(tempWord2 );
        }

    }

    public String getNewQuestion() {
        StringBuilder question = new StringBuilder();

        super.QandAIndex = super.rng.nextInt(personalPronouns.size() );
        question.append(personalPronouns.get(super.QandAIndex).getFinnishWord() );
        question.append(" ");

        verbConjucateIndex = super.rng.nextInt(verbConjucates.size() );
        question.append(verbConjucates.get(verbConjucateIndex).getFinnishWords() );


        return question.toString();
    }

    public String getCorrectAnswer() {
        StringBuilder correctAnswer = new StringBuilder();

        correctAnswer.append(personalPronouns.get(super.QandAIndex).getGermanWord(0) );
        correctAnswer.append(" ");

        correctAnswer.append(verbConjucates.get(verbConjucateIndex).getGermanWord(super.QandAIndex) );

        return correctAnswer.toString();
    }


}
