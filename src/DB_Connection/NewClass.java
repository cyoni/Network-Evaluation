/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DB_Connection;

/**
 *
 * @author caron
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


// test java class to read the database 

public class NewClass {
    
    public static void main(String[] args) throws SQLException {
        
        Connection conn=DriverManager.getConnection("jdbc:ucanaccess://C:/tmp/test/zzz.accdb"); // need to change it to your path
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery("SELECT [person_id] FROM [T_Owners]");
        while (rs.next()) {
            System.out.println(rs.getString(1));
        }

    }

}
