<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    	               "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>login</title>
</head>
<body>
	<script type="text/javascript">function validateAndSubmit() {    var name = document.f1.id.value;  //first textbox document.formname.elementname.value    var name1=document.f1.pw.value;    /*if(f1.login[0].checked==false || f1.login[1].checked==false)    {        alert("select the login type");        return false;*/     if(name ==""  ||name == null ) {        alert("user id cannot be blank!!" );        return false;    }    else if(name1=="" ||name1==null)    {        alert("enter the password" );        return false;    }    document.f1.action ="Servlet";    document.f1.submit();    }</script>
	<form name="f1">
		<form action="Servlet" method="get">
			<center>
				<font color="253556">USERNAME</font><input type="text" name="id" /><font
					color="253556">PASSWORD</font><input type="password" name="pw" />
			</center>
			<br>
			<center>
				<b><input type="submit" value="Submit"
					onclick="javascript:validateAndSubmit();" /></b>
			</center>
			<br>
			<a href="register.jsp">REGISTER HERE</a>
		</form>
</body>
</html>