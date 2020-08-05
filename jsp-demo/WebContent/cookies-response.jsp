<html>
<head> 
	<title> Confirmation </title>
</head>

<body>
	<%
		String favLang = request.getParameter("favLang");
	
		Cookie theCookie = new Cookie("myApp", favLang);
		
		theCookie.setMaxAge(60*60*24*1);
		
		response.addCookie(theCookie);
	%>
	
	The number ${param.favLang} is recorded.
	<br/><br/>
	<a href= "cookies-form.html"> Return to set a new number </a>

</body>
</html>