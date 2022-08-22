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



<!-- ADD -->

<c:if test="${sessionScope.log}">
<h1>Hello ${sessionScope.logged.prenom}</h1>
<h3>Add a new Topic</h3>
	<fieldset>	
		<form action="/Base/NewsServlet?type=add&userId=${sessionScope.logged.id}" method="POST">
			<label>Title</label><br>
			<input type="text" name="title"><br>
			<label>Texte</label><br>
			<textarea name="texte" rows="4" cols="50"></textarea>
			<input type="submit" value="add">
		</form>
	</fieldset>
	<br><hr>
</c:if>

<!-------------------------------------- SI NON ADMIN --------------------------->
<!-------------------------------------------------------------------------------->
<h3>Topic's list</h3>
<c:if test="${!sessionScope.logged.admin}">
	<!-- Liste des topics -->
	<c:forEach items="${listeNews}" var="news">
		
		<!-- Si créateur du topic possibilité de update/delete -->
		<c:if test="${news.userId == sessionScope.logged.id}">
		
			<fieldset>
				<!-- DELETE -->
				<div class="delete">
					<form action="/Base/NewsServlet?type=delete&id=${news.id}" method="POST">
						<input type="submit" value="X">
					</form>
				</div><br>
				<form action="/Base/NewsServlet?type=modify&id=${news.id}&userId=${news.userId}" method="POST">
					<input type="text" name="title" value="${news.title}"><br>
					<textarea name="texte" rows="4" cols="50"> ${news.texte} </textarea><br>
					<input type="submit" value="change">
				</form>
			</fieldset>

		</c:if>
		<c:if test="${news.userId != sessionScope.logged.id}">
			<fieldset>
				<h3>${news.title}</h3>
				<div>${news.texte}</div>
			</fieldset>
		</c:if>

	<!-- COMMENTS -->
	<fieldset>
	<br>
	<!-- Ajouter comments si loged -->
	<c:if test="${sessionScope.log}">
		<div class="comment">
			<fieldset>
			Comment:<br>
				<form action="/Base/NewsServlet?type=addComment&newsId=${news.id}&userId=${sessionScope.logged.id}" method="POST">
					<textarea name="text" rows="4" cols="50"> </textarea><br>
					<input type="submit" value="add">
				</form>
			</fieldset>
		</div>	
	</c:if>
		<!-- Liste des commentaires -->
		<h4>Comments: </h4>
		<c:forEach items="${news.comments}" var="comment">
			<div class="comment">
				<div>@${comment.user.username}</div>
				<div>${comment.text}</div>	
			</div>
			<hr>
		</c:forEach>
			
		</fieldset>
		<br><hr><br>
	</c:forEach>
</c:if>
<!----------------------------------- SI ADMIN -------------------------------------->
<!------------------------------------------------------------------------------------>
<c:if test="${sessionScope.logged.admin}">


	<!-- UPDATE -->
	<c:forEach items="${listeNews}" var="news">
		<fieldset>
		<div class="delete">
			<form action="/Base/NewsServlet?type=delete&id=${news.id}" method="POST">
				<input type="submit" value="X">
			</form>
		</div>
			<br>
			<form action="/Base/NewsServlet?type=modify&id=${news.id}&userId=${news.userId}" method="POST">
				<input type="text" name="title" value="${news.title}"><br>
				<textarea name="texte" rows="4" cols="50"> ${news.texte} </textarea><br>
				<input type="submit" value="change">
			</form>
			<br>
			<div class="comment">	
				<fieldset>	
				Comment:<br>
					<form action="/Base/NewsServlet?type=addComment&newsId=${news.id}&userId=${sessionScope.logged.id}" method="POST">
						<textarea name="text" rows="4" cols="50"> </textarea><br>
						<input type="submit" value="add">
					</form>
				</fieldset>
			</div>
		</fieldset>
		<!-- DELETE -->

		<fieldset>
			<h4>Comments: </h4>
			<c:forEach items="${news.comments}" var="comment">
				<form action="/Base/NewsServlet?type=deleteComment&id=${comment.id}" method="POST">
				<div class="comment">
					<div>@ ${comment.user.username}</div>
					<div>${comment.text}  <input type="submit" value="X"></div>	
				</div>
				</form>
				<hr>
			</c:forEach>
		</fieldset>
		<br>
	</c:forEach>
</c:if>

</body>
</html>