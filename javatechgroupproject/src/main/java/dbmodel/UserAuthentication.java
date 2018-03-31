/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbmodel;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author Levi
 */
public class UserAuthentication {
    
    /**
     * Checks validity of a session
     * @param request
     * @param response
     * @return
     * @throws ClassNotFoundException
     * @throws IOException 
     */
    public static User CheckSession(HttpServletRequest request, HttpServletResponse response) throws ClassNotFoundException, IOException
    {
        
            AGDatabase db = new AGDatabase();
            
            Cookie[] c = request.getCookies();
            
            String sessionid = "";
            
            //iterate through cookies to find session id
            if(c!=null)
            {
                for(int i = 0; i<c.length; i++)
                {
                    if(c[i].getName().equals("sessionid"))
                    {
                        System.out.println(c[i].getValue());
                        sessionid=c[i].getValue();
                    }
                }              

            }
            
            //search database for corresponding user & role.
            return db.getSessionUser(sessionid);
    }
    
}
