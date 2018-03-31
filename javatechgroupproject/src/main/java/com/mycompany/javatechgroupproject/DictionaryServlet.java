/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.javatechgroupproject;

import dbmodel.AGDatabase;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Levi
 */
public class DictionaryServlet extends HttpServlet {

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
            //do do
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
             AGDatabase db = new AGDatabase();
             
             Cookie[] c = request.getCookies();
             
            
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
            out.println("<span class=\"glyphicon glyphicon-user\">"+c[0].getValue()+"</span>");
                        
            //header banner
            out.println("<div id=\"headerDiv\"><img src=\"banner2.png\" id=\"draigImage\">");
            out.println("<button class=\"headerButton\" >Test History</button>");
            out.println("<button class=\"headerButton\" id=\"currentTab\">Dictionary</button>");
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
            "          <h4 class=\"modal-title\">Add Word</h4>\n" +
            "        </div>\n" +
            "        <div class=\"modal-body\">\n" +
            "          <form action=\"LoginServlet\" method=\"POST\"><br><label>Welsh Word:</label>\n" +
            "<br>\n" +
            "                <input id=\"wwInput\" type=\"text\" name=\"welsh_word\">\n" +
            "                <br><br><label>English Word:</label><br>\n" +
            "                <input id=\"ewInput\" type=\"text\" name=\"english_word\">\n" +
            "                <br><br><label>Gender:</label>\n" +
            "                <br>\n" +
            "                \n" +
            "<input type=\"radio\" name=\"gender\" value=\"male\">Male<br><input type=\"radio\" name=\"gender\" value=\"female\">Female\n" +
            "                <br><br>\n" +
            "                \n" +
            "            </form>\n" +
            "        </div>\n" +
            "        <div class=\"modal-footer\">\n" +
            "          <button id=\"addWordDB\" type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Save</button>\n" +
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
            "          <h4 class=\"modal-title\">Edit Word</h4>\n" +
            "        </div>\n" +
            "        <div class=\"modal-body\">\n" +
            "          <form action=\"LoginServlet\" method=\"POST\"><br><label>Welsh Word:</label>\n" +
            "<br>\n" +
            "                <input id=\"wwEdit\" type=\"text\" name=\"welsh_word\">\n" +
            "                <br><br><label>English Word:</label><br>\n" +
            "                <input id=\"ewEdit\" type=\"text\" name=\"english_word\">\n" +
            "                <br><br><label>Gender:</label>\n" +
            "                <br>\n" +
            "                \n" +
            "<input type=\"radio\" name=\"genderEdit\" value=\"male\">Male<br><input type=\"radio\" name=\"genderEdit\" value=\"female\">Female\n" +
            "                <br><br>\n" +
            "                \n" +
            "            </form>\n" +
            "        </div>\n" +
            "        <div class=\"modal-footer\">\n" +
            "          <button id=\"editWordDB\" type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Save Changes</button>\n" +
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
            "          <h4 class=\"modal-title\">Delete Word</h4>\n" +
            "        </div>\n" +
            "        <div class=\"modal-body\">\n" +
            "    <p>Are you sure you want to delete this record?</p> \n" +
            "        </div>\n" +
            "        <div class=\"modal-footer\">\n" +
            "          <button id=\"deleteWordDB\" type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Delete</button>\n" +
            "          <button type=\"button\" class=\"btn btn-default\" data-dismiss=\"modal\">Cancel</button>\n" +
            "        </div>\n" +
            "      </div>\n" +
            "      \n" +
            "    </div>\n" +
            "  </div>");
            
            out.println("<button type=\"button\" class=\"addButton\" data-toggle=\"modal\" data-target=\"#addModal\"><span class=\"glyphicon glyphicon-plus\"> </span>Add word</button>");

            out.println(db.getTableHTML("dictionary","welsh_word"));
            
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
        catch (ClassNotFoundException ex) {
                
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
    }// </editor-fold>

}
