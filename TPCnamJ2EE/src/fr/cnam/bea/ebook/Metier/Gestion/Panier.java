package fr.cnam.bea.ebook.Metier.Gestion;

import java.util.ArrayList;
import java.util.List;

import fr.cnam.bea.ebook.Metier.Catalogue.Livre;

public class Panier {

	
	 private List<LignePanier> lignesPanier; 
	 private float total=0.00f;
	 
	 /**
	 * 
	 */
	public Panier() {
		super();
	}


	public List<LignePanier> getLignesPanier() {
		return lignesPanier;
	}


	public void setLignesPanier(List<LignePanier> lignesPanier) {
		this.lignesPanier = lignesPanier;
	}

	 
	 public void ajouterLigne(Livre livre){
		if(lignesPanier==null)
		{
			lignesPanier = new ArrayList<LignePanier>();
		}
		//test existence
		if(!lignesPanier.contains(livre))
		{
			lignesPanier.add(new LignePanier(livre));
		   // recalculer
		   this.total = 0;
		   for(LignePanier ligne : lignesPanier)
		   {
			  this. total += ligne.getPrixligne();
		/*	  BigDecimal bd1 = new BigDecimal("1.3"); 
			  BigDecimal bd2 = new BigDecimal("1.2"); 
			  System.out.println(bd1.substract(bd2)); */
		   }
		}
		 
	 }
	 
	 
	 public void supprimerLigne(Livre livre){
		 
	 };
	 
	 public float getTotal(){
		 // calcul du total des lignes
		 float totalLignes = 0.0f;
		 for(LignePanier ligne : lignesPanier)
		 {
			 totalLignes += ligne.getPrixligne();
		 }
		 return totalLignes;
	 }
	 
	


	@Override
	public String toString() {
		return "Panier [lignesPanier=" + lignesPanier + "]";
	}
}
