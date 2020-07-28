package Database;

import ManageUsers.Gui_manageUsers;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import Utils.MouseCursor;

/**
 * This class connects to the database
 * @author Yoni
 */
public class PublicDatabase {
    


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
    
    public static int query_alter_db(String q){
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
        System.out.println("sql ans: " + rs);
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
   //connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/eval","root","1234");
    }
    catch(Exception e){}
      return connection;
    }


  
    
}
