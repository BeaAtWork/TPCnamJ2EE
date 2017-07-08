<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="fr.cnam.bea.ebook.Metier.Catalogue.Livre,fr.cnam.bea.ebook.Metier.Catalogue.Auteur,fr.cnam.bea.ebook.Metier.Catalogue.Editeur, java.text.SimpleDateFormat"%>
<%@ page errorPage="afficherMessage.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/global.js"></script>
<title>Liste de livres</title>

</head>
<body>

	<jsp:useBean id="livreBean" class="fr.cnam.bea.ebook.Beans.LivreBean"
		scope="session" />


	<!-- Debut Page  -->
	<div id="conteneur">

		<!-- entête -->
		<%@ include file="./template/entete.jsp"%>
		<!-- Fin entête -->

		<!-- page -->
		<div id="page">
			<!-- menu  -->
			<%@ include file="./template/menu.jsp"%>

			<div id="contenu">


				<p id="contenutitre">
					<br />R&eacute;sultat
				</p>


				<table id="resultats"
					summary="Ce tableau affiche les caract&eacute;ristiques des livres">
					<thead>
						<tr>
							<th>Titre</th>
							<th>Qté</th>
							<th>Prix</th>
							<th>Catégorie</th>
							<th>Date parution</th>
							<th>Auteurs</th>
							<th>Editeurs</th>
							<th>Choix</th>
						</tr>
					</thead>
					<tbody>

						<form id='frmSelection'
							action="<%=request.getContextPath()%>/ajouterPanier"
							method="post">
							<%
							   int x=0;
							
								for (Livre livre : livreBean.getListeLivres()) {
									SimpleDateFormat fmt = new SimpleDateFormat("dd-MM-yyyy");
									String sdate = fmt.format(livre.getDateParution());
							%>

							<tr id="<%=x %>" onmouseover="selectionLigne(this);" onmouseout="nonselectionLigne(this);">
								<td><%=livre.getTitre()%></td>
								<td><%=livre.getQuantite()%></td>
								<td><%=livre.getPrix()%></td>
								<td><%=livre.getCategorie().name()%></td>

								<td><%=sdate%></td>
								<td>
									<%
										for (Auteur auteur : livre.getAuteurs()) {
									%> <%=auteur.getNom()%>&nbsp;<%=auteur.getPrenom()%><br />
									<%
											}
									%>
								</td>

								<td>
									<%
										for (Editeur editeur : livre.getEditeurs()) {
									%> <%=editeur.getNom()%>&nbsp;<%=editeur.getPays()%>
									<%
									}
									%>
								</td>

									<td><input type="checkbox" id="check" name="check" value="<%=livre.getIsbn() %>"/></td>						
							</tr>
							<%
								x++;}
							%>


						</form>
					</tbody>

					<tfoot>
						<fmt:formatDate value="${livreBean.dateRecherche}"
							var="formattedDateRech" type="date" pattern="dd-MM-yyyy HH:mm:ss" />
						<tr>
							<th colspan="8">Recherche effectuée le &nbsp; <c:out
									value="${formattedDateRech}" /></th>
						</tr>

					</tfoot>
				</table>
				
				
				<br></br>
	<div id="commandes"><input type="submit" onclick="envoyer();" value="Ajouter au panier" />&nbsp;&nbsp;
							<input type="reset" onclick="document.getElementById('frmSelection').reset();"  value="Abandonner" />
							</div>
	
	
	
			</div>
			<!-- fin contenu -->

		</div>
		<!-- Fin Page  -->

		<!-- PiedPage -->
		<%@ include file="./template/pied.jsp"%>
		<!-- Fin Conteneur -->
	</div>


</body>
</html>