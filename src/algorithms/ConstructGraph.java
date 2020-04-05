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
import graph.Node;
import nodes.Member;
import nodes.Post;
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
    private AccesConnection accessConnection_ToDatabase;
    private Statement statment;
    private NetCalculations cal;


    public ConstructGraph(AccesConnection accessConnection_toDatabase, int width, int height) {
        this.accessConnection_ToDatabase = accessConnection_toDatabase;
        statment = accessConnection_toDatabase.getStatment();
        this.width = width;
        this.height = height;
        graph = new DGraph();
        cal = new NetCalculations(accessConnection_ToDatabase);
        addMembers();
        addPosts();
    }
    
    // read db and add to graph the members
    private void addMembers() {
        try {
            int numMembers = cal.CalNumOfMembers();
            double RADIUS = 25;
            // add nodes of members
            ResultSet rs = statment.executeQuery("SELECT T_Members.person_id, T_Persons.name, T_Members.friends FROM T_Members INNER JOIN T_Persons ON T_Members.person_id = T_Persons.person_id;");
           int idx = 0;
                    while ( rs.next()) {
                       String name = rs.getString("name");
                       int id = rs.getInt("person_id");
                       int friends = rs.getInt("friends");
                       Node m = new Member(name, friends, id, circlePoint(numMembers, idx++, RADIUS,30,35));
                       graph.addNode(m);
                    }
            ResultSet rs1 = statment.executeQuery("SELECT * FROM [T_Friends]");
            while ( rs1.next()) {
                int idSrc = rs1.getInt("person_id");
                int idyDest = rs1.getInt("friend_id");
                
                int keySrc = graph.getKeyById(idSrc);
                int keyDest = graph.getKeyById(idyDest);       
                graph.connect(new Friend(keySrc, keyDest, 1)); // node -[:friends] -> node
                                }
        } catch (SQLException ex) {
            Logger.getLogger(ConstructGraph.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void addPosts() {
        try {
            int numPosts = cal.CalNumOfPosts();
            double RADIUS = 10;
            ResultSet rs = statment.executeQuery("SELECT [post_id] FROM [T_Posts];");
            int idx =0;
            while (rs.next()) {
                       int id = rs.getInt("post_id");
                       Node p = new Post(id, circlePoint(numPosts,idx++,RADIUS,70,70));
                       graph.addNode(p);
                    }
          ResultSet rs1 = statment.executeQuery("SELECT [post_id],[member_id] FROM [T_Posts] INNER JOIN T_Likes ON T_Posts.post_id = T_Likes.compoment_id;");
           while (rs1.next()) {
                       int memberId = rs1.getInt("member_id");
                       int postId = rs1.getInt("post_id");
                       
                        int keySrc = graph.getKeyById(memberId);
                        int keyDest = graph.getKeyById(postId);
                       
                        graph.connect(new Like(keySrc, keyDest, 1)); // member -[:likes] -> post
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
    
    private Point2D circlePoint(int n, int i, double radius, int a, int b) {

//        final double a = width / 2;
//        final int b = height / 2;
        System.out.println("n: " + n);
        System.out.println("i: " + i);

        double angle = 2 * Math.PI * i / n;
        System.out.println("angle: " + angle);

        Point2D p = new Point2D(
                a + (Math.cos(angle) * radius),
                b + (Math.sin(angle) * radius)
        );
        System.out.println("p: " + p.toString());   
        return p;
    }

    public Graph getGraph() {
        return graph;
    }
    
    
    
    
}
