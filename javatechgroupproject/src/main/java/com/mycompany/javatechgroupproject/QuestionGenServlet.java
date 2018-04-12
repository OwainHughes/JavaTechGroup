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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Answers;
import model.HTMLTemplate;
import model.Submission;
import model.User;
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
            
            User user = UserAuthentication.CheckSession(request, response);
            HTMLTemplate navBar = new HTMLTemplate(user,getClass().getSimpleName());
            String navBarString = navBar.getNavBar();
            
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
            
            //header banner
            out.println(navBarString);
            
            
            out.println("<div class = \"tabGUI\">");
            
            //print page decriptions
            out.println("<h2><span class=\"glyphicon glyphicon-question-sign\"></span>  Quiz <span class=\"h2HL\"></span></h1>");
            out.println("<p class=\"pageDescription\">Complete the following 20 questions. </p>");
            out.println("</div>");
            
            for(int i = 0; i < 20; i++)
            {                
                int r = rand.nextInt(3) + 1;
                out.println("<div class='questionDiv' name='question" + (i + 1) + "' id='question"+(i + 1)+"' qType='" + r + "'>");
                out.println(getQuestion(wordList.get(i), i + 1, r));
                //out.println("<h1>"+ wordList.get(i).getWelshWord() + " is Welsh for " + wordList.get(i).getEnglishWord() + "</h1>");
                out.println("</div>");
            }
            
            System.out.println(xmlString);
            out.println("<div class='submitDiv questionDiv'>");
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
        
        
        
        if(r == 3)
        {            
            toReturn += "<h1>Q" + (questNum) + ") What is the gender of the word \"" + word.getWelshWord() + "\"?</h1>";
            toReturn += "<input type='radio' class=\"gender\" name=\"questRadio" + questNum + "\" wordId=\"word"+ word.getId() + "\" value='male'><span class=\"gender\">Male</span>";
            toReturn += "<input type='radio' class=\"gender\" name=\"questRadio" + questNum + "\" wordId=\"word"+ word.getId() + "\" value='female'><span class=\"gender\">Female</span>";
        }
        else if(r == 2)
        {
            toReturn += "<h1>Q" + (questNum) + ") What is the English word for \"" + word.getWelshWord() + "\"?</h1>";
            toReturn += "<input id=\"userInput"+ questNum +"\" type='text'  wordId=\"word" + word.getId() + "\">";
            toReturn += "<input type='radio' class=\"gender\" name=\"questRadio" + questNum + "\" wordId=\"word"+ word.getId() + "\" value='male'><span class=\"gender\">Male</span>";
            toReturn += "<input type='radio' class=\"gender\" name=\"questRadio" + questNum + "\" wordId=\"word"+ word.getId() + "\" value='female'><span class=\"gender\">Female</span>";
        }
        else
        {
            String gender = "";
            if(word.getGender().equals("male"))
            {
                gender = "masculine";
            }
            else
            {
                gender = "feminine";
            }
        
            toReturn += "<h1>Q" + (questNum) + ") What is the "+gender+" Welsh word for \"" + word.getEnglishWord() + "\"?</h1>";
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
        String submissionIdString = "";
        
        User user = null;
        //authenticate user
        try 
        {
            user = UserAuthentication.CheckSession(request, response);
        }
        catch (ClassNotFoundException ex) 
        {
            Logger.getLogger(QuestionGenServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //Retrieve JSON object from post
        String JSONString = request.getParameter("JSONString");
        int score = 0;
        
        JSONObject jObj = new JSONObject(JSONString);
        JSONArray jArray = jObj.getJSONArray("questions");
        
        try {
            AGDatabase conn = new AGDatabase();

            //pass JSON object to database to calculate score & map to object
            Submission s = conn.checkAnswer(jArray,user);
            ArrayList<Answers> submissionAnswers = s.getAnswers();
            
            score = s.getScore();
            
            
            //record test submission in database
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String creationDate = formatter.format(new Date());
            
            //save score to the database
            String[] columnArray = {"user_id","creation_date","score"};
            String[] valueArray = {""+user.getUserid(),creationDate,""+score};
            ArrayList<String[]> rows = new ArrayList<String[]>();
            rows.add(valueArray);
            
            //insert & return pk
            int submission_id = conn.insertRows("submissions", columnArray, rows).get(0);
            submissionIdString = "" + submission_id;
            System.out.println("submission id="+submission_id);
            
           //insert submission
            String[] resultsColumns = {"submission_id","dictionary_id","question_type","user_answer","correct_answer"};
            
            //Insert each answer associated with a submission
            ArrayList<String[]> resultsRows = new ArrayList<String[]>();
            
            for (int i = 0; i<submissionAnswers.size();i++)
            {
                String wordId = submissionAnswers.get(i).getWordId();
                String qTextType = submissionAnswers.get(i).getQuestionType();
                String uAns = submissionAnswers.get(i).getUserAnswer();
                String cAns = submissionAnswers.get(i).getCorrectAnswer();
                
                String[] submissionVals = {""+submission_id,wordId,qTextType,uAns,cAns};
                resultsRows.add(submissionVals);
            }
                        
            //Insert rows
            conn.insertRows("results", resultsColumns, resultsRows);
            
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(QuestionGenServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        //display score to the user
        
       /* response.setContentType("text/html;charset=UTF-8");
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
        }*/
        
        response.sendRedirect("/javatechgroupproject/QuizAnswersServlet?id=" + submissionIdString);
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
