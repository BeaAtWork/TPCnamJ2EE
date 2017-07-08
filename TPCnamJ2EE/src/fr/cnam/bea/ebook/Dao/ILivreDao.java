package fr.cnam.bea.ebook.Dao;

import java.util.List;

import fr.cnam.bea.ebook.Exceptions.ExceptionAccesDonnees;
import fr.cnam.bea.ebook.Metier.Catalogue.Auteur;
import fr.cnam.bea.ebook.Metier.Catalogue.Editeur;
import  fr.cnam.bea.ebook.Metier.Catalogue.Livre;

//Interface qui normalise le nom des messages pour les traitements sur les livres  
/**
 * @author Beatrice
 *
 */
public interface ILivreDao {
	public List<Livre> rechercher( String titre ) throws ExceptionAccesDonnees ;
	public void ajouter ( Livre livre ) throws ExceptionAccesDonnees ;
	public void supprimer ( String titre ) throws ExceptionAccesDonnees;
	public List<Livre> rechercher(String titre, String categorie, String isbn) throws ExceptionAccesDonnees ;
	public List<Auteur> rechercherAuteurs() throws ExceptionAccesDonnees;
	public List<Editeur> rechercherEditeurs() throws ExceptionAccesDonnees; 
}
