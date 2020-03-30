/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import DB_Connection.AccesConnection;
import graph.DGraph;
import graph.Graph;
import graph.Member;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.Point2D;

/**
 *
 * @author caron
 */
public class ConstructGraph {
    
    Graph graph;
    int width;
    int heigh;
    AccesConnection acc;
    Statement statment ;


    public ConstructGraph( AccesConnection a, int width, int heigh) {
        acc =a;
        statment = acc.getStatment();
        this.width = width;
        this.heigh = heigh;
        graph = new DGraph();
        
        addMembers ();
       
    }
    
    // read db and add to graph the members
    private void addMembers () {
        try {
            // add nodes of members
            ResultSet rs = statment.executeQuery("SELECT T_Members.person_id, T_Persons.name, T_Members.friends FROM T_Members INNER JOIN T_Persons ON T_Members.person_id = T_Persons.person_id;");
                    while ( rs.next()) {
                       String name = rs.getString("name");
                       int key = rs.getInt("person_id");
                       int friends = rs.getInt("friends");
                       
                        Member m = new Member(name, friends,key , randomPoint ());
                        graph.addNode(m);
                    }
            ResultSet rs1 = statment.executeQuery("SELECT * FROM [T_Friends]");
            while ( rs1.next()) {
                int keySrc = rs1.getInt("person_id");
                int keyDest = rs1.getInt("friend_id");
                graph.connect(keySrc, keyDest, 1);
                                }
 

        } catch (SQLException ex) {
            Logger.getLogger(ConstructGraph.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    // get random point in range of the bord
    private Point2D randomPoint () {
        int x = (int)  (Math.random() * width);
        int y = (int)(Math.random() * heigh) ;
        
        return new Point2D(x, y);
              
    }

    public Graph getGraph() {
        return graph;
    }
    
    
    
    
}
