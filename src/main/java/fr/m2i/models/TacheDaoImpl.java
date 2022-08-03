package fr.m2i.models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import fr.m2i.methods.DaoFactory;

public class TacheDaoImpl implements TacheDAO{
	
	 private DaoFactory daoFactory;
	 //private DataSource dataSource;
	 
	 public TacheDaoImpl(DaoFactory daoFactory){
	        this.daoFactory = daoFactory;
	        //Context envContext = InitialContext.doLookup("java:/comp/env");
	        //this.dataSource = (DataSource) envContext.lookup("dataSource");
	   }
	 
	 @Override
	    public void add(Tache tache) {
	        Connection connexion = null;
	        PreparedStatement preparedStatement = null;

	        try {
	            connexion = daoFactory.getConnection();
	            preparedStatement = connexion.prepareStatement("INSERT INTO taches(nom, description, date) VALUES(?, ?, ?);");
	            preparedStatement.setString(1, tache.getNom());
	            preparedStatement.setString(2, tache.getDescription());
	            preparedStatement.setTimestamp(3, tache.getDate());
	            

	            preparedStatement.executeUpdate();
	            preparedStatement.close();
	            connexion.close();
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }
	 
	 @Override
	    public HashMap<Integer,Tache> display(){
		 HashMap<Integer,Tache> elements = new HashMap<>();
	        Connection connexion = null;
	        Statement statement = null;
	        ResultSet resultat = null;

	        try{
	            connexion = daoFactory.getConnection();
	            statement = connexion.createStatement();
	            resultat = statement.executeQuery("SELECT nom, description,id, date FROM taches;");

	            while (resultat.next()) {
	                String nom = resultat.getString("nom");
	                String description = resultat.getString("description");

	                Timestamp date1 = resultat.getTimestamp("date");
	                //DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");  
	                //String strDate = dateFormat.format(date1).replace(" ","T");
	                //Timestamp newDate = Timestamp.valueOf(strDate);
	                //System.out.println("Date avant affichage dans formulaire"+newDate);
	                Integer id = Integer.parseInt(resultat.getString("id"));
	                
	                Tache tache = new Tache(nom, description, date1);
	                System.out.println("Tache"+id+": "+tache.getNom());
	                elements.put(id,tache);
	            }
	            statement.close();
	            connexion.close();
	        } catch (SQLException e) {
	            e.printStackTrace();
	      
	        }
	    
	        return elements;
	    }
	 
	 @Override
	    public void delete(Integer id) {
	        
	        PreparedStatement preparedStatement = null;
	        Connection connexion = null;
	        
	        try {
	            connexion = daoFactory.getConnection();
	            preparedStatement = connexion.prepareStatement("DELETE FROM taches WHERE id=?");
	            preparedStatement.setInt(1, id);
	           
	            preparedStatement.executeUpdate();
	            preparedStatement.close();
	            connexion.close();
	            
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	@Override
	public void modify(Integer id, Tache tache) {
		Connection connexion = null;
        PreparedStatement preparedStatement = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = connexion.prepareStatement("UPDATE taches SET nom = ?, description = ?, date = ?"
            		+ "WHERE id=?;");
            preparedStatement.setString(1, tache.getNom());
            preparedStatement.setString(2, tache.getDescription());
            preparedStatement.setTimestamp(3, tache.getDate());
            preparedStatement.setInt(4, id);
           
            preparedStatement.executeUpdate();
            preparedStatement.close();
            connexion.close();
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}
	 
	 

}
