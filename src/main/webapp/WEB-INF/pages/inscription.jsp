<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Inscription</title>
</head>
<body>
	Inscription
</body>
<fieldset>
<jsp:include page="./inc/nav.jsp"/>
	<form action="/Base/Inscription" method="POST">
		<label>Prenom:</label>
		<input type="text" placeholder="Prenom" name="prenom"><br>
		<label>Nom:</label>
		<input type="text" placeholder="Nom" name="nom"><br>
		<label>Username:</label>
		<input type="text" placeholder="Username" name="username"><br>
		<label>Password:</label>
		<input type="text" placeholder="Password" name="password"><br>

      	<input type="checkbox" id="admin" name="admin" checked>
      		<label for="admin">Admin</label><br>
      	<input type="submit" value="Register">
	</form>
	<c:if test="${errorInscription != null}">
		${errorInscription}
	</c:if>
</fieldset>
</html>