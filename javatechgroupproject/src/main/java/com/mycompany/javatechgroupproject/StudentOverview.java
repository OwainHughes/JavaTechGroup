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
import model.HTMLTemplate;
import model.User;

/**
 *
 * @author Levi
 */
public class StudentOverview extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            AGDatabase db = new AGDatabase();
             
            User user = UserAuthentication.CheckSession(request, response);
            HTMLTemplate navBar = new HTMLTemplate(user);
            String navBarString = navBar.getNavBar();
                          
            
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
            
         
                        
            //header banner
            out.println(navBarString);
            
            out.println("<div class=\"tabGUI\">");
            out.println("<h2><span class=\"glyphicon glyphicon-stats\"></span>  Student Performance Overview</h1>");
            out.println("<p class=\"pageDescription\">Click on a student to see the quizzes they have completed.</p>");
            //int submission_id = Integer.parseInt(request.getParameter("submission_id"));
            
            out.println(db.getUserTableScoreHTML());
            
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
            if(user.isValid() && (user.getRole().equals("ADMINISTRATOR") || user.getRole().equals("INSTRUCTOR")))
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
    }// </editor-fold>

}
