package fr.cnam.bea.ebook.Beans;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import fr.cnam.bea.ebook.Metier.Catalogue.Livre;

public class LivreBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7198949412648821311L;
	/**
	 * 
	 */

	private List<Livre>  listeLivres = new ArrayList<Livre>() ;  
	private Date dateRecherche;
	

	public LivreBean(){
	
	}
	
	public Date getDateRecherche() {
		return dateRecherche;
	}


	public void setDateRecherche(Date dateRecherche) {
		this.dateRecherche = dateRecherche;
	}


	public List<Livre> getListeLivres() {
		return listeLivres;
	}


	public void setListeLivres(List<Livre> listeLivres) {
		this.listeLivres = listeLivres;
	}
	
	
	
	
}
