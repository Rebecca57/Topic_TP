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
import fr.m2i.models.Comment;
import fr.m2i.models.News;

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
		//request.setAttribute("listeComment", Application.displayComment());
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
				Integer id = Integer.parseInt(request.getParameter("id"));
				Integer userId = Integer.parseInt(request.getParameter("userId"));
				News news = new News(id,title, texte);
				Application.modify(userId,news);
						
				break;
				
			case "add":
				String title1 = request.getParameter("title");
				String texte1 = request.getParameter("texte");
				Integer userId1 = Integer.parseInt(request.getParameter("userId"));
				News news1 = new News(title1, texte1);
				Application.add(userId1,news1);
				
				break;	
				
			case "deleteComment":
				Application.deleteComment(Integer.parseInt(request.getParameter("id")));
				break;
				
			case "modifyComment":
				String text = request.getParameter("text");
				Comment comment = new Comment(text);
				Application.modifyComment(Integer.parseInt(request.getParameter("id")),comment);
						
				break;
				
			case "addComment":
				String text1 = request.getParameter("text");
				String newsId = request.getParameter("newsId");
				String userId2 = request.getParameter("userId");
				Comment comment1 = new Comment(text1);
				Application.addComment(comment1,Integer.parseInt(newsId),Integer.parseInt(userId2));
				
				break;
			}	
		
		doGet(request, response);
	}

}
