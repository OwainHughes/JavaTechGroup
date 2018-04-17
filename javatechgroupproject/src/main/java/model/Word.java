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
public class Word 
{
    private int id;
    private String welshWord;
    private String englishWord;
    private String gender;    
    
    public Word(int id, String welsh, String english, String gender)
    {
        this.id = id;
        this.welshWord = welsh;
        this.englishWord = english;
        this.gender = gender;
    }

    /**
     * @return the welshWord
     */
    public String getWelshWord() {
        return welshWord;
    }

    /**
     * @param welshWord the welshWord to set
     */
    public void setWelshWord(String welshWord) {
        this.welshWord = welshWord;
    }

    /**
     * @return the englishWord
     */
    public String getEnglishWord() {
        return englishWord;
    }

    /**
     * @param englishWord the englishWord to set
     */
    public void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }

    /**
     * @return the gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * @param gender the gender to set
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }
    
    
}
