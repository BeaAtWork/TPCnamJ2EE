package fr.cnam.bea.ebook.Dao;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import fr.cnam.bea.ebook.Utilitaires.BaseDonneesPostgresql;
import fr.cnam.bea.ebook.Utilitaires.BaseDonneesPostgresql;
import fr.cnam.bea.ebook.Exceptions.ExceptionAccesDonnees;
import fr.cnam.bea.ebook.Metier.Catalogue.TCategorie;


/**
 * @author Beatrice
 *
 */
public class CategorieDaoDb  implements ICategorieDao {
	
	
	public CategorieDaoDb() {
		super();
		// TODO Auto-generated constructor stub
	}

	
	/* (non-Javadoc)
	 * @see fr.cnam.bea.ebook.Dao.ICategorieDao#rechercher(java.lang.String)
	 */
	@Override
	public int rechercher(String libelle) throws ExceptionAccesDonnees{
	
		ResultSet result=null;
		Statement stmt=null;
		Connection connection =null;
	
	int idcat=0;
	// mysql
	//String reqCat = "select idcategorie, libelle from categorie where libelle='"+libelle+"'";
	
	
	//postgres
	String reqCat = "select id, nom from \"Categorie\" where nom='"+libelle+"'";
	 try
	 {
		 connection = BaseDonneesPostgresql .getInstance();		
		 stmt = connection.createStatement();		 
       result = stmt.executeQuery(reqCat);
      if(result!=null)
      {
       while (result.next())
        {
  		   idcat = result.getInt(1);
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
     	return idcat;
	 }
		
	   catch (Exception ex) {
		  ex.printStackTrace();
		  throw new ExceptionAccesDonnees(ex.getMessage());
		 
		  }
		  
		finally
		{
			result=null;
			stmt=null;		
			connection=null;

		}
	}

	
	/* (non-Javadoc)
	 * @see fr.cnam.bea.ebook.Dao.ICategorieDao#ajouter(fr.cnam.bea.ebook.Metier.Catalogue.TCategorie)
	 */
	@Override
	public void ajouter(TCategorie tcategorie) throws ExceptionAccesDonnees {
		// TODO Auto-generated method stub
		
		String req = "";
		Statement stmt=null;
		Connection connection =null;
		try {
			connection = BaseDonneesPostgresql.getInstance();		
			stmt =connection.createStatement();		    
			req = "insert into \"Categorie\"  (nom) values('" + tcategorie.name() +"')";			
			stmt.executeUpdate(req);
		
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
			  
			finally
			{			
				stmt=null;	
				connection=null;
			}
	}

}
