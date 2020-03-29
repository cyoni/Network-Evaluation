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
            ResultSet rs = statment.executeQuery("SELECT [person_id], [friends] FROM [T_Members]");
            int key = 0;
                    while ( rs.next()) {
                        key++;
                       String name = rs.getString("person_id");
                       int friends = rs.getInt("friends");
                       
                        Member m = new Member(name, friends,key , randomPoint ());
                        graph.addNode(m);

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
