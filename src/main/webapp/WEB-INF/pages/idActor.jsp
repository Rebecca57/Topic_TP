<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Actor with Id</title>
</head>
<body>

		<div>
		<c:if test="${sessionScope.valid}">
		<h2>Actor avec id = ${param.id} </h2>
			Prenom: ${sessionScope.idUser.prenom}<br>
			Nom: ${sessionScope.idUser.nom}<br>
			Last update: ${sessionScope.idUser.getLast_update()}<br>
			<br>
		</c:if>
		<c:if test="${!sessionScope.valid}">
			Il n'y a pas d'acteur avec un id = ${param.id}
		</c:if>
		</div>

</body>
</html>