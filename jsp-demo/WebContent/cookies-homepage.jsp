<html>
<body>
	<% 
		String favLang = "1";
		
		Cookie[] theCookies = request.getCookies();
		
		if(theCookies != null) {
			for (Cookie tempCookie: theCookies) {
				if ("myApp".equals(tempCookie.getName())) {
					favLang = tempCookie.getValue();
					break;
				}
			}
		}
	%>
	
	<h4> Your number is : <%= favLang %></h4>

</body>
</html>