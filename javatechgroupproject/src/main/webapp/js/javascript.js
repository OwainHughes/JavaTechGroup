/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).ready(function() {
    
    setEventListeners();

    //add the row from a table
    $("#addWordDB").click(function() {
        //alert("detected");
        saveWordDB();
    });

    //update a word record in the database
    $("#editWordDB").click(function() {
        //alert("detected");
        editWordDB($(this).val());
    });

    //deletes a word record in the database
    $("#deleteWordDB").click(function() {
        //alert("detected");
        deleteWordDB($(this).val());
    });
    
    //function to save word in database
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
            success : function(response) {
                console.log("Success!");
                console.log(response);
                $('tbody').html(response);
                $("tbody").fadeToggle(500);
                $("tbody").fadeToggle(500);
                setEventListeners();
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
               $("tbody").fadeToggle(250);
                $('tbody').html(response);
                $("tbody").fadeToggle(250);
                setEventListeners();
            },
            error : function(e) {
                    alert("ERROR: Unable to add word");
                    console.log("ERROR: ", e);
            }
        });
    }

    //function to delete word from database
    function deleteWordDB(wordid){

        // create form data
        var word = {
                wordid : wordid
        }


        //Post data to the database
        $.ajax({
            type : "POST",
            contentType : "application/x-www-form-urlencoded",
            url : "/javatechgroupproject/DeleteWordServlet",
            data : word,
            dataType : 'text',
            success : function(response) {
                console.log("Success!");
                console.log(response);
                console.log("#row"+wordid);
                $("tbody").fadeToggle(250);
                $('tbody').html(response);
                $("tbody").fadeToggle(250);
                setEventListeners();
            },
            error : function(e) {
                    alert("ERROR: Unable to delete word");
                    console.log("ERROR: ", e);
            }
        });

    }

    //function to save a word to database
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
            success : function(response) {
                console.log("Success!");
                console.log(response);
                $("tbody").fadeToggle(250);
                $('tbody').html(response);
                $("tbody").fadeToggle(250);
                setEventListeners();
            },
            error : function(e) {
                    alert("ERROR: Unable to add word");
                    console.log("ERROR: ", e);
            }
        });

    }
    
    function setEventListeners(){
    
        //function to add row data to form
        $(".glyphicon-edit").click(function() {
            var cRow = $(this).closest("tr")[0];
            console.log($(this).closest("tr").attr("id"));

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

        //function to add row data to form
        $(".glyphicon-remove-sign").click(function() {
            var cRow = $(this).closest("tr")[0];
            console.log($(this).closest("tr").attr("id"));

            //pass id value to delete button
            $("#deleteWordDB").val($(cRow).find("td").eq(0).html()); 
            
        });
        
    }
});