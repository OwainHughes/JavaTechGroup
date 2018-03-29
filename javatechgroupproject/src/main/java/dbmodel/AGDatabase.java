/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbmodel;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Word;

/**
 * Provides connection to database and provides sql statements.
 * @author Levi
 */
public class AGDatabase {
    
    /**
     * Constructor for the class, intialises data source using properties file
     */
    public AGDatabase() throws IOException, ClassNotFoundException
    {     
        //get database properties from file
        InputStream stream = AGDatabase.class.getResourceAsStream("/database.properties");
        SimpleDataSource.init(stream);
        
    }
    
    /**
     * Returns HTML for a specific table
     * @param table
     */
    public String getTableHTML(String table)
    {

        try{
             //create variables
            Connection conn = SimpleDataSource.getConnection();

            //get resultset from database
            try {
                Statement stat = conn.createStatement();
                ResultSet rs = stat.executeQuery("SELECT * FROM "+table);

                return printTable(rs);
                //print table
                //printTable(rs);
            } 
            finally
            {
                conn.close();
            }
        }
        catch (SQLException ex) {
                Logger.getLogger(AGDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "fail";
    }
    
    /**
     * Returns HTML for a specific table, sorted by a specific column
     * @param table
     */
    public String getTableHTML(String table, String sortingColumn)
    {
        
        try{
             //create variables
            Connection conn = SimpleDataSource.getConnection();

            //get resultset from database
            try {
                Statement stat = conn.createStatement();
                ResultSet rs = stat.executeQuery("SELECT * FROM "+table+" ORDER BY "+sortingColumn+" ASC");

                return printTable(rs);
            } 
            finally
            {
                conn.close();
            }
        }
        catch (SQLException ex) {
                Logger.getLogger(AGDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "fail";
    }
    
    /**
     * Formats a ResultSet as a HTML table
     * @param rs the results set
     * @throws SQLException 
     */
    private String printTable(ResultSet rs) throws SQLException
    {
        //get metadata
        ResultSetMetaData md = rs.getMetaData();

        //get number of columns
        int columnCount = md.getColumnCount();
        
        String table = "<table>";
        
        table+=("<tr>");
        
        //print column headers
        for(int i=1; i <= columnCount; i++) 
        {
            //if (i > 1)
            table+= ("<th>"+md.getColumnLabel(i)+"</th>");
        }
        
        table+= ("<th class=\"actions\">Actions</th>");
        
        table+=("</tr>");
        
        
        //loop to print the table
        while (rs.next())
        {
            table+=("<tr id=\"row"+rs.getString("dictionary_id")+"\">");
            //dynamically adjusts according to number of columns in metadata
            for(int i=1; i <= columnCount; i++)
            {
                //if (i > 1)
                table+=("<td>"+rs.getString(i)+"</td>");
            }
            table+=("<td class=\"actions\"><span class=\"glyphicon glyphicon glyphicon-edit\" data-toggle=\"modal\" data-target=\"#editModal\">");
            table+=("</span><span class=\"glyphicon glyphicon-remove-sign\" data-toggle=\"modal\" data-target=\"#deleteModal\"></span></td>");
            table+=("</tr>");
        }
        
        table+="<table>";
        
        return table;
    }
    
    /**
     * Function to check username and password credentials.
     * @param userName
     * @param password
     * @return 
     */
    public String QueryLogin(String userName, String password)
    {
        ResultSet rs = null;
        String resultString = "NoRole";
        try {
            Connection conn = SimpleDataSource.getConnection();
            try {
                Statement stat = conn.createStatement();
                rs = stat.executeQuery("SELECT Role FROM users" 
                        + " WHERE username = '" + userName + "' AND passwords = '" + password + "';");
            while(rs.next()) 
            {
                resultString = rs.getString(1);
            }
                
            }
            finally
            {
                conn.close();
            }           
        } catch (SQLException ex) {
            Logger.getLogger(AGDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return resultString;
    }
    
    /**
     * Inserts a new word into the database.
     * @param welshword
     * @param englishword
     * @param gender 
     */
    public String addWord(String welshword, String englishword, String gender)
    {
        String wordid = "";
        try{
            //create variables
            Connection conn = SimpleDataSource.getConnection();
        
            //update rows in database
            try {
                //generate sql statement
                PreparedStatement pstat = conn.prepareStatement(
                    "INSERT INTO dictionary(welsh_word, english_word, gender)"
                            + " VALUES(?,?,?);");

                //add parameters specified by user
                pstat.setString(1, welshword);
                pstat.setString(2, englishword);
                pstat.setString(3, gender);

                //print table
                pstat.executeUpdate();
                
                PreparedStatement idStat = conn.prepareStatement(
                    "SELECT dictionary_id FROM dictionary WHERE welsh_word = ? AND english_word = ? AND gender = ?");
                idStat.setString(1, welshword);
                idStat.setString(2, englishword);
                idStat.setString(3, gender);
                
                ResultSet rs = idStat.executeQuery();
                
                rs.next();
                wordid= rs.getString("dictionary_id");
            }
            finally{
                conn.close();
            }
        }
        catch (SQLException ex){
            Logger.getLogger(AGDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return wordid;
    }
    
    /**
     * Updates a table in the database with specified parameters.
     * @param table the table to update
     * @param idColumn column name used to identify row(s)
     * @param idValue value used to specify a row
     * @param updateField field to update
     * @param updateValue new value to replace old value in database.
     */
    public void updateTable(String table, String idColumn, String idValue, String updateField, String updateValue)
    {
        try{
            //create variables
            Connection conn = SimpleDataSource.getConnection();
        
            //update rows in database
            try {
                //generate sql statement
                PreparedStatement pstat = conn.prepareStatement("UPDATE "+table+" SET "+updateField+" = ? WHERE "+idColumn+" = ?");

                //add parameters specified by user
                pstat.setString(1, updateValue);
                pstat.setString(2, idValue);

                //update table
                pstat.executeUpdate();
                System.out.println(pstat.toString());

            }
            finally{
                conn.close();
            }
        }
        catch (SQLException ex){
            Logger.getLogger(AGDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public ArrayList<Word> getWordsList()
    {        
        ArrayList<Word> wordList = new ArrayList<Word>();
        
        try {
            Connection conn = SimpleDataSource.getConnection();
            try {                
                Statement stat = conn.createStatement();
                ResultSet rs = stat.executeQuery("SELECT * FROM dictionary;");                
            while(rs.next())
            {
                int id = rs.getInt("dictionary_id");
                String welshWord = rs.getString("welsh_word");
                String englishWord = rs.getString("english_word");
                String gender = rs.getString("gender");
                
                Word tempWord = new Word(id, welshWord, englishWord, gender);
                
                wordList.add(tempWord);
            }            
            Collections.shuffle(wordList);
            }
            finally
            {
                conn.close();
            }           
        } catch (SQLException ex) {
            Logger.getLogger(AGDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        return wordList;
    }
    
}
