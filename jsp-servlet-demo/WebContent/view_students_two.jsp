<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix ="c"%>

<html>
<body>
	<h2>Student Table</h2> <br/>
	<table border = 2>
	<tr>
		<th>First Name</th>
		<th>Last Name</th>
		<th>Email</th>
	</tr>
	
	<c:forEach var="temp" items="${student_list}">
		<tr>
			<td>${temp.firstName}</td>
			<td>${temp.lastName}</td>
			<td>${temp.email}</td>
		</tr>

	</c:forEach>
	
	</table>
</body>
</html>

