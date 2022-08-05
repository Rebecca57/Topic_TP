<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>
<fieldset>
<jsp:include page="./inc/nav.jsp"/>
	<form action="/Base/Application" method="POST">
		<label>Username:</label>
		<input type="text" placeholder="Username" name="username"><br>
		<label>Password:</label>
		<input type="text" placeholder="Password" name="psw"><br>
		<input type="submit" value="Login">		
	</form>
</fieldset>
<div class="error">
	<c:if test="${error}">
		Username/password are not valid
	</c:if>
		<c:if test="${errorAdmin}">
		Vous devez vous connecter en tant qu'administrateur pour accéder à la page inscription
	</c:if>
</div>

</body>
</html>