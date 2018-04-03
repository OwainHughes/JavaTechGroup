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
    
    private String wordid;
    private int qType;
    private int questionNum;
    private String userInput;
    
    //constructor
    public Question(String wordid, int qType, int questionNum, String userInput)
    {
        this.wordid = wordid;
        this.qType = qType;
        this.questionNum = questionNum;
        this.userInput = userInput;
    }

    /**
     * @return the word
     */
    public String getWordid() {
        return wordid;
    }

    /**
     * @param word the word to set
     */
    public void setWordId(String wordid) {
        this.wordid = wordid;
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
