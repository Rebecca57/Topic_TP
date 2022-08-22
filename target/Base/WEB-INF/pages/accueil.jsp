<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Accueil</title>
<link rel="stylesheet" href="/Base/ressources/css/style.css">
</head>
<body>
<jsp:include page="./inc/nav.jsp"/>
<h1>Hello ${sessionScope.logged.prenom}</h1>
<!-- SI NON ADMIN -->
<c:if test="${!sessionScope.logged.admin}">
	<c:forEach items="${listeNews}" var="news">
		<fieldset>
			<fieldset>
				<h3>${news.title}</h3>
				<div>${news.texte}</div>
			</fieldset>
			<fieldset>
				<form action="/Base/NewsServlet?type=addComment&newsId=${news.id}&userId=${sessionScope.logged.id}" method="POST">
					<textarea name="text" rows="4" cols="50"> </textarea><br>
					<input type="submit" value="addComment">
				</form>
			</fieldset>
		<!-- DELETE -->
		<form action="/Base/NewsServlet?type=delete&id=${news.id}" method="POST">
				<input type="submit" value="delete">
		</form>
		<h4>Comments: </h4>
		<c:forEach items="${news.comments}" var="comment">
			<div>${comment.text}</div>
			<div>User: ${comment.user.id}</div>
		</c:forEach>
			
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
			<fieldset>
				<form action="/Base/NewsServlet?type=addComment&newsId=${news.id}&userId=${sessionScope.logged.id}" method="POST">
					<textarea name="text" rows="4" cols="50"> </textarea><br>
					<input type="submit" value="addComment">
				</form>
			</fieldset>
		</fieldset>
		<!-- DELETE -->
		<form action="/Base/NewsServlet?type=delete&id=${news.id}" method="POST">
				<input type="submit" value="delete">
		</form>
		<fieldset>
			<h4>Comments: </h4>
			<c:forEach items="${news.comments}" var="comment">
				<div class="comment">
					<div>${comment.text}</div>
					<div>@ ${comment.user.username}</div>
				</div>
			</c:forEach>
		</fieldset>
		<br>
	</c:forEach>
</c:if>

</body>
</html>