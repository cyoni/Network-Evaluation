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
            StdDraw.text(n.getLocation().x()+1, n.getLocation().y()+1.5, n.getName() + "(" + n.getKey() + ")"); // print the id of the point
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
                   Point2D text_pos = line.getPointOnLine(new Point2D(g.getNode(current_edge.getSrc()).getLocation().x(), g.getNode(current_edge.getSrc()).getLocation().y()),
                     new Point2D(g.getNode(current_edge.getDest()).getLocation().x(), g.getNode(current_edge.getDest()).getLocation().y()), 50);
                     StdDraw.text(text_pos.x()-1.5, text_pos.y()+1.5, g.getEdge(src, dest).getTag()+ ""); // print text connection
                     
                     StdDraw.line(g.getNode(current_edge.getSrc()).getLocation().x(), g.getNode(current_edge.getSrc()).getLocation().y(),
                            g.getNode(current_edge.getDest()).getLocation().x(), g.getNode(current_edge.getDest()).getLocation().y());  // draw line 
                     
//                     StdDraw.text(n.getLocation().x()+1, n.getLocation().y()+1.5, n.getKey()+"");
                }
                
    }
}
