/* Operations */
$(function(){
	
	var options = {
		    "backdrop" : "static",
		    "keyboard"  : "true"
		}
	
	$('body').on('click', 'a#btnDeleteDoc', function() {
		var isOk = false;
		$('#tbDoclist tbody tr').each(function() {
	       if ( $(this).hasClass('highlight') ) {
	    	   //Show confirmation
	    	   isOk = true;
	    	   $('#modalConfirmDelete').modal(options);   	   
	       } 
	     });
		
		if (!isOk) {
			$('#modalMessage').modal(options);
		}
	});
	
	$('body').on('click', 'button#btnConfirmDelete', function() {
		
		$('#tbDoclist tbody tr').each(function() {
	       if ( $(this).hasClass('highlight') ) {
	    	  $('#modalConfirmDelete').modal('toggle');
	    	   
	          var $row = $(this);
	          var selectedDocId = $row.find("td:first").text();
	          $row.remove();
	          $.ajax({
	 	         url: '/gpm/document/delete',
	 	         type: 'post',
	 	         dataType: 'application/json',
	 	         data: selectedEmpId,
	 	         success: function(data) {
	 	             alert("Delete Successfully");
	 	         }
	 		 });
	       } 
	     });
		  
	});
	
		
	$('body').on('click', 'a#btnModifyDoc', function() {
		var isOk = false;
		$('#tbDoclist tbody tr').each(function() {
	       if ( $(this).hasClass('highlight') ) {
	    	   isOk = true;
	    	   copyDocumentData($(this));
	    	   $('#modalAddDocument').modal(options);
	    	   $('#modalAddDocument').find('.modal-title').text('Modify Document');
	           return;
	       }
	     });
		if (!isOk) {
			$('#modalMessage').modal(options);
		}
	});
	
	$('body').on('click', 'a#btnAddEmp', function() {
	   $('#modalAddDocument').modal(options);
 	   $('#modalAddDocument').find('.modal-title').text('Add Document');
	});
	
	function copyDocumentData(row) {
		var values = row.find("td");
		
	    $("input#textEmpId").val(values[0].innerText);
	    $("input#textName").val(values[1].innerText);
	    $("input#textStartdate").val(values[2].innerText);
		
        	        
	}
	
		
	$('body').on('click', '#btnSaveChanges', function() {
		$('#formAddDocument').submit();
	});
	
	$('body').on('click', 'a#btnimport', function() {
		var filePath = $("#filePath").val();
		
		$('#importInfoForm').submit();
		
	});
	
	$('body').on('click', 'a#btnimportX', function() {
		
		$('#importInfoForm').action = '/gpm/document/extractdata';
		$('#importInfoForm').submit();
		
	});
	
	
	$('body').on('click', 'a#btnexportcontract', function() {
		var empId = "JM1";
		 $.ajax({
	         url: '/gpm/document/exportContract',
	         type: 'GET',
	         data: empId,
	         dataType: 'text',	         
	         success: function(data) {
	             //alert(data);
	        	 //alert("Successfully");
	         }
		 });
		
	});
	
	$('#tbemplist').on('click', 'tbody tr', function(event) {
		
		if ( $(this).hasClass('highlight') ) {
            $(this).removeClass('highlight');
        } else {
			$(this).addClass('highlight').siblings().removeClass('highlight');
		}
	    
	});
	
    // The options used for the login/register modal

/*	
    function copyShippingForm() {
        $('.cloneable').each(function() {
            $("#billing_info input[name='" + $(this).attr('name') + "']").val($(this).val()).attr("disabled", "disabled");
            $("#billing_info select[name='" + $(this).attr('name') + "']").val($(this).val()).attr("disabled", "disabled");
        })
    }*/

/*    var updateState = function(newState,$context){
        $context.find("input[name='address.state']").val(newState);
    }
*/
	/*$('body').on('click', 'a#btn-import-employee', function() {
		
		var filePath = $("#text-file-path").val();
		console.log("filename ",filename);
		
		 $.ajax({
             url: '/gpm/employee/importexcel',
             type: 'post',
             dataType: 'json',
             data: filename,
             success: function(data) {
                 
             }
		 });
		
    });*/
	
	/*$('#btn-import-employee').submit(function( event ) {
	  
		var filename = $("#text-file-path").val();
		console.log("filename ",filename);
		
		alert( "Handler for .submit() called." );
	  event.preventDefault();	  
	});*/
   
});
