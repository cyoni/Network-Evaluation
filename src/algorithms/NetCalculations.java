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
   
  
   
   public int CalNumOfGroups () throws SQLException {
        ResultSet rs = statment.executeQuery("SELECT Count(*) AS [Groups] FROM [T_Groups]");
         if(rs.next())
            return rs.getInt("Groups");
         else
             return 0;
   }
   
   public int CalNumOfAdvertisers () throws SQLException {
        ResultSet rs = statment.executeQuery("SELECT Count(*) AS [Advertisers] FROM [T_Advertisers]");
         if(rs.next())
            return rs.getInt("Advertisers");
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
    
     public int CalNumOfEmployees () throws SQLException {
        ResultSet rs = statment.executeQuery("SELECT Count(*) AS [employees] FROM [T_Employees]");
         if(rs.next())
            return rs.getInt("employees");
         else
             return 0;
   }
     
     public double CalSumOfSalaries () throws SQLException {
        ResultSet rs = statment.executeQuery("SELECT sum(salary) AS [salaries] FROM [T_Employees]");
         if(rs.next())
            return rs.getDouble("salaries");
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
   
   public double CalAvgTime () throws SQLException {
        ResultSet rs = statment.executeQuery("SELECT Avg (time_spent) AS [avg_time] FROM [T_Members];");
         if(rs.next())
            return rs.getDouble("avg_time");
         else
             return 0;
   } 
   
 

    public double CalAvgViews () throws SQLException {
        ResultSet rs = statment.executeQuery("SELECT Avg (countOfDay) AS [avg_views] "
                + "FROM( SELECT Count(view_id) AS [countOfDay], view_date FROM [T_Views] GROUP BY view_date);");
         if(rs.next())
            return rs.getDouble("avg_views");
         else
             return 0;
   }
    
     public double CalAvgLikes () throws SQLException {
        ResultSet rs = statment.executeQuery("SELECT Avg (countOfDay) AS [avg_likes] "
                + "FROM( SELECT Count(like_id) AS [countOfDay], like_date FROM [T_Likes] GROUP BY like_date);");
         if(rs.next())
            return rs.getDouble("avg_likes");
         else
             return 0;
   }
     
      public double CalAvgShares () throws SQLException {
        ResultSet rs = statment.executeQuery("SELECT Avg (countOfDay) AS [avg_shares] "
                + "FROM( SELECT Count(share_id) AS [countOfDay], share_date FROM [T_Shares] GROUP BY share_date);");
         if(rs.next())
            return rs.getDouble("avg_shares");
         else
             return 0;
   } 
  

public double CalAvgPosts () throws SQLException {
        ResultSet rs = statment.executeQuery(" SELECT  Avg (countOfDay) AS [avg_posts] FROM "
                + "(SELECT  Count(T_Posts.post_id) AS [countOfDay], T_Components.creation_date FROM [T_Posts]"
                + " INNER JOIN T_Components ON T_Posts.post_id = T_Components.compoment_id GROUP BY creation_date ) ; ");
         if(rs.next())
            return rs.getDouble("avg_posts");
         else
             return 0;
   } 

   
}