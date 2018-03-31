/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.javatechgroupproject;

import dbmodel.AGDatabase;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
                                String userName, String role)
            throws ServletException, IOException {
        
        String reply = "";
        
        if(role.equals("NoRole"))
        {
            reply = "Incorrect username or password";
        }
        else
        {
            // Create cookies for first and last names.      
            Cookie un = new Cookie("username",userName);
            Cookie ps = new Cookie("password", request.getParameter("password"));
            
            // Set expiry date after 30 minutes.
            un.setMaxAge(60*30);
            ps.setMaxAge(60*30);

            // Add cookies to response.
            response.addCookie(un);
            response.addCookie(ps);

            
            reply = userName + " Role:" + role;
        }
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
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
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
        String role = "";
        try {
            //check if user is in database
            //if so grab user roll
            AGDatabase dbConn  = new AGDatabase();
            
            role = dbConn.QueryLogin(userName, password);            
                       
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        processRequest(request, response, userName, role);    
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

}
