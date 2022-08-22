package fr.m2i.models;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="comment")
public class Comment {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Basic
	@Column(name="text")
	private String text;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UserLogin user;
	
	@ManyToOne
	@JoinColumn(name = "news_id")
	private News news;
	
	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}

	public UserLogin getUser() {
		return user;
	}


	public void setUser(UserLogin user) {
		this.user = user;
	}


	public News getNews() {
		return news;
	}


	public void setNews(News news) {
		this.news = news;
	}


	//Constructeur
	public Comment() {	
	}
	
	public Comment(String text) {	
		this.text = text;
	}

}
