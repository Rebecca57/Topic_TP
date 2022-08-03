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
 * Servlet implementation class ActorName
 */
@WebServlet("/ActorName")
public class ActorName extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PAGE="/WEB-INF/pages/actor.jsp";
	private static final String PAGENAME="/WEB-INF/pages/nameActor.jsp";
    private static final String BDD = "jdbc:mysql://localhost:3306/sakila";
    private static final String LOGIN = "root";
    private static final String MDP = "FormationM2i";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ActorName() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		request.setAttribute("nomListe", Utilities.getNom(request.getParameter("nom")));
		//Redirection
		this.getServletContext().getRequestDispatcher(PAGENAME).forward(request, response);

	}

}
