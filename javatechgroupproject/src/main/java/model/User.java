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

    /**
     * Constructor for user object.
     * @param userid
     * @param username
     * @param role 
     */
    public User(int userid, String username, String role) {
        this.userid = userid;
        this.username = username;
        this.role = role;
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
