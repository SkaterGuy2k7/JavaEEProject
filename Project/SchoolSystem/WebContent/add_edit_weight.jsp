<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page import="sheridan.*, java.util.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Add/Edit Weight</title>
</head>
<script>
	function fillMats(){		
		var course = document.getElementById("coursesList").value;
		document.location.href="http://localhost:8080/SchoolSystem/Regservlet?courseName="+course+"";
	}
</script>
<body>
	<form action="Regservlet" method="post">
		<table>
			<tr>
			<th>Courses</th>
			</tr>
			<tr>
				<td>Course Name: </td>
				<td>				
					<select id="coursesList" name="courses" onchange="fillMats()">
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
				<td>Material: </td>
				<td>
					<select id="matList" name="courses">
							<% 
							ArrayList<Material> matList = (ArrayList<Material>) session.getAttribute("materials");						
							
							if (null != matList) {
								for (Material m : matList) {				
									out.println("<option name=\" "+m.getMatId()+" \">"+m.getMatName()+"</option>");								
								}
							}
							%>
					</select>
				</td>
			</tr>
			<tr>
				<td>Material Weight: </td>
				<td><input type="text" name="editWeight"/></td>
			</tr>
		</table>
		<input type="submit" name="addWeight" value="Add/Edit Weight"/>
	</form>
</body>
</html>