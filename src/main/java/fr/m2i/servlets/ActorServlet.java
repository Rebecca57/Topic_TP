package fr.m2i.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.m2i.methods.Utilities;
import fr.m2i.models.ActorBean;

/**
 * Servlet implementation class ActorServlet
 */
@WebServlet("/ActorServlet")
public class ActorServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PAGE="/WEB-INF/pages/actor.jsp";
	private static final String PAGEID="/WEB-INF/pages/idActor.jsp";
    private static final String BDD = "jdbc:mysql://localhost:3306/sakila";
    private static final String LOGIN = "root";
    private static final String MDP = "FormationM2i";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActorServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		//request.setAttribute("listeUsers", Utilities.getAll());
		this.getServletContext().getRequestDispatcher(PAGE).forward(request, response);
	}

	//Affichage des requetes sur Actor
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String type = request.getParameter("type");

		switch(type) {
		case "all":
			request.setAttribute("idListe", Utilities.getAll());
			break;
		case "nom":
			request.setAttribute("nomListe", Utilities.getNom(request.getParameter("nom")));
			break;
		case "id":
			request.setAttribute("nomListe", Utilities.getId(Integer.parseInt(request.getParameter("id"))));		
		}
		this.getServletContext().getRequestDispatcher(PAGE).forward(request, response);
	}

}
