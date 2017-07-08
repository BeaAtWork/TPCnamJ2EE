package fr.cnam.bea.ebook.Utilitaires;

import java.util.List;
import java.util.StringTokenizer;

import fr.cnam.bea.ebook.Metier.Catalogue.Auteur;
import fr.cnam.bea.ebook.Metier.Catalogue.Editeur;
import fr.cnam.bea.ebook.Metier.Catalogue.Livre;


public class TraitementChaines {

	
	 public static String listeAvecSeparateur(List<String> liste, char separateur)
	   {
		  StringBuffer sb = new StringBuffer();
		  
			for(String ch : liste)
			{
				sb.append("'");
				sb.append(ch);
				sb.append("'");
			    sb.append(separateur);
			}
		 sb.deleteCharAt(sb.length()-1);
		  return sb.toString();
	   }
	 
	 public static Auteur creerAuteur(String auteurs)
	 {
		try
		{
			Auteur auteur= new Auteur();
			int i = 	auteurs.indexOf(" ");
			if(i>0)
			{
			 auteur.setNom(auteurs.substring(0,i));		 
			 auteur.setPrenom(auteurs.substring(i+1,auteurs.length()));
			 return auteur;	
			}
			else
			{
				auteur.setNom(auteurs);
			}
			return auteur;
		}
		catch(Exception ex)
		{
			return null;
		}
		
	 }

	public static Editeur creerEditeur(String editeurs) {
			try
			{
				Editeur editeur=new Editeur();
				int i = 	editeurs.indexOf(" ");
				if(i>0)
				{
					editeur.setNom(editeurs.substring(0,i));		 
					editeur.setPays(editeurs.substring(i+1,editeurs.length()));
				 return editeur;	
				}
				else
				{
					editeur.setNom(editeurs);
				}
			    return editeur;
			}
	
			catch(Exception ex)
			{
				return null;
			}
		
	}
	
	
	
}
