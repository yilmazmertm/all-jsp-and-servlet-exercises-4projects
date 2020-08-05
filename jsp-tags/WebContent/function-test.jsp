<%@ taglib uri = "http://java.sun.com/jsp/jstl/core"  prefix = "c" %>

<%@ taglib uri = "http://java.sun.com/jsp/jstl/functions"  prefix = "fn" %>

<html>
<body>

<c:set var= "data" value = "randomValue"></c:set>
Length of the String <b> ${data} </b>: ${fn:length(data)} <br/><br/>

Uppercase version of <b> ${data} </b>: ${fn:toUpperCase(data)} <br/><br/>

Does the string <b> ${data} </b>stars with : <b>lu</b> ? Answer:  ${fn:startsWith(data, "lu")} <br/><br/>


</body>
</html>

