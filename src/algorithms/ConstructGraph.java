/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import DB_Connection.AccesConnection;
import graph.DGraph;
import graph.Edge;
import graph.Graph;
import graph.Member;
import graph.Post;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import relationship.Friend;
import relationship.Like;
import utils.Point2D;

/**
 *
 * @author caron
 */
public class ConstructGraph {
    
    private Graph graph;
    private int width;
    private int height;
    private AccesConnection acc;
    private Statement statment;


    public ConstructGraph(AccesConnection a, int width, int height) {
        this.acc = a;
        statment = acc.getStatment();
        this.width = width;
        this.height = height;
        graph = new DGraph();
        addMembers();
        addPosts();
       
    }
    
    // read db and add to graph the members
    private void addMembers() {
        try {
            // add nodes of members
            ResultSet rs = statment.executeQuery("SELECT T_Members.person_id, T_Persons.name, T_Members.friends FROM T_Members INNER JOIN T_Persons ON T_Members.person_id = T_Persons.person_id;");
                    while ( rs.next()) {
                       String name = rs.getString("name");
                       int key = rs.getInt("person_id");
                       int friends = rs.getInt("friends");
                       Member m = new Member(name, friends, key, randomPoint());
                       graph.addNode(m);
                    }
            ResultSet rs1 = statment.executeQuery("SELECT * FROM [T_Friends]");
            while ( rs1.next()) {
                int keySrc = rs1.getInt("person_id");
                int keyDest = rs1.getInt("friend_id");
                graph.connect(new Friend(keySrc, keyDest, 1)); // node -[:friends] -> node
                                }
        } catch (SQLException ex) {
            Logger.getLogger(ConstructGraph.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void addPosts() {
        try {
            ResultSet rs = statment.executeQuery("SELECT [post_id] FROM [T_Posts];");
            while (rs.next()) {
                       int key = rs.getInt("post_id");
                       Post p = new Post(key, randomPoint());
                       graph.addNode(p);
                    }
          ResultSet rs1 = statment.executeQuery("SELECT [post_id],[member_id] FROM [T_Posts] INNER JOIN T_Likes ON T_Posts.post_id = T_Likes.compoment_id;");
           while (rs1.next()) {
                       int memberKey = rs1.getInt("member_id");
                       int postKey = rs1.getInt("post_id");
                        graph.connect(new Like(memberKey, postKey, 1)); // member -[:likes] -> post
                    }
        } catch (SQLException ex) {
            Logger.getLogger(ConstructGraph.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
    // get random point in range of the bord
    private Point2D randomPoint() {
        int x = (int)  (Math.random() * width);
        int y = (int)(Math.random() * height) ;
        
        return new Point2D(x, y);
              
    }

    public Graph getGraph() {
        return graph;
    }
    
    
    
    
}
