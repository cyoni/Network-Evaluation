/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_Connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Yoni
 */
public class DB_Connection {
    
    
    public static void main(String[] args) {
        
        DB_Connection obj_DB_Connection=new DB_Connection();
	Connection connection=null;
	connection=obj_DB_Connection.get_connection();
	System.out.println(connection);
        
     }
         
    public Connection get_connection() {
    Connection connection=null;
    try{
    Class.forName("com.mysql.cj.jdbc.Driver");
    connection=DriverManager.getConnection("jdbc:mysql://sql9.freemysqlhosting.net:3306/sql9327280","sql9327280","tUEjZTaZHh");
    }
    catch(Exception e){}
      return connection;
    }
    
}
