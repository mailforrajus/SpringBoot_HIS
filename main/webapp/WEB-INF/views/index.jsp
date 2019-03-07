<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <jsp:include page="header.jsp"/>
<jsp:include page="simpleNav.jsp"/>
<head>
<script src="<c:url value="/resources/js/wow.min.js" />"></script>
<script src="https://cdn.jsdelivr.net/jquery.validation/1.16.0/jquery.validate.min.js"></script>
<script>
	$(function() {
		
		$('form[id="userRegForm"]').validate({
			rules : {
				email : 'required',
				pwd : 'required',
				email : {
					required : true,
					email : true,
				},
				pwd : {
					required : true,
					minlength : 5,
				},
							},
		 	messages : {
				

				email : 'Please enter a valid email',
				pwd : {
					required :'Please enter password',
					minlength : 'Password must be at least 5 characters long'
				},
			},
 		submitHandler : function(form) {
				form.submit();
			}
		});
		

		
	});
</script>
</head>
 
<div class="container top-bar2">
<div class="row">
<div class="col-sm-7">
<div id="my_slide" class="carousel slide" data-ride="carousel" data-interval="4000">
<ol class="carousel-indicators">
<li data-target="#my_slide" data-slide-to="0" class="active"></li>
<li data-target="#my_slide" data-slide-to="1"></li>
<li data-target="#my_slide" data-slide-to="2"></li>
<li data-target="#my_slide" data-slide-to="3"></li>
<li data-target="#my_slide" data-slide-to="4"></li>
<li data-target="#my_slide" data-slide-to="5"></li>
<li data-target="#my_slide" data-slide-to="6"></li>
</ol>
<div class="carousel-inner">
<div class="item active">
<img src="./resources/images/main_top_bg.jpg" class="img-responsive"/>
<div class="carousel-caption wow bounceInRight" data-wow-duration="3s">
<b>Welcome To Health Insurance</b>
</div>
</div>
<div class="item">
<img src="./resources/images/1.jpg" class="img-responsive"/>
<div class="carousel-caption wow bounceInDown" data-wow-duration="3s">
<b>Health Insurance</b>
</div>
</div>

<div class="item">
<img src="./resources/images/img1.jpg" class="img-responsive"/>
<div class="carousel-caption wow bounceInLeft" data-wow-duration="3s">
<b>Welcome To Our Health Insurance</b>
</div>
</div>
<div class="item">
<img src="./resources/images/img2.jpg" class="img-responsive"/>
<div class="carousel-caption wow bounceInLeft" data-wow-duration="3s">
<b>Welcome To Our Health Insurance</b>
</div>
</div>
<div class="item">
<img src="./resources/images/img3.jpg" class="img-responsive"/>
<div class="carousel-caption wow bounceInLeft" data-wow-duration="3s">
<b>Welcome To Our Health Insurance</b>
</div>
</div>
<div class="item">
<img src="./resources/images/img4.jpg" class="img-responsive"/>
<div class="carousel-caption wow bounceInLeft" data-wow-duration="3s">
<b>Welcome To Our Health Insurance</b>
</div>
</div>
<div class="item">
<img src="./resources/images/img5.jpg" class="img-responsive"/>
<div class="carousel-caption wow bounceInLeft" data-wow-duration="3s">
<b>Welcome To Our Health Insurance</b>
</div>
</div>


</div>
<a class="left carousel-control" href="#my_slide" data-slide="prev"><span class="glyphicon glyphicon-chevron-left"></span></a>
<a class="right carousel-control" href="#my_slide" data-slide="next"><span class="glyphicon glyphicon-chevron-right"></span></a>
</div>
</div>

<div class="col-sm-5 col-sd-5 col-xs-12">
<form:form action="login" method="post" modelAttribute="login" id="userRegForm" name="userRegForm" 	>
<h5 class="text-center" style="color:white; font-weight: bolder;">Login Admin / Case Worker</h5>
<img class="img img-responsive img-circle" src="resources/images/login.gif">
<div class="text-center" id="resultAll"></div>
<div class="text-center" style="color:red;font-size:15px;font-weight: bold;">${ERROR}</div>
<c:if test="${param.ac eq 'lo'}">
<div class="text-center" style="color:#FF0000">
Logout Successfully! Thanks for using contact application.
</div>
</c:if>
<br>
 <div class="form-group">
  
    <form:input  class="form-control text-center" path="email" placeholder="Username" title="Enter Username" data-toggle="tooltip"/>
 </div>
 
<div class="form-group">
    
    <form:password  class="form-control text-center" path="pwd" placeholder="Password" title="Enter Password" data-toggle="tooltip"/>
    </div>
  
  <input type="submit"  id="userSaveBtn" value="Login" class="btn btn-success btn-block" onclick="validation(this);" />
<br>
<span class="login-style1"><a href="forgetPass" title="Send Registered Email" data-toggle="tooltip"><span style="color:white; font-weight: bolder;">Forget Password?</span></a></span>
</form:form>
</div>
</div>
</div>


<!-- -Login  closed -->

<jsp:include page="footer.jsp"></jsp:include> 
