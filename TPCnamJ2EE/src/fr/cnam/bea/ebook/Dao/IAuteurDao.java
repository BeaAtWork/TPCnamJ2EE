package fr.cnam.bea.ebook.Dao;

import java.sql.SQLException;
import java.util.List;

import fr.cnam.bea.ebook.Exceptions.ExceptionAccesDonnees;
import fr.cnam.bea.ebook.Metier.Catalogue.Auteur;


/**
 * @author Beatrice
 *
 */
public interface IAuteurDao {
	public Auteur rechercher(int idauteur) throws ExceptionAccesDonnees ;
	public void ajouter(Auteur auteur) throws ExceptionAccesDonnees;
	public List<Auteur> rechercher(String nom) throws ExceptionAccesDonnees  ;
	List<Auteur> rechercher(String nom, String prenom) throws ExceptionAccesDonnees ;
	public int rechercherNb(List<Auteur> listeAuteurs);
	boolean supprimer(Auteur auteur) throws ExceptionAccesDonnees ;
	public boolean existe(Auteur a) throws ExceptionAccesDonnees ;
}
