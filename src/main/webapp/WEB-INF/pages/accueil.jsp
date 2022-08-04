<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Accueil</title>
</head>
<body>

<h1>Hello ${sessionScope.logged.prenom}</h1>
<!-- SI NON ADMIN -->
<c:if test="${!sessionScope.logged.admin}">
	<c:forEach items="${listeNews}" var="news">
		<fieldset>
			<h3>${news.title}</h3>
			<div>${news.texte}</div>
		</fieldset>
		<br>
	</c:forEach>
</c:if>

<!-- SI ADMIN -->
<c:if test="${sessionScope.logged.admin}">
	<!-- ADD -->
	<fieldset>
		<form action="/Base/NewsServlet?type=add" method="POST">
			<label>Title</label><br>
			<input type="text" name="title"><br>
			<label>Texte</label><br>
			<textarea name="texte" rows="4" cols="50"></textarea>
			<input type="submit" value="add">
		</form>
	</fieldset>
	
	<br>
	<!-- UPDATE -->
	<c:forEach items="${listeNews}" var="news">
		<fieldset>
			<form action="/Base/NewsServlet?type=modify&id=${news.id}" method="POST">
				<input type="text" name="title" value="${news.title}"><br>
				<textarea name="texte" rows="4" cols="50"> ${news.texte} </textarea><br>
				<input type="submit" value="change">
			</form>
		</fieldset>
		<!-- DELETE -->
		<form action="/Base/NewsServlet?type=delete&id=${news.id}" method="POST">
				<input type="submit" value="delete">
		</form>
		<br>
	</c:forEach>
</c:if>

</body>
</html>