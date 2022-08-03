package fr.m2i.models;
import java.beans.JavaBean;
import java.sql.Timestamp;

@JavaBean
public class Tache {
	private String nom;
	private String description;
	private Timestamp date;
	
	//Constructeur
	public Tache(String n, String d,  Timestamp da) {
		this.setNom(n);
		this.setDescription(d);
		this.setDate(da);
	}
	
	//Getter Setter
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String _description) {
		this.description = _description;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp _date) {
		this.date = _date;
	}
	
	
}
