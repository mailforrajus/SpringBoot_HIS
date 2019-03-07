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
<div class="row">
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
<li><a href="#services">Application Registration</a></li>
<li><a href="#services">Data Collection</a></li>
<li><a href="#services">Determine Eligible</a></li>
<li><a href="#services">correspondent</a></li>


<li class="dropdown"><a  class="dropdown-toggle" data-toggle="dropdown"href="#">Reports<span class="caret"></span></a>
<ul class="dropdown-menu drop">
          <li><a href="#">Generate Report</a></li>
          </ul></li>

<li class="dropdown"><a  class="dropdown-toggle" data-toggle="dropdown"href="#">Profile<span class="caret"></span></a>
<ul class="dropdown-menu drop">
          <li><a href="#">View Profile</a></li>
          <li><a href="#">Edit Profile</a></li>
          </ul></li>


<li class="dropdown"><a  class="dropdown-toggle" data-toggle="dropdown"href="#">Admin<span class="caret"></span></a>
<ul class="dropdown-menu drop">
          <li><a href="userReg">Create Case Worker</a></li>
          <li><a href="listCaseWorker?cpn=1">View Case Worker</a></li>
        <li><a href="viewCaseWorker">Case Worker(Table Data)</a></li> 
         </ul></li>
         
<li><a href="${url_logout}">LogOut</a></li>
</ul>
</div>
</nav>
</div>

</div>
</body>
</html>
