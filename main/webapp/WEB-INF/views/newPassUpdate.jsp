<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="header.jsp"/>
<jsp:include page="simpleNav.jsp"/>
<head>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<script>

$(document).ready(function()
		{
	$(window).load(function() {
		$("#add_fields_placeholderValue").hide();
		        
		    });
		});


$(function() {
		
		$('form[id="userRegForm"]').validate({
			rules : {
				email : {
					required : true,
					email : true,
				}
			},
			messages : {
				email : 'Please enter a valid email',
				
			},
			submitHandler : function(form) {
				form.submit();
			}
		});
		

		$("#userSaveBtn").click(function() {
			var enteredEmail = $("#email").val();
			$.ajax({
				url : window.location + "/pass",
				data : "email=" + enteredEmail,
				success : function(result) {
					$("#resultAll").html("<h5 style='color:green'>Your UserName has been search successfully</h5>");
					$("#add_fields_placeholderValue").show();
					$("#hide_fields_placeholderValue").hide();
				       
					   
				            
				        
					},
			
			error : function(e) {
				
				$("#resultAll").html("<h5 style='color:red'>Your UserName has no search successfully!</h5>");
				
				$("#add_fields_placeholderValue").hide();
		        

				}
			});

		});

		
	});
	

</script>
              
</head>
<body>
<div id="registration">
<div id="product">
<div class="container my_course_red">
<div class="container">

<form:form action="newPassUpdate" method="post" modelAttribute="newPass" id="userRegForm" name="forgetUserForm" style="max-width:500px;margin:auto">

<div id="hide_fields_placeholderValue">

<h4 class="text-center" style="color:#ff0000; border-radius: 5px;">Registered Email-id of Case Worker</h4>
<br/><br/>
  <div class="text-center" id="resultAll"></div>
  <div class="input-container">
    <i class="fa fa-user icon"></i>
    <form:input  class="input-login" path="email" id="email" name="email" placeholder="Enter UserName"/>
    </div>
    <input type="button"  id="userSaveBtn" value="Search" class="btn2" onclick="validation(this);" />
    
    
  
  </div>
  
    <div id="add_fields_placeholderValue">
    <h4 class="text-center" style="color:#ff0000; border-radius: 5px;">New Password And Confirm Password of Case Worker</h4>
     <div class="input-container">
  
    <i class="fa fa-lock icon"></i>
    <form:password  class="input-login" path="pwd" id="pass" name="pass" placeholder="Enter New Password"/>
     </div>
     
     <div class="input-container">
    <i class="fa fa-lock icon"></i>
    <form:password  class="input-login" path="pwd" id="cpass" name="pass" placeholder="Enter Confirm Password"/> 
     </div>
     
    <input type="button"  id="userSaveBtn" value="Update" class="btn2" onclick="validation(this);" />
    
    
    </div>
   </form:form>
</div>
</div >
</div>
</div>
</body>
<jsp:include page="footer.jsp"></jsp:include>