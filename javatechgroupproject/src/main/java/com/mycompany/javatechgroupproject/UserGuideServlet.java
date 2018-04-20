/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.javatechgroupproject;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.User;
import dbmodel.UserAuthentication;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author eeu87c
 */
@WebServlet(name = "UserGuideServlet", urlPatterns = {"/UserGuideServlet"})
public class UserGuideServlet extends HttpServlet {

  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
       String role = "";
       String userName = "";
       User user = null;
       String userGuideString = ""; 
        //authenticate user
        try 
        {
            user = UserAuthentication.CheckSession(request, response);
        }
        catch (ClassNotFoundException ex) 
        {
            Logger.getLogger(UserGuideServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (role.equals("STUDENT")) {
           
           userGuideString += " <div class = \"Userimage\">";
           userGuideString += "  <h2> How to Log Out</h2>";
           userGuideString += " <p> To log out of your account please find the logout button on your home page as shown .</p> ";
           userGuideString += " <img id=\"Image1\" src = \"GuideImages/TakeTest.png\" /> ";
           userGuideString += " </div>";
           
           userGuideString += " <div class = \"Userimage\">";
           userGuideString += " <h2> How to view test History</h2>";
           userGuideString += " <p> Firstly to access the History page please find and click on the test history button on your home page.</p> ";
           userGuideString += " <img id=\"Image1\" src = \"GuideImages/TakeTest.png\" /> ";
           userGuideString += " <p> You will now be presented with the test History, from here you can view the answers to multiple quiz and when finished you can then click on the test score to view the individual answers as shown below.</p>";
           userGuideString += " <img id=\"\" src = \"GuideImages/.png\" /> ";
           userGuideString += "  </div> ";
           
           userGuideString += " <div class = \"Userimage\">";
           userGuideString += " <h2> How do tests work </h2>";
           userGuideString += " <p> Here we will explain how tests are marked and what is expected and accepted as a response to the questions</p> ";
           userGuideString += " <p> Firstly please note that you are not required to answer all questions on the quiz and failing to answer a questino will result in a amrk of zero for that question.</p>";
           userGuideString += " <p> Secondly where there is a question that asks for the gender of the noun and the noun itself a mark of zero will be awarded if both sections are not completed and answered correctly. </p> ";
           userGuideString += "  </div> ";
           
           userGuideString += " <div class = \"Userimage\"> ";
           userGuideString += " <h2> Taking a test</h2>";
           userGuideString += " <p> Firstly to access the take a test page please find and click on the take a test button on your homepage.</p> ";
           userGuideString += " <img id=\"Image1\" src = \"GuideImages/TakeTest.png\" />";
           userGuideString += " <p> You will now be presented with the test page, from here you can answer questions and when finished you must press save and commit button as shown below.</p>";
           userGuideString += " <img id=\"\" src = \"GuideImages/.png\" /> ";
           userGuideString += " <p> Finally you will be taken to an answer page where you will be shown your score and the correct answers. </p> ";
           userGuideString += " <img id=\"\" src = \"GuideImages/.png\" /> ";
           userGuideString += " </div> ";
            
        }
        else 
        if (role.equals("ADMINISTRATOR")){
            
           userGuideString += " <div class = \"Userimage\"> ";
           userGuideString += " <h2> How to use Admin tools</h2>";
           userGuideString += " <p> Firstly to access admin tools please find and click on the user management button on your homepage.</p> ";
           userGuideString += " <img id=\"\" src = \"GuideImages/.png\" />";
           userGuideString += " <p> You will now be presented with the admin page, from here you can manage user accounts such as add, delete or edit user details. To add a user please select the button shown below.</p>";
           userGuideString += " <img id=\"\" src = \"/.png\" /> ";
           userGuideString += " <p> This will then bring up a pop up and will allow you to enter the suer data to add them to the system, this allows for easy input and allows you to saty on tha page in case you have further need of tools . </p> ";
           userGuideString += " <img id=\"\" src = \"GuideImages/.png\" /> ";
           userGuideString += " </div> ";
           
           
           userGuideString += " <div class = \"Userimage\"> ";
           userGuideString += " <h2>How to access test history</h2>";
           userGuideString += " <p> Firstly to access test history please find and click on the test history button on your homepage.</p> ";
           userGuideString += " <img id=\"\" src = \"GuideImages/.png\" />";
           userGuideString += " <p> You will now be presented with the history page, from here you can see all ofthe students test details such as score and id.</p>";
           userGuideString += " <img id=\"\" src = \"/.png\" /> ";
           userGuideString += " <p> This will then bring up a pop up and will allow you to enter the suer data to add them to the system, this allows for easy input and allows oyu to saty on tha page in case you have further need of tools . </p> ";
           userGuideString += " <img id=\"\" src = \"GuideImages/.png\" /> ";
           userGuideString += " </div> ";
           
        }
        
        else
            if (role.equals("INSTRUCTOR"))
            {
                 userGuideString += " <div class = \"Userimage\"> ";
           userGuideString += " <h2>How to access test history</h2>";
           userGuideString += " <p> Firstly to access test history please find and click on the test history button on your homepage.</p> ";
           userGuideString += " <img id=\"\" src = \"GuideImages/.png\" />";
           userGuideString += " <p> You will now be presented with the history page, from here you can see all ofthe students test details such as score and id.</p>";
           userGuideString += " <img id=\"\" src = \"/.png\" /> ";
           userGuideString += " <p> This will then bring up a pop up and will allow you to enter the suer data to add them to the system, this allows for easy input and allows oyu to saty on tha page in case you have further need of tools . </p> ";
           userGuideString += " <img id=\"\" src = \"GuideImages/.png\" /> ";
           userGuideString += " </div> ";
           
           
           userGuideString += " <div class = \"Userimage\"> ";
           userGuideString += " <h2>How to access Dictionary</h2>";
           userGuideString += " <p> Firstly to access Dictionary please find and click on the Dictionary button on your homepage.</p> ";
           userGuideString += " <img id=\"\" src = \"GuideImages/.png\" />";
           userGuideString += " <p> You will now be presented with the Dictionary page, from here you can see all of the current words available in a test. You can also add and remove wrods as well as edit from here</p>";
           userGuideString += " <img id=\"\" src = \"/.png\" /> ";
           userGuideString += " </div> ";
           
           userGuideString += " <div class = \"Userimage\"> ";
           userGuideString += " <h2>How to add a word</h2>";
 
           userGuideString += " <p Firstly to add a word clcik on the plus sumbol, this will takr you to the add word page, from here you can see all of the options needed to present a word in a quiz and set an answer an question type.</p>";
           userGuideString += " <img id=\"\" src = \"/.png\" /> ";
           userGuideString += " </div> ";
            }
        
        try (PrintWriter out = response.getWriter()) {
                
           
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet UserGuideServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println(userGuideString);
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
    }// </editor-fold>

}
