<%@ taglib uri = "http://java.sun.com/jsp/jstl/core"  prefix = "c" %>

<html>
<body>

<c:set var = "varName" value="<%= new java.util.Date() %>" />

Time on the server : ${ varName }


</body>
</html>