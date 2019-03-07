<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="header.jsp"/>
<jsp:include page="simpleNav.jsp"/>

<head>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<script>
$(function() {
   $("#error_email").hide();
   var e_email=false;
   $("#email").focusout(function()
		   {
	  check_email();
		   });
   function check_email()
   {
	   var email=$("#email").val().length;
	   if(email<5)
		   {
		   $("#error_email").html("Email id is Required");
		   $("#error_email").show();
		  e_email=true;
		   }else{
			   $("#error_email").hide();
		   }
          }
       $("#userRegForm").submit(function()
    		   {
    	   e_email=false;
    	   check_email();
    	   if(e_email==false)
    		   {
    		   return true;
    		   }else{
    			   return false;
    		   }
    		   });
		});
		

</script>
              
</head>
<body>
<div id="registration">
<div id="product">
<div class="container my_course_red">
<div class="container">
<form action="forgetPass"  method="post" id="userRegForm" name="forgetUserForm" style="max-width:500px;margin:auto">
<h4 class="text-center" style="color:#ff0000;background:#00ffbf; border-radius: 5px;">Registered Email of Case Worker</h4>
<br/>
<div class="text-center" style="color:#ff0000;">${ERROR}</div>
<div class="text-center" style="color:green;">${SEND}</div>
<div class="text-center" style="color:green;">${SUCCESS}</div>
<br/><div class="form-group">
  <span class="text-center" style="color:red;" id="error_email"></span>
<input class="form-control text-center" type="email" id="email" name="email" placeholder="Username" title="Enter Username" data-toggle="tooltip"/>
 </div>
 
<input type="submit"  id="userSaveBtn" value="Submit" class="btn btn-success btn-block" onclick="validation(this);" />
    
    
   </form>
</div>
</div>
</div>
</div>
</body>
<jsp:include page="footer.jsp"></jsp:include>