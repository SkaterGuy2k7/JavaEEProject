<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page import="sheridan.*, java.util.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Professor View</title>
<script>
	function display(courseName) {					
		document.getElementByName("material").style.display = 'none';
		document.getElementById(courseName).style.display = 'block';		
	}
</script>
</head>
<body>
	<span class="prof">Name: ${professor.getFirstName()} ${professor.getLastName()}</span><br/>
	<span class="prof">ID: ${professor.getId()}</span><br/>
	<u>Courses</u>
	<table>		
		
		<tr>			
			<th>Course ID</th>
			<th>Course Name</th>
			<th>Course Time</th>
			<th>Room Number</th>
		</tr>
	</table>	
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
						out.println("<div name=\"material\" id='course"+c.getCourseId()+">Type: "+m.getMatType()+"&nbsp");
						out.println("Weight: "+m.getMatWeight()+"</div>");												
					}
				}				
			}
		}
	%>	
	<input type='submit' name='materialPage' value='Add/Edit Material'/>
	<input type='submit' name='addWeight' value='Add/Edit Weight'/>
	</form>
</body>

</html>