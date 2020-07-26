/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph.Algorithms;

import Evaluation.NetworkQueriesCalculations;
import Database.LocalDatabase;
import Graph.DGraph;
import Graph.Edge;
import Graph.Graph;
import Graph.Node;
import Utils.ScreenSize;
import Nodes.Member;
import Nodes.Post;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import Nodes.Ad;
import Nodes.Advertiser;
import Nodes.Group;
import Nodes.Page;
import Relationship.Advertise;
import Relationship.Friend;
import Relationship.Like;
import Relationship.Share;
import Utils.Point2D;
import Utils.StdDraw;

/**
 *
 * @author caron
 */
public class ConstructGraph {
    
    private Graph graph;
    private int width;
    private int height;
    private LocalDatabase accessConnection_ToDatabase;
    private Statement statment;
    private NetworkQueriesCalculations cal;


    public ConstructGraph(LocalDatabase accessConnection_toDatabase, int width, int height) {
        this.accessConnection_ToDatabase = accessConnection_toDatabase;
        statment = accessConnection_toDatabase.getStatment();
        this.width = width;
        this.height = height;
        graph = new DGraph();
        cal = new NetworkQueriesCalculations(accessConnection_ToDatabase);
        
        addMembers(300,400,400);
        addPosts(100,600,500);
        addAdvertisers(50,300,300);
        addAds(50,400,400);
        addPages(100,100,100);
        addGroups(400,500,500);
    }
    
    // read db and add to graph the members
    private void addMembers(double radius, double a, double b) {
        try {
            int numMembers = cal.CalNumOfMembers();
            // add nodes of members
            ResultSet rs = statment.executeQuery("SELECT T_Members.person_id, T_Persons.name, T_Members.friends FROM T_Members INNER JOIN T_Persons ON T_Members.person_id = T_Persons.person_id;");
           int idx = 0;
                    while ( rs.next()) {
                       String name = rs.getString("name");
                       int id = rs.getInt("person_id");
                       int friends = rs.getInt("friends");
                       Node m = new Member(name, friends, id, circlePoint(numMembers, idx++, radius, a, b));
                       
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
    
    private void addPosts(double radius, double a, double b) {
        try {
            int numPosts = cal.CalNumOfPosts();
            ResultSet rs = statment.executeQuery("SELECT [post_id] FROM [T_Posts];");
            int idx =0;
            while (rs.next()) {
                       int id = rs.getInt("post_id");
                       Node p = new Post(id, circlePoint(numPosts,idx++,radius,a,b));
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
           
            // add shares connections 
           ResultSet rs2 = statment.executeQuery("SELECT [post_id],[member_id] FROM [T_Posts] INNER JOIN T_Shares ON T_Posts.post_id = T_Shares.compoment_id;");
            while (rs2.next()) {
                       int memberId = rs2.getInt("member_id");
                       int postId = rs2.getInt("post_id");
                       int keySrc = graph.getKeyById(memberId);
                       int keyDest = graph.getKeyById(postId);

                        graph.connect(new Share(keySrc, keyDest, 1)); // member -[:share] -> post
                    }
            
        } catch (SQLException ex) {
            Logger.getLogger(ConstructGraph.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    
     private void addPages(double radius, double a, double b) {
        try {
            int numPages = cal.CalNumOfPages();
            ResultSet rs = statment.executeQuery("SELECT [page_id] FROM [T_Pages];");
            int idx =0;
            while (rs.next()) {
                       int id = rs.getInt("page_id");
                       Node p = new Page(id, circlePoint(numPages,idx++,radius,a,b));
                       graph.addNode(p);
                    }
          ResultSet rs1 = statment.executeQuery("SELECT [page_id],[member_id] FROM [T_Pages] INNER JOIN T_Likes ON T_Pages.page_id = T_Likes.compoment_id;");
           while (rs1.next()) {
                       int memberId = rs1.getInt("member_id");
                       int pageId = rs1.getInt("page_id");
                       int keySrc = graph.getKeyById(memberId);
                       int keyDest = graph.getKeyById(pageId);
                       
                        graph.connect(new Like(keySrc, keyDest, 1)); // member -[:likes] -> page
                    }
        } catch (SQLException ex) {
            Logger.getLogger(ConstructGraph.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
     
      private void addGroups(double radius, double a, double b) {
        try {
            int numGroups = cal.CalNumOfGroups();
            ResultSet rs = statment.executeQuery("SELECT [group_id] FROM [T_Groups];");
            int idx =0;
            while (rs.next()) {
                       int id = rs.getInt("group_id");
                       Node p = new Group(id, circlePoint(numGroups,idx++,radius,a,b));
                       graph.addNode(p);
                    }
          ResultSet rs1 = statment.executeQuery("SELECT [group_id],[member_id] FROM [T_Groups] INNER JOIN T_Likes ON T_Groups.group_id = T_Likes.compoment_id;");
           while (rs1.next()) {
                       int memberId = rs1.getInt("member_id");
                       int groupId = rs1.getInt("group_id");
                       int keySrc = graph.getKeyById(memberId);
                       int keyDest = graph.getKeyById(groupId);
                       
                        graph.connect(new Like(keySrc, keyDest, 1)); // member -[:likes] -> group
                    }
        } catch (SQLException ex) {
            Logger.getLogger(ConstructGraph.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    private void addAdvertisers(double radius, double a, double b) {
        
        try {
            int numAdvertisers = cal.CalNumOfAdvertisers();
            ResultSet rs = statment.executeQuery("SELECT [person_id] FROM [T_Advertisers]");
            int idx =0;
            while (rs.next()) {
                       int advertiserId = rs.getInt("person_id");
                       Node p = new Advertiser(advertiserId, circlePoint(numAdvertisers,idx++,radius,a,b));
                       graph.addNode(p);
            }
            

        } catch (SQLException ex) {
            Logger.getLogger(ConstructGraph.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   
     private void addAds(double radius, double a, double b) {
         
        try {
            int numAds = cal.CalNumOfAds();
        ResultSet rs = statment.executeQuery("SELECT [advertisement_id] ,[advertisers_id] FROM [T_advertisements]");
         int idx =0;
            while (rs.next()) {
                       int adId = rs.getInt("advertisement_id");
                       int advertiserId = rs.getInt("advertisers_id");
                       
                       Node p = new Ad(adId, circlePoint(numAds,idx++,radius,a,b));
                       graph.addNode(p);

                       
                       int keySrc = graph.getKeyById(advertiserId);
                       int keyDest = graph.getKeyById(adId);
                       
                       graph.connect(new Advertise(keySrc, keyDest, 1)); // advertise -[:Advertise] -> ad

                       
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
    
    private Point2D circlePoint(int n, int i, double radius, double a, double b) {

//        final double a = width / 2;
//        final int b = height / 2;
        System.out.println("n: " + n);
        System.out.println("i: " + i);

        double angle = 2 * Math.PI * i / n;
        System.out.println("angle: " + angle);

        Point2D p = new Point2D(a + (Math.cos(angle) * radius) , ScreenSize.HEIGHT - (b + (Math.sin(angle) * radius)) ); 
        System.out.println("p: " + p.toString());   
        return p;
    }

    public Graph getGraph() {
        return graph;
    }
    
    
    
    
}
