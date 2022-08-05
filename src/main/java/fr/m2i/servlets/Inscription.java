package fr.m2i.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.m2i.methods.Application;
import fr.m2i.models.UserLogin;

/**
 * Servlet implementation class Inscription
 */
@WebServlet("/Inscription")
public class Inscription extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PAGE_INSCRIPTION="/WEB-INF/pages/inscription.jsp";
	private static final String PAGE_LOGIN="/WEB-INF/pages/login.jsp";
	private static final String PAGE_ACCUEIL="/WEB-INF/pages/accueil.jsp";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Inscription() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if (request.getSession().getAttribute("logged") != null) {
			UserLogin loggedUser = (UserLogin) request.getSession().getAttribute("logged");
			if(loggedUser.isAdmin()) {
				request.getServletContext().getRequestDispatcher(PAGE_INSCRIPTION).forward(request, response);
			}
		}
		request.setAttribute("errorAdmin",true);
		request.getServletContext().getRequestDispatcher(PAGE_LOGIN).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ADMIN VALUE recupere: "+request.getParameter("admin"));
		boolean admin = request.getParameter("admin").equals("on")?true:false;
		String prenom = request.getParameter("prenom");
		String nom = request.getParameter("nom");
		String username = request.getParameter("username");
		String psw = request.getParameter("password");
		System.out.println("ADMIN VALUE: "+admin);
		UserLogin newUser = new UserLogin(prenom, nom, username, psw, admin);
		boolean rep = Application.register(newUser);
		if (!rep) {
			request.setAttribute("errorInscription","Le username "+newUser.getUsername()+" is not available");
		}

		request.getServletContext().getRequestDispatcher(PAGE_INSCRIPTION).forward(request, response);
	}

}
