package neuprojekt;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */




import java.sql.*;
import java.sql.Connection;

/**
 *
 * @author tugce
 */
public class Connectionsql {
 
    static Connection con;
    static  Statement st;
    /**
     * @param args the command line arguments
     */
    public static Connection getConnection () {
        Connection connection = null;
        try {
System.out.println("Connecting database...");
Class.forName("org.hsqldb.jdbcDriver");
String url = "jdbc:hsqldb:file:C:\\Users\\tugce\\Desktop\\base\\example";
connection = DriverManager.getConnection(url, "tugce", "tugce");
System.out.println("Database connected!");
} catch (ClassNotFoundException e) {
System.out.println("Database connection error. ClassNotFoundException: " + e);
} catch (SQLException e) {
System.out.println("Database connection error. SQLException: " + e);
}
        
        
    return connection;
    }


  
    
}
