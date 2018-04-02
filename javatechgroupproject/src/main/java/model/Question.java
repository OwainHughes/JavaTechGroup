/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Owain
 */
public class Question {   
    
    private Word word;
    private int qType;
    private int questionNum;
    private String userInput;
    
    //constructor
    public Question(Word word, int qType, int questionNum, String userInput)
    {
        this.word = word;
        this.qType = qType;
        this.questionNum = questionNum;
        this.userInput = userInput;
    }

    /**
     * @return the word
     */
    public Word getWord() {
        return word;
    }

    /**
     * @param word the word to set
     */
    public void setWord(Word word) {
        this.word = word;
    }

    /**
     * @return the qType
     */
    public int getqType() {
        return qType;
    }

    /**
     * @param qType the qType to set
     */
    public void setqType(int qType) {
        this.qType = qType;
    }

    /**
     * @return the userInput
     */
    public String getUserInput() {
        return userInput;
    }

    /**
     * @param userInput the userInput to set
     */
    public void setUserInput(String userInput) {
        this.userInput = userInput;
    }
    
    /**
     * @return the questionsNum
     */
    public int getQuestionNum() {
        return questionNum;
    }

    /**
     * @param questionsNum the questionsNum to set
     */
    public void setQuestionNum(int questionsNum) {
        this.questionNum = questionsNum;
    }
    
}
