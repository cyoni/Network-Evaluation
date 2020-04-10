/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;
import nodes.node_metadata;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import utils.User_Dialog;

/**
 * This class represents a graph.
 * @author Yoni
 */

public class DGraph implements Graph, Serializable {
    
	private static final long serialVersionUID = -8995919428111032917L;
	private Map<Integer, node_metadata> g;
	private Map<Integer, List<edge_metadata>> e;
        private Map<Integer, Integer> id2key;


	public DGraph() {
            g = new HashMap<>();
            e = new HashMap<>();
            id2key = new HashMap<>();
	}
	
	@Override
	public node_metadata getNode(int key) {
            return g.get(key); 
	}
        
       
        @Override
         public int getKeyById(int id) {
            return id2key.get(id);
         }

	@Override
	public edge_metadata getEdge(int src, int dest) {
	edge_metadata edge = null;
	List<edge_metadata> list = e.get(src);
            for (edge_metadata current_edge : list) {
            if (current_edge.getDest() == dest) edge = current_edge; 
            }
	return edge;
	}

	@Override
	public void addNode(node_metadata n) {
            g.put(n.getKey(), n);
            
            id2key.put( n.getId(), n.getKey());
            System.out.println("new node: " + n.getKey() + ". " + n.getId());
	}

	@Override
	public void connect(Edge edge) {
		List<edge_metadata> list = e.get(edge.getSrc());
		if (list == null) list = new ArrayList<>();
                
           /*     int x1 = (int) getNode(edge.getSrc()).getLocation().x();
                int x2 = (int) getNode(edge.getDest()).getLocation().x();
                int y1 = (int) getNode(edge.getSrc()).getLocation().y();
                int y2 = (int) getNode(edge.getDest()).getLocation().y();
             */   
            //    int  dis=(int) Math.sqrt((x2-x1)*(x2-x1) + (y2-y1)*(y2-y1));	
             //   edge.setWeight(dis);
		list.add(edge);
		e.put(edge.getSrc(), list);
                System.out.println("new adge: " + edge.getSrc() + "->" + edge.getDest());

               // list = e.get(edge.getDest());
           //     if (list == null) list = new ArrayList<>();
           //     list.add(edge);
            //    e.put(edge.getDest(), list);
             //   System.out.println("..adge: " + edge.getDest() + "->" + edge.getSrc());	
	}
       


	@Override
	public Collection<node_metadata> getV() {
		return (Collection<node_metadata>) g.values() ;
	}

	@Override
	public Collection<edge_metadata> getE(int node_id) {
		return e.get(node_id);
	}

	@Override
	public node_metadata removeNode(int key) {
		List<edge_metadata> old_node_edges = e.get(key);
		g.remove(key); // remove the node
		e.remove(key); //  remove its edges	
		for (edge_metadata edge : old_node_edges) { // remove the edges that connect with him from his neighbors 
			int dest = edge.getDest();
			List<edge_metadata> l = e.get(dest); 
			for (int i=0; i< l.size(); i++) {
				edge_metadata edge_to_check = l.get(i);
				if (edge_to_check.getDest() == key)   {e.get(dest).remove(edge_to_check); i--;} 
			}
		}
		return null;
	}

	@Override
	public edge_metadata removeEdge(int src, int dest) {
		e.remove(src);
		e.remove(dest);
		return null;
	}

	@Override
	public int nodeSize() {
		return g.size();
	}

	@Override
	public int edgeSize() {
		return e.size();
	}

	@Override
	public int getMC() {
		// TODO Auto-generated method stub
		return -100;
	}
	
        @Override
	public List<edge_metadata> getEdges() {
            List<edge_metadata> edges = new ArrayList<>();
            for (node_metadata n : g.values())  { // pass over the nodes
                 int NodeKey = n.getKey(); // key of the node
                 if (getE(NodeKey) != null)
                    edges.addAll(getE(NodeKey));
            }
                return edges;
        }
        
        
        @Override
        @SuppressWarnings("unchecked")
	public List<edge_metadata>[] getArrayOfVertciesWithEdges() {
	List<edge_metadata>[] array_graph = new ArrayList[g.size()];
    	
        for (Map.Entry<Integer,  List<edge_metadata>> entry : e.entrySet()){
        System.out.println(entry.getKey() + "," + entry.getValue());
        }
        
    	        for (int i=0; i<g.size(); i++){
                array_graph[i] = new ArrayList<>();
                Collection<edge_metadata> l = getE(i);
                if (l!=null)
                    array_graph[i].addAll(l);
		}
		return array_graph;
	}
	

}

        