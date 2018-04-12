/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author Owain
 */
public class HTMLTemplate {
    
    String htmlNavBar = "";
    
    public HTMLTemplate(String userName, String role, int id, String currentPage)
    {
        htmlNavBar += "<span class=\"glyphicon glyphicon-user username\">"+userName+"</span>";
        htmlNavBar += "<div id=\"headerDiv\"><img src=\"banner2.png\" id=\"draigImage\"/>";
        htmlNavBar += "<button class=\"headerButton\" onclick=\"location.href = '/javatechgroupproject/LogoutServlet';\">Log Out</button>";
        if(role.equals("STUDENT"))
        {            
            htmlNavBar += "<button class=\"headerButton\" onclick=\"location.href = '/javatechgroupproject/QuestionGenServlet';\">Take a test</button>";
            htmlNavBar += "<button class=\"headerButton\" onclick=\"location.href = '/javatechgroupproject/TestResultsHistory?id="+id+"';\">Test History</button>";
            
        }
        else if(role.equals("ADMINISTRATOR"))
        {            
            htmlNavBar += "<button class=\"headerButton\" onclick = \"location.href = '/javatechgroupproject/RegisterUsersServlet';\">User Management</button>";
            htmlNavBar += "<button class=\"headerButton\" onclick = \"location.href = '/javatechgroupproject/StudentOverview';\" >Student Overview</button>";
                        
        }
        else if(role.equals("INSTRUCTOR"))
        {            
            htmlNavBar += "<button class=\"headerButton\" onclick = \" location.href = '/javatecjgroupproject/DictionaryServlet';\">Dictionary</button>";
            htmlNavBar += "<button class=\"headerButton\" onclick = \"location.href = '/javatechgroupproject/StudentOverview';\" >Student Overview</button>";
                        
        }
        else
        {
            
        }
        //htmlNavBar += "<button class=\"headerButton\" onclick=\"location.href = '/javatecjgroupproject/UserGuideServlet';\">User Guide</button>";
        htmlNavBar += "<button class=\"headerButton\" onclick=\"location.href = '/javatechgroupproject/HomePageServlet';\">Home</button>";
        htmlNavBar += "</div>";
        htmlNavBar += "<div id=\"headerLine\"></div>";
    }
    
    public String getNavBar()
    {
        return htmlNavBar;
    }
    
    public static String getHomePageIcons(User user)
    {
        String icons = "";
        if(user.getRole().equals("ADMINISTRATOR"))
        {
            icons+=("<div class=\"container-fluid text-center homePageIconRow\">");
            icons+=("<div class=\"row\">");
            icons+=("<div class=\"col-sm-3\">");
            icons+=("<a href=\"/javatechgroupproject/StudentOverview\">");
            icons+=("<span class=\"glyphicon glyphicon-education homeIcon\"></span>");
            icons+=("<h4>Student Overview</h4>");
            icons+=("</a>");
            icons+=("</div><div class=\"col-sm-3\">");
            icons+=("<a href=\"/javatechgroupproject/RegisterUsersServlet\">");
            icons+=("<span class=\"glyphicon glyphicon-user homeIcon\"></span>");
            icons+=("<h4>Manage User Accounts</h4>");
            icons+=("</a>");
            icons+=("</div><div class=\"col-sm-3\">");
            icons+=("<a href=\"/javatechgroupproject/QuestionGenServlet\">");
            icons+=("<span class=\"glyphicon glyphicon-question-sign homeIcon\"></span>");
            icons+=("<h4>Take a Test</h4>");
            icons+=("</a>");
            icons+=("</div>");
            icons+=("<div class=\"col-sm-3\">");
            icons+=("<a href=\"/javatechgroupproject/TestResultsHistory?id="+user.getUserid()+"\">");
            icons+=("<span class=\"glyphicon glyphicon-stats homeIcon\"></span>");
            icons+=("<h4>Your Test History</h4>");
            icons+=("</a>");
            icons+=("</div>");
            icons+=("</div>");
            icons+=("<br>");
            icons+=("</div>");
            icons+=("</div>");
            icons+=("</div>");
        }
        else if(user.getRole().equals("INSTRUCTOR"))
        {
            icons+=("<div class=\"container-fluid text-center homePageIconRow\">");
            icons+=("<div class=\"row\">");
            icons+=("<div class=\"col-sm-3\">");
            icons+=("<a href=\"/javatechgroupproject/StudentOverview\">");
            icons+=("<span class=\"glyphicon glyphicon-education homeIcon\"></span>");
            icons+=("<h4>Student Overview</h4>");
            icons+=("</a>");
            icons+=("</div><div class=\"col-sm-3\">");
            icons+=("<a href=\"/javatechgroupproject/DictionaryServlet\">");
            icons+=("<span class=\"glyphicon glyphicon-text-color homeIcon\"></span>");
            icons+=("<h4>Dictionary</h4>");
            icons+=("</a>");
            icons+=("</div><div class=\"col-sm-3\">");
            icons+=("<a href=\"/javatechgroupproject/QuestionGenServlet\">");
            icons+=("<span class=\"glyphicon glyphicon-question-sign homeIcon\"></span>");
            icons+=("<h4>Take a Test</h4>");
            icons+=("</a>");
            icons+=("</div>");
            icons+=("<div class=\"col-sm-3\">");
            icons+=("<a href=\"/javatechgroupproject/TestResultsHistory?id="+user.getUserid()+"\">");
            icons+=("<span class=\"glyphicon glyphicon-stats homeIcon\"></span>");
            icons+=("<h4>Your Test History</h4>");
            icons+=("</a>");
            icons+=("</div>");
            icons+=("</div>");
            icons+=("<br>");
            icons+=("</div>");
            icons+=("</div>");
            icons+=("</div>");
        }
        else
        {
            icons+=("<div class=\"container-fluid text-center homePageIconRow\">");
            icons+=("<div class=\"row\">");
           icons+=("</div><div class=\"col-sm-4\">");
            icons+=("<a href=\"/javatechgroupproject/QuestionGenServlet\">");
            icons+=("<span class=\"glyphicon glyphicon-question-sign homeIcon\"></span>");
            icons+=("<h4>Take a Test</h4>");
            icons+=("</div><div class=\"col-sm-4\">");
             icons+=("<a href=\"/javatechgroupproject/TestResultsHistory?id="+user.getUserid()+"\">");
            icons+=("<span class=\"glyphicon glyphicon-stats homeIcon\"></span>");
            icons+=("<h4>Your Test History</h4>");
            icons+=("</a>");
            icons+=("</div>");
            icons+=("<div class=\"col-sm-3\">");
            icons+=("<a href=\"/javatechgroupproject/TestResultsHistory?id="+user.getUserid()+"\">");
            icons+=("<span class=\"glyphicon glyphicon-info-sign homeIcon\"></span>");
            icons+=("<h4>User Guide</h4>");
            icons+=("</a>");
            icons+=("</div>");
            icons+=("</div>");
            icons+=("<br>");
            icons+=("</div>");
            icons+=("</div>");
            icons+=("</div>");
        }
        return icons;
            
    }
    
}