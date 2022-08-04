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
				<label>Date: </label>
				<input type="date" placeholder="Date" name="date"><br>
				<input type="submit" value="Ajouter">			
		</form>
	</fieldset>
	
	<br>
	
	<fieldset>
	<legend>Affichage recherche </legend>		    	
		<form action="/Base/TachesServlet?type=displayTache" method="POST"> 
			<label>Nom: </label>
			<input type="text" name="nom"> 
			<input type="submit" value="Search">			
		</form>
		<br>
		<c:if test="${listTacheS != null }">
		<c:forEach items="${listTacheS}" var="tacheS">
			<form>
		    	<label class="labelTitle">Tache ${tacheS.id} | </label><label>Nom:</label>
		    	${tacheS.getNom()}
		    	<label>Description: </label>
		    	${tacheS.getDescription()} 
		    	<label>date: </label>
		    	${tacheS.getDate()}
			 </form>
			 </c:forEach>
		 </c:if>
	</fieldset>
	<br>
	<fieldset>
	<legend>Affichage liste des taches</legend>
	<div id='display'>
		<c:forEach items="${listeTaches}" var="tache">
			<div class="tache">
				<form action="/Base/TachesServlet?type=modify&id=${tache.id}" method="POST"> 
			    	<label class="labelTitle">Tache ${tache.id} |</label><label> Nom: </label>
			    	<input type="text" name="nom" value="${tache.getNom()}">
			    	<label>Nom: </label>
			    	<input type="text" name="description" value="${tache.getDescription()}"> 
			    	<label>date</label>
			    	<input type="date" name="date" value="${tache.getDate()}">
			    	<input type="submit" value="edit">	
		    	</form>
		    	<form action="/Base/TachesServlet?type=delete&id=${tache.id}" method="POST"> 
					<input type="submit" value="x">			
				</form>					
		    </div>	
		</c:forEach>
	</div>
	</fieldset>
	</div>
</body>
</html>