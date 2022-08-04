package fr.m2i.models;

import java.beans.JavaBean;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@JavaBean
@Entity
@Table(name="news")
public class News{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	@Basic
	@Column(name="title")
	private String title;
	@Basic
	@Column(name="texte")
	private String texte;
	
	
	//Constructeur
	public News() {	
	}
	
	public News(String title, String texte) {
		this.setTitle(title);
		this.setTexte(texte);
	}
	public Integer getId() {
		return this.id;
	}
	public void setId(Integer id) {
		this.id=id;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	public String getTitle() {
		return this.title;
	}
	public void setTexte(String texte) {
		this.texte = texte;
	}
	public String getTexte() {
		return this.texte;
	}
	
	
	
}
