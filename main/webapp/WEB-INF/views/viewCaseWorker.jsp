<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:include page="header.jsp"></jsp:include>
<jsp:include page="adminNav.jsp"/>
<head>
<link rel="stylesheet" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css">
<script src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
 
<script>
$(document).ready(function() {
    $('#Tabledata').DataTable( {
        "pagingType": "full_numbers"
    } );
} );
	function confirmDelete() {
		var status = confirm("Are you sure, you want to Delete?");
		if (status) {
			return true;
		} else {
			return false;
		}
	}
	function confirmActivate() {
		var status = confirm("Are you sure, you want to Activate?");
		if (status) {
			return true;
		} else {
			return false;
		}
	}
</script>

</head> 
<body>
<div id="registration">
<div id="product">
<div class="container my_course_red">

<div class="container">

<!-- Form Name -->
<form:form action="viewCaseWorker" method="post" modelAttribute="viewCaseWorker" id="userRegForm" name="userRegForm" style="max-width:1000px;margin:auto">
  
<c:if test="${result ne null}">
 <h5 class="text-right" style="color:red">Hi,${result}<br>
</h5>
</c:if>
<h4 class="text-center" style="color:#ff0000;background:#00ffbf;border-radius: 5px;">Case Worker Records in Table Data</h4>
<c:if test="${caseWorkers eq null }">
<h3 style="color:red;text-align:center">No Records Found</h3>
</c:if>
<div style="overflow-x:auto;">
  <table class="table" id="Tabledata" style="background-color:white;">
  
    <thead>
      <tr>
      <th>S.No</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Email</th>
          <th>Phone No</th>
            <th>DOB</th>
              <th>CreatedBy</th>
                <!-- <th>Status</th> -->
              <th>Edit</th>
              <th>Actions</th>
      </tr>
     
    </thead>
    <tbody>
    <c:forEach items="${caseWorkers}" var="cw" varStatus="index">
      <tr>
				<td><c:out value="${index.count}" /></td>
					<td><c:out value="${cw.firstName }" /></td>
					<td><c:out value="${cw.lastName }" /></td>
					<td><c:out value="${cw.email }" /></td>
					<td><c:out value="${cw.phno }" /></td>
					<td><c:out value="${cw.dob }" /></td>
					<td><c:out value="${cw.role }" /></td>
					
	<td><a href="editCW?uid=${cw.userId}" title="Edit" data-toggle="tooltip"><span class="glyphicon glyphicon-pencil"></span></a></td>
      <td>
       <c:if test="${cw.activeSw=='Y'}">
<a href="deleteCwProfile?uid=${cw.userId}" onclick="return confirmDelete()" title="Delete" data-toggle="tooltip"><span class="glyphicon glyphicon-trash"></span></a>
						</c:if> 
						<c:if test="${cw.activeSw=='N'}">
							<a href="activateCwProfile?uid=${cw.userId}" onclick="return confirmActivate()" title="Activate" data-toggle="tooltip"><span class="glyphicon glyphicon-sort"></span></a>
									
						</c:if>
						</td>
			</c:forEach>
         </tbody> </table>
        

</div>
</form:form>
</div>
  </div>
  </div>
  </div>
</body>
<jsp:include page="footer.jsp"></jsp:include>