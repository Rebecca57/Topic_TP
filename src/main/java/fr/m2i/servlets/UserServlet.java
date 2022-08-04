package fr.m2i.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.m2i.models.TacheDAO;
import fr.m2i.models.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String PAGE="/WEB-INF/pages/user.jsp";
       
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.getServletContext().getRequestDispatcher(PAGE).forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String prenom = request.getParameter("prenom");
		String nom = request.getParameter("nom");
		User user = new User(prenom, nom);
		try {
			System.out.println("transaction");//this.transaction(user);
		}
		catch(Exception e){
			System.out.println(e);
		}
		doGet(request, response);
	}
	
	/**
	private void transaction(User user) throws SQLException {

		Connection connexion = null;
		boolean transac = false;
		DaoFactory df = DaoFactory.getInstance();
        
		try {
			
			connexion = df.getConnection();
			connexion.setAutoCommit(false);
	        String userRequest = "INSERT INTO user(prenom, nom) VALUES(?, ?);";
	        try(PreparedStatement preparedStatement = connexion.prepareStatement(userRequest)) {
	            
	            preparedStatement.setString(1, user.getPrenom());
	            preparedStatement.setString(2, user.getNom());
	            preparedStatement.executeUpdate();
	        }
	        String logRequest = "INSERT INTO log(description) VALUES(?);";
	        try(PreparedStatement preparedStatement = connexion.prepareStatement(logRequest)) {
	        	System.out.println("Table logs is accessible");
	        	String log = "User "+user.getPrenom()+" "+user.getNom()+" a été créé";
	        	preparedStatement.setString(1, log);
	        	preparedStatement.executeUpdate();
	        	transac = true;
	        }
	        
		}
        finally {
        	System.out.println("transaction OK: "+transac);
        	if (transac) {
        		connexion.commit();
        		System.out.println("Connexion OK: les deux request ont été éxécutées");
        	}
        	else {
        		connexion.rollback();
        		System.out.println("Connexion KO: les requêtes n'ont pas été éxécutées");
        	}
        }
	}
	**/

}
