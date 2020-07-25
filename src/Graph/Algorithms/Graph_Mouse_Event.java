package Graph.Algorithms;

import Graph.Graph;
import Graph.Node;
import Graph.NodeInfo;
import java.util.Collection;
import Nodes.node_metadata;
import Utils.StdDraw;

/**
 *
 * @author Yoni
 */
public class Graph_Mouse_Event {
    Graph graph;
    static NodeInfo nodeInfo = new NodeInfo();
    
    public static void show_data_of_node(Graph g, int x, int y){
       
       boolean found = false;
       node_metadata current_node = null;
        for (int i=0; i< g.nodeSize(); i++){
            current_node = g.getNode(i);
            
            double distanceX = Math.abs(current_node.getLocation().x() - x);
            double distanceY = Math.abs(current_node.getLocation().y() - y);
            System.out.println(current_node.getLocation().x() + ", " + current_node.getLocation().y() + "loc: " + x + ", " + y);
            if (distanceX < 10 && distanceY < 10){
                StdDraw.text(current_node.getLocation().x(), current_node.getLocation().y()+5, current_node.getName());
                found = true;
                break;
            }
            
        }
        if (found){
            nodeInfo.setNode(current_node);
            nodeInfo.setVisible(true);   
            nodeInfo.pack();
            System.out.println("FOUND NODE");
        }
        else{
            System.out.println("NODE WAS NOT FOUND");
        }
            
        
    }
    
}
