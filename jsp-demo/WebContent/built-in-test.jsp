<html>
	<head>
	JSP BUILT-IN OBJECTS
	</head>
	
	<body>
	
	<br/><br/>
	Request user agent: <%= request.getHeader("User-Agent") %>
	
	<br/><br/>
	
	Request language : <%= request.getLocale() %>
	
	</body>
</html>