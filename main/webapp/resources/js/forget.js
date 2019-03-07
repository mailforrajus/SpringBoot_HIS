$(document).ready(function() {
	
	
	
	$('form[id="forgetUserForm"]').validate({
		rules : {
		       
			email : {
				required : true,
				email : true,
			}
		},
		messages : {
			
			email : 'Please enter a valid email'
		},
		submitHandler : function(form) {
			form.submit();
		}
	});
	
	
	
	
	$('#userSaveBtn').click(function(e) {
		var email = $("#email").val();
		$.ajax({
			url :window.location+"/pass",
			data : {
				email : email
					},
					cache : false,
			dataType : 'json',
			success : function(result) {
				$("#resultAll").html("<h5 style='color:green'>Your Password has been sent registered Email-Id.</h5>");
							
			
			},
			error : function(e) {
				
				$("#resultAll").html("<h5 style='color:red'>Invalid Credentials!!</h5>");

			}
			});
		
		});
	});
	