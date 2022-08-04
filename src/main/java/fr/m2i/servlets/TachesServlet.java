package fr.m2i.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import fr.m2i.methods.Utilities;
import fr.m2i.models.Actor;
import fr.m2i.models.Tache;
import fr.m2i.models.TacheDAO;

/**
 * Servlet implementation class TachesServlet
 */

@WebServlet("/TachesServlet")
public class TachesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PAGE="/WEB-INF/pages/taches.jsp";
	@Resource(name="dataSource")
	private DataSource dataSource;
       
    //Constructeur
    public TachesServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	//Get affichage
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setAttribute("listeTaches", this.display());
		this.getServletContext().getRequestDispatcher(PAGE).forward(request, response);
	}
	
	//Post: add, delete, research, modify
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String type = request.getParameter("type");
		
		switch(type) {
		
			case "delete":
				this.delete(Integer.parseInt(request.getParameter("id")));
				break;
				
			case "modify":
				String nom = request.getParameter("nom");
				String description = request.getParameter("description");
				Date date;
				
				try {
					date = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"));
					Tache newTache = new Tache(nom, description, date);
					this.modify(Integer.parseInt(request.getParameter("id")),newTache);
				} catch (ParseException e1) {

					e1.printStackTrace();
				}
				
				break;
				
			case "add":
				String nom1 = request.getParameter("nom");
				String description1 = request.getParameter("description");
				
				Date date1;
				try {
					
					date1 = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("date"));
					Tache newTache1 = new Tache(nom1, description1, date1);
					this.add(newTache1);
				} catch (ParseException e) {

					e.printStackTrace();
					
				}// new Date();
				
				break;
				
			case "displayTache":
				request.setAttribute("listTacheS", this.displayTache(request.getParameter("nom")));
				break;
		}
		
		doGet(request, response);
	}
	
	
	// ------------------------------------------ Methodes internes -----------------------------------
	//ADD
	protected void add(Tache tache) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();
		
		boolean transac = false;
		try {
			em.getTransaction().begin();
			em.persist(tache);
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
	protected void delete(Integer id) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();
		System.out.println("ID :"+id);
		Tache tache = em.find(Tache.class,id);
		
		boolean transac = false;
		try {
			em.getTransaction().begin();
			em.remove(tache);
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
	protected void modify(Integer id, Tache tache) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();

		Tache tacheS = em.find(Tache.class,id);
		boolean transac = false;
		try {
			em.getTransaction().begin();
				tacheS.setNom(tache.getNom());
				tacheS.setDescription(tache.getDescription());
				tacheS.setDate(tache.getDate());
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
	
	//RESEARCH
	protected ArrayList<Tache> displayTache(String nom) {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();
		
		@SuppressWarnings("unchecked")
		ArrayList<Tache> listeTaches = (ArrayList<Tache>) em.createNativeQuery("SELECT * from taches WHERE nom LIKE ?", Tache.class)
				.setParameter(1, nom)
				.getResultList();

		em.close();
		return listeTaches;
	}
	

	//DISPLAY
	protected ArrayList<Tache> display() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();

		@SuppressWarnings("unchecked")
		ArrayList<Tache> listeTaches = (ArrayList<Tache>) em.createNativeQuery("SELECT * from taches", Tache.class)
				.getResultList();

		em.close();
		System.out.println(listeTaches);
		return listeTaches;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	//em.persist(nouvel actor)
	//em.remove(un actor)
	//em.refresh(entity) -- rafraichir DB
	//em.detach(entity) 
	//em.flush() -- on pousse tout dans la DB attention

/**
	//POST: modification de la DB san JPA
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//Récuperation de la méthode à utiliser
		String type = request.getParameter("type");
		
		DaoFactory df = DaoFactory.getInstance();
		TacheDAO tdao = df.getTacheDao();
		
		switch(type) {
		
			case "add":
	
				//Attributs de la tache 
				String nom = (String) request.getParameter("nom");
				String description = (String) request.getParameter("description");
				String dateS = (String) request.getParameter("date").replace("T", " ")+":00";
				System.out.println("Date du formulaire : "+dateS);
				Timestamp date = Timestamp.valueOf(dateS);
				System.out.println("Date du formulaire 2 : "+date);
				
				tdao.add(new Tache(nom,description,date));				
				
				break;
				
				
			case "delete":
				int id = Integer.parseInt(request.getParameter("id"));
				tdao.delete(id);
				
				break;	
				
				
			case "modify":
				
				int id1 = Integer.parseInt(request.getParameter("id"));
				String nom1 = request.getParameter("nom");
				String description1 = (String) request.getParameter("description");
				String dateS1 = (String) request.getParameter("date").replace("T", " ")+":00";;
				Timestamp date1 = Timestamp.valueOf(dateS1);
				
				tdao.modify(id1,new Tache(nom1,description1,date1));
						
			}
			this.doGet(request,response);
		
		}
	
**/
}
