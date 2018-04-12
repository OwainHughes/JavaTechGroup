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
public class User {
    
    private int userid;
    private String username;
    private String role;
    private String tests_taken;
    private String score;
    private boolean valid;

    public User(int userid, String username, String role, String tests_taken, String score) {
        this.userid = userid;
        this.username = username;
        this.role = role;
        this.tests_taken = tests_taken;
        this.score = score;
    }
    
    public User(int userid, String username, String role) {
        this.userid = userid;
        this.username = username;
        this.role = role;
        this.tests_taken = "";
        this.score = "";
        this.valid = false;
    }

    public String getTests_taken() {
        return tests_taken;
    }

    public void setTests_taken(String tests_taken) {
        this.tests_taken = tests_taken;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    /**
     * returns the unique identifier of a user
     * @return 
     */
    public int getUserid() {
        return userid;
    }

    /**
     * returns the username of a user.
     * @return 
     */
    public String getUsername() {
        return username;
    }


    //returns the role of a user.
    public String getRole() {
        return role;
    }
    
    
}
