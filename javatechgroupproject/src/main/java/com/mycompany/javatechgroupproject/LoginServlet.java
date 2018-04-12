/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.javatechgroupproject;

import dbmodel.AGDatabase;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author Owain
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response,
                                String userName, String sessionid)
        throws ServletException, IOException {
        
        String reply = "";

        // Create cookies for first and last names.      
        Cookie sessionCookie = new Cookie("sessionid",sessionid);

        // Set expiry date after 30 minutes.
        sessionCookie.setMaxAge(60*30);

        // Add cookies to response.
        response.addCookie(sessionCookie);
        response.sendRedirect("/javatechgroupproject/HomePageServlet");
        
        /*reply = "Session Id:" + sessionid;
                
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {           
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet LoginServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>User:" + reply + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }*/
    }

    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       /* String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        String role = "";
        try {
            //check if user is in database
            //if so grab user roll
            AGDatabase dbConn  = new AGDatabase();
            
            role = dbConn.QueryLogin(userName, password);
            
            System.out.println("User Role = " + role);            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }*/
        processRequest(request, response, "wrong method", "Still Wrong");
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String userName = request.getParameter("userName");
        String password = request.getParameter("password");
        
        try {
            //check if user is in database
            AGDatabase dbConn  = new AGDatabase();
            User user = dbConn.QueryLogin(userName, password);
            
            //check if user exists
            if(user.getUserid()>-1)
            {
                //create cookie to record login
                String sessionid = UUID.randomUUID().toString();
                
                String[] columns = {"user_id","session_id"};
                String[] values = {""+user.getUserid(),sessionid};
                ArrayList<String[]> rows = new ArrayList<String[]>();
                rows.add(values);
                
                dbConn.insertRows("sessions", columns, rows);
                
                //pass to process request
                processRequest(request, response, userName, sessionid);
                
                
            }
            else
            {
                //inform user of incorrect password.
                response.setContentType("text/html;charset=UTF-8");
                try (PrintWriter out = response.getWriter()) {           
                    out.println("<!DOCTYPE html>");
                    out.println("<html>");
                    out.println("<head>");
                    out.println("<title>Servlet LoginServlet</title>");            
                    out.println("</head>");
                    out.println("<body>");
                    out.println("<h1>User: Invalid Username & Password</h1>");
                    out.println("</body>");
                    out.println("</html>");
                }
            }
            
            
                       
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
           
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    private void eraseCookie(HttpServletRequest request, HttpServletResponse response)
    {
        Cookie[] cookies = request.getCookies();
        if(cookies != null)
        {
            for(int i = 0; i < cookies.length; i++)
            {
                cookies[i].setValue("");
                cookies[i].setPath("/");
                cookies[i].setMaxAge(0);
                response.addCookie(cookies[i]);
            }
        }
    }

}
