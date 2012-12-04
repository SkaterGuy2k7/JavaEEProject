<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    	               "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>
</head>
<body>
		<form action="Regservlet" method="post">
			<div align="center">
				<font color="253556">USERNAME</font><input type="text" name="user" /><font
					color="253556">PASSWORD</font><input type="password" name="pass" />
			</div>
			<br>
			<div align="center">
				<b><input type="submit" name="login" value="Login" /></b>
					<b><input type="submit" name="register" value="Register"/></b>
			</div>
		</form>
</body>
</html>