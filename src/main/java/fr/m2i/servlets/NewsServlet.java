package fr.m2i.servlets;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.m2i.methods.Application;
import fr.m2i.models.News;
import fr.m2i.models.Tache;

/**
 * Servlet implementation class NewsServlet
 */
@WebServlet("/NewsServlet")
public class NewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PAGE="/WEB-INF/pages/accueil.jsp";

	
    public NewsServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("coucou GET");
		request.setAttribute("listeNews", Application.display());
		System.out.println(request.getAttribute("listeNews"));
		this.getServletContext().getRequestDispatcher(PAGE).forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("coucou POST");
		String type = request.getParameter("type");
		switch(type) {
			case "delete":
				Application.delete(Integer.parseInt(request.getParameter("id")));
				break;
				
			case "modify":
				String title = request.getParameter("title");
				String texte = request.getParameter("texte");
				News news = new News(title, texte);
				Application.modify(Integer.parseInt(request.getParameter("id")),news);
						
				break;
				
			case "add":
				String title1 = request.getParameter("title");
				String texte1 = request.getParameter("texte");
				News news1 = new News(title1, texte1);
				Application.add(news1);
				
				break;		
			}	
		
		doGet(request, response);
	}

}
