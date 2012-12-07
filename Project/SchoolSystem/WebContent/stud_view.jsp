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
	function display2() {
		document.getElementById('course1').style.display = 'none';
		document.getElementById('course2').style.display = 'block';	
		document.getElementById('course3').style.display = 'none';
		document.getElementById('course4').style.display = 'none';
		document.getElementById('course5').style.display = 'none';
	}
	function display3() {
		document.getElementById('course1').style.display = 'none';
		document.getElementById('course2').style.display = 'none';
		document.getElementById('course3').style.display = 'block';	
		document.getElementById('course4').style.display = 'none';
		document.getElementById('course5').style.display = 'none';
	}
	function display4() {
		document.getElementById('course1').style.display = 'none';
		document.getElementById('course2').style.display = 'none';
		document.getElementById('course3').style.display = 'none';	
		document.getElementById('course4').style.display = 'block';
		document.getElementById('course5').style.display = 'none';	
	}
	function display5() {
		document.getElementById('course1').style.display = 'none';
		document.getElementById('course2').style.display = 'none';
		document.getElementById('course3').style.display = 'none';	
		document.getElementById('course4').style.display = 'none';	
		document.getElementById('course5').style.display = 'block';
	}
</script>
</head>
<body>
	<span class="student">Name: ${student.getFirstName()} ${s.getLastName()}</span><br/>
	<span class="student">ID: ${student.getStudid()}</span><br/>
	<u>Courses</u>
	<div>				
		<a href=# onclick='display1();'>${student.getCourse1()}</a>
	</div>
	<div id="course1" class="grade">78</div>
	<div>				
		<a href=# onclick='display2();'>${student.getCourse2()}</a>
	</div>
	<div id="course2" class="grade">60</div>
	<div>				
		<a href=# onclick='display3();'>${student.getCourse3()}</a>
	</div>
	<div id="course3" class="grade">90</div>
	<div>				
		<a href=# onclick='display4();'>${student.getCourse4()}</a>
	</div>
	<div id="course4" class="grade">80</div>
	<div>				
		<a href=# onclick='display5();'>${student.getCourse5()}</a>
	</div>
	<div id="course5" class="grade">52</div>
</body>
</html>