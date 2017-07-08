<?xml version="1.0" encoding="UTF-8" ?>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/css/style.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/prototype-1.7.1.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/formulaireLivreRecherche.js"></script>

<title>Recherche de livres</title>
</head>
<body>


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
					<br /> Votre recherche
				</p>


				<form class="formulaire"
					action="<%=request.getContextPath()%>/chercherLivres" method="post"
					id="frmRecherche">

					<div>

						<fieldset title="Critères">
							<br />
							<div>
								<label for="type">Type de recherche</label> <input
									name="typerech" id="rtitre" type="radio" checked="checked" /><label>Titre</label>
								<input name="typerech" type="radio" id="risbn" /> <label>Référence</label>
								<input name="typerech" type="radio" id="rcategorie" /> <label>Catégorie</label>


							</div>

							<div id="div1" class="criterevisible">
								<label for="titre">Titre </label> <input name="titre"
									id="titre" type="text" value=""></input> <span id="titreerr"></span>
							</div>


							<div id="div2" class="criterecache">
								<label for="isbn">Référence</label> <input name="isbn"
									id="isbn" type="text" enabled="false" /> <span id="isbnerr"></span>
							</div>

							<div id="div3" class="criterecache">
								<label for="categorie">Catégorie </label> <select name="cat">

									<option value="Indéfinie" label="Indéfinie" selected="selected">Indéfinie</option>
									<option value="Informatique" label="Informatique">Informatique</option>
									<option label="Policier" value="Policier">Policier</option>
									<option label="Médecine" value="Médecine">Médecine</option>
								</select>
							</div>
						</fieldset>
						<br />
						<div id="commandes">
							<input type="submit" id="cmdValider" disabled="true"
								value="Valider" /> <input type="reset" id="cmdEffacer"
								onclick="effacer();" value="Effacer" />
						</div>

					</div>
				</form>

			</div>
			<!--  fin contenu  -->

		</div>
		<!-- Fin Page  -->

		<!-- PiedPage -->
		<%@ include file="./template/pied.jsp"%>
		<!-- Fin Conteneur -->
	</div>
</body>
</html>