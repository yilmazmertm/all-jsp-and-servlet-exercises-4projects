<html>
<head>
<title>Student Confirmation </title>

</head>

<body>
	The student is confirmed, name and surname: ${param.firstName } ${param.lastName }
	
	<br/><br/>
	
	<ul>
		<%
		String[] langs = request.getParameterValues("favLang");
		
		if (langs != null) {
            for (String tempLang : langs) {
                out.println("<li>" + tempLang + "</li>");
            }
        }else {
        	out.println("Please choose a valid lang.");
        }
		%>
	
	</ul>
	
</body>


</html>