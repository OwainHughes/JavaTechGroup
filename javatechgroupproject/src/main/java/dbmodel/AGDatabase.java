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
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Answers;
import model.Submission;
import model.User;
import model.Word;
import org.json.JSONArray;
import org.json.JSONObject;

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

                return printTable(rs,true);
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
     * Returns the answers associated with a quiz as a HTML table. 
     */
    public String getAnswersTableHTML(int submission_id)
    {
        
        try{
            //create variables
            Connection conn = SimpleDataSource.getConnection();
            
            //update rows in database
            try {
                //generate sql statement
                PreparedStatement pstat = conn.prepareStatement(
                "SELECT case "+
                        "when question_type = 1 then concat('What is the Welsh word for ',english_word) "+
                        "when question_type = 2 then concat('What is the English word for ',welsh_word) "+
                        "when question_type = 3 then concat('What is the gender of the Welsh word ',welsh_word) "+
                    "end as question,"+
                "user_answer,correct_answer "+
                "from results as x "+
                "inner join dictionary as y on x.dictionary_id = y.dictionary_id "+
                "where submission_id = ?");
                
                //add parameters specified by user
                pstat.setInt(1, submission_id);

                //select from table
                ResultSet rs  = pstat.executeQuery();
                
                return printTable(rs,false);

            }
            finally
            {
                //close the connection
                conn.close();
            }
        }
        catch (SQLException ex)
        {
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

                return printTable(rs,true);
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
    public String getUserTableHTML()
    {

        try{
             //create variables
            Connection conn = SimpleDataSource.getConnection();

            //get resultset from database
            try {
                Statement stat = conn.createStatement();
                ResultSet rs = stat.executeQuery("SELECT user_id,username,'******' as passwords,role FROM users ORDER BY username ASC");

                return printTable(rs,true);
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
    private String printTable(ResultSet rs,boolean includeActions) throws SQLException
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
        
        if(includeActions)
        {
            table+= ("<th class=\"actions\">Actions</th>");
        }
        
        
        table+=("</tr>");
        
        
        //loop to print the table
        while (rs.next())
        {
            table+=("<tr id=\"row"+rs.getString(1)+"\">");
            //dynamically adjusts according to number of columns in metadata
            for(int i=1; i <= columnCount; i++)
            {
                //if (i > 1)
                table+=("<td>"+rs.getString(i)+"</td>");
            }
            if(includeActions)
            {
                table+=("<td class=\"actions\"><div class=\"editIcon\" ><span class=\"glyphicon glyphicon glyphicon-edit\" data-toggle=\"modal\" data-target=\"#editModal\">");
                table+=("</span></div><div class=\"deleteIcon\" ><span class=\"glyphicon glyphicon-remove-sign\" data-toggle=\"modal\" data-target=\"#deleteModal\"></span></div></td>");
            }
            table+=("</tr>");
        }
        
        table+="<table>";
        
        return table;
    }
    
    /**
     * Deletes a record from a specified table according to a single column
     * @param table
     * @param idColumn
     * @param idValue 
     */
    public void deleteFromTable(String table, String idColumn, String idValue)
    {
        
        try{
            //create variables
            Connection conn = SimpleDataSource.getConnection();
            
            //update rows in database
            try {
                //generate sql statement
                PreparedStatement pstat = conn.prepareStatement("DELETE FROM "+table+" WHERE "+idColumn+" = ?");

                //add parameters specified by user
                pstat.setString(1, idValue);

                //update table
                pstat.executeUpdate();

            }
            finally
            {
                //close the connection
                conn.close();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(AGDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Function to check username and password credentials.
     * @param userName
     * @param password
     * @return 
     */
    public User QueryLogin(String userName, String password)
    {
        ResultSet rs = null;
        
        User user = new User(-1,"","");
        
        String resultString = "NoRole";
        try {
            Connection conn = SimpleDataSource.getConnection();
            try {
                Statement stat = conn.createStatement();
                rs = stat.executeQuery("SELECT * FROM users" 
                        + " WHERE username = '" + userName + "' AND passwords = '" + password + "';");
            while(rs.next()) 
            {
                int userId = Integer.parseInt(rs.getString("user_id"));
                String role = rs.getString("role");
                user = new User(userId,userName,role);
            }
                
            }
            finally
            {
                conn.close();
            }           
        } catch (SQLException ex) {
            Logger.getLogger(AGDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return user;
    }
    
    /**
     * Add new rows to the specified table. Returns the new primary keys generated by the insert.
     * @param table table to add the new row.
     * @param values values to add to the db
     */
    public ArrayList<Integer> insertRows(String table, String[] columns,ArrayList<String[]> rows)
    {
        ArrayList<Integer> pk = new ArrayList<Integer>();
        //Build up insert String
        String columnList = sqlListBuider(columns).replace("'", "");
        
        String valueList="";
        
        //build up all rows
        for(int i = 0;i<rows.size();i++)
        {
            valueList+="("+sqlListBuider(rows.get(i))+")";
            if(i<rows.size()-1)
            {
                valueList+=",";
            }
        }
        
        
        // debug: System.out.println(row);
        try{
            //create variables
            Connection conn = SimpleDataSource.getConnection();
            
            //update rows in database
            try {
                //generate sql statement
                
                PreparedStatement stat = conn.prepareStatement(
                        "INSERT INTO "+table
                        +"("+columnList+")"
                        +"VALUES "+valueList,
                        Statement.RETURN_GENERATED_KEYS);

                //update table
                System.out.println("INSERT INTO "+table+"("+columnList+")"+"VALUES "+valueList+"");
                stat.executeUpdate();
                
                ResultSet keys = stat.getGeneratedKeys();
                while(keys.next())
                {
                    pk.add(keys.getInt(1));
                }

            }
            finally
            {
                //close the connection
                conn.close();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(AGDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return pk;
    }
    
    /**
     * Converts an array into a list usable by SQL statements, in the format "(e,e,e,e)"
     * @param array
     * @return 
     */
    private String sqlListBuider(String[] array)
    {
        //Build up insert String
        String row="";
        
        //builds up string for SQL insert statement
        for(int i = 0; i<array.length;i++)
        {
            if(i>0)
            {
                row+=",'"+array[i]+"'";
            }
            else
            {
                row+="'"+array[i]+"'";
            }
        }
        return row;
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
    
    
    /**
     * Return user associated with a session id
     */
    public User getSessionUser(String sessionid)
    {
        //create cookie to record login
        User user = new User(-1,"","");
            
        try {
                        
            //create variables
            Connection conn = SimpleDataSource.getConnection();
            
            //update rows in database
            try {
                //generate sql statement
                PreparedStatement pstat = conn.prepareStatement(
                        "SELECT * FROM sessions AS x"
                                +" INNER JOIN users AS y on x.user_id=y.user_id "
                                +"WHERE session_id = ?");
                
                //add parameters specified by user
                pstat.setString(1, sessionid);
                
                System.out.println(pstat.toString());
                
                //get table table
                ResultSet rs = pstat.executeQuery();
                
                while(rs.next())
                {
                    //set user object to corresponding user
                    user = new User(Integer.parseInt(rs.getString("user_id")),
                                    rs.getString("username"),
                                    rs.getString("role"));
                    user.setValid(true);
                }
                
                
            }
            finally{
                conn.close();
            }
            
           
        }
        catch (SQLException ex) {
            Logger.getLogger(AGDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
         //return new sessionid
         return user;
    }

    /**
     * Formats the JSONArray associated with a quiz submission, and returns an object
     * holding the answers and score.
     * @param jArray
     * @param user
     * @return 
     */
    public Submission checkAnswer(JSONArray jArray, User user)
    {
        //create list to hold answers
        ArrayList<Answers> answers = new ArrayList<Answers>();
        int score = 0;
        
        try{
            //create variables
            Connection conn = SimpleDataSource.getConnection();
            
            try 
            {
                for(int i = 0; i < jArray.length(); i++)
                {            
                    JSONObject tempObj = (JSONObject)jArray.get(i);
                    String wordId = tempObj.getString("wordId");
                    String qType = tempObj.getString("questionType");
                    String userAnswer;
                    String userGenderInput = "";

                    try{
                    userAnswer = tempObj.getString("userAnswer");
                    userGenderInput = tempObj.getString("userGenderInput");
                    }catch(org.json.JSONException e)
                    {
                        userAnswer = "";
                    }
                    System.out.println(wordId + " " + qType + " " + userAnswer);

                    //update rows in database

                    //generate sql statement

                    String correctAnswerCol = "";
                    String correctGender = "";
                    if(qType.equals("1"))
                    {
                        correctAnswerCol = "welsh_word";
                    }
                    else if(qType.equals("2"))
                    {
                        correctAnswerCol = "english_word";                        
                    }
                    else
                    {
                        correctAnswerCol = "gender";
                    }

                    PreparedStatement pstat = conn.prepareStatement("SELECT " + correctAnswerCol + " FROM dictionary WHERE dictionary_id = " + wordId.substring(4));
                    ResultSet rs = pstat.executeQuery();
                    rs.next();
                    String correctAnswer = rs.getString(1);
                    if(userAnswer.equals(correctAnswer))
                    {
                        if(qType.equals("2"))
                        {
                            PreparedStatement pstat1 = conn.prepareStatement("SELECT gender FROM dictionary WHERE dictionary_id = " + wordId.substring(4));
                            ResultSet rs1 = pstat1.executeQuery();
                            rs.next();
                            String cG= rs.getString(1);
                            if(userGenderInput.equals(cG))
                            {
                                score++;
                            }
                        }
                        else
                        {
                            score++;
                        }
                    }
                    
                    
                    //add the answers to the list
                    Answers a = new Answers(qType,userAnswer,correctAnswer,wordId.replace("word", ""));
                    answers.add(a);
                }
            }
            finally
            {
                //close the connection
                conn.close();
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(AGDatabase.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //create a submission object to hold the answers & score
        Submission s = new Submission(score, answers);
        
        return s;
    }
    
}
