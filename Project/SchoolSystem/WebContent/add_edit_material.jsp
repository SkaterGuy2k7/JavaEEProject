<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page import="sheridan.*, java.util.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Add/Edit Material</title>
</head>
<body>
	<form name="matForm" action="Regservlet" method="post">
	<input type="hidden" name="nameOfForm" value="matForm" />
		<table>
			<tr>
			<th>Courses</th>
			</tr>
			<tr>
				<td>Course Name: </td>
				<td>
					<select name="courseList" onchange="document.matForm.submit()">
						<% 
						ArrayList<Course> courseList = (ArrayList<Course>) session.getAttribute("courses");							
						
						if (null != courseList) {
							for (Course c : courseList) {				
								out.println("<option name=\" "+c.getCourseCode()+" \">"+c.getCourseName()+"</option>");								
							}
						}
						%>
					</select>
				</td>
			</tr>
			<tr>
				<td>Material Name: </td>
				<td><input type="text" name="newName"/></td>
			</tr>
			<tr>
				<td>Material Type: </td>
				<td>
					<select name="newType">
						<option name="assignType">Assignment</option>
						<option name="testType">Test</option>
					</select>
				</td>
			</tr>
			<tr>
				<td>Material Weight: </td>
				<td><input type="text" name="newWeight"/></td>
			</tr>
		</table>
		<input type="submit" name="addMaterial" value="Add/Edit Material"/>
	</form>

</body>
</html>