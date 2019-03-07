<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>jQuery UI Datepicker - Default functionality</title>
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<link rel="stylesheet" href="/resources/demos/style.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
	$(function() {
		$("#date").datepicker();
	});
</script>
</head>
<body>

	${msg}
	<br>
	<table>
		<form:form action="register" method="post" modelAttribute="um">
			<tr>
				<td>First Name ::</td>
				<td><form:input path="firstName" /></td>
			</tr>
			<tr>
				<td>Last Name ::</td>
				<td><form:input path="lastName" /></td>
			</tr>
			<tr>
				<td>DOB ::</td>
				<td><form:input path="dob" id="date" /></td>
			</tr>

			<tr>
				<td>SSN No. ::</td>
				<td><form:input path="ssnNo" /></td>
			</tr>
			<tr>
				<td><input type="submit" value="register"></td>
			</tr>
		</form:form>
	</table>

</body>
</html>