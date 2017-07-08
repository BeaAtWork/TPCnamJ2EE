<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

<head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Afficher message</title>

</head>

<body>

<jsp:useBean id="exceptionBean" class= "fr.cnam.bea.ebook.Exceptions.ExceptionAccesDonnees" scope="request"/>
<jsp:useBean id="livre" class= "fr.cnam.bea.ebook.Metier.Catalogue.Livre" scope="request"/>


<!-- Debut Page  -->
<div id="conteneur">
	
	<!-- entête -->
		<%@ include file="./template/entete.jsp" %>
	<!-- Fin entête -->
	
	<!-- page -->
	<div id="page">
		<!-- menu  -->
		<%@ include file="./template/menu.jsp" %>
<div id="contenu">

<p id="contenutitre"><br/>Message </p>

<div id="message"> 



<%
if(request.getAttribute("exception") != null)
{
%>
<div class="exception">
L'application a rencontré des problèmes techniques : <br/> 

<%= exception.getMessage() %>
</div>
<%
}%>


<%
if(request.getAttribute("message") != null)
{
%>
<div class="confirmation">
<c:out value="${requestScope.message}"/>
</div>
<%
}%>


<%
if(request.getAttribute("exceptionBean") != null)
{
	%>

<div class="avertissement">

<c:out value="${exceptionBean.message}"></c:out>
</div>
<%
}%>




</div> 

	</div> <!-- fin contenu -->
	</div>	<!-- Fin Page  -->
	
	<!-- PiedPage -->
	<%@ include file="./template/pied.jsp" %>
<!-- Fin Conteneur -->
</div>

</body> </html>
