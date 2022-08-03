<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>User</title>
</head>
<body>
	<fieldset>
		<form action="/Base/UserServlet" method="POST"> 
				<label>Prenom:</label>
				<input type="text" placeholder="Prenom" name="prenom"><br>
				<label>Nom:</label>
				<input type="text" placeholder="Nom" name="nom"><br>
				<input type="submit" value="AjouterUser">			
		</form>
	</fieldset>
</body>
</html>