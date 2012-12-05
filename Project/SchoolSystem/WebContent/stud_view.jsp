<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" type="text/css" href="style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>Student View</title>
<script>
	document.getElementById('test').style.display = 'none';
	function display() {
		if (document.getElementById('test').style.display == 'block') {
			document.getElementById('test').style.display = 'none';
		} else {
			document.getElementById('test').style.display = 'block';
		}
	}
</script>
</head>
<body>
	Name:
	<br /> ID:
	<br />
	<u>Courses</u>
	<div>
		<a href="#" onclick="display();">Love</a>
	</div>
	<div id="test" class="grade">rainbows and butterflies</div>
</body>
</html>