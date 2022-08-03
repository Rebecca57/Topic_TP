package fr.m2i.methods;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.HashMap;

import fr.m2i.models.ActorBean;
import fr.m2i.models.Tache;
import fr.m2i.models.TacheDAO;

public class MethodesTaches {
	 private static final String BDD = "jdbc:mysql://localhost:3306/CRUD";
	 private static final String LOGIN = "root";
	 private static final String MDP = "FormationM2i";
	
	public static ArrayList<Tache> delete(Tache tache) {
		ArrayList<Tache> elements = new ArrayList<>(); 
		try {
			
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Connection connection = DriverManager.getConnection(BDD,LOGIN,MDP);
			Statement state = connection.createStatement();
			 
			state.executeQuery("INSERT INTO taches(nom,description,date) "
					+ "VALUES ("+tache.getNom()+","+tache.getDescription()+","+tache.getDate()+"),");

			state.close();	
			connection.close();
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		elements.add(tache);
		System.out.println(elements+" "+elements.size());
		return elements;
	}
	
	public static void add(int id) {
	
		
	}
	
	
	public HashMap<Integer,Tache> getAll() {
		HashMap<Integer,Tache> elements = new HashMap<>(); 
		try {
			
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Connection connection = DriverManager.getConnection(BDD,LOGIN,MDP);
			Statement state = connection.createStatement();
			 
			ResultSet rs = state.executeQuery("select nom, description, date from taches");

			while(rs.next()) {
				String nom = rs.getString("nom");
				String description = rs.getString("description");
				Timestamp date = rs.getTimestamp("date");
				Integer id = rs.getInt("id");
				elements.put(id,new Tache(nom,description,date));
			}
			
			state.close();	
			connection.close();
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return elements;

	}
	
	
	

}
