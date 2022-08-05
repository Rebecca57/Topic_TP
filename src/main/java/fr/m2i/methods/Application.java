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
		ArrayList<News> listeTaches = (ArrayList<News>) em.createNativeQuery("SELECT * from news", News.class)
				.getResultList();

		em.close();
		System.out.println(listeTaches);
		return listeTaches;
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
	
	//INSCRIPTION
	//Check username
	public static boolean checkUsername(String username){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();
		
		ArrayList<UserLogin> user = (ArrayList<UserLogin>) em.createNativeQuery("SELECT * from user WHERE username=?", UserLogin.class)
				.setParameter(1,username)
				.getResultList();
		
		System.out.println("USER: "+user);
		em.close();
		if (user!= null) {
			return false;	
		}else {
			return true;
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
		
	
	


}
