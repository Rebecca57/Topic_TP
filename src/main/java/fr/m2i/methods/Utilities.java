package fr.m2i.methods;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import fr.m2i.models.ActorBean;

public class Utilities {
	
	 private static final String BDD = "jdbc:mysql://localhost:3306/sakila";
	 private static final String LOGIN = "root";
	 private static final String MDP = "FormationM2i";
	
	public static ArrayList<ActorBean> getAll(){
		ArrayList<ActorBean> elements = new ArrayList<>(); 
		try {
			
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Connection connection = DriverManager.getConnection(BDD,LOGIN,MDP);
			Statement state = connection.createStatement();
			 
			ResultSet rs = state.executeQuery("select first_name, last_name, last_update, actor_id from actor");

			while(rs.next()) {
				String prenom = rs.getString("first_name");
				String nom = rs.getString("last_name");
				int id = rs.getInt("actor_id");
				Date date = rs.getDate("last_update");
				elements.add(new ActorBean(prenom,nom,id,date));
			}
			
			state.close();	
			connection.close();
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		return elements;
	}
	
	public static ArrayList<ActorBean> getId(int id){
		ArrayList<ActorBean> elements = new ArrayList<>(); 
		try {
			
			System.out.println("id= "+id);
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Connection connection = DriverManager.getConnection(BDD,LOGIN,MDP);
			Statement state = connection.createStatement();
			ResultSet rs = state.executeQuery(
					"select first_name, last_name, last_update, actor_id from actor where actor_id="+id);


			while(rs.next()) {
				
					String prenom = rs.getString("first_name");
					String nom = rs.getString("last_name");
					Date date = rs.getDate("last_update");
					elements.add(new ActorBean(prenom,nom,id,date));
				}
			
			state.close();	
			connection.close();
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		return elements;
	}
	
	public static ArrayList<ActorBean> getNom(String nom){
		ArrayList<ActorBean> elements = new ArrayList<>(); 
		try {
			
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			Connection connection = DriverManager.getConnection(BDD,LOGIN,MDP);
			Statement state = connection.createStatement();
			 
			ResultSet rs = state.executeQuery(
					"select first_name, last_name, last_update, actor_id from actor where last_name='"+nom+"'");

				
			while(rs.next()) {

				String prenom = rs.getString("first_name");
				int id = rs.getInt("actor_id");
				Date date = rs.getDate("last_update");
				elements.add(new ActorBean(prenom,nom,id,date));
			}
			state.close();	
			connection.close();
			
		} catch (SQLException e) {
			System.out.println(e);
		}
		
		return elements;	
	}
	

}
