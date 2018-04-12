/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.javatechgroupproject;

import dbmodel.AGDatabase;
import dbmodel.UserAuthentication;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author Levi
 */
public class TestResultsHistory extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response, String username)
            throws ServletException, IOException {
        
        User user = null;
        int userID = 0;
        int urlID = Integer.parseInt(request.getParameter("id"));
        
        try 
        {
            user = UserAuthentication.CheckSession(request, response);
            
            userID = user.getUserid();
        }
        catch (ClassNotFoundException ex) 
        {
            Logger.getLogger(HomePageServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(userID != urlID)
        {
            response.sendRedirect("/javatechgroupproject/HomePageServlet");            
        }
        
        try (PrintWriter out = response.getWriter()) {
            
             AGDatabase db = new AGDatabase();
                          
            
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DictionaryServlet</title>");  
            out.println("<link href=\"css/styles.css\" type=\"text/css\" rel=\"stylesheet\"/>");
            out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>");
            out.println("<script src=\"js/javascript.js\"></script>");
            out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>");
            out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\"></link>");
            out.println("</head>");
            out.println("<body>");
            
            //print current user
            out.println("<span class=\"glyphicon glyphicon-user username\">"+username+"</span>");
                        
            //header banner
            out.println("<div id=\"headerDiv\"><img src=\"banner2.png\" id=\"draigImage\">");
            out.println("<button class=\"headerButton\" >Test History</button>");
            out.println("<button class=\"headerButton\" id=\"currentTab\">Dictionary</button>");
            out.println("<button class=\"headerButton\">Home</button></div>");
            out.println("<div id=\"headerLine\"></div>");
            
            out.println("<div class=\"tabGUI\">");
            
            //Get username of this page's user.
            int userId = Integer.parseInt(request.getParameter("id"));
            User u = db.getUserById(userId);
            
            //get table html
            String table = db.getSubmissionsTableHTML(userId);
            
            //print page decriptions
            out.println("<h2><span class=\"glyphicon glyphicon-education\"></span>  Quiz Scores: <span class=\"h2HL\">"+u.getUsername()+"</span></h1>");
            out.println("<p class=\"pageDescription\"><span class=\"pageStats\">Quizzes Taken:</span> "+u.getTests_taken()+" </p>");
            out.println("<p class=\"pageDescription\"><span class=\"pageStats\">Average Score: </span> "+u.getScore()+" <br/></p>");
            out.println("<p class=\"pageDescription\">Click on a quiz attempt to see its answers.</p>");
                                  
            out.println(table);
            
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
        catch (ClassNotFoundException ex) {
                
        }
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
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) 
        {
            //check validity of user
            User user = UserAuthentication.CheckSession(request, response);

            //if user is valid, process request
            if(user.isValid())
            {
                processRequest(request,response,user.getUsername());
            }
            else
            {
                response.sendRedirect("index.xhtml");
            }
        }
        catch (ClassNotFoundException ex) 
        {
            Logger.getLogger(DictionaryServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
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
        //processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }
    
    
}
