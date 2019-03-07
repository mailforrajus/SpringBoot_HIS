<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="com.his.util.AppConstants" %>
 <jsp:include page="header.jsp"></jsp:include>
 <jsp:include page="adminNav.jsp"/>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>|userReg|</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
 
<script>
	$(function() {
		
		$('form[id="userRegForm1"]').validate({
			rules : {
				firstName : 'required',
				lastName : 'required',
				email : {
					required : true,
					email : true,
				},
				pwd : {
					required : true,
					minlength : 5,
				},
				dob:'required',
				role:'required',
				phno:'required'
			},
			messages : {
				firstName : 'Please enter first name',
				lastName : 'please enter last name',
				email : 'Please enter a valid email',
				pwd : {
					required :'Please enter password',
					minlength : 'Password must be at least 5 characters long'
				},
				dob:'Please select dob',
				role:'Please select a role',
				phno:'Please enter Phno'
			},
			submitHandler : function(form) {
				form.submit();
			}
		});
		

		$("#email").blur(function() {
			var enteredEmail = $("#email").val();
			$.ajax({
				url : window.location + "/checkEmailUpdate",
				data : "email=" + enteredEmail,
				success : function(result) {

					if (result == 'DUPLICATE') {
						$("#emailMsg").html("Email already registered.!!");
						$("#email").focus();
					} else {
						$("#emailMsg").html("");
					}

				}
			});

		});

		$("#datepicker").datepicker({
			changeMonth : true,
			changeYear : true,
			dateFormat : 'dd/mm/yy',
			maxDate : new Date()
		});
	});
</script>
</head>
<body>
<div class="container my_course_red">
<form:form action="editCaseWorker" method="POST" name="regUserForm"  id="userRegForm1" modelAttribute="editCW" class="form-horizontal" style="max-width:400px;margin:auto">
<h4 class="text-center" style="color:#ff0000;background:#00ffbf; border-radius: 5px;">Update Case Worker Informations</h4>
 <div class="text-center" style="color:green">${SUCCESS}</div>
<div class="text-class" style="color:red">${ERROR}</div>
 <br/>

 <div class="form-group">
      <label class="col-sm-3 control-label">UserId:</label>
      <div class="col-sm-9">
        <form:input path="userId" class="form-control" readonly="true"/>
      </div>
    </div>
    
    <div class="form-group">
      <label class="col-sm-3 control-label">First Name:</label>
      <div class="col-sm-9">
        <form:input path="firstName" class="form-control" readonly="false"/>
      </div>
    </div>
    <form:hidden path="createdDate"/>
    <form:hidden path="updatedDate"/>
    <form:hidden path="createdBy"/>
   <form:hidden path="activeSw"/>
    
    <div class="form-group">
      <label class="col-sm-3 control-label">Last Name:</label>
      <div class="col-sm-9">
        <form:input path="lastName" class="form-control" readonly="false"/>
      </div>
    </div>
    
    <div class="form-group">
      <label class="col-sm-3 control-label">Email:</label>
      <div class="col-sm-9">
        <form:input path="email" class="form-control" readonly="false" id="email"/>
        <span style="color:red" id="emailMsg"></span>
      </div>
    </div>
    
    <div class="form-group">
      <label class="col-sm-3 control-label">Password:</label>
      <div class="col-sm-9">
        <form:input path="pwd" class="form-control" readonly="false"/>
      </div>
    </div>
    
    <div class="form-group">
      <label class="col-sm-3 control-label">Phone No:</label>
      <div class="col-sm-9">
        <form:input path="phno" class="form-control" readonly="false"/>
      </div>
    </div>
    
    <div class="form-group">
      <label class="col-sm-3 control-label">DOB:</label>
      <div class="col-sm-9">
        <form:input path="dob" class="form-control" readonly="false" id="datepicker"/>
      </div>
    </div>
    
    <div class="form-group">
      <label class="col-sm-3 control-label">Role:</label>
      <div class="col-sm-9">
        <form:select class="form-control"  items="${rols}" path="role">
  </form:select>
      </div>
    </div>
 
 
 <input type="reset" class="btn2"/>
<input type="submit"  id="userSaveBtn" value="Update" class="btn2" onclick="validation(this);" />
<br/>
 

</form:form>

</div>
</body>
 <jsp:include page="footer.jsp"></jsp:include> 
</html>