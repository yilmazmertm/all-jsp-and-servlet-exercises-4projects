<%@ taglib uri = "http://java.sun.com/jsp/jstl/core"  prefix = "c" %>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions"  prefix = "fn" %>

<html>
<body>

<c:set var= "data" value = "Istanbul, Ankara, Izmir"></c:set>

<h3> Split </h3>

<c:set var = "cities" value = "${fn:split(data, ',')}"></c:set>

<c:forEach var ="tempCity" items ="${cities}">
	${tempCity } <br/>
</c:forEach>

<h3> Join Demo </h3>
	<c:set var = "fun" value = "${fn:join(cities, '*')}"></c:set>
	Result of Joining :${fun}
</body>
</html>