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
</head>

<div class="container my_menu">
<div class="row ">
<nav class="navbar navbar-default"> 
<div class="navbar-header"> 
<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#mynav">
<span class="icon-bar"></span>
<span class="icon-bar"></span>
<span class="icon-bar"></span>
</button>
</div>
<c:url var="url_logout" value="/logout"/>
<div class="collapse navbar-collapse" id="mynav">
<ul class="nav navbar-nav">
<li><a href="index">Home</a></li>
<li><a href="#services">Services</a></li>
<li class="dropdown"><a  class="dropdown-toggle" data-toggle="dropdown"href="#">Forget Password<span class="caret"></span></a>
<ul class="dropdown-menu drop">
          <li><a href="forgetPass">Forget Password</a></li>
          <!-- <li><a href="newPassUpdate">Forget Password By New Update</a></li> -->
        </ul>
         
<li><a href="#services">Blog</a></li>
<li><a href="#about">About Us</a></li>
<li><a href="#contact">Contact Us</a></li>
</ul>
</div>
</nav>
</div>
</div>

</body>
</html>
