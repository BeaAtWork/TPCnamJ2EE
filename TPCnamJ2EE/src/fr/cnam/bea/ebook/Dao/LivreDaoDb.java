package fr.cnam.bea.ebook.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import fr.cnam.bea.ebook.Exceptions.ExceptionAccesDonnees;
import fr.cnam.bea.ebook.Metier.Catalogue.Auteur;
import fr.cnam.bea.ebook.Metier.Catalogue.Editeur;
import fr.cnam.bea.ebook.Metier.Catalogue.Livre;
import fr.cnam.bea.ebook.Metier.Catalogue.TCategorie;
import fr.cnam.bea.ebook.Utilitaires.BaseDonnees;
import fr.cnam.bea.ebook.Utilitaires.BaseDonneesPostgresql;
import fr.cnam.bea.ebook.Utilitaires.UtilFichier;

/**
 * @author Beatrice
 * 
 */
public class LivreDaoDb implements ILivreDao {

	public LivreDaoDb() {
		super();
		// TODO Auto-generated constructor stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.cnam.bea.ebook.Dao.ILivreDao#rechercher(java.lang.String,
	 * java.lang.String, java.lang.String)
	 */
	@Override
	public List<Livre> rechercher(String titre, String categorie, String isbn)
			throws ExceptionAccesDonnees {

		ResultSet result = null;
		Statement stmt = null;
		ResultSet result2 = null;
		Statement stmt2 = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		String req = "";

		try {
			connection = BaseDonneesPostgresql.getInstance();

			if (isbn == null || isbn.equals("")) {
				// recherche par catégorie
				if (titre == null || titre.equals("")) {
					int idcategorie = 0;
					// recherche idcategorie
					try {
						idcategorie = FabriqueDao.getInstanceCategorie()
								.rechercher(categorie);
					} catch (ExceptionAccesDonnees exad) {
						throw new ExceptionAccesDonnees(
								UtilFichier.getValeur(exad.getMessage()));
					}
					// requete mysql
					// req =
					// "SELECT  idlivre,isbn, titre,dateparution,quantite,prix,idcategorie  FROM livre WHERE livre.idcategorie =?";

					// postgres
					req = "SELECT  isbn,categorie, titre,dateparution,quantite,prix FROM \"Livre\"  WHERE categorie =? ";
					pstmt = connection.prepareStatement(req);
					pstmt.setInt(1, idcategorie);
				}
				// TITRE
				else {

					// req =
					// "SELECT  idlivre,isbn, titre,dateparution,quantite,prix, libelle  FROM livre,categorie  WHERE livre.titre like ? and categorie.idcategorie= livre.idcategorie ";

					// requete postgresql
					req = "SELECT  isbn,categorie, titre,dateparution,quantite,prix ,id,nom FROM \"Livre\",\"Categorie\"  WHERE titre like ? and categorie= id";
					pstmt = connection.prepareStatement(req);
					pstmt.setString(1, (titre + "%"));
				}

			}
			// ISBN
			else {
				// requete mysql
				// req =
				// "SELECT  idlivre,isbn, titre,dateparution,quantite,prix,libelle  FROM livre,categorie  WHERE livre.isbn  =? and categorie.idcategorie= livre.idcategorie ";

				// requete postgresql
				String idlivre = "{" + isbn + "}";
				req = "SELECT  isbn,categorie, titre,dateparution,quantite,prix,id ,nom FROM \"Livre\",\"Categorie\"  WHERE isbn  ='"
						+ idlivre +"' and categorie= id ";

				System.out.println(req);
				pstmt = connection.prepareStatement(req);
				 
			}

			List<Livre> listeLivres = new ArrayList<Livre>();
			result = pstmt.executeQuery();
			if (result != null) {
				while (result.next()) {
					Livre livre = new Livre(); // initialisation

					// int idlivre = result.getInt(1); // mysql
					livre.setIsbn(result.getString("isbn"));
					livre.setTitre(result.getString("titre"));
					livre.setDateParution(result.getDate("dateparution"));

					livre.setQuantite(result.getInt("quantite"));
					livre.setPrix((float) result.getDouble("prix"));
					
					if ( (isbn == null || isbn.equals("")) && (titre == null || titre.equals("")) )	 {
						 livre.setCategorie(TCategorie.valueOf(categorie));
						
					 }
					 else
					 {						
						 livre.setCategorie(TCategorie.valueOf(result.getString("nom")));
					 }


					// pour chaque livre, recherche auteurs
					List<Auteur> listeAuteurs = new ArrayList<Auteur>();
					// requete mysql
					// String reqAuteur =
					// "SELECT a.idlivre, b.idauteur, b.nom, b.prenom FROM livre_auteur a ,auteur b  WHERE a.idlivre ="
					// + idlivre + " and b.idauteur=a.idauteur";

					// requete postgresql

					String idlivre =  livre.getIsbn() ;

					String reqAuteur = "SELECT a.isbn,a.id_auteur, b.id, b.nom, b.prenom FROM \"Livre_auteur\" a,\"Auteur\" b   WHERE a.isbn ='"
							+ idlivre + "' and b.id=a.id_auteur";

					System.out.println(reqAuteur);
					stmt2 = connection.createStatement();
					result2 = stmt2.executeQuery(reqAuteur);

					while (result2.next()) {
						Auteur aut = new Auteur(); // initialisation
						aut.setNom(result2.getString("nom"));
						aut.setPrenom(result2.getString("prenom"));
						listeAuteurs.add(aut);
						aut = null;
					}
					livre.setAuteurs(listeAuteurs);

					// pour chaque livre, recherche éditeurs
					List<Editeur> listeEditeurs = new ArrayList<Editeur>();
					// requete mysql
					// String reqEditeur =
					// "SELECT a.idlivre, b.idediteur, b.nom, b.pays  FROM livre_editeur a, editeur b  WHERE  a.idlivre="
					// + idlivre + " and b.idediteur=a.idediteur";
					// requete postgresql
					String reqEditeur = "SELECT a.isbn,a.id_editeur, b.id, b.nom, b.pays FROM \"Livre_editeur\" a,\"Editeur\" b   WHERE a.isbn ='"
							+idlivre + "' and b.id=a.id_editeur";

					result2 = stmt2.executeQuery(reqEditeur);
					while (result2.next()) {
						Editeur edit = new Editeur(); // initialisation
						edit.setNom(result2.getString("nom"));
						edit.setPays(result2.getString("pays"));
						listeEditeurs.add(edit);
						edit = null;

					}
					livre.setEditeurs(listeEditeurs);

					listeLivres.add(livre);
				}
			}
			if (!result.isClosed()) {
				result.close();
			}
			if (!pstmt.isClosed()) {
				pstmt.close();
			}
			if (result2 != null) {
				if (!result2.isClosed()) {
					result2.close();
				}
			}
			if (stmt2 != null) {
				if (!stmt2.isClosed()) {
					stmt2.close();
				}
			}
			if (!connection.isClosed()) {
				BaseDonneesPostgresql.deconnexion(connection);
			}

			return listeLivres;
		} catch (SQLException sqlex) {
			sqlex.printStackTrace();
			throw new ExceptionAccesDonnees(
					UtilFichier.getValeur("erreur.base.connexion")
							+ sqlex.getMessage());
		} catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

		finally {
			result = null;
			result2 = null;
			stmt2 = null;
			pstmt = null;
			connection = null;
		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.cnam.bea.ebook.Dao.ILivreDao#ajouter(fr.cnam.bea.ebook.Metier.Catalogue
	 * .Livre)
	 */
	@Override
	public void ajouter(Livre livre) throws ExceptionAccesDonnees {
		// TODO Auto-generated method stub
		int idLivre = 0;
		int idAuteurLivre = 0;
		int idEditeurLivre = 0;

		ResultSet result = null;
		Statement stmt2 = null;
		Statement stmt = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		try {

			connection = BaseDonneesPostgresql.getInstance();

			// ******************************** transaction
			connection.setAutoCommit(false);
			// ************************
			// recherche idcategorie
			TCategorie tcat = livre.getCategorie();
			String lib = tcat.name();
			// requete mysql
			String reqCat = "select idcategorie, libelle from categorie where libelle='"
					+ lib + "'";
			stmt = connection.createStatement();
			result = stmt.executeQuery(reqCat);
			int idcategorie = 0;
			if (result != null) {
				while (result.next()) {
					idcategorie = result.getInt(1);
				}
				result.close();
			} else {
				// si absente, la créer
				// requete mysql
				String reqInsCat = "insert into categorie  (libelle) values('"
						+ lib + "')";
				stmt.executeUpdate(reqInsCat);
			}

			// traitements des auteurs : inserer le différentiel entre existants
			// en base et les nouveaux
			String req = null;
			String reqIns = null;

			stmt2 = connection.createStatement();

			for (Auteur a : livre.getAuteurs()) {
				// requete mysql
				req = "SELECT count(*) FROM auteur WHERE nom='" + a.getNom()
						+ "' and prenom ='" + a.getPrenom() + "'";

				result = stmt.executeQuery(req);
				if (result != null) {

					while (result.next()) {
						int nb = result.getInt(1);
						if (nb <= 0) {
							// insertion
							// requete mysql
							reqIns = "insert into auteur (nom, prenom)  values('"
									+ a.getNom() + "','" + a.getPrenom() + "')";

							stmt2.executeUpdate(reqIns);
						}
					}
				}
			}

			// Traitement des éditeurs
			for (Editeur e : livre.getEditeurs()) {
				// requete mysql
				req = "SELECT count(*) FROM editeur WHERE nom='" + e.getNom()
						+ "' and pays ='" + e.getPays() + "'";

				result = stmt.executeQuery(req);
				if (result != null) {
					while (result.next()) {
						int nb = result.getInt(1);
						if (nb <= 0) {
							// insertion
							// requete mysql
							reqIns = "insert into editeur (nom, pays)  values('"
									+ e.getNom() + "','" + e.getPays() + "')";

							stmt2.executeUpdate(reqIns);
						}
					}
				}

			}

			// Insertion du livre
			// ne pas ajouter si déjà en base :
			String reqExiste = "select count(*) from livre where isbn=? and titre=?  and idcategorie=?";

			pstmt = connection.prepareStatement(reqExiste);

			pstmt.setString(1, livre.getIsbn());
			pstmt.setString(2, livre.getTitre());
			pstmt.setInt(3, idcategorie);

			result = pstmt.executeQuery();
			int nb = 0;
			if (result != null) {
				while (result.next()) {
					nb = result.getInt(1);
				}
			}
			if (nb <= 0) {

				// On continue
				String reqInsLivre = "insert into livre (isbn,titre,dateparution,quantite,prix,idcategorie,dateinsertion) values(?,?,?,?,?,?,?)";
				pstmt2 = connection.prepareStatement(reqInsLivre);

				pstmt2.setString(1, livre.getIsbn());
				pstmt2.setString(2, livre.getTitre());
				pstmt2.setDate(3, new java.sql.Date(livre.getDateParution()
						.getTime()));
				pstmt2.setInt(4, livre.getQuantite());
				pstmt2.setFloat(5, livre.getPrix());
				pstmt2.setInt(6, idcategorie);
				// date de création en base
				Calendar cal = Calendar.getInstance();
				pstmt2.setDate(7, new java.sql.Date(cal.getTimeInMillis()));
				pstmt2.executeUpdate();

				// recuperer id du livre
				String reqMax = "select max(idlivre) from livre";
				result = stmt.executeQuery(reqMax);
				if (result != null) {
					while (result.next()) {
						idLivre = result.getInt(1);
					}
				}
				// Insertion dans les tables livre_auteur , livre_editeur
				// pour chaque auteur trouver son id et insérer

				if (livre.getAuteurs() != null && livre.getAuteurs().size() > 0) {
					for (Auteur a : livre.getAuteurs()) {
						req = "select idauteur from auteur where nom='"
								+ a.getNom() + "' and prenom='" + a.getPrenom()
								+ "'";

						result = stmt.executeQuery(req);

						if (result != null) {
							while (result.next()) {
								idAuteurLivre = result.getInt(1);
								String reqInsLivreAuteur = "insert into livre_auteur (idlivre,idAuteur)  values ("
										+ idLivre + "," + idAuteurLivre + ")";
								stmt2.executeUpdate(reqInsLivreAuteur);
							}
						}
					}
				}

				// chercher les id des éditeurs du livre et insérer
				if (livre.getEditeurs() != null
						&& livre.getEditeurs().size() > 0) {
					for (Editeur e : livre.getEditeurs()) {
						req = "select idediteur from editeur where nom='"
								+ e.getNom() + "' and pays='" + e.getPays()
								+ "'";

						result = stmt.executeQuery(req);

						if (result != null) {
							while (result.next()) {
								idEditeurLivre = result.getInt(1);
								String reqInsLivreEditeur = "insert into livre_editeur (idlivre,idEditeur)  values ("
										+ idLivre + "," + idEditeurLivre + ")";
								stmt2.executeUpdate(reqInsLivreEditeur);
							}
						}
					}

				}

				// ****************************Commit**************
				connection.commit();

				if (!result.isClosed()) {
					result.close();

				}

				if (!stmt.isClosed()) {
					stmt.close();

				}
				if (!stmt2.isClosed()) {
					stmt2.close();

				}
				if (!pstmt.isClosed()) {
					pstmt.close();

				}
				if (!pstmt2.isClosed()) {
					pstmt2.close();

				}
				if (!connection.isClosed()) {
					BaseDonneesPostgresql.deconnexion(connection);
				}

			} else {
				connection.rollback(); // ************* rollback **************
				throw new ExceptionAccesDonnees(
						UtilFichier.getValeur("erreur.livre.dejaexistant"));
			}

		} catch (SQLException exsql) {

			try {
				connection.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // ************* rollback **************
			exsql.printStackTrace();
			throw new ExceptionAccesDonnees(
					UtilFichier.getValeur("erreur.base.requete") + " "
							+ exsql.getErrorCode() + " " + exsql.getMessage());

		}

		catch (Exception ex) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // ************* rollback **************
			ex.printStackTrace();
			throw new ExceptionAccesDonnees(
					UtilFichier.getValeur("erreur.livre.ajout"));
		} finally {
			result = null;
			stmt = null;
			pstmt = null;
			pstmt2 = null;
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.cnam.bea.ebook.Dao.ILivreDao#supprimer(java.lang.String)
	 */
	@Override
	public void supprimer(String isbn) throws ExceptionAccesDonnees {
		// TODO Auto-generated method stub
		ResultSet result = null;
		Statement stmt = null;
		Connection connection = null;
		PreparedStatement pstmt = null;
		int idLivre = 0;

		try {

			connection = BaseDonneesPostgresql.getInstance();

			connection.setAutoCommit(false);
			// ******************************** transaction

			// recuperer id du livre
			String reqId = "select idlivre,isbn from livre where isbn=?";
			pstmt = connection.prepareStatement(reqId);
			pstmt.setString(1, isbn);
			result = pstmt.executeQuery();
			if (result != null) {
				while (result.next()) {
					idLivre = result.getInt(1);
				}

			} else {
				throw new ExceptionAccesDonnees(
						fr.cnam.bea.ebook.Utilitaires.UtilFichier
								.getValeur("erreur.livre.supprimer"));
			}
			result.close();
			pstmt.close();

			// supprimer ds livre_auteur et livre_editeur
			stmt = connection.createStatement();
			String reqDel = "delete from livre_auteur where idlivre=" + idLivre;
			stmt.executeUpdate(reqDel);
			reqDel = "delete from livre_editeur where idlivre=" + idLivre;
			stmt.executeUpdate(reqDel);
			reqDel = "delete from livre where idlivre=" + idLivre;
			stmt.executeUpdate(reqDel);

			connection.commit();

			result.close();
			stmt.close();
			if (!result.isClosed()) {
				result.close();
				result = null;
			}
			if (!pstmt.isClosed()) {
				pstmt.close();
				pstmt = null;
			}
			if (!connection.isClosed()) {
				BaseDonneesPostgresql.deconnexion(connection);
			}

		}

		catch (Exception ex) {
			try {
				connection.rollback();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} // ************* rollback **************

			throw new ExceptionAccesDonnees(
					UtilFichier.getValeur("erreur.livre.supprimer"));
		} finally {

			result = null;
			stmt = null;
			connection = null;

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.cnam.bea.ebook.Dao.ILivreDao#rechercher(java.lang.String)
	 */
	@Override
	public List<Livre> rechercher(String titre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Auteur> rechercherAuteurs() throws ExceptionAccesDonnees {
		// TODO Auto-generated method stub
		ResultSet result = null;
		Statement stmt = null;
		Connection connection = null;

		Auteur auteur = null;
		List<Auteur> listeResultat = new ArrayList<Auteur>();
		String nom, prenom;
		String requete = "SELECT * FROM \"Auteur\" order by nom";

		try {
			connection = BaseDonneesPostgresql.getInstance();
			stmt = connection.createStatement();
			result = stmt.executeQuery(requete);
			if (result != null) {
				while (result.next()) {
					nom = result.getString("nom");
					prenom = result.getString("prenom");
					auteur = new Auteur(nom, prenom);
					listeResultat.add(auteur);
				}
			}

			if (!result.isClosed()) {
				result.close();
			}
			if (!stmt.isClosed()) {
				stmt.close();
			}
			if (!connection.isClosed()) {
				BaseDonneesPostgresql.deconnexion(connection);
			}
			return listeResultat;
		} catch (Exception e) {
			System.out
					.println("Erreur sélection des auteurs " + e.getMessage());
			e.printStackTrace();
			throw new ExceptionAccesDonnees("Erreur sélection des auteurs "
					+ e.getMessage());
		} finally {

			result = null;
			stmt = null;
			connection = null;
		}
	}

	@Override
	public List<Editeur> rechercherEditeurs() throws ExceptionAccesDonnees {
		// TODO Auto-generated method stub
		ResultSet result = null;
		Statement stmt = null;

		Connection connection = null;
		Editeur editeur = null;
		List<Editeur> listeResultat = new ArrayList<Editeur>();
		String nom, pays;
		String requete = "SELECT * FROM \"Editeur\" order by nom";

		try {
			connection = BaseDonneesPostgresql.getInstance();
			stmt = connection.createStatement();
			result = stmt.executeQuery(requete);
			if (result != null) {
				while (result.next()) {
					nom = result.getString("nom");
					pays = result.getString("pays");
					editeur = new Editeur(nom, pays);
					listeResultat.add(editeur);
				}
			}
			if (!result.isClosed()) {
				result.close();
			}
			if (!stmt.isClosed()) {
				stmt.close();
			}
			if (!connection.isClosed()) {
				BaseDonneesPostgresql.deconnexion(connection);
			}
			return listeResultat;

		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionAccesDonnees("Erreur sélection des editeurs "
					+ e.getMessage());

		} finally {

			result = null;
			stmt = null;
			connection = null;
		}
	}

}
