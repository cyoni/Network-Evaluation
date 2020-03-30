/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;



public class DGraph implements Graph, Serializable {

	
	/**
	 * This class represents a graph.
         * @author Yoni
	 */
	private static final long serialVersionUID = -8995919428111032917L;
	
	private Map<Integer, Node> g;
	private Map<Integer, List<Edge>> e;

	public DGraph() {
		g = new HashMap<>();
		e = new HashMap<>();
	}
	
	@Override
	public Node getNode(int key) {
		return g.get(key); 
	}

	@Override
	public Edge getEdge(int src, int dest) {
		Edge edge = null;
		List<Edge> list = e.get(src);
		for (Edge current_edge : list) {
			if (current_edge.getDest() == dest) edge = current_edge; 
			
		}
		return edge;
	}

	@Override
	public void addNode(Node n) {
		g.put(n.getKey(), n);
	}

	@Override
	public void connect(int src, int dest, double w) {

		edge_metadata edge_new = new edge_metadata(src, dest, w);
		
		List<Edge> list = e.get(src);
		if (list == null) list = new ArrayList<>();
		list.add(edge_new);
		e.put(src, list);

/*	
		list = e.get(dest);
		if (list == null) list = new ArrayList<>();
		edge_new = new edge_metadata(dest, src, w);
		list.add(edge_new);
		e.put(dest, list);
		*/
				
	}

	@Override
	public Collection<Node> getV() {
		return (Collection<Node>) g.values() ;
	}

	@Override
	public Collection<Edge> getE(int node_id) {
		return e.get(node_id);
	}

	@Override
	public Node removeNode(int key) {
		List<Edge> old_node_edges = e.get(key);
		g.remove(key); // remove the node
		e.remove(key); //  remove its edges	
		for (Edge edge : old_node_edges) { // remove the edges that connect with him from his neighbors 
			int dest = edge.getDest();
			List<Edge> l = e.get(dest); 
			for (int i=0; i< l.size(); i++) {
				Edge edge_to_check = l.get(i);
				if (edge_to_check.getDest() == key)   {e.get(dest).remove(edge_to_check); i--;} 
			}
		}
		return null;
	}

	@Override
	public Edge removeEdge(int src, int dest) {
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
	public List<Edge> getEdges() {
		
//		List<Integer>[] array_graph = new ArrayList[this.g.size()];
//    	for (int i = 0; i < array_graph.length; i++) array_graph[i] = new ArrayList<>();
//		List<Edge> list = new ArrayList<>();
//		
//		for (Entry<Integer, List<Edge>> edges : e.entrySet()) {
//			List<Edge> list_Edges = edges.getValue();
//			for (Edge current_edge : list_Edges) {
//				//System.out.println(array_graph.length + "," + current_edge.getSrc() + "-" + current_edge.getDest());
//				if (array_graph[current_edge.getDest()].contains(current_edge.getSrc()) ) continue;
//				
//                                
//                                        
//				array_graph[current_edge.getSrc()].add(current_edge.getDest());
//				
//				//System.out.println("added " + current_edge.getSrc() + "," +current_edge.getDest());
//				list.add(current_edge);
//			}
//		}
//               
//		return list;

List<Edge> edges = new ArrayList<>();
for (Node n : g.values())  { // pass over the nodes
     int NodeKey = n.getKey(); // key of the node
     if (getE(NodeKey)!= null )
        edges.addAll(getE(NodeKey));
}
	return edges;
        }

}

        