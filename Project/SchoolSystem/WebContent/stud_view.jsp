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
	function display(course) {
		if(document.getElementById("mat"+course).style.display == 'block'){
			document.getElementById("mat"+course).style.display = 'none'
		} else {
			document.getElementById("mat"+course).style.display = 'block'
		}
		
		//alert(document.getElementById("mat"+course));
	}
</script>
</head>
<body>
	Name: ${student.getFirstName()} ${s.getLastName()} <br/>
	ID: ${student.getStudid()}<br/>
	<u>Courses</u>
	<form action='RegServlet' method='post'>
	<%
		ArrayList<Course> courseList = (ArrayList<Course>) session.getAttribute("courses");
		ArrayList<Material> matList = (ArrayList<Material>) session.getAttribute("materials");		
		
		if (null != courseList) {
			for (Course c : courseList) {				
				out.println("<div><a href='#' onclick=\"display("+c.getCourseId()+")\">"+c.getCourseCode()+"</a> - ");
				out.println(c.getCourseName()+" - "+c.getProfName());
				
				if (null != matList) {
					out.println("<div name=\"material\" id=\"mat"+c.getCourseId()+"\" >");
					for (Material m : matList) {
						if(c.getCourseId() == m.getCourseId())
						{
							//double gradePercentage = Double.valueOf(m.getGrade()) / Double.valueOf(m.getMatWeight()) *100;
							
							out.print("Type: "+m.getMatType()+" - "+m.getMatName()+"<br/>");
							if(null != m.getGrade()){
								out.println("Grade: "+m.getGrade()+"</div>");	
							} else {
								
							}
							
						}						
					}
				}		
				System.out.println("boomkin");
				//end material div
				out.println("</div>");
				//end course div
				out.println("</div>");
			}
		}
	%>		
	<br />
	<input type='submit' name='addGrade' value='Add/Edit Grade'/>
	</form>
	
	<script>
	var mats = document.getElementsByName("material");
	
	var length = mats.length;

    for (var i = 0; i < length; i++)
    {
        mats[i].style.display='none';
    }
    </script>
</body>
</html>