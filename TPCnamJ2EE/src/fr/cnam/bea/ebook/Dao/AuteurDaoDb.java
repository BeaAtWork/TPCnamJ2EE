package fr.cnam.bea.ebook.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.cnam.bea.ebook.Utilitaires.BaseDonnees;
import fr.cnam.bea.ebook.Utilitaires.BaseDonneesPostgresql;
import fr.cnam.bea.ebook.Exceptions.ExceptionAccesDonnees;
import fr.cnam.bea.ebook.Metier.Catalogue.Auteur;

public class AuteurDaoDb implements IAuteurDao {

	public AuteurDaoDb() {
		super();
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.cnam.bea.ebook.Dao.IAuteurDao#rechercher(int)
	 */
	public Auteur rechercher(int idauteur) throws ExceptionAccesDonnees {

		Auteur aut = new Auteur();

		String req = "";
		ResultSet result = null;
		Statement stmt = null;
		Connection connection = null;
		try {
			connection = BaseDonneesPostgresql.getInstance();
			req = "SELECT nom, prenom FROM auteur WHERE idauteur=" + idauteur;
			stmt = connection.createStatement();
			result = stmt.executeQuery(req);

			while (result.next()) {

				aut.setNom(result.getString(1));
				aut.setPrenom(result.getString(2));

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
			return aut;
		}

		catch (Exception ex) {
			ex.printStackTrace();
			throw new ExceptionAccesDonnees(ex.getMessage());

		}

		finally {
			result = null;
			stmt = null;
			connection=null;

		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.cnam.bea.ebook.Dao.IAuteurDao#ajouter(fr.cnam.bea.ebook.Metier.Catalogue
	 * .Auteur)
	 */
	public void ajouter(Auteur auteur) throws ExceptionAccesDonnees {
		// TODO Auto-generated method stub

		String req = "";
		Statement stmt = null;
		Connection connection = null;

		try {
			connection = BaseDonnees.getInstance();
			req = "insert into auteur (nom, prenom)  values('"
					+ auteur.getNom() + "','" + auteur.getPrenom() + "')";
			stmt = connection.createStatement();
			stmt.executeUpdate(req);
			
			if (!stmt.isClosed()) {
				stmt.close();
			}
			if (!connection.isClosed()) {
				BaseDonnees.deconnexion(connection);
			}
		}

		catch (Exception ex) {
			ex.printStackTrace();
			throw new ExceptionAccesDonnees(ex.getMessage());
		}

		finally {

			stmt = null;
			connection=null;


		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.cnam.bea.ebook.Dao.IAuteurDao#supprimer(fr.cnam.bea.ebook.Metier.Catalogue
	 * .Auteur)
	 */
	public boolean supprimer(Auteur auteur) throws ExceptionAccesDonnees {
		// TODO Auto-generated method stub

		return false;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.cnam.bea.ebook.Dao.IAuteurDao#rechercher(java.lang.String)
	 */
	public List<Auteur> rechercher(String nom) throws ExceptionAccesDonnees {
		// TODO Auto-generated method stub

		List<Auteur> listeAuteurs = new ArrayList<Auteur>();
		ResultSet result = null;
		Statement stmt = null;
		Connection connection = null;

		String req = "";
		try {
			connection = BaseDonnees.getInstance();
			req = "SELECT idauteur, nom, prenom FROM auteur WHERE nom='" + nom
					+ "'";
			stmt = connection.createStatement();
			result = stmt.executeQuery(req);

			while (result.next()) {
				Auteur aut = new Auteur();
				aut.setIdauteur(result.getInt(1));
				aut.setNom(result.getString(2));
				aut.setPrenom(result.getString(3));
				listeAuteurs.add(aut);
				aut = null;
			}
			if (!result.isClosed()) {
				result.close();
			}
			if (!stmt.isClosed()) {
				stmt.close();
			}
			if (!connection.isClosed()) {
				BaseDonnees.deconnexion(connection);
			}
			return listeAuteurs;
		}

		catch (Exception ex) {
			ex.printStackTrace();
			throw new ExceptionAccesDonnees(ex.getMessage());

		}

		finally {
			result = null;
			stmt = null;
			connection=null;

		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.cnam.bea.ebook.Dao.IAuteurDao#rechercher(java.lang.String,
	 * java.lang.String)
	 */
	public List<Auteur> rechercher(String nom, String prenom)
			throws ExceptionAccesDonnees {
		// TODO Auto-generated method stub

		List<Auteur> listeAuteurs = new ArrayList<Auteur>();
		ResultSet result = null;
		Statement stmt = null;
		Connection connection = null;

		String req = "";
		try {
			connection = BaseDonnees.getInstance();
			req = "SELECT idauteur, nom, prenom FROM auteur WHERE nom='" + nom
					+ "' and prenom ='" + prenom + "'";
			stmt = connection.createStatement();
			result = stmt.executeQuery(req);

			while (result.next()) {
				Auteur aut = new Auteur();
				aut.setIdauteur(result.getInt(1));
				aut.setNom(result.getString(2));
				aut.setPrenom(result.getString(3));
				listeAuteurs.add(aut);
				aut = null;
			}

			if (!result.isClosed()) {
				result.close();
			}
			if (!stmt.isClosed()) {
				stmt.close();
			}
			if (!connection.isClosed()) {
				BaseDonnees.deconnexion(connection);
			}

			return listeAuteurs;
		}

		catch (Exception ex) {
			ex.printStackTrace();
			throw new ExceptionAccesDonnees(ex.getMessage());

		}

		finally {
			result = null;
			stmt = null;
			connection=null;


		}

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see fr.cnam.bea.ebook.Dao.IAuteurDao#rechercherNb(java.util.List)
	 */
	public int rechercherNb(List<Auteur> listeAuteurs) {
		// requete avec liste nom et prenoms
		List<String> listeNoms = new ArrayList<String>();
		List<String> listePrenoms = new ArrayList<String>();
		for (Auteur a : listeAuteurs) {
			listeNoms.add(a.getNom());
			listePrenoms.add(a.getPrenom());
		}

		// rechercher par nom avec contrainte prénom ...
		String listeNomsAvecSep = fr.cnam.bea.ebook.Utilitaires.TraitementChaines
				.listeAvecSeparateur(listeNoms, ',');

		String req = "";

		int nb = 0;
		return nb;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * fr.cnam.bea.ebook.Dao.IAuteurDao#existe(fr.cnam.bea.ebook.Metier.Catalogue
	 * .Auteur)
	 */
	public boolean existe(Auteur auteur) throws ExceptionAccesDonnees {

		String req = "";
		ResultSet result = null;
		Statement stmt = null;
		Connection connection = null;
		try {
			connection = BaseDonnees.getInstance();
			req = "SELECT count(*) FROM auteur WHERE nom='" + auteur.getNom()
					+ "' and prenom ='" + auteur.getPrenom() + "'";
			stmt = connection.createStatement();
			result = stmt.executeQuery(req);
			if (result.first()) {
				int nb = result.getInt(1);
				if (nb > 0)
					return true;
			}

			if (!result.isClosed()) {
				result.close();
			}
			if (!stmt.isClosed()) {
				stmt.close();
			}
			if (!connection.isClosed()) {
				BaseDonnees.deconnexion(connection);
			}
			return false;
		} catch (Exception ex) {
			ex.printStackTrace();
			throw new ExceptionAccesDonnees(ex.getMessage());

		}

		finally {
			result = null;
			stmt = null;
			connection=null;


		}
	}

}
