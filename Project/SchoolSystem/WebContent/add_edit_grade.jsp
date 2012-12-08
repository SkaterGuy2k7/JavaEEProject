<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page import="sheridan.*, java.util.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Add/Edit Grade</title>
</head>
<body>
<u>Add of Edit a Grade</u><br/><br/>
<form name="gradeForm" action="Regservlet" method="post">
<input type="hidden" name="nameOfForm" value="gradeForm" />
		
		<%
		ArrayList<Course> courses = (ArrayList<Course>) session.getAttribute("courses");
		String beenSent = (String)session.getAttribute("selectedCourse");
		boolean dis = false;
		
		out.print("<select name=\"courses\" onchange=\"document.gradeForm.submit()	\"");
		
		if(null == beenSent)
		{
			out.print("><option>Select a course...</option>");
			if(null != courses)
			{
				for(Course c: courses)
				{
					out.println("<option>"+c.getCourseName()+"</option>");
				}
				out.println("</select>");
			}
		}else{
			out.println("disabled >");
			out.println("<option>"+beenSent+"</option></select><br/>");
			ArrayList<Material> mats = (ArrayList<Material>) session.getAttribute("mats");
			
			if(null != mats)
			{
				
				if(mats.size() != 0){
				out.println("<select name=\"materials\">");
				
					for(Material m : mats)
					{
						out.println("<option>"+m.getMatName()+"</option>"); 
					}
					out.println("</select><br/>");
					out.println("Enter grade:<input type='text' name='newGrade' /><br/>");
					out.println("<input type='submit' name='btnGrade' />");
				}
				else{
					out.println("No material for this course");
				}
			}
		}
		%>
</form>
</body>
</html>