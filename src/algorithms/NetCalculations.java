/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author caron
 */
public class NetCalculations {
    
   private String dbPath;
   Connection conn;
   Statement statment ;

   
   public NetCalculations ( String path ) {
       this.dbPath = path;
       try {
           this.conn= DriverManager.getConnection("jdbc:ucanaccess://"+dbPath);
           this.statment = conn.createStatement();
       } catch (SQLException ex) {
           Logger.getLogger(NetCalculations.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
   
   /**
    * This method calculate the number of members in the network
    * @return
    * @throws SQLException 
    */
   public int CalNumOfMembers () throws SQLException {
        ResultSet rs = statment.executeQuery("SELECT Count(*) AS [Members] FROM [T_Members]");
         if(rs.next())
            return rs.getInt("Members");
         else
             return 0;
   }
   
   /**
    * This method calculate the number of likes in the network
    * @return
    * @throws SQLException 
    */
    public int CalNumOfLikes () throws SQLException {
        ResultSet rs = statment.executeQuery("SELECT Count(*) AS [Likes] FROM [T_Likes]");
         if(rs.next())
            return rs.getInt("Likes");
         else
             return 0;
   }
    
    /**
    * This method calculate the number of shares in the network
    * @return
    * @throws SQLException 
    */
    public int CalNumOfShares () throws SQLException {
        ResultSet rs = statment.executeQuery("SELECT Count(*) AS [Shares] FROM [T_Shares]");
         if(rs.next())
            return rs.getInt("Shares");
         else
             return 0;
   }
    
    /**
    * This method calculate the number of pages in the network
    * @return
    * @throws SQLException 
    */
   public int CalNumOfPages () throws SQLException {
        ResultSet rs = statment.executeQuery("SELECT Count(*) AS [Pages] FROM [T_Pages]");
         if(rs.next())
            return rs.getInt("Pages");
         else
             return 0;
   }
   
   
   public int CalActiveMembers () throws SQLException {
        ResultSet rs = statment.executeQuery("SELECT Count (*) AS [actives] FROM [T_Members] WHERE ((active)=Yes);");
         if(rs.next())
            return rs.getInt("actives");
         else
             return 0;
   } 
   // SELECT Avg (time_spent) AS avg_time FROM T_Members;
   
   public double CalAvgTime () throws SQLException {
        ResultSet rs = statment.executeQuery("SELECT Avg (time_spent) AS [avg_time] FROM [T_Members];");
         if(rs.next())
            return rs.getDouble("avg_time");
         else
             return 0;
   } 
}
