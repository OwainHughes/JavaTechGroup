/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

 //Passes new record to the db.
$(document).ready(function() {
    
    //function to add row data to form
    $(".glyphicon-edit").click(function() {
        var cRow = $(this).closest("tr")[0];
        
        //pass id value to button
        $("#editWordDB").val($(cRow).find("td").eq(0).html()); 

        //add english and welsh words
        $("#wwEdit").val($(cRow).find("td").eq(1).html());  
        $("#ewEdit").val($(cRow).find("td").eq(2).html());

        //check male/female radio button
        if($("input[name=genderEdit]").eq(0).val() == $(cRow).find("td").eq(3).html())
        {
            $("input[name=genderEdit]").eq(0).prop("checked", true); 
        }
        else
        {
            $("input[name=genderEdit]").eq(1).prop("checked", true); 
        }

    });

    //code to add the row from a table
    $("#addWordDB").click(function() {
        //alert("detected");
        saveWordDB();
    });
    
    //code to update a word record in the database
    $("#editWordDB").click(function() {
        //alert("detected");
        editWordDB($(this).val());
    });
    
    
    //function to delete survet from database
    function saveWordDB(){
    	
    	// create form data
    	var word = {
    		welshword : $("#wwInput").val(),
    		englishword : $("#ewInput").val(),
    		gender : $('input[name=gender]:checked').val()
    	}

    	
    	//Post data to the database
    	$.ajax({
            type : "POST",
            contentType : "application/x-www-form-urlencoded",
            url : "/javatechgroupproject/AddWordServlet",
            data : word,
            dataType : 'text',
            success : function() {
                console.log("Success!");
            },
            error : function(e) {
                    alert("ERROR: Unable to add word");
                    console.log("ERROR: ", e);
            }
        });
 
    }
    
    //function to delete survet from database
    function editWordDB(wordid){
    	
    	// create form data
    	var word = {
                wordid : wordid,
    		welshword : $("#wwEdit").val(),
    		englishword : $("#ewEdit").val(),
    		gender : $('input[name=genderEdit]:checked').val()
    	}

    	
    	//Post data to the database
    	$.ajax({
            type : "POST",
            contentType : "application/x-www-form-urlencoded",
            url : "/javatechgroupproject/EditWordServlet",
            data : word,
            dataType : 'text',
            success : function(response) {
                console.log("Success!");
                console.log(response);
                console.log("#row"+wordid);
                $("#row"+wordid).html(response);
            },
            error : function(e) {
                    alert("ERROR: Unable to add word");
                    console.log("ERROR: ", e);
            }
        });
 
    }
});