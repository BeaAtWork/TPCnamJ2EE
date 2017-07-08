package fr.cnam.bea.ebook.Dao;

import java.sql.SQLException;

import fr.cnam.bea.ebook.Exceptions.ExceptionAccesDonnees;
import fr.cnam.bea.ebook.Metier.Catalogue.TCategorie;

public interface ICategorieDao {
	public int rechercher(String libelle) throws ExceptionAccesDonnees ;
	public void ajouter(TCategorie tcategorie) throws ExceptionAccesDonnees ;

}
