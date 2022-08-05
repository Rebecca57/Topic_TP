<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div id="menu">
	<a href="<c:url value='/NewsServlet'/>">Accueil</a>
	<a href="<c:url value='/Application'/>">Login</a>
	<a href="<c:url value='/Inscription'/>">Inscription</a>
	<%--<c:if test="${sessionScope.isLogin}"><a href="<c:url value='/Accueil?page=PAGE2'/>">Page2</a></c:if>--%>
</div>
<div>
	<c:if test="${sessionScope.logged != null}">
		<a href="<c:url value='/Application?deco=yes'/>">logout</a>
	</c:if>
</div>
${sessionScope.logged.nom}
<hr>
<br>