<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"  import="fr.cnam.bea.ebook.Metier.Catalogue.*, java.util.*"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/prototype-1.7.1.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/formulaireLivreAjout.js"></script>
	
<title>Ajouter un livre</title>

</head>
<body>


	<!-- Debut Page  -->
	<div id="conteneur">

		<!-- entête -->
		<%@ include file="./../template/entete.jsp"%>
		<!-- Fin entête -->

		<!-- page -->
		<div id="page">
			<!-- menu  -->
			<%@ include file="./../template/menu.jsp"%>

			<div id="contenu">

				<p id="contenutitre">
					<br /> Ajouter un livre
				</p>


				<form class="formulaire"
					action="<%=request.getContextPath()%>/ajouterLivre" method="post"
					id="frmAjoutLivre">

					<div>
						<fieldset title="Livre">

							<div>
								<label for="titre">Titre </label> <input name="titre"	id="titre" type="text" value=""></input> 
								<span id="titreerr"></span>
							</div>
							<div>
								<label for="categorie" >Catégorie </label>
								<select name="cat">
								<option value="Aucune" label="Sélectionnez une catégorie ..." selected="selected">Aucune</option>
									<option value="Indéfinie" label="Indéfinie" >Indéfinie</option>
								<option value="Informatique" label="Informatique" >Informatique</option>
								<option label="Policier" value="Policier">Policier</option>
								<option label="Théatre" value="Théatre">Théatre</option></select>
							</div>
							

							<div>
								<label for="isbn">ISBN </label> <input name="isbn" id="isbn" type="text" value=""></input>
								<span id="isbnerr"></span>
							</div>

<div>
								<label for="dateparution">Date de parution </label> <input name="dateparution" id="dateparution" type="text" value=""
								 title="format attendu : dd-mm-yyyy"></input>
								<span id="dateparutionerr"></span>
							</div>
							
							<div>
								<label for="prix">Prix </label> <input name="prix" id="prix" type="text" value=""></input>
								 <span id="prixerr"></span>
							</div>
							<div>
								<label for="quantite">Quantite </label> <input name="quantite"	id="quantite" type="text" value=""></input> 
								<span	id="quantiteerr"></span>
							</div>
							
							
							<div style="height:110px">
					
							<label for="txtAuteur">Auteurs</label>
							<select name="txtAuteur" id="txtAuteur" multiple="true" style="height:100px">
								<%
								List<Auteur> listeAuteurs = ( List<Auteur>) request.getAttribute("listeauteurs"); 
								for ( Auteur a : listeAuteurs  ) { 
								%>
								<option value="<%=a.getNom()%> <%=a.getPrenom() %>"><%=a.getNom()%> <%=a.getPrenom() %></option>
								<%} %>
							</select> 
							</div>
							<div>
								<label for="auteur">Ajouter un Auteur </label> <input name="auteur"	id="auteur" type="text"  title="format attendu : nom prenom"/>
								<input type="button" value="+" onclick="ajouter('auteur');" />
								<span	id="auteurerr"></span>
							</div>
							
							
							<div style="height:110px">
								<label for="txtEditeur">Editeurs</label>
					<select name="txtEditeur" id="txtEditeur" multiple="true" style="height:100px" >
						<%
						List<Editeur> listeEditeur = ( List<Editeur>) request.getAttribute("listeediteurs"); 
						for ( Editeur e : listeEditeur  )
						{ %>
						<option value="<%=e.getNom()%> <%=e.getPays()%>"><%=e.getNom()%> <%=e.getPays() %></option>
						<%} %>
					</select>
					</div>
							<div>
								<label for="editeur">Ajouter un éditeur </label> <input name="editeur"	id="editeur" type="text"  title="format attendu : nom pays"/> 
								<input type="button" value="+" onclick="ajouter('editeur');" />
								<span	id="editeurerr"></span>
							</div>
					</fieldset>
							
					</div>
					<br/>
					<div id="commandes">
						<input type="submit" id="cmdValider" value="Valider" disabled="true"/>
								<input type="reset" id="cmdEffacerr" value="Effacer" />
								</div>

				</form>
						

			</div> <!-- fin contenu -->
			

		</div>
		<!-- Fin Page  -->

		<!-- PiedPage -->
		<%@ include file="./../template/pied.jsp"%>
		<!-- Fin Conteneur -->
	</div>
</body>
</html>