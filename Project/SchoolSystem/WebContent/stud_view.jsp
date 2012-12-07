<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page import="sheridan.*, java.util.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" type="text/css" href="style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Student View</title>
<script>
	function display1() {
		document.getElementById('course1').style.display = 'block';
		document.getElementById('course2').style.display = 'none';
		document.getElementById('course3').style.display = 'none';
		document.getElementById('course4').style.display = 'none';
		document.getElementById('course5').style.display = 'none';
	}
</script>
</head>
<body>
	<span class="student">Name: ${student.getFirstName()} ${s.getLastName()}</span><br/>
	<span class="student">ID: ${student.getStudid()}</span><br/>
	<u>Courses</u>
	<form action='RegServlet' method='post'>
	<%
		ArrayList<Course> courseList = (ArrayList<Course>) session.getAttribute("courses");
		ArrayList<Material> matList = (ArrayList<Material>) session.getAttribute("materials");		
		
		if (null != courseList) {
			for (Course c : courseList) {				
				out.println("<div><a href='#' onclick=\"display('course"+c.getCourseId()+"')\">"+c.getCourseId()+"</a>&nbsp;");
				out.println(""+c.getCourseName()+"&nbsp;");
				out.println(""+c.getCourseTime()+"&nbsp;");
				out.println(""+c.getRoomNum()+"&nbsp;</div>");
				if (null != matList) {					
					for (Material m : matList) {
						double gradePercentage = Double.valueOf(m.getGrade()) / Double.valueOf(m.getMatWeight()) *100;
						out.println("<div name=\"material\" id='course"+c.getCourseId()+">Type: "+m.getMatType()+"&nbsp");
						out.println("Grade: "+gradePercentage+"%</div>");												
					}
				}				
			}
		}
	%>		
	<br />
	<input type='submit' name='addGrade' value='Add/Edit Grade'/>
	</form>
</body>
</html>