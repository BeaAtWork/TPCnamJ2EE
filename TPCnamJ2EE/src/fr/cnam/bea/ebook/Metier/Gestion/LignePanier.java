package fr.cnam.bea.ebook.Metier.Gestion;
import fr.cnam.bea.ebook.Metier.Catalogue.*;

public class LignePanier {
  private static int quantite=1;
  private Livre livre;
  private float prixligne=0.00f;  
  /**
 * 
 */
public LignePanier() {
	super();
}


/**
 * @param livre
 */
public LignePanier(Livre livre) {
	super();
	this.livre = livre;
}


public Livre getLivre() {
	return livre;
}


public void setLivre(Livre livre) {
	this.livre = livre;
}


public float getPrixligne (){
	  return (float)quantite*livre.getPrix();
  }


@Override
public String toString() {
	return "LignePanier [livre=" + livre + "]";
}
  
}
