/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Levi
 */
public class Submission {
    private int score;
    private int userId;
    private String date;
    private ArrayList<Answers> answers;

    
    public Submission(int score,int userId, String date, ArrayList<Answers> answers) {
        this.score = score;
        this.answers = answers;
        this.userId = userId;
        this.date = date;
    }
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    

    

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public ArrayList<Answers> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answers> answers) {
        this.answers = answers;
    }
}
