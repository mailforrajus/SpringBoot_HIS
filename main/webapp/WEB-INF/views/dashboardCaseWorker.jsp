<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="caseWorkerNav.jsp"></jsp:include>
<head>
<script src="<c:url value="/resources/js/login.js" />"></script>
</head> 
<body>
<div id="registration">
<div id="product">
<div class="container my_course_red">
<div class="container">

<!-- Form Name -->
<form:form action="login" method="post" modelAttribute="userLogin" id="userRegForm" name="userRegForm" style="max-width:500px;margin:auto">
<h1 class="text-center" style="color:red">Under Development
</h1>
</form:form>
</div>
  </div>
  </div>
  </div>
</body>
<jsp:include page="footer.jsp"></jsp:include>