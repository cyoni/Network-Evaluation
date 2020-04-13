package graph_visualization;

import DB_Connection.AccesConnection;
import algorithms.ConstructGraph;
import algorithms.Graph_Algo;
import algorithms.line;
import graph.Graph;
import graph.Node;
import graph.edge_metadata;
import gui.ScreenSize;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import nodes.Ad;
import nodes.Advertiser;
import nodes.Member;
import nodes.Post;
import nodes.node_metadata;
import relationship.Friend;
import relationship.Like;
import relationship.RelationshipTypes;
import utils.Point2D;
import utils.StdDraw;

/**
 *
 * @author Yoni
 */
public class Draw {

    private Graph graph;
    private AccesConnection accessConnection_toDatabase;
    private Set<String> relationship_to_filter = new HashSet<String>();

    public Draw(Graph graph, AccesConnection accessConnection_toDatabase){
        this.graph = graph;
        this.accessConnection_toDatabase = accessConnection_toDatabase;
    }
    
    public void drawGraph(){   
        StdDraw.setCanvasSize(ScreenSize.WIDTH, ScreenSize.HEIGHT);
        StdDraw.setXscale(0, ScreenSize.WIDTH);
        StdDraw.setYscale(0, ScreenSize.HEIGHT);
        drawNodes(); 
        drawEdges();
    }

    private void drawConnection(node_metadata src, node_metadata dest, Point2D label_location) {
        double degree = Math.toDegrees(Math.atan2(src.getLocation().y()-dest.getLocation().y(), src.getLocation().x()-dest.getLocation().x()));
        StdDraw.text(label_location.x(), label_location.y()+9, graph.getEdge(src.getKey(), dest.getKey()).getTag(), degree);// print text connection
    }

    public void add_to_filter(String str) {
        relationship_to_filter.add(str);
    }

    public void remove_from_filter(String str) {
        if (relationship_to_filter.contains(str)) 
            relationship_to_filter.remove(str);
    }
    
    public Set<String> getRelationshipHashMap(){return relationship_to_filter;}

    private void drawEdges() {
        List<edge_metadata> graphEdge = new ArrayList<>();
        graphEdge = graph.getEdges();
            for (edge_metadata current_edge : graphEdge){
                if (!relationship_to_filter.contains(current_edge.getTag())){
                    StdDraw.setPenColor(current_edge.getColor());          
                    // print the edges and connection
                    StdDraw.setPenRadius(0.005);
                    node_metadata Node_src = graph.getNode(current_edge.getSrc());
                    node_metadata Node_dest = graph.getNode(current_edge.getDest());
                    Point2D label_location = line.getPointOnLine(new Point2D(Node_src.getLocation().x(), Node_src.getLocation().y()), new Point2D(Node_dest.getLocation().x(), Node_dest.getLocation().y()), 50);
                    drawConnection(Node_src, Node_dest, label_location);
                    StdDraw.line(graph.getNode(current_edge.getSrc()).getLocation().x(), graph.getNode(current_edge.getSrc()).getLocation().y(),
                    graph.getNode(current_edge.getDest()).getLocation().x(), graph.getNode(current_edge.getDest()).getLocation().y());  // draw line 
                }
            }
    }

    private void drawNodes() {
         List<node_metadata> graphNode =  new ArrayList<> (graph.getV());
         for (node_metadata n: graphNode) {
                StdDraw.setPenColor(n.getColor()); // get node color from the son class
                StdDraw.setPenRadius(0.02);
                StdDraw.point(n.getLocation().x(), n.getLocation().y()); // draw the node.
          }
    }
    
    
}
