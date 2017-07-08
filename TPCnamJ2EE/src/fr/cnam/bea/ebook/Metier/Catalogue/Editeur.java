package fr.cnam.bea.ebook.Metier.Catalogue;

import java.io.Serializable;

public class Editeur implements Serializable{
	
	public int idediteur;
	public  String nom;
	public String pays;
	
	
	/**
	 * 
	 */
	public Editeur() {
		super();
	}


	public Editeur(String nom, String pays) {
		super();
		this.nom = nom;
		this.pays = pays;
	}
	
	
	public  String getNom() {
		return nom;
	}
	public  void setNom(String nom) {
		this.nom = nom;
	}
	public  String getPays() {
		return pays;
	}
	public  void setPays(String pays) {
		this.pays = pays;
	}
	
	public int getIdediteur() {
		return idediteur;
	}


	public void setIdediteur(int idediteur) {
		this.idediteur = idediteur;
	}


	@Override
	public String toString() {
		return "Editeur [nom=" + nom + ", pays=" + pays + "]";
	}

}
