package Graph.Algorithms;

import Graph.Graph;
import Graph.Node;
import java.util.Collection;
import Nodes.node_metadata;
import Utils.StdDraw;

/**
 *
 * @author Yoni
 */
public class Graph_Mouse_Event {
    
    Graph graph;
    
    public static void show_data_of_node(Graph g, int x, int y){
       
        for (int i=0; i< g.nodeSize(); i++){
            node_metadata current_node = g.getNode(i);
            
            double distanceX = Math.abs(current_node.getLocation().x() - x);
            double distanceY = Math.abs(current_node.getLocation().y() - y);
            
            if (distanceX < 10 && distanceY < 10){
                StdDraw.text(current_node.getLocation().x(), current_node.getLocation().y()+5, current_node.getName());
                break;
            }
            
        }
        
    }
    
}
