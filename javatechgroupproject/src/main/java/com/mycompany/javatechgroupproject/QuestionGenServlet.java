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
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Word;

/**
 *
 * @author Owain
 */
public class QuestionGenServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet QuestionGenServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet QuestionGenServlet at " + request.getContextPath() + "</h1>");
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
        
        try {
            AGDatabase dbConn = new AGDatabase();
            ArrayList<Word> wordList = new ArrayList<Word>();
            wordList = dbConn.getWordList();
            Random rand = new Random();
            
            response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet QuestionGenServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            
            for(int i = 0; i < 20; i++)
            {
                out.println("<div class='questionDiv' name='question" + (i + 1) + "'>");
                out.println("<h1>Question " + (i + 1) + "</h1>");
                int r = rand.nextInt(3) + 1;
                out.println(getQuestion(wordList.get(i)));
                //out.println("<h1>"+ wordList.get(i).getWelshWord() + " is Welsh for " + wordList.get(i).getEnglishWord() + "</h1>");
                out.println("</div>");
            }
            
            out.println("</body>");
            out.println("</html>");
        }
            
            
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QuestionGenServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        //processRequest(request, response);
    }
    
    public String getQuestion(Word word)
    {
        String toReturn = "";
        Random rand = new Random();
        int r = rand.nextInt(3) + 1;
        
        if(r == 1)
        {
            toReturn += "<h1>What is the gender of the word " + word.getWelshWord() + "?</h1>";
            toReturn += "<input type='radio' name=\"word"+ word.getId() + "\" value='male'>Male";
            toReturn += "<input type='radio' name=\"word"+ word.getId() + "\" value='female'>Female";
        }
        else if(r == 2)
        {
            toReturn += "<h1>What is the English word for " + word.getWelshWord() + "?</h1>";
            toReturn += "<input answer=" + word.getEnglishWord() + "type='text' name=\"word" + word.getId() + "\">";
        }
        else
        {
            toReturn += "<h1>What is the Welsh word for " + word.getEnglishWord() + "?</h1>";
            toReturn += "<input answer=" + word.getWelshWord() + "type='text' name=\"word" + word.getId() + "\">";
        }
        
        return toReturn;
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
