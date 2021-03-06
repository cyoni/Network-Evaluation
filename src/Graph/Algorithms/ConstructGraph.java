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
import java.awt.Color;

/**
 * This class create graph from database
 * @author caron
 */
public class ConstructGraph {

    private Graph graph;
    private int width;
    private int height;
    private LocalDatabase accessConnection_ToDatabase;
    private Statement statment;
    private NetworkQueriesCalculations cal;
    private Zone[] zones;

    public ConstructGraph(LocalDatabase accessConnection_toDatabase, int width, int height) {
        this.accessConnection_ToDatabase = accessConnection_toDatabase;
        statment = accessConnection_toDatabase.getStatment();
        this.width = width;
        this.height = height;
        graph = new DGraph();
        cal = new NetworkQueriesCalculations(accessConnection_ToDatabase);

        // createBaseNode();
        // addMembers(300,400,400);
        //  addPosts(100,600,500);
        //  addAdvertisers(50,300,300);
        //  addAds(50,400,400);
        //  addPages(100,100,100);
        //  addGroups(600,600,600);
        // groups: members, advertisers, items for sell, posts,  (add more if you can think of any)
        createZones(6);

        drawNodesInZone1();
        drawNodesInZone2();
        drawNodesInZone3();
        drawNodesInZone4();
        drawNodesInZone5();
        drawNodesInZone6();
        drawZones();

    }

    // read db and add to graph the members
    private void addMembers(Zone zone) {
        try {
            int numMembers = cal.CalNumOfMembers();
            // add nodes of members
            ResultSet rs = statment.executeQuery("SELECT T_Members.person_id, T_Persons.name, T_Members.friends FROM T_Members INNER JOIN T_Persons ON T_Members.person_id = T_Persons.person_id;");
            int idx = 0;
            while (rs.next()) {
                String name = rs.getString("name");
                int id = rs.getInt("person_id");
                int friends = rs.getInt("friends");
                Node m = new Member(name, friends, id, giveRandomPointInZone(zone));

                graph.addNode(m);
            }
            ResultSet rs1 = statment.executeQuery("SELECT * FROM [T_Friends]");
            while (rs1.next()) {
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

    private void addPosts(Zone zone) {
        try {
            int numPosts = cal.CalNumOfPosts();
            ResultSet rs = statment.executeQuery("SELECT [post_id] FROM [T_Posts];");
            int idx = 0;
            while (rs.next()) {
                int id = rs.getInt("post_id");
                Node p = new Post(id, giveRandomPointInZone(zone));
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

    /*
    
   

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
    
     */
    private void addAdvertisers(Zone zone) {

        try {
            int numAdvertisers = cal.CalNumOfAdvertisers();
            ResultSet rs = statment.executeQuery("SELECT [person_id] FROM [T_Advertisers]");
            int idx = 0;
            while (rs.next()) {
                int advertiserId = rs.getInt("person_id");
                Node p = new Advertiser(advertiserId, giveRandomPointInZone(zone));
                graph.addNode(p);
            }

        } catch (SQLException ex) {
            Logger.getLogger(ConstructGraph.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addAds(Zone zone) {

        try {
            int numAds = cal.CalNumOfAds();
            ResultSet rs = statment.executeQuery("SELECT [advertisement_id] ,[advertisers_id] FROM [T_advertisements]");
            int idx = 0;
            while (rs.next()) {
                int adId = rs.getInt("advertisement_id");
                int advertiserId = rs.getInt("advertisers_id");

                Node p = new Ad(adId, giveRandomPointInZone(zone));
                graph.addNode(p);

                int keySrc = graph.getKeyById(advertiserId);
                int keyDest = graph.getKeyById(adId);

                graph.connect(new Advertise(keySrc, keyDest, 1)); // advertise -[:Advertise] -> ad

            }
        } catch (SQLException ex) {
            Logger.getLogger(ConstructGraph.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addGroups(Zone zone) {
        try {
            int numGroups = cal.CalNumOfGroups();
            ResultSet rs = statment.executeQuery("SELECT [group_id] FROM [T_Groups];");
            int idx = 0;
            while (rs.next()) {
                int id = rs.getInt("group_id");
                Node p = new Group(id, giveRandomPointInZone(zone));
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

    public Graph getGraph() {
        return graph;
    }

    private void createBaseNode() {
        graph.addNode(new Node(-1, (new Point2D(ScreenSize.WIDTH / 2, ScreenSize.HEIGHT / 2)), Color.BLACK));
    }

    /**
     * creates a circle that represents zone
     *
     * @param number_of_zones
     */
    private void createZones(int number_of_zones) {
        zones = new Zone[number_of_zones];
//        int radius = 300;
//        zones = new Zone[number_of_zones];
//        
//        for (int i=0; i< number_of_zones; i++){
//            double angle = Math.random() * 2 * Math.PI;
//            double x = (Math.cos(angle)*radius) + ScreenSize.WIDTH/2;
//            double y =  (Math.sin(angle)*radius)+ ScreenSize.HEIGHT/2;
//            zones[i] = new Zone(new Point2D(x, y), 200);
//            System.out.println(x + "," + y);
//
//        }
//        
    }

    private void drawZones() {
        for (int i = 0; i < zones.length; i++) {
            graph.addNode(new Node(i, zones[i].position, Color.BLUE));
        }

    }

    private void addPages(Zone zone) {
        try {
            int numPages = cal.CalNumOfPages();
            ResultSet rs = statment.executeQuery("SELECT [page_id] FROM [T_Pages];");
            int idx = 0;
            while (rs.next()) {
                int id = rs.getInt("page_id");
                Node p = new Page(id, giveRandomPointInZone(zone));
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

    private Point2D giveRandomPointInZone(Zone zone) {

        double a = Math.random() * 2 * Math.PI;
        double r = zone.radius * Math.sqrt(Math.random());

        double x = r * Math.cos(a) + zone.position.x();
        double y = r * Math.sin(a) + zone.position.y();

        return new Point2D(x, y);
    }

    private void drawNodesInZone1() { // members
        zones[0] = new Zone(new Point2D(500, 350), 300);
        addMembers(zones[0]);
    }

    private void drawNodesInZone2() { // posts
        zones[1] = new Zone(new Point2D(800, 500), 150);
        addPosts(zones[1]);
    }

    private void drawNodesInZone3() { // advertisers
        zones[3] = new Zone(new Point2D(900, 100), 70);
        addAdvertisers(zones[3]);
    }

    private void drawNodesInZone4() { // ads
        zones[2] = new Zone(new Point2D(100, 600), 50);
        addAds(zones[2]);
    }

    private void drawNodesInZone5() { // pages
        zones[4] = new Zone(new Point2D(300, 300), 200);
        addPages(zones[4]);
    }

    private void drawNodesInZone6() { // groups
        zones[5] = new Zone(new Point2D(90, 90), 10);
        addGroups(zones[5]);
    }

}
