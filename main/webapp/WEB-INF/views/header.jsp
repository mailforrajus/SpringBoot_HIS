<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@taglib uri="http://www.springframework.org/tags" prefix="s"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-compatible" content="IE-edge">
<meta name="viewport" content="width=device-width">
 <meta name="viewport" content="width=device-width, initial-scale=1">
<title>|home|</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">

<style>
body{
/*background-image:url(resources/images/img.gif);*/
background:radial-gradient(circle,#009cde,#FFF9F9);
}

</style>
<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
<link href="<c:url value="/resources/css/mystyle.css" />" rel="stylesheet">
 <link href="<c:url value="/resources/css/animate.css" />" rel="stylesheet"> 

<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
 
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>

<script type="text/javascript">
$(document).ready(function()
{
$('[data-toggle="tooltip"]').tooltip();
});	
</script>
              
</head>
<body>
<!--header-->
<!-- <div class="container-flud top-bar"> -->
<div class="container top-bar">
<div class="row">
<div class="col-sm-3 toogle info_toogle text-left">
<a href="https://www.facebook.com/" data-toggle="tooltip" data-placement="bottom" title="Facebook" class="social" target="_blank"><i class="fa fa-facebook" style="font-size:17px;color:white"></i></a>
<a href="https://twitter.com/" data-toggle="tooltip" data-placement="bottom" title="Twitter" class="social" target="_blank"><i class="fa fa-twitter" style="font-size:17px;color:white"></i></a>
<a href="https://www.youtube.com" data-toggle="tooltip" data-placement="bottom" title="Youtube" class="social" target="_blank"><i class="fa fa-youtube-play" style="font-size:17px;color:white"></i></a>
<a href="https://plus.google.com" data-toggle="tooltip" data-placement="bottom" title="Google+" class="social" target="_blank"><i class="fa fa-google-plus" style="font-size:17px;color:white"></i></a>

</div>
<div class="col-sm-9 info text-right">
<span class="glyphicon glyphicon-envelope"></span>
nitishsmithcomputer@gmail.com
<span class="glyphicon glyphicon-earphone"></span>
+919031541640
</div>
</div>
</div>



<!-- log and web name -->

<div class="container top-bar1">
<div class="row">
<div class="col-sm-1">
<img src="./resources/images/logos.png" class="img-responsive logo1">
</div>
<div class="col-sm-11">
 <div  class="companyname"><marquee behavior="alternate">RHODE ISLAND HEALTH INSURANCE SYSTEM TELENGANA</marquee></div> 
</div>
</div>
</div>

</body>
</html>
