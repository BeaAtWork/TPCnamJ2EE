 package fr.cnam.bea.ebook.Dao;

import java.lang.reflect.Constructor;
import fr.cnam.bea.ebook.Utilitaires.UtilFichier;

import fr.cnam.bea.ebook.Metier.Catalogue.Catalogue;

// Fabrique qui centralise la création d'objet de type catalogue
public class FabriqueDao {
	
	
	// Renvoie un catalogue ( Version simple ) 
	public static ILivreDao getInstanceV1 () 
	{
		return new Catalogue () ; 
	}
	
	// Renvoie un catalogue ( Version2 )
	// On va chercher le nom du catalogue dans un fichier de config
	public static ILivreDao getInstanceLivre () 
	{
		Class c = null ;
		ILivreDao obj=null; 
		try {
			c=  Class.forName(UtilFichier.getValeur("livreDao"));
			Constructor<ILivreDao> constr = c.getDeclaredConstructor();
			obj = constr.newInstance();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj ; 
	}
	
	public static IAuteurDao getInstanceAuteur () 
	{
		Class c = null ;
		IAuteurDao obj=null; 
		try {
			c=  Class.forName(UtilFichier.getValeur("auteurDao"));
			Constructor<IAuteurDao> constr = c.getDeclaredConstructor();
			obj = constr.newInstance();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj ; 
	}
	
	public static IEditeurDao getInstanceEditeur() 
	{
		Class c = null ;
		IEditeurDao obj=null; 
		try {
			c=  Class.forName(UtilFichier.getValeur("editeurDao"));
			Constructor<IEditeurDao> constr = c.getDeclaredConstructor();
			obj = constr.newInstance();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj ; 
	}
	
	public static ICategorieDao getInstanceCategorie () 
	{
		Class c = null ;
		ICategorieDao obj=null; 
		try {
			c=  Class.forName(UtilFichier.getValeur("categorieDao"));
			Constructor<ICategorieDao> constr = c.getDeclaredConstructor();
			obj = constr.newInstance();
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obj ; 
	}
	
	
}