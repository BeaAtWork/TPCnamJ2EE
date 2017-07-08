package fr.cnam.bea.ebook.Metier.Catalogue;

import java.io.Serializable;

public class Auteur implements Serializable {
	
	public int idauteur;
	public String nom;
	public String prenom;
	
	/**
	 * 
	 */
	public Auteur() {
		super();
	}

	public Auteur(String nom, String prenom) {
		super();
		this.nom = nom;
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	

	public int getIdauteur() {
		return idauteur;
	}

	public void setIdauteur(int idauteur) {
		this.idauteur = idauteur;
	}

	@Override
	public String toString() {
		return "Auteur [nom=" + nom + ", prenom=" + prenom + "]";
	}

	

	
	
}
