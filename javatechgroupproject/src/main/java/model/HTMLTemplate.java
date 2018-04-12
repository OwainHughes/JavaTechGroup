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
    
}
