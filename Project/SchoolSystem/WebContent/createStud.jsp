<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page import="sheridan.*, java.util.*" language="java"
	import="java.util.*" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" type="text/css" href="style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Create Student</title>
</head>
<body>
<form name="studForm" action="Regservlet" method="post">
<input type="hidden" name="nameOfForm" value="studForm" />
	First Name:
	<input type="text" name="firstName"  value="${stud.getFirstName()}"/>
	<span class="error">${errors.firstNError}</span>
	<br /> Last Name:
	<input type="text" name="lastName" value="${stud.getLastName()}"/>
	<span class="error">${errors.lastNError}</span>
	<br /> Email:
	<input type="text" name="email" value="${stud.getEmail()}"/>
	<span class="error">${errors.emailError}</span>
	<br /> Username:
	<input type="text" name="user" value="${stud.getUser()}"/>
	<span class="error">${errors.userError}</span>
	<br /> Password:
	<input type="password" name="pass" value="${stud.getPass()}"/>
	<br /> Retype Password:
	<input type="password" name="repass" />
	<span class="error">${errors.repassError}</span>
	<br /> Program:
	<select name="programs" id="program" onchange="document.studForm.submit()">
		<option>Select a program...</option>
		<option ${prog1}>1. Computer Programmer</option>
		<option ${prog2}>2. Animal Care</option>
	</select>
	<span class="error">${errors.progError}</span>
	<br />
	<u>Courses</u>
	<br />
	<%
		ArrayList<Course> courses = (ArrayList<Course>)session.getAttribute("courses");
		if(null != courses)
		{
			for(Course c : courses)
			{
				out.println(c.getCourseCode()+" - "+c.getCourseName()+" - "+c.getProfName()+"<br/>");
			}
		}
	%>

	<br />
	
		<input type="submit" name="createStud" value="Create Student" />
	</form>
</body>
</html>