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
     * Prints the specified table
     * @param table
     */
    public ResultSet queryTable(String table)
    {
        ResultSet rs = null;
        
        try{
             //create variables
            Connection conn = SimpleDataSource.getConnection();

            //get resultset from database
            try {
                Statement stat = conn.createStatement();
                rs = stat.executeQuery("SELECT * FROM "+table);

                return rs;
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
        
        return rs;
    }
    
}
