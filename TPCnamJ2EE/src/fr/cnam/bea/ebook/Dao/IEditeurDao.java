package fr.cnam.bea.ebook.Dao;

import java.sql.SQLException;
import java.util.List;

import fr.cnam.bea.ebook.Exceptions.ExceptionAccesDonnees;
import fr.cnam.bea.ebook.Metier.Catalogue.Editeur;

public interface IEditeurDao {

	Editeur rechercher(int idediteur) throws ExceptionAccesDonnees;

	List<Editeur> rechercher(String nom, String pays) throws ExceptionAccesDonnees ;

	List<Editeur> rechercher(String nom) throws ExceptionAccesDonnees ;

	List<Editeur> rechercher(Editeur editeur) throws ExceptionAccesDonnees  ;

	boolean supprimer(Editeur editeur) throws ExceptionAccesDonnees ;

	void ajouter(Editeur editeur) throws ExceptionAccesDonnees ;

	int rechercherNb(List<Editeur> editeurs);

	boolean existe(Editeur e) throws ExceptionAccesDonnees ;

}
