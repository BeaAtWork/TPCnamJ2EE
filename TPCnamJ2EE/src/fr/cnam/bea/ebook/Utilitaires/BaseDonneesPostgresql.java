package fr.cnam.bea.ebook.Utilitaires;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BaseDonneesPostgresql  {
	

	/**
	 * URL de connection
	 */
	private static String url = "jdbc:postgresql://127.0.0.1:5432/TPCnam";
	/**
	 * Nom du user
	 */
	private  static String user = "postgres";
	/**
	 * Mot de passe du user
	 */
	private static  String passwd = "Adminpostgre";
	/**
	 * 
	 *
	 */
	
	/**
	 * Méthode qui va nous retourner notre instance
	 * et la créer si elle n'existe pas...
	*/
	/**
	 * @return
	 */
	public static Connection getInstance() {
		Connection connect =null;
			try {
				Class.forName("org.postgresql.Driver");
				connect = DriverManager.getConnection(url, user, passwd);
			}
			catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				System.out.println("driver non trouvcé : "+e.getMessage());
			}
			 catch (SQLException sqle) {
					System.out.println("pb de connection : "+sqle.getMessage());
		   }
		
		return connect;	
	}	
	
	
	public static void deconnexion( Connection connection )
	{
		try
		{
			if (connection != null)
				connection.close();
		}catch(SQLException e)
		{
			System.out.println("Erreur de deconnexion" + e.getMessage () );
			e.printStackTrace() ; 
		}
	}
}
