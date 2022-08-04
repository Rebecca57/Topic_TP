package fr.m2i.methods;


import java.util.ArrayList;

import javax.activation.DataSource;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
			if (user.getPsw().equals(psw)) return null;
			System.out.println(user.getNom());
		}
		System.out.println("USER: "+user);
		em.close();
		
		return user;
	}
	
	//CRUD NEWS
	//DISPLAY
	public static ArrayList<News> display() {
		
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
	public static void add(News news){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();

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
	public static void modify(Integer id, News news){
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();

		News newsU = em.find(News.class,id);
		boolean transac = false;
		try {
			em.getTransaction().begin();
				newsU.setTitle(news.getTitle());
				newsU.setTexte(news.getTexte());
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
	
	


}
