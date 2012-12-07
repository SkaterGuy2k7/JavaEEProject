<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page import="sheridan.*, java.util.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Add/Edit Grade</title>
<script>

function fillMats(){
		document.location.href = "http://localhost:8080/SchoolSystem/Regservlet?method=addGrade_fillMats";
}
</script>
</head>
<body>
<u>Add of Edit a Grade</u><br/><br/>
		<select name="courses" onchange="fillMats()">
		<option>Select a course...</option>
		<%
		ArrayList<Course> courses = (ArrayList<Course>) session.getAttribute("courses");
		if(null != courses)
		{
			for(Course c: courses)
			{
				out.println("<option>"+c.getCourseName()+"</option>");
			}
		}
		out.println("</select><br/>");
		%>
</select>
<select name="materials" onchange="">
		
</select>
</body>
</html>