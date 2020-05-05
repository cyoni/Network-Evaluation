package Database;

import Evaluation.NetworkQueriesCalculations;
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
public class LocalDatabase {
    
   private String dbPath;
   Connection conn;
   Statement statment ;
   
   public LocalDatabase (String path) {
       this.dbPath = path;
       try {
           this.conn= DriverManager.getConnection("jdbc:ucanaccess://" + dbPath);
           this.statment = conn.createStatement();
       } catch (SQLException ex) {
           Logger.getLogger(NetworkQueriesCalculations.class.getName()).log(Level.SEVERE, null, ex);
       }
   }
    
    public String getDbPath() {
        return dbPath;
    }

    public Connection getConnection() {
        return conn;
    }

    public Statement getStatment() {
        return statment;
    }
}
