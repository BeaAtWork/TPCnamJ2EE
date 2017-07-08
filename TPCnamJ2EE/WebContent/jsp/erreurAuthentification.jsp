<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"
import="fr.cnam.bea.ebook.Utilitaires.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/js/prototype-1.7.1.js"></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/js/formulaireUtilisateur.js" ></script>

<title>Erreur d'authentification</title>
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
				<p id="contenutitre"><br/>Contrôle d'identification</p>
		
		<div id="message">		
<div class="avertissement"><%=UtilFichier.getValeur("erreur.authentification.role")%></div>
</div>
				
		</div> <!--  fin contenu  -->

</div>	<!-- Fin Page  -->
	
	<!-- PiedPage -->
	<%@ include file="./template/pied.jsp" %>
<!-- Fin Conteneur -->
</div>
</body>
</html>
 