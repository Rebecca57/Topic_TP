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
import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import fr.m2i.methods.DaoFactory;
import fr.m2i.methods.Utilities;
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

		DaoFactory df = DaoFactory.getInstance();
		TacheDAO tdao = df.getTacheDao();
		
		
		HashMap<Integer, Tache> listeTaches;
		try {
			listeTaches = tdao.display();
			request.getSession().setAttribute("listeTaches", listeTaches);	
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		this.getServletContext().getRequestDispatcher(PAGE).forward(request, response);
	}


	//POST: modification de la DB
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
	
	/** ------------------------------------ Methodes CRUD ----------------------------------------**/
	// DISPLAY  
	public HashMap<Integer,Tache> display(){
		 HashMap<Integer,Tache> elements = new HashMap<>();
	        Statement statement = null;
	        ResultSet resultat = null;

	        try(Connection connexion = dataSource.getConnection()){
	            statement = connexion.createStatement();
	            resultat = statement.executeQuery("SELECT nom, description,id, date FROM taches;");

	            System.out.println("DISPLAY");
	            while (resultat.next()) {
	                String nom = resultat.getString("nom");
	                String description = resultat.getString("description");
	                System.out.println("Date de serveur: "+resultat.getTimestamp("date"));
	                Timestamp date = resultat.getTimestamp("date");
	                System.out.println("Date de Client: "+date);

	                Integer id = Integer.parseInt(resultat.getString("id"));
	                
	                Tache tache = new Tache(nom, description, date);
	                System.out.println("Tache"+id+": "+tache.getNom());
	                elements.put(id,tache);
	            }
	            statement.close();
	            connexion.close();
	        } 
	        catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return elements;
	    }

}
