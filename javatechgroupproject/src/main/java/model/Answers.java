/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Levi
 */
public class Answers {
    private String question_type;
    private String userAnswer;
    private String correctAnswer;
    private String wordId;

    public Answers(String question_type, String userAnswer, String correctAnswer, String wordId) {
        this.question_type = question_type;
        this.userAnswer = userAnswer;
        this.correctAnswer = correctAnswer;
        this.wordId = wordId;
    }

    public String getWordId() {
        return wordId;
    }

    public void setWordId(String wordId) {
        this.wordId = wordId;
    }
    
    
    public String getQuestionType() {
        return question_type;
    }

    public void setQuestionType(String question_type) {
        this.question_type = question_type;
    }

    public String getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(String userAnswer) {
        this.userAnswer = userAnswer;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
    
    
    
}
