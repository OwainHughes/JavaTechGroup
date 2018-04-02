/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;

/**
 *
 * @author Owain
 */
public class Test {
    
    ArrayList<Question> questionList = new ArrayList<Question>();
    String userID;
    
    
    public Test(String userID)
    {        
        this.userID = userID;
    }
    
    public void addQuestion(Question question)
    {
        questionList.add(question);
    }
}
