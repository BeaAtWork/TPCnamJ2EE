<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ page  import="fr.cnam.bea.ebook.Utilitaires.*"%>




<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" />
<title>Aucun livre</title>

</head>
<body>
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

<p id="contenutitre"><br/>R&eacute;sultat </p>

<div id="message">
<div class="avertissement"> <%=UtilFichier.getValeur("Livre.nontrouve") %> </div>
</div>
<div id="aucun">


<div><label>Critères de recherche  </label> </div><br></br>


<div><label>Titre : </label> <span>  <%=request.getParameter("titre") %> </span></div>

<div><label>ISBN : </label><span>  <%=request.getParameter("isbn") %> </span></div>

<div><label>Catégorie :</label> 
<span>  <%
String cat="";
if(!request.getParameter("cat").equals("Indéfinie"))
	cat=request.getParameter("cat");
	%> 
	<%=cat %>
	</span></div>


	
	</div> <!--  fin aucun -->
	
	</div> <!-- fin contenu -->

</div>	<!-- Fin Page  -->
	
	<!-- PiedPage -->
	<%@ include file="./template/pied.jsp" %>
<!-- Fin Conteneur -->
</div>

</body>
</html>