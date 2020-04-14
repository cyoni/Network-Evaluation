/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Database;

import Evaluation.NetCalculations;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author caron
 */
public class AccesConnection {
    
   private String dbPath;

   
   Connection conn;
   Statement statment ;
   
   public AccesConnection ( String path ) {
       this.dbPath = path;
       try {
           this.conn= DriverManager.getConnection("jdbc:ucanaccess://"+dbPath);
           this.statment = conn.createStatement();
       } catch (SQLException ex) {
           Logger.getLogger(NetCalculations.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
    
    public String getDbPath() {
        return dbPath;
    }

    public Connection getConn() {
        return conn;
    }

    public Statement getStatment() {
        return statment;
    }
}
