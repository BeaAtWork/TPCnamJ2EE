package fr.cnam.bea.ebook.Dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import fr.cnam.bea.ebook.Utilitaires.BaseDonneesPostgresql;
import fr.cnam.bea.ebook.Utilitaires.TraitementChaines;
import fr.cnam.bea.ebook.Utilitaires.UtilFichier;
import fr.cnam.bea.ebook.Exceptions.ExceptionAccesDonnees;
import fr.cnam.bea.ebook.Metier.Catalogue.Editeur;


/**
 * @author Beatrice
 *
 */
public class EditeurDaoDb  implements IEditeurDao {

	public EditeurDaoDb() {
		super();
		// TODO Auto-generated constructor stub
	}

	/* (non-Javadoc)
	 * @see fr.cnam.bea.ebook.Dao.IEditeurDao#rechercher(int)
	 */
	@Override
	public Editeur rechercher(int idediteur) throws ExceptionAccesDonnees {

		
		Editeur edit = new Editeur();
		ResultSet result=null;
		Statement stmt=null;
		Connection connection =null;

		String req = "";
		try {
			connection = BaseDonneesPostgresql.getInstance();		
			req = "SELECT nom, pays FROM editeur WHERE idediteur=" + idediteur;
			stmt = connection.createStatement();
			result = stmt.executeQuery(req);

			while (result.next()) {

				edit.setNom(result.getString(1));
				edit.setPays(result.getString(2));

			}

			result.close();
			stmt.close();
			return edit;
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

	/* (non-Javadoc)
	 * @see fr.cnam.bea.ebook.Dao.IEditeurDao#ajouter(fr.cnam.bea.ebook.Metier.Catalogue.Editeur)
	 */
	@Override
	public void ajouter(Editeur editeur) throws ExceptionAccesDonnees {
		// TODO Auto-generated method stub
		ResultSet result=null;
		Statement stmt=null;
		Connection connection =null;
		String req = "";
		try {
			connection = BaseDonneesPostgresql.getInstance();		

			req = "insert into editeur (nom, pays)  values('"
					+ editeur.getNom() + "','" + editeur.getPays() + "')";
			stmt = connection.createStatement();
			stmt.executeUpdate(req);
			if (!result.isClosed()) {
				result.close();
			}
			if (!stmt.isClosed()) {
				stmt.close();
			}
			if (!connection.isClosed()) {
				BaseDonneesPostgresql.deconnexion(connection);
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

	/* (non-Javadoc)
	 * @see fr.cnam.bea.ebook.Dao.IEditeurDao#supprimer(fr.cnam.bea.ebook.Metier.Catalogue.Editeur)
	 */
	@Override
	public boolean supprimer(Editeur editeur) throws ExceptionAccesDonnees {
		// TODO Auto-generated method stub
		
		return false;
	}

	/* (non-Javadoc)
	 * @see fr.cnam.bea.ebook.Dao.IEditeurDao#rechercher(fr.cnam.bea.ebook.Metier.Catalogue.Editeur)
	 */
	@Override
	public List<Editeur> rechercher(Editeur editeur) throws ExceptionAccesDonnees {
		// TODO Auto-generated method stub
		
		List<Editeur> listeEditeurs = new ArrayList<Editeur>();
		ResultSet result=null;
		Statement stmt=null;
		Connection connection =null;
		String req = "";
		try {
			connection = BaseDonneesPostgresql.getInstance();		
			req = "SELECT idediteur, nom, pays FROM editeur WHERE nom='"
					+ editeur.getNom() + "' and pays='" + editeur.getPays()
					+ "'";
			stmt = connection.createStatement();
			result = stmt.executeQuery(req);

			while (result.next()) {
				Editeur edit = new Editeur();
				edit.setIdediteur(result.getInt(1));
				edit.setNom(result.getString(2));
				edit.setPays(result.getString(3));
				listeEditeurs.add(edit);
				edit = null;
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

			return listeEditeurs;
		}

		catch (Exception ex) {
			ex.printStackTrace();
			return null;
		}

		finally {
			result = null;
			stmt = null;
			connection=null;


		}

	}

	/* (non-Javadoc)
	 * @see fr.cnam.bea.ebook.Dao.IEditeurDao#rechercher(java.lang.String, java.lang.String)
	 */
	@Override
	public List<Editeur> rechercher(String nom, String pays)
			throws ExceptionAccesDonnees {
		// TODO Auto-generated method stub
		
		List<Editeur> listeEditeurs = new ArrayList<Editeur>();
		ResultSet result=null;
		Statement stmt=null;
		Connection connection =null;
		String req = "";
		try {
			connection = BaseDonneesPostgresql.getInstance();		
			req = "SELECT idediteur, nom, pays FROM editeur WHERE nom='" + nom
					+ "' and pays ='" + pays + "'";
			stmt = connection.createStatement();
			result = stmt.executeQuery(req);

			while (result.next()) {
				Editeur edit = new Editeur();
				edit.setIdediteur(result.getInt(1));
				edit.setNom(result.getString(2));
				edit.setPays(result.getString(3));
				listeEditeurs.add(edit);
				edit = null;
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

			return listeEditeurs;
		}

		catch (Exception ex) {
			ex.printStackTrace();

			return null;
		}

		finally {
			result = null;
			stmt = null;
			connection=null;

		}

	}

	/* (non-Javadoc)
	 * @see fr.cnam.bea.ebook.Dao.IEditeurDao#rechercherNb(java.util.List)
	 */
	@Override
	public int rechercherNb(List<Editeur> listeEditeurs) {
		// requete avec liste nom et prenoms
		List<String> listeNoms = new ArrayList<String>();
		List<String> listePays = new ArrayList<String>();
		for (Editeur a : listeEditeurs) {
			listeNoms.add(a.getNom());
			listePays.add(a.getPays());
		}
		String listePenomsAvecSep = fr.cnam.bea.ebook.Utilitaires.TraitementChaines
				.listeAvecSeparateur(listeNoms, ',');
		String listePaysAvecSep = fr.cnam.bea.ebook.Utilitaires.TraitementChaines
				.listeAvecSeparateur(listePays, ',');

		String req = "";

		int nb = 0;
		return nb;
	}

	/* (non-Javadoc)
	 * @see fr.cnam.bea.ebook.Dao.IEditeurDao#existe(fr.cnam.bea.ebook.Metier.Catalogue.Editeur)
	 */
	@Override
	public boolean existe(Editeur editeur) throws ExceptionAccesDonnees {
	
		String req = "";
		ResultSet result=null;
		Statement stmt=null;
		Connection connection =null;
		try {
			connection = BaseDonneesPostgresql.getInstance();		
			req = "SELECT count(*) FROM editeur WHERE nom='" + editeur.getNom()
					+ "' and pays ='" + editeur.getPays() + "'";
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
				BaseDonneesPostgresql.deconnexion(connection);
			}
			return false;

		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}

		finally {
			result = null;
			stmt = null;
			connection=null;


		}
	}

	
	/* (non-Javadoc)
	 * @see fr.cnam.bea.ebook.Dao.IEditeurDao#rechercher(java.lang.String)
	 */
	@Override
	public List<Editeur> rechercher(String nom)throws ExceptionAccesDonnees {
		// TODO Auto-generated method stub
	
		return null;
	}

}
