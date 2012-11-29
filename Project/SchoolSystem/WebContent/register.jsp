<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>example</title>
<script type="text/javascript">function validateAndSubmit() {    var name = document.f1.id.value;  //first textbox document.formname.elementname.value    var name1=document.f1.pw.value;    /*if(f1.login[0].checked==false || f1.login[1].checked==false)    {        alert("select the login type");        return false;*/     if(name ==""  ||name == null ) {        alert("user id cannot be blank!!" );        return false;    }    else if(name1=="" ||name1==null)    {        alert("enter the password" );        return false;    }    document.f1.action ="Servlet";    document.f1.submit();    }</script>
<form name="f1" onsubmit="return validateAndSubmit()"
	action="Regservlet" mehtod="post">
	<center>
		<font color="253556">USERNAME</font><input type="text" name="id" /><font
			color="253556">PASSWORD</font><input type="password" name="pw" />
	</center>
	<br><center>
			<b><input type="submit" value="Regsiter" /></b>
		</center>
</form>
</body>
</html>