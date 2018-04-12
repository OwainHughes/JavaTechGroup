/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.javatechgroupproject;

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
 * @author Owain
 */
public class HomePageServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        User user = null;
        String role = "";
        String userName = "";
        int userID = 0;
        //authenticate user
        try 
        {
            user = UserAuthentication.CheckSession(request, response);
            role = user.getRole();
            userName = user.getUsername();
            userID = user.getUserid();
            if(!user.isValid())
            {
                response.sendRedirect("/javatechgroupproject/index.xhtml");
            }
        }
        
        catch (ClassNotFoundException ex) 
        {
            Logger.getLogger(HomePageServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        HTMLTemplate navBar = new HTMLTemplate(userName, role, userID,  "THIS IS FOR CURRENT PAGE/TODO");
        String navBarString = navBar.getNavBar();
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Questionaire</title>");  
            out.println("<link href=\"css/styles.css\" type=\"text/css\" rel=\"stylesheet\"/>");
            out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>");          
            out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>");
            out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\"></link>");
            out.println("</head>");
            out.println("<body>");
            out.println(navBarString);
            
            //print page decriptions
            out.println("<div class = \"tabGUI\">");
            out.println("<h2>  Welcome, "+user.getUsername()+"</h1>");
            out.println("<p class=\"pageDescription\">Please find above your main links for the site, here you have access to all services offered to your role.\n" +
            "\n" +
            "looking for help ? please find information on the user guide page, it contains instructions on how to perform tasks relevant to your role. </p>");
            out.println("</div>");
            
            //print icons
            out.println(HTMLTemplate.getHomePageIcons(user));
            
            out.println("</body>");
            out.println("</html>");
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
        processRequest(request, response);
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
        processRequest(request, response);
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
