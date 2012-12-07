<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" import="java.util.*" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Create Student</title>
<script>
//cache the select and span elements
var mySelect = document.getElementsByName("program");
var courses = mySelect.options[mySelect.selectedIndex].text;

mySelect.onChange = function() {
	   <% System.out.println("In ");%>	   
       document.location.href = "http://localhost:8080/SchoolSystem/Regservlet?program="+ courses;       
}
</script>
</head>
<body>
First Name:<input type="text" name="firstName" /><span class="error">${errors.firstNError}</span><br/>
Last Name:<input type="text" name="lastName" /><span class="error">${errors.lastNError}</span><br/>
Email:<input type="text" name="email" /><span class="error">${errors.emailError}</span><br/>
Username:<input type="text" name="user" /><span class="error">${errors.userError}</span><br/>
Password:<input type="password" name="pass" /><span class="error">${errors.passError}</span><br/>
Program:<select name="program">
<option>Select a program...</option>
<option>Computer Programmer</option>
<option>Animal Care</option>
</select><span class="error">${errors.passError}</span><br/>
<u>Courses</u><br/>

</body>
</html>