<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page import="sheridan.*, java.util.*" language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Add/Edit Weight</title>
</head>
<body>
	<form action="Regservlet" method="post">
		<table>
			<tr>
			<th>Courses</th>
			</tr>
			<tr>
				<td>Course Name: </td>
				<td>${material.courseName }</td>
			</tr>
			<tr>
				<td>Material Type: </td>
				<td>${material.type }</td>
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