package fr.m2i.models;

public class User {
	
	private String prenom;
	private String nom;
	
	public User(String p, String n) {
		this.setPrenom(p);
		this.setNom(n);
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

}
