package fr.cnam.bea.ebook.Metier.Catalogue;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import fr.cnam.bea.ebook.Dao.ILivreDao;
import fr.cnam.bea.ebook.Exceptions.ExceptionAccesDonnees;


// Catalogue qui respecte le contrat des livres ( impl�mente interface )
// Il doit donc red�finir l'ensemble des m�thodes de l'interface 
public class Catalogue  implements ILivreDao, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1746644984409067480L;
	// D�claration de la liste de livre 
	// List est une classe g�n�rique on doit donc pr�ciser le type des �l�ments 
	private List<Livre>  listeLivres = new ArrayList<Livre>() ;  

	public Catalogue () 
	{	
		init() ; 
	}
	
	// Simule la base de donn�e en initialisant le catalogue avec un ensemble de livres 
	public void init () 
	{
		SimpleDateFormat  df  = new SimpleDateFormat("dd/MM/yyyy");
		try {
		 	 Auteur auteur1 = new Auteur ( "Vigny" , "B�atrice" );
			 Auteur auteur2 = new Auteur ( "Dupond" , "Alfred" );
			  List<Auteur> auteurs = new ArrayList<Auteur>();
		       auteurs.add(auteur1);
		       auteurs.add(auteur2);
		       List<Auteur> auteurs2 = new ArrayList<Auteur>();
		       auteurs2.add(new Auteur("Dupond","Alfred"));
		       List<Auteur> auteurs3 = new ArrayList<Auteur>();
		       auteurs3.add(new Auteur("Martin","Alice"));
		       auteurs3.add(new Auteur("Martin","Benoit"));
		    		  
			 
			
			 List<Editeur> editeurs = new ArrayList<Editeur>();		    
		       editeurs.add(new Editeur("CNAM", "France"));		
		       List<Editeur> editeurs2 = new ArrayList<Editeur>();		    
		       editeurs2.add(new Editeur("Pocket", "France"));		
		    		  
       Livre livre = new Livre( "123456","Le premier livre",12.3f,1,df.parse( "01/12/1990") ,TCategorie.Informatique,auteurs,editeurs);
       Livre livre2 = new Livre( "223457","Le deuxi�me livre",18.5f,2,df.parse( "15/02/2001") ,TCategorie.Informatique,auteurs2,editeurs);
      Livre livre3 = new Livre( "323458","Le troisieme livre",2.5f,1,df.parse( "01/02/2012") ,TCategorie.Policier,auteurs3,editeurs2);		
      listeLivres.add(livre);
      listeLivres.add(livre2);
      listeLivres.add(livre3);
			
		} catch (ParseException e) {
				System.out.println ( e.getMessage()); 
		}
	}
	
	@Override
	public void ajouter ( Livre livre ) throws ExceptionAccesDonnees
	{
		try
		{
			listeLivres.add (livre) ;  
		}
		catch(Exception ex)
		{
		   throw new ExceptionAccesDonnees(  fr.cnam.bea.ebook.Utilitaires.UtilFichier.getValeur("erreur.livre.ajout")) ; 
		}
	}

	
	@Override
	public List<Livre> rechercher ( String titre ) throws ExceptionAccesDonnees
	{ 
		List<Livre> liste = new ArrayList<Livre>()   ; 
		try
		{
			// parcours de la liste 
			for ( Livre l : listeLivres )
			{
				// comparaison du titre avec la m�thode equals 
				if ( l.getTitre().equals(titre))
				{
					liste.add(l); 
				}
			}
		}
		catch(Exception ex)
		{
		   throw new ExceptionAccesDonnees(  fr.cnam.bea.ebook.Utilitaires.UtilFichier.getValeur("Probl�me de recherche par titre")) ; 
		}
		return liste ; 
	}
	
	@Override
	public void supprimer ( String isbn ) throws ExceptionAccesDonnees
	{
		try
		{
			for (  Livre l : listeLivres ) 
			{
				if ( l.getTitre().equals(isbn))
				{
					listeLivres.remove(l);
				}
			}
		}
		catch(Exception ex)
		{
		   throw new ExceptionAccesDonnees(  fr.cnam.bea.ebook.Utilitaires.UtilFichier.getValeur("erreur.livre.supprimer")) ; 
		}
	}
	
	public int getNbrLivres() {
		return listeLivres.size();
	}

	@Override
	public List<Livre> rechercher(String titre, String categorie, String isbn) throws ExceptionAccesDonnees{
		// TODO Auto-generated method stub
		List<Livre> liste = new ArrayList<Livre>() ; 
		try
		{
			// parcours de la liste 
			for ( Livre l : listeLivres )
			{
				// comparaison du titre avec la m�thode equals 
				if ( l.getTitre().equals(titre)|| l.getCategorie().name().equals(categorie)|| l.getIsbn().equals(isbn))
				{
					liste.add(l);
				} 
			}
			return liste  ; 
		}
		catch(Exception ex)
		{
		   throw new ExceptionAccesDonnees(  fr.cnam.bea.ebook.Utilitaires.UtilFichier.getValeur("Probl�me de recherche pour un crit�re titre , categorie, ou isbn")) ; 
		}
	}

	@Override
	public List<Auteur> rechercherAuteurs() throws ExceptionAccesDonnees{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Editeur> rechercherEditeurs() throws ExceptionAccesDonnees{
		// TODO Auto-generated method stub
		return null;
	}
}
