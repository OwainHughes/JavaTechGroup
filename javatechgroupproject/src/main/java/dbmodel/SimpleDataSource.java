/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbmodel;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * A simple data source for getting database connections. 
 * @author      Levi Roque-Nunes
 * @version     1
 * @since       11/02/2018
 */
public class SimpleDataSource
{
   private static String url;
   private static String username;
   private static String password;

   /**
      Initialises the data source.
      @param fileName the name of the property file that 
      contains the database driver, URL, username, and password
   */
   public static void init(String fileName) throws IOException, ClassNotFoundException
   {  
      Properties props = new Properties();
      FileInputStream in = new FileInputStream(fileName);
      props.load(in);

      String driver = props.getProperty("jdbc.driver");
      url = props.getProperty("jdbc.url");
      username = props.getProperty("jdbc.username");
      if (username == null) { username = ""; }
      password = props.getProperty("jdbc.password");
      if (password == null) { password = ""; }
      if (driver != null) { Class.forName(driver); }
   }
   
   /**
      Alternative to the above Initialises the data source.
      @param stream the fileinputstream of the file 
      contains the database driver, URL, username, and password
   */
   public static void init(InputStream stream) throws IOException, ClassNotFoundException
   {  
      Properties props = new Properties();
      props.load(stream);

      String driver = props.getProperty("jdbc.driver");
      url = props.getProperty("jdbc.url");
      username = props.getProperty("jdbc.username");
      if (username == null) { username = ""; }
      password = props.getProperty("jdbc.password");
      if (password == null) { password = ""; }
      if (driver != null) { Class.forName(driver); }
   }

   /**
      Gets a connection to the database.
      @return the database connection
   */
   public static Connection getConnection() throws SQLException
   {
      return DriverManager.getConnection(url, username, password);
   }
}
