/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
function createTestObj()
{
    var questions = new Array();
    for(i = 1; i <= 20; i++)
    {
        var qType = document.getElementById("question" + i).getAttribute("qType");
        
        if(qType === "2")
        {
            var userInputString = document.getElementById("userInput" + i).value;
            var wordID = document.getElementById("userInput" + i).getAttribute("wordId");
            
            var userGenderInput = $('input[name="questRadio' + i +'"]:checked').val();
            
            var aQuestion = new Question(wordID, qType, userInputString);
            aQuestion.setUserGenderInput(userGenderInput);
            questions.push(aQuestion);
        }        
        else if(qType !== "3")
        {
            var userInputString = document.getElementById("userInput" + i).value;
            var wordID = document.getElementById("userInput" + i).getAttribute("wordId");
            
           /* console.log(userInputString);
            console.log("Question:" + i);
            console.log("qType:" + qType);
            console.log("User Answer:" + userInputString);
            console.log("WordID:" + wordID);*/
            
            var aQuestion = new Question(wordID, qType, userInputString);
            questions.push(aQuestion);
        }
        else
        {
            var userInputString = $('input[name="questRadio' + i +'"]:checked').val();
            //var wordID = $('input[name="questRadio' + i +'"]').val();
            var wordID = document.getElementsByName("questRadio" + i)[0].getAttribute("wordId");
            
            
            
           
            /*console.log("Question:" + i);
            console.log("qType:" + qType);
            console.log("User Answer:" + userInputString);
            console.log("WordID:" + wordID);*/
            
            var aQuestion = new Question(wordID, qType, userInputString);
            questions.push(aQuestion);
            
        }
    }
    
    var testObj = new Test();
    
    for(i = 0; i < 20; i++) 
    {
        console.log("Loop:" + i);
        testObj.addQuestion(questions[i]);
    }
    
    testObj.toString();
    
    var toServerString = JSON.stringify(testObj);
    
    var form = document.createElement("form");
    form.setAttribute("method", "POST");
    form.setAttribute("action", "/javatechgroupproject/QuestionGenServlet");    
    
    var hiddenString = document.createElement("input");
    hiddenString.setAttribute("type", "hidden");
    hiddenString.setAttribute("name", "JSONString");
    hiddenString.setAttribute("value", toServerString);    
    
    form.appendChild(hiddenString);
    document.body.appendChild(form);
    form.submit();   
}

class Question
{
    constructor(wordId, questionType, userAnswer)
    {
        this.wordId = wordId;
        this.questionType = questionType;
        this.userAnswer = userAnswer; 
        this.userGenderInput = null;
    }
    
    getWordID()
    {
        return wordId;
    }
    getQuestionType()
    {
        return questionType;
    }
    getUserInput()
    {
        return userAnswer;
    }
    setUserGenderInput(userGenderInput)
    {
        this.userGenderInput = userGenderInput;
    }
    getUserGenderInput()
    {
        return userGenderInput;
    }
}

class Test
{   
    
    constructor()
    {    
        this.questions = new Array();
        /*for(i = 0; i < 20; i++)
	{
            console.log("Loop:" + i);
            this.questions.push(questionsArray[i]);
	}*/
    }
    
    addQuestion(question)
    {
        this.questions.push(question);
    }
    
    getQuestions(i)
    {
        return this.questions[i];
    }
    
    toString()
    {
        for(i = 0; i < this.questions.length; i++)
        {
            //console.log(i);
            console.log("Question:" + i + ":" + this.questions[i].wordId + " : " + this.questions[i].userAnswer + " : " + this.questions[i].questionType);
        }
    }
}