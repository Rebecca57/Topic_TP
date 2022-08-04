package fr.m2i.models;
import java.beans.JavaBean;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@JavaBean
@Entity
@Table(name="taches")
public class Tache {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Basic
	@Column(name="nom")
	private String nom;
	
	@Basic
	@Column(name="description")
	private String description;
	
	@Column(name="date")
	@Temporal(TemporalType.DATE)
	private Date date;
	
	//Constructeur
	public Tache() {
	}
	
	public Tache(String n, String d,  Date da) {
		//this.setId(id);
		this.setNom(n);
		this.setDescription(d);
		this.setDate(da);
	}
	
	//Getter Setter
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public Tache setNom(String nom) {
		this.nom = nom;
		return this;
	}
	public String getDescription() {
		return description;
	}
	public Tache setDescription(String _description) {
		this.description = _description;
		return this;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	
}
