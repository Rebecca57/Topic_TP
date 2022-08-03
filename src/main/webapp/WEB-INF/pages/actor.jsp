<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Insert title here</title>
		<link rel="stylesheet" href="/Base/ressources/css/style.css">
	</head>
	
	<body>

		Hello Actor 
		
		<!--  Prenom: ${sessionScope.listeUsers}-->
		
		<form action="/Base/ActorServlet?type=id" method="POST"> 
			<label>Id:</label>
			<input type="text" placeholder="Id" name="id"><br>
			<input type="submit" value="Rechercher">			
		</form>
		
		<form action="/Base/ActorServlet?type=nom" method="POST"> 
			<label>Nom:</label>
			<input type="text" placeholder="Nom" name="nom"><br>
			<input type="submit" value="Rechercher">			
		</form>
		<form action="/Base/ActorServlet?type=all" method="POST"> 

			<input type="submit" value="All actors">			
		</form>
		
		<div class="resultat">
		<c:forEach items="${listeUsers}" var="actor">
			<div class="actor">
	    		Prenom: ${actor.prenom}
	    		Nom: ${actor.nom}
	    		Id: ${actor.id}
	    		Dernière actualisation: ${actor.getLast_update()}<br>
    		</div>
		</c:forEach>
		<c:forEach items="${idListe}" var="actor">
			<div class="actor">
	    		Prenom: ${actor.prenom}
	    		Nom: ${actor.nom}
	    		Id: ${actor.id}
	    		Dernière actualisation: ${actor.getLast_update()}<br>
	    	</div>	
		</c:forEach>
			<c:forEach items="${nomListe}" var="actor">
			<div class="actor">
	    		Prenom: ${actor.prenom}
	    		Nom: ${actor.nom}
	    		Id: ${actor.id}
	    		Dernière actualisation: ${actor.getLast_update()}<br>
    		</div>
		</c:forEach>
		</div>
		
		
	</body>
</html>