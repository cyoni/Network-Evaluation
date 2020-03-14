/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Yoni
 */
public class database {
    

    /**
     * This method runs queries. The use of this method is only to read from the DB.
     * @param q
     * @return 
     */
    public static ResultSet query(String q){
            	
        Connection connection = get_connection();
        ResultSet rs = null;
	PreparedStatement ps=null;
	try {
	    String query=q  +";";
	    ps = connection.prepareStatement(query);
            rs = ps.executeQuery();
          //   while(rs.next()){
         //        System.out.println("name- "+rs.getString("name"));
         //    }
	} catch (Exception e) {
	    System.out.println(e);
	}
        return rs;
    }
    
    
    /**
     * This method runs queries that are to change the database. (update/insert..)
     * @param q
     * @return 
     */
    
    public static int query_update(String q){
            	
        Connection connection = get_connection();
        int rs = -1;
	PreparedStatement ps=null;
	try {
	    String query=q  +";";
	    ps = connection.prepareStatement(query);
            rs = ps.executeUpdate();
	} 
        catch (Exception e) {
	    System.out.println(e);}
        return rs;
    }
         
    /**
     * This method connects to the remote DB
     * @return 
     */
    public static Connection get_connection() {
    Connection connection=null;
    try{
    Class.forName("com.mysql.cj.jdbc.Driver");
    connection=DriverManager.getConnection("jdbc:mysql://sql9.freemysqlhosting.net:3306/sql9327280","sql9327280","tUEjZTaZHh");
    }
    catch(Exception e){}
      return connection;
    }


  
    
}
