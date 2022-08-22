<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
<meta charset="ISO-8859-1">
<title>Clients page</title>
<link rel="stylesheet" href="/Base/ressources/css/style.css">
</head>
<body>

<h3>Liste des users</h3>
<c:forEach items="${sessionScope.liste}" var="client">
	<fieldset>
		<label>Prenom: </label>${client.getPrenom()}<br>
		<label>Nom: </label>${client.nom}<br><br>
		
		<fieldset>
		<label>COMMANDES: </label>
			<c:forEach items="${client.getCommandes()}" var="commande">
				<div>
					<label>Commande ${commande.getId()}: </label>
					<label>Nom: </label>${commande.getNom()}
					<label>Description: </label>${commande.getDescription()}
				</div>	
			</c:forEach>
		</fieldset>
	</fieldset>
	<br>
</c:forEach>

</body>
</html>