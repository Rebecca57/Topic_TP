package fr.m2i.methods;


import java.util.ArrayList;

import javax.activation.DataSource;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import fr.m2i.models.Commande;
import fr.m2i.models.Comment;
import fr.m2i.models.News;
import fr.m2i.models.UserLogin;


public class Application {
	
	@Resource(name="dataSource")
	private DataSource dataSource;
	
	//LOGIN
	public static UserLogin login(String username, String psw) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();
		
		UserLogin user = (UserLogin) em.createNativeQuery("SELECT * from user WHERE username=?", UserLogin.class)
				.setParameter(1,username)
				.getSingleResult();
		
		
		if (user!= null) {
			System.out.println("PSW DB= " +user.getPsw()+"  USER enterd: "+psw);
			if (user.getPsw().equals(psw)) {
				em.close();
				return user;	
			}
			
		}
		System.out.println("USER: "+user);
		em.close();
		
		return null;
	}
	
	//CRUD NEWS
	//DISPLAY
	public static ArrayList<News> display() {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();

		@SuppressWarnings("unchecked")
		ArrayList<News> listeNews = (ArrayList<News>) em.createNativeQuery("SELECT * from news", News.class)
				.getResultList();
		
		//COMMANDS FOREACH USER
		for(int i = 0 ; i < listeNews.size(); i++) {		
			Integer id = listeNews.get(i).getId();
			ArrayList<Comment> listeComments = (ArrayList<Comment>) em.createQuery("select c from Comment c where c.news.id ="+id, Comment.class).getResultList();
			listeNews.get(i).setComments(listeComments);			
		}

		em.close();
		return listeNews;
	}
	
	public static ArrayList<News> displayFive() {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();

		@SuppressWarnings("unchecked")
		ArrayList<News> listeTaches = (ArrayList<News>) em.createNativeQuery("SELECT * from news", News.class)
				.getResultList();

		em.close();
		System.out.println(listeTaches);
		return listeTaches;
	}
	
	//ADD
	public static void add(Integer userId, News news){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();

		UserLogin user = em.find(UserLogin.class,userId);
		news.setUser(user);
		boolean transac = false;
		try {
			em.getTransaction().begin();
			em.persist(news);
			transac = true;
		}
		finally {
			if (transac) {
				em.getTransaction().commit();
			}
			else {
				em.getTransaction().rollback();
			}	
		}			
		em.close();
	}
	
	//DELETE
	public static void delete(Integer id){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();

		News news = em.find(News.class,id);
		
		boolean transac = false;
		try {
			em.getTransaction().begin();
			em.remove(news);
			transac=true;
		}finally {
			if (transac) {
				em.getTransaction().commit();
			}
			else {
				em.getTransaction().rollback();
			}	
		}			
		em.close();
	}
	
	//MODIFY
	public static void modify(Integer userId, News news){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();

		News newsU = em.find(News.class,news.getId());
		UserLogin user = em.find(UserLogin.class,userId);
		boolean transac = false;
		try {
			em.getTransaction().begin();
				newsU.setTitle(news.getTitle());
				newsU.setTexte(news.getTexte());
				newsU.setUser(user);
				transac=true;
		}finally {
			if (transac) {
				em.getTransaction().commit();
			}
			else {
				em.getTransaction().rollback();
			}	
		}		
			
		em.close();
	}
	
	//INSCRIPTION
	//Check username
	public static boolean checkUsername(String username){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();
		UserLogin user = null;
		try{
			user = (UserLogin) em.createNativeQuery("SELECT * from user WHERE username=?", UserLogin.class)
					.setParameter(1,username)
					.getSingleResult();
		}catch (NoResultException nre){
			user = null;
		}

		System.out.println("USER: "+user);
		em.close();
		if (user == null) {
			return true;	
		}else {
			return false;
		}
		
	}
	//ADD
	public static boolean register(UserLogin user){
		
		if (Application.checkUsername(user.getUsername())) {
			EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
			EntityManager em = factory.createEntityManager();

			boolean transac = false;
			try {
				em.getTransaction().begin();
				em.persist(user);
				transac = true;
			}
			finally {
				if (transac) {
					em.getTransaction().commit();
				}
				else {
					em.getTransaction().rollback();
				}	
			}			
			em.close();
			return true;
		}else {
			return false;
		}
	}
	
	
	
	/** COMMENT **/
	public static ArrayList<Comment> displayComment(Integer newsId) {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();
		
		@SuppressWarnings("unchecked")
		ArrayList<Comment> listeComment = (ArrayList<Comment>) em.createNativeQuery("SELECT * from comment WHERE newsId =?", Comment.class)
				.setParameter(1,newsId)
				.getResultList();

		em.close();
		System.out.println(listeComment);
		return listeComment;
	}
	
	//ADD
	public static void addComment(Comment comment, Integer newsId, Integer userId){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();
		
		News news = em.find(News.class,newsId);
		comment.setNews(news);
		
		UserLogin user = em.find(UserLogin.class,userId);
		comment.setUser(user);

		boolean transac = false;
		try {
			em.getTransaction().begin();
			em.persist(comment);
			transac = true;
		}
		finally {
			if (transac) {
				em.getTransaction().commit();
			}
			else {
				em.getTransaction().rollback();
			}	
		}			
		em.close();
	}
	
	//DELETE
	public static void deleteComment(Integer id){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();

		Comment comment = em.find(Comment.class,id);
		
		boolean transac = false;
		try {
			em.getTransaction().begin();
			em.remove(comment);
			transac=true;
		}finally {
			if (transac) {
				em.getTransaction().commit();
			}
			else {
				em.getTransaction().rollback();
			}	
		}			
		em.close();
	}
	
	//MODIFY
	public static void modifyComment(Integer id, Comment comment){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();

		Comment commentU = em.find(Comment.class,id);
		boolean transac = false;
		try {
			em.getTransaction().begin();
				commentU.setText(comment.getText());
				transac=true;
		}finally {
			if (transac) {
				em.getTransaction().commit();
			}
			else {
				em.getTransaction().rollback();
			}	
		}		
			
		em.close();
	}
	
	//GET users of news
	//CRUD NEWS
	//DISPLAY
	public static ArrayList<News> getNewsUser() {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();

		@SuppressWarnings("unchecked")
		ArrayList<News> listeNews = (ArrayList<News>) em.createNativeQuery("SELECT * from news", News.class)
				.getResultList();
		
		//COMMANDS FOREACH USER
		for(int i = 0 ; i < listeNews.size(); i++) {		
			Integer id = listeNews.get(i).getId();
			ArrayList<Comment> listeComments = (ArrayList<Comment>) em.createQuery("select c from Comment c where c.news.id ="+id, Comment.class).getResultList();
			listeNews.get(i).setComments(listeComments);			
		}

		em.close();
		return listeNews;
	}
	
		
	
	


}
