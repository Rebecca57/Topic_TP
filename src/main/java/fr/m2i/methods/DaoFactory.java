package fr.m2i.methods;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import fr.m2i.models.TacheDAO;
import fr.m2i.models.TacheDaoImpl;

public class DaoFactory {
	//private String url;
    //private String username;
    //private String password;
    
    private DataSource dataSource;

    DaoFactory() throws NamingException {
        //this.url = url;
        //this.username = username;
        //this.password = password;
        
        Context envContext = InitialContext.doLookup("java:/comp/env");
        this.dataSource = (DataSource) envContext.lookup("dataSource");
    }

    //public static DaoFactory getInstance() {
    public static DaoFactory getInstance() {
        //try {
        //    Class.forName("com.mysql.cj.jdbc.Driver");
        //} catch (ClassNotFoundException e) {

        //}
        //System.out.println("Creation du DAOFactory");
        //DaoFactory instance = new DaoFactory("jdbc:mysql://localhost:3306/crud", "root", "FormationM2i");
        //return instance;
        
        DaoFactory instance = null;
        try {
            instance =new DaoFactory();

        }catch (NamingException e) {
            e.printStackTrace();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
    	System.out.println("Connection par DAO Factory avec DataSource");
        return this.dataSource.getConnection();
    }

    public TacheDAO getTacheDao() {
    	System.out.println("New DAO Tache");
        return new TacheDaoImpl(this);
    }
}

