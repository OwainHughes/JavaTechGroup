/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbmodel;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        ResultSet rs = null;
        
        try{
             //create variables
            Connection conn = SimpleDataSource.getConnection();

            //get resultset from database
            try {
                Statement stat = conn.createStatement();
                rs = stat.executeQuery("SELECT * FROM "+table);

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
            if (i > 1)
            table+= ("<th>"+md.getColumnLabel(i)+"</th>");
        }
        
        table+=("</tr>");
        
        //loop to print the table
        while (rs.next())
        {
            table+=("<tr>");
            //dynamically adjusts according to number of columns in metadata
            for(int i=1; i <= columnCount; i++) 
            {
                if (i > 1)
                table+=("<th>"+rs.getString(i)+"</th>");
            }
            table+=("</tr>");                 
        }
        
        table+="<table>";
        
        return table;
    }
    
     public String QueryLogin(String userName, String password)
    {
        ResultSet rs = null;
        String resultString = "";
        try {
            Connection conn = SimpleDataSource.getConnection();
            try {
                Statement stat = conn.createStatement();
                rs = stat.executeQuery("SELECT Role FROM Users" 
                        + " WHERE Username = " + userName + "AND Password = " + password);
                        
                resultString = rs.getString(0);
                
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
    
}
