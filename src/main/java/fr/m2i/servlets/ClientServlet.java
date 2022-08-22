package fr.m2i.servlets;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.m2i.models.Client;
import fr.m2i.models.Commande;
import fr.m2i.models.News;

/**
 * Servlet implementation class ClientServlet
 */
@WebServlet("/Client")
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PAGE = "/WEB-INF/pages/client.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClientServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	//GET
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Client> clients = this.display();
		request.getSession().setAttribute("liste", clients);
		
		request.getServletContext().getRequestDispatcher(PAGE).forward(request,response);
	}

	//POST
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	
	
	/** -----------------------METHODS-------------------------- **/
	//DISPLAY ALL CLIENTS WITH COMMANDS
	public ArrayList<Client> display(){
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("UnityPersist");
		EntityManager em = factory.createEntityManager();

		//USERS
		@SuppressWarnings("unchecked")
		ArrayList<Client> listeClients = (ArrayList<Client>) em.createNativeQuery("SELECT * from client", Client.class)
				.getResultList();
		
		//COMMANDS FOREACH USER
		
		for(int i = 0 ; i < listeClients.size(); i++) {		
			Integer id = listeClients.get(i).getId();
			ArrayList<Commande> listeCommandes = (ArrayList<Commande>) em.createQuery("select c from Commande c where c.client.id ="+id, Commande.class).getResultList();
			listeClients.get(i).setCommandes(listeCommandes);			
		}

		em.close();

		return listeClients;
		
	}

}
