<%@ taglib uri = "http://java.sun.com/jsp/jstl/core"  prefix = "c" %>

<html>
<body>
<%
	int[] primeNumbers = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47};

	pageContext.setAttribute("myPrimeNumbers", primeNumbers);

%>
	<c:forEach var= "tempPrime" items= "${myPrimeNumbers}">
	
		${tempPrime} <br/>
		
	</c:forEach>

</body>
</html>