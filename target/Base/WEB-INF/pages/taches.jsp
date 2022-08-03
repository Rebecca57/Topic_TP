<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Liste de taches</title>
<link rel="stylesheet" href="/Base/ressources/css/style.css">
</head>
<body>
	<div id="general">
	<h1>Ma ToDo Liste</h1>
	<fieldset>
	<legend>Ajouter une tache</legend>
		<form action="/Base/TachesServlet?type=add" method="POST"> 
				<label>Nom:</label>
				<input type="text" placeholder="Nom" name="nom"><br>
				<label>Description:</label>
				<input type="text" placeholder="Description" name="description"><br>
				<label>Date</label>
				<input type="datetime-local" placeholder="Date" name="date"><br>
				<input type="submit" value="Ajouter">			
		</form>
	</fieldset>
	
	<br>
	<br>
	
	<fieldset>
	<legend>Affichage</legend>
	<div id='display'>
		<c:forEach items="${sessionScope.listeTaches}" var="tache">
			<div class="tache">
				<form action="/Base/TachesServlet?type=modify&id=${tache.getKey()}" method="POST"> 
			    	<label>Tache ${tache.getKey()}:<br> Nom</label>
			    	<input type="text" name="nom" value="${tache.getValue().getNom()}">
			    	<label>Nom: </label>
			    	<input type="text" name="description" value="${tache.getValue().getDescription()}"> 
			    	<label>date</label>
			    	<input type="datetime-local" name="date" value="${tache.getValue().getDate()}">
			    	<input type="submit" value="edit">	
		    	</form>
		    	<form action="/Base/TachesServlet?type=delete&id=${tache.getKey()}" method="POST"> 
					<input type="submit" value="x">			
				</form>					
		    </div>	
		</c:forEach>
	</div>
	</fieldset>
	</div>
</body>
</html>