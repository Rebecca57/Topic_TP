<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
	</head>
	
	<body>

		Hello Actor 
		
		<!--  Prenom: ${sessionScope.listeUsers}-->
		

		
		<form action="/Base/ActorServlet" method="POST"> 
			<label>Id:</label>
			<input type="text" placeholder="Id" name="id"><br>
			<input type="submit" value="Rechercher">			
		</form>
		
		<form action="/Base/ActorName" method="POST"> 
			<label>Nom:</label>
			<input type="text" placeholder="Nom" name="nom"><br>
			<input type="submit" value="Rechercher">			
		</form>
		
		<c:forEach items="${sessionScope.listeUsers}" var="actor">
    		Prenom: ${actor.prenom}
    		Nom: ${actor.nom}
    		Id: ${actor.id}
    		Dernière actualisation: ${actor.getLast_update()}<br>
		</c:forEach>

		
		
	</body>
</html>