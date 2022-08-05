package fr.m2i.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.m2i.methods.Application;

/**
 * Servlet implementation class ApplicationServlet
 */
@WebServlet("/Application")
public class ApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PAGE="/WEB-INF/pages/login.jsp";
	private static final String PAGE_ACCUEIL="/WEB-INF/pages/accueil.jsp"; 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplicationServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String deco = request.getParameter("deco");

		if (deco != null) {
			request.getSession().removeAttribute("logged");
		}
		this.getServletContext().getRequestDispatcher(PAGE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("DO POST");
		if (request.getSession().getAttribute("logged") == null) {
			request.setAttribute("error", true);
			this.getServletContext().getRequestDispatcher(PAGE).forward(request, response);
		}
		else {
			request.setAttribute("listeNews", Application.display());
			this.getServletContext().getRequestDispatcher(PAGE_ACCUEIL).forward(request, response);
		}
	
	}

}
