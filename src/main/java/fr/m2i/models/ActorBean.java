package fr.m2i.models;

import java.beans.JavaBean;
import java.sql.Date;

@JavaBean
public class ActorBean {
	
	private String _prenom;
	private String _nom;
	private int _id;
	private Date _last_update;
	
	public ActorBean(String p, String n, int i, Date c) {
		this.setId(i);
		this.setPrenom(p);
		this.setNom(n);
		this.setLast_update(c);
	}

	public String getPrenom() {
		return _prenom;
	}

	public void setPrenom(String _prenom) {
		this._prenom = _prenom;
	}

	public String getNom() {
		return _nom;
	}

	public void setNom(String _nom) {
		this._nom = _nom;
	}

	public int getId() {
		return _id;
	}

	public void setId(int _id) {
		this._id = _id;
	}

	public Date getLast_update() {
		return _last_update;
	}

	public void setLast_update(Date _last_update) {
		this._last_update = _last_update;
	}
	
	

}
