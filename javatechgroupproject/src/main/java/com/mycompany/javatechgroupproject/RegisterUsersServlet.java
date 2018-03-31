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
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;

/**
 *
 * @author Levi
 */
public class RegisterUsersServlet extends HttpServlet {

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
            
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet DictionaryServlet</title>");  
            out.println("<link href=\"css/styles.css\" type=\"text/css\" rel=\"stylesheet\"/>");
            out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>");
            out.println("<script src=\"js/registerjs.js\"></script>");
            out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>");
            out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\"></link>");
            out.println("</head>");
            out.println("<body>");
            
            //print current user
            out.println("<span class=\"glyphicon glyphicon-user\">"+username+"</span>");
            
            //header banner
            out.println("<div id=\"headerDiv\"><img src=\"banner2.png\" id=\"draigImage\">");
            out.println("<button class=\"headerButton\" >Test History</button>");
            out.println("<button class=\"headerButton\" id=\"currentTab\">User Details</button>");
            out.println("<button class=\"headerButton\">Home</button></div>");
            out.println("<div id=\"headerLine\"></div>");
            
            out.println("<div class=\"tabGUI\">");
            
            //print the add modal form.
            out.println("<div class=\"modal fade\" id=\"addModal\" role=\"dialog\" style=\"display: none;\">\n" +
                    "    <div class=\"modal-dialog\">\n" +
            "    \n" +
            "      \n" +
            "      <div class=\"modal-content\">\n" +
            "        <div class=\"modal-header\">\n" +
            "          <button type=\"button\" class=\"close\" data-dismiss=\"modal\">×</button>\n" +
            "          <h4 class=\"modal-title\">Register new user</h4>\n" +
            "        </div>\n" +
            "        <div class=\"modal-body\">\n" +
            "          <form action=\"LoginServlet\" method=\"POST\"><br><label>Username:</label>\n" +
            "<br>\n" +
            "                <input id=\"usernameInput\" type=\"text\" name=\"username\">\n" +
            "                <br><label>Password:</label>\n" +
            "\n" +
            "                <input id=\"passwordInput\" type=\"password\" \"=\"\" name=\"password\">\n" +
            "                \n" +
            "            <br>\n" +
            "<label>Role</label>\n" +
            "\n" +
            "<select id=\"roleInput\" name=\"role\">\n" +
            "  <option value=\"ADMINISTRATOR\">ADMINISTRATOR</option>\n" +
            "  <option value=\"STUDENT\">STUDENT</option>\n" +
            "  <option value=\"INSTRUCTOR\">INSTRUCTOR</option>\n" +
            "</select><br><br></form>\n" +
            "                                                                              \n" +
            "\n" +
            "        </div>\n" +
            "        <div class=\"modal-footer\">\n" +
            "          <button id=\"addUserDB\" type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Save</button>\n" +
            "          <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Cancel</button>\n" +
            "        </div>\n" +
            "      </div>\n" +
            "      \n" +
            "    </div>\n" +
            "  </div>");
            
            //print the edit modal
            out.println("<div class=\"modal fade\" id=\"editModal\" role=\"dialog\" style=\"display: none;\">\n" +
            "    <div class=\"modal-dialog\">\n" +
            "    \n" +
            "      \n" +
            "      <div class=\"modal-content\">\n" +
            "        <div class=\"modal-header\">\n" +
            "          <button type=\"button\" class=\"close\" data-dismiss=\"modal\">×</button>\n" +
            "          <h4 class=\"modal-title\">Edit User</h4>\n" +
            "        </div>\n" +
            "        <div class=\"modal-body\">\n" +
            "          <form action=\"LoginServlet\" method=\"POST\"><br><label>Username:</label>\n" +
            "<br>\n" +
            "                <input id=\"usernameEdit\" type=\"text\" name=\"username\">\n" +
            "                <br><label>Password:</label>\n" +
            "\n" +
            "                <input id=\"passwordEdit\" type=\"password\" \"=\"\" name=\"password\">\n" +
            "                \n" +
            "            <br>\n" +
            "<label>Role</label>\n" +
            "\n" +
            "<select id=\"roleEdit\" name=\"role\">\n" +
            "  <option value=\"ADMINISTRATOR\">ADMINISTRATOR</option>\n" +
            "  <option value=\"STUDENT\">STUDENT</option>\n" +
            "  <option value=\"INSTRUCTOR\">INSTRUCTOR</option>\n" +
            "</select><br><br></form>\n" +
            "                                                                              \n" +
            "\n" +
            "        </div>\n" +
            "        <div class=\"modal-footer\">\n" +
            "          <button id=\"editUserDB\" type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\" value=\"1\">Save Changes</button>\n" +
            "          <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Cancel</button>\n" +
            "        </div>\n" +
            "      </div>\n" +
            "      \n" +
            "    </div>\n" +
            "  </div>");
            
            //print the delete modal
            out.println("<div class=\"modal fade\" id=\"deleteModal\" role=\"dialog\" style=\"display: none;\">\n" +
            "    <div class=\"modal-dialog\">\n" +
            "    \n" +
            "      \n" +
            "      <div class=\"modal-content\">\n" +
            "        <div class=\"modal-header\">\n" +
            "          <button type=\"button\" class=\"close\" data-dismiss=\"modal\">×</button>\n" +
            "          <h4 class=\"modal-title\">Delete User</h4>\n" +
            "        </div>\n" +
            "        <div class=\"modal-body\">\n" +
            "    <p>Are you sure you want to delete this user?</p> \n" +
            "        </div>\n" +
            "        <div class=\"modal-footer\">\n" +
            "          <button id=\"deleteUserDB\" type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\" value=\"1\">Delete</button>\n" +
            "          <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Cancel</button>\n" +
            "        </div>\n" +
            "      </div>\n" +
            "      \n" +
            "    </div>\n" +
            "  </div>");
            
            out.println("<button type=\"button\" class=\"addButton\" data-toggle=\"modal\" data-target=\"#addModal\"><span class=\"glyphicon glyphicon-plus\"> </span> Add word</button>");

            try {
                AGDatabase db = new AGDatabase();
                out.println(db.getUserTableHTML());

            } catch (ClassNotFoundException ex) {
                out.println("CLASS ERROR");
            }
            out.println("</div>");
            out.println("</body>");
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
        try (PrintWriter out = response.getWriter()) 
        {
            //check validity of user
            User user = UserAuthentication.CheckSession(request, response);

            //if user is valid, process request
            if(user.getUserid()>-1)
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
