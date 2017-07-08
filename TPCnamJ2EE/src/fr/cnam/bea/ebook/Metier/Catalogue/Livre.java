/**
 * 
 */
package fr.cnam.bea.ebook.Metier.Catalogue;

import java.io.Serializable;
import java.util.*;

/**
 * @author Beatrice
 *
 */
public class Livre implements Serializable{
	
	

	private  String isbn;
	private  String titre;
	private  float prix;
	private  int quantite;
	private  Date dateParution;
	private TCategorie tcategorie;
	private List<Auteur> auteurs;
	private List<Editeur> editeurs;
	
	public Livre(){
		ArrayList<Auteur> auteurs = new ArrayList<Auteur>();
		ArrayList<Editeur> editeurs = new ArrayList<Editeur>();
		 Calendar cal = Calendar.getInstance();   // GregorianCalendar
		Calendar calTemp;
		    calTemp = (Calendar) cal.clone();
		    calTemp.set(9999,9,9 );
		    Date dt = calTemp.getTime();
		     TCategorie categorie  = TCategorie.Indéfinie;
		this.setAuteurs(auteurs);
		this.setEditeurs(editeurs);
      this.setCategorie(categorie);
      this.setDateParution(dt);
      this.setIsbn("0000000000000");
      this.setPrix(0);
      this.setQuantite(0);
      this.setTitre("");
	}
	
	
	public Livre(String isbn, String titre, float prix, int quantite,
			Date dateParution, TCategorie tcategorie, List<Auteur> auteurs,
			List<Editeur> editeurs) {
		super();
		this.isbn = isbn;
		this.titre = titre;
		this.prix = prix;
		this.quantite = quantite;
		this.dateParution = dateParution;
		this.tcategorie = tcategorie;
		this.auteurs = auteurs;
		this.editeurs = editeurs;
	}


	public  String getIsbn() {
		return isbn;
	}
	public  void setIsbn(String isbn) {
		this.isbn = isbn;
	}
	public  String getTitre() {
		return titre;
	}
	public  void setTitre(String titre) {
		this.titre = titre;
	}
	public  float getPrix() {
		return prix;
	}
	public  void setPrix(float prix) {
		this.prix = prix;
	}
	public  int getQuantite() {
		return quantite;
	}
	public  void setQuantite(int nombre) {
		this.quantite = nombre;
	}
	public  Date getDateParution() {
		return dateParution;
	}
	public  void setDateParution(Date dateParution) {
		this.dateParution = dateParution;
	}
	public  TCategorie getCategorie() {
		return tcategorie;
	}
	public void  setCategorie(TCategorie tcategorie) {
		this.tcategorie = tcategorie;
	}
	

	public List<Auteur> getAuteurs() {
	if (auteurs == null) {
	  this.auteurs = new ArrayList<Auteur>();
	}
	return auteurs;
	}
	public void setAuteurs(List<Auteur> auteurs) {
	 this.auteurs = auteurs;
	}
	
	
	public List<Editeur> getEditeurs() {
		if (this.editeurs == null) {
			editeurs = new ArrayList<Editeur>();
		}
		return editeurs;
		}
		public void setEditeurs(List<Editeur> editeurs) {
			this.editeurs = editeurs;
		}
	
	

	/** En supposant qu'il existe bien un auteur à l'index spécifié */
	public Auteur getAuteur(int iIndex) {
	return getAuteurs().get(iIndex);
	}
	
	/** En supposant qu'il existe bien un auteur à l'index spécifié */
	public Editeur getEditeur(int iIndex) {
	return getEditeurs().get(iIndex);
	}
	
	
	
	public void ajouter(int nombre)
	{
		this.quantite += nombre;
	}
	
	public boolean retirer(int nombre)
	{
		try
		{
			this.quantite -= nombre;
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}
	
	@Override
	public String toString() {
		return "Livre [isbn=" + isbn + ", titre=" + titre + ", prix=" + prix
				+ ", quantite=" + quantite + ", dateParution=" + dateParution
				+ ", tcategorie=" + tcategorie + ", auteurs=" + auteurs
				+ ", editeurs=" + editeurs + "]";
	}

  

}
