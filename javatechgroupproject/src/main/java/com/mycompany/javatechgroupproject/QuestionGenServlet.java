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
import org.json.*;

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
    
    String xmlString;
    
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
            wordList = dbConn.getWordsList();
            Random rand = new Random();
            
            response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Questionaire</title>");  
            out.println("<link href=\"css/styles.css\" type=\"text/css\" rel=\"stylesheet\"/>");
            out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>");
            out.println("<script src=\"js/javascript.js\"></script>");
            out.println("<script src=\"js/javascript1.js\"></script>");            
            out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>");
            out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\"></link>");
            out.println("</head>");
            out.println("<body>");
            
            
            for(int i = 0; i < 20; i++)
            {                
                int r = rand.nextInt(3) + 1;
                out.println("<div class='questionDiv' name='question" + (i + 1) + "' id='question"+(i + 1)+"' qType='" + r + "'>");
                out.println(getQuestion(wordList.get(i), i + 1, r));
                //out.println("<h1>"+ wordList.get(i).getWelshWord() + " is Welsh for " + wordList.get(i).getEnglishWord() + "</h1>");
                out.println("</div>");
            }
            
            System.out.println(xmlString);
            out.println("<div class='questionDiv'>");
            out.println("<input type=\"submit\" onclick=createTestObj() value=\"Save and Submit\"");
            out.println("</div>");
            out.println("");            
            out.println("</body>");
            out.println("</html>");
        }
            
            
            
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QuestionGenServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        //processRequest(request, response);
    }
    
    public String getQuestion(Word word, int questNum, int r)
    {
        String toReturn = "";
        Random rand = new Random();
        
        
        if(r == 3)
        {            
            toReturn += "<h1>Q" + (questNum) + ") What is the gender of the word \"" + word.getWelshWord() + "\"?</h1>";
            toReturn += "<input type='radio' name=\"questRadio" + questNum + "\" wordId=\"word"+ word.getId() + "\" value='male'>Male";
            toReturn += "<input type='radio' name=\"questRadio" + questNum + "\" wordId=\"word"+ word.getId() + "\" value='female'>Female";
        }
        else if(r == 2)
        {
            toReturn += "<h1>Q" + (questNum) + ") What is the English word for \"" + word.getWelshWord() + "\"?</h1>";
            toReturn += "<input id=\"userInput"+ questNum +"\" type='text'  wordId=\"word" + word.getId() + "\">";
        }
        else
        {
            toReturn += "<h1>Q" + (questNum) + ") What is the Welsh word for \"" + word.getEnglishWord() + "\"?</h1>";
            toReturn += "<input id=\"userInput"+ questNum +"\"  type='text'  wordId=\"word" + word.getId() + "\">";
        }
        
        return toReturn;
    }
    
    public String getXML()
    {
        
        
        String toReturn = "";
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
        //processRequest(request, response);
        
        String JSONString = request.getParameter("JSONString");
        int score = 0;
        //System.out.println("START/");
        //System.out.println(JSONString);
        //System.out.println("/END");
        
        JSONObject jObj = new JSONObject(JSONString);
        JSONArray jArray = jObj.getJSONArray("questions");
        for(int i = 0; i < jArray.length(); i++)
        {            
            JSONObject tempObj = (JSONObject)jArray.get(i);
            String outPutID = tempObj.getString("wordId");
            String outPutType = tempObj.getString("questionType");
            String outPutAnswer;
            
            try{
            outPutAnswer = tempObj.getString("userAnswer");
            }catch(org.json.JSONException e)
            {
                outPutAnswer = "";
            }
            System.out.println(outPutID + " " + outPutType + " " + outPutAnswer);
            
            try {
                AGDatabase conn = new AGDatabase();
                boolean correct = conn.checkAnswer(outPutID, outPutAnswer, outPutType);
                if(correct)
                {
                    score++;
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(QuestionGenServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            
        }
        
        
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Questionaire</title>");  
        out.println("<link href=\"css/styles.css\" type=\"text/css\" rel=\"stylesheet\"/>");
        out.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js\"></script>");
        out.println("<script src=\"js/javascript.js\"></script>");
        out.println("<script src=\"js/javascript1.js\"></script>");            
        out.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js\"></script>");
        out.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css\"></link>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h1>Post Recieved     Score:" + score +"</h1>");
        out.println("</body>");
        out.println("</html>");
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

}
