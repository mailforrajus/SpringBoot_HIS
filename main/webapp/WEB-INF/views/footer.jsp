<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-compatible" content="IE-edge">
<meta name="viewport" content="width=device-width">
 <meta name="viewport" content="width=device-width, initial-scale=1">
<title>|home|</title>
<style>
body{
/*background-image:url(resources/images/img.gif);*/
background:radial-gradient(circle,#009cde,#FFF9F9);
}</style>

<script type="text/javascript">
$(document).ready(function()
{
$('[data-toggle="tooltip"]').tooltip();
});	
</script>
</head>
<body>
<!--footer-->

<div class="container footer">
<div class="row">
<div class="col-sm-2 logo2">
<img src="./resources/images/logos.png" class="img-responsive">
<p>Health Insurance
</p>
<p><a href="#" class="btn btn-danger">Read more...</a></p>
</div>

<div class="col-sm-2">
<h3>Services</h3>
<h5>Rhode Island Health Insurance
Rhode Island Health Insurance
Rhode Island Health Insurance
Rhode Island Health Insurance
</h5>
</div>

<div class="col-sm-3">
<div id="contact">
<h3>Contact Details</h3>
<p><strong>Address:Ameerpet Hyderabad</strong></p>
<p><strong>Email:nitishsmithcomputer@gmail</strong></p>
<p><img src="./resources/images/number.png">:<a href="tel:"><font style=color:white;>9934638540</font></a></p>
</div>
</div>

<div class="col-sm-4">
<div id="about">
<h3>About Us</h3>
<p><strong>Health Insurance System government of Telengana.</strong></p>
<p><strong>
Health Insurance government</strong></p>
<p><strong>
Health Insurance government</strong></p>


</div>
</div>

</div>
<div class="row">
<h5 class="text-center">

 Copyright &copy; Health Insurance All Rights Reserved.</h5>
</div>
</div>
<script src="<c:url value="/resources/js/wow.min.js" />"></script>
<script>
  new WOW().init();
 </script>
 </body>
</html>
