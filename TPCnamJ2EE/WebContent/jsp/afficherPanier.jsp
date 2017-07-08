<%@ page language="java" contentType="text/html; charset=UTF-8"    pageEncoding="UTF-8"%>
<%@ page
	import="fr.cnam.bea.ebook.Metier.Catalogue.Livre,fr.cnam.bea.ebook.Metier.Gestion.Panier,fr.cnam.bea.ebook.Metier.Gestion.LignePanier"%>
<%@ page errorPage="afficherMessage.jsp"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
 
 
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/css/style.css" />
<script type="text/javascript"	src="<%=request.getContextPath()%>/js/global.js"></script>
<title>Panier</title>

</head>
<body>

<jsp:useBean id="panier" class= "fr.cnam.bea.ebook.Metier.Gestion.Panier" scope="session"/>

<!-- Debut Page  -->
<div id="conteneur">
	
	<!-- entête -->
		<%@ include file="./template/entete.jsp" %>
	<!-- Fin entête -->
	
	<!-- page -->
	<div id="page">
		<!-- menu  -->
		<%@ include file="./template/menu.jsp" %>
		
<div id="contenu" >


<p id="contenutitre"><br/>Votre panier </p>
  
	    <table id="resultats" summary="Ce tableau affiche les lignes du panier">
	    <thead>
	      <tr>
			<th>Titre</th><th>Qté</th><th>Prix</th>
			</tr>
		</thead>
		<tbody>
		 <c:forEach var="ligne" items="${sessionScope.panier.lignesPanier}">
		 	
			<tr> 
				  <td><c:out value="${ligne.livre.titre}"/>  </td>
			    <td>1</td>			
			    <td><c:out value="${ligne.prixligne}"/></td>
			
			</tr>
		
			</c:forEach>
		</tbody>
		
		<tfoot>	
		
		<tr><th colspan="2"> Total</th><th><c:out value="${panier.total}"/></th></tr>
		
	  </tfoot>	
	</table>
				
				
	</div> <!-- fin contenu -->
	
	</div>	<!-- Fin Page  -->
	
	<!-- PiedPage -->
	<%@ include file="./template/pied.jsp" %>
<!-- Fin Conteneur -->
</div>
	

</body>
</html>