/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph_visualization;

import DB_Connection.AccesConnection;
import algorithms.ConstructGraph;
import algorithms.Graph_Algo;
import algorithms.line;
import graph.Graph;
import graph.Node;
import graph.edge_metadata;
import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import nodes.Member;
import nodes.Post;
import nodes.node_metadata;
import relationship.Friend;
import relationship.Like;
import utils.Point2D;
import utils.StdDraw;

/**
 *
 * @author Yoni
 */
public class Draw {

    private Graph g;
    private AccesConnection acc;
   
    public Draw(Graph g, AccesConnection acc){
        this.g = g;
        this.acc = acc;
    }
    
    
    public void drawGraph(){
                
        StdDraw.setCanvasSize(1300, 800);
        StdDraw.setXscale(0, 100);
        StdDraw.setYscale(0, 100);
         
         // draw all nodes 
         List<node_metadata> graphtNode =  new ArrayList<> (g.getV());
         for (node_metadata n: graphtNode) {
             if (n instanceof Member)
                StdDraw.setPenColor(Color.red);
              else if (n instanceof Post) 
                StdDraw.setPenColor(Color.blue);
             
            StdDraw.setPenRadius(0.02);
            StdDraw.point(n.getLocation().x(), n.getLocation().y()); // draw the node(point)
            // print the key of the nodes
            StdDraw.setPenColor(StdDraw.BLACK);
            StdDraw.text(n.getLocation().x()+1, n.getLocation().y()+1.5, n.getName() + "(" + Math.floor(n.getLocation().x()) + "," + Math.floor( n.getLocation().y()) + ")"); // print the id of the point
          }

          // draw all edges
            List<edge_metadata> graphEdge = new ArrayList<>();
            graphEdge = g.getEdges();
                for (edge_metadata current_edge : graphEdge){
                int src = current_edge.getSrcId();
                int dest = current_edge.getDestId();
                    
                if (current_edge instanceof Friend) 
                    StdDraw.setPenColor(Color.GREEN); // set color
                else if (current_edge instanceof Like) 
                    StdDraw.setPenColor(Color.BLUE);
                // .... else if...
                
                  // print the edges and connection
                   StdDraw.setPenRadius(0.005);
                   node_metadata Node_src = g.getNode(current_edge.getSrc());
                   node_metadata Node_dest = g.getNode(current_edge.getDest());
                   
                   Point2D label_location = line.getPointOnLine(new Point2D(Node_src.getLocation().x(), Node_src.getLocation().y()),
                     new Point2D(Node_dest.getLocation().x(), Node_dest.getLocation().y()), 50);
                   double x1 = Node_src.getLocation().x();
                   double y1 = Node_src.getLocation().y();
                   double x2 = Node_dest.getLocation().x();
                   double y2 = Node_dest.getLocation().y();
                   
                   double m = (y2-y1)/(x2-x1);
                          
                   drawConnection(Node_src, Node_dest, label_location);
                     
                     StdDraw.line(g.getNode(current_edge.getSrc()).getLocation().x(), g.getNode(current_edge.getSrc()).getLocation().y(),
                            g.getNode(current_edge.getDest()).getLocation().x(), g.getNode(current_edge.getDest()).getLocation().y());  // draw line 
                  
                             
//                     StdDraw.text(n.getLocation().x()+1, n.getLocation().y()+1.5, n.getKey()+"");
                }
                
    }

    private void drawConnection(node_metadata src, node_metadata dest, Point2D label_location) {
    
        // find the right most point:
        node_metadata right_node = null, left_node = null;

        if (src.getLocation().x() > dest.getLocation().x()){
            right_node = src;
            left_node = dest;
        }
        else {
            left_node = src;
            right_node = dest;
        }
        Point2D p3 = new Point2D(right_node.getLocation().x(), left_node.getLocation().y());
                
        double distance_x = line.distance(right_node.getLocation(), left_node.getLocation());
        double distance_y = right_node.getLocation().y() - p3.y();
        double degree = (Math.asin(distance_y / distance_x)*180/Math.PI);
        
        System.out.println("distance_x=" + distance_x + "distance_y="+distance_y + "degree:" + (degree)  + "x=" + right_node.getLocation().x() + ",y=" + right_node.getLocation().y()  );
        
        
        StdDraw.text(label_location.x(), label_location.y()+1.5, g.getEdge(src.getKey(), dest.getKey()).getTag(), degree);// print text connection
       

    }
}
