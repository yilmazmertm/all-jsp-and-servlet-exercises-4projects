<%@ page import ="java.util.*" %>

<html>
<body>

<form action= "todolist-demo.jsp">
	Add New Item : <input type= "text" name= "theItem">
	
	<input type= "submit" value= "Submit">
</form>

<br/><br/>
<%
List<String> items = (List<String>) session.getAttribute("myToDoList");
if(request.getParameter("theItem") == null){
	out.println("Please Enter an item");
	out.println(" ");
} else {
	if (items == null) {
		items = new ArrayList<String>();
		session.setAttribute("myToDoList", items);
	}
	String theItem = request.getParameter("theItem");
	if (theItem != null) {
		items.add(theItem);
	}
}
%>
<br/><br/>
<b>To Do List Items: </b>
<%
	if (items == null) {
		out.println("Your List is empty");
	}else {
		for (String temp: items) {
			out.println("<li>" + temp + "</li>");
		}	
	}
%>
</body>
</html>









