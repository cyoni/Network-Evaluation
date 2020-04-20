/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

import Nodes.node_metadata;
import java.util.Collection;
import java.util.List;

/**
 * This interface represents a directional weighted graph.
 * The interface has a road-system or communication network in mind - and should support a large number of nodes (over 100,000).
 * The implementation should be based on an efficient compact representation (should NOT be based on a n*n matrix).
 *
 */

public interface Graph {
	/**
	 * return the node_data by the node_id,
	 * @param key - the node_id
	 * @return the node_data by the node_id, null if none.
	 */
	public node_metadata getNode(int key);
	/**
	 * return the data of the edge (src,dest), null if none.
	 * Note: this method should run in O(1) time.
	 * @param src
	 * @param dest
	 * @return
	 */
	public edge_metadata getEdge(int src, int dest);
	/**
	 * add a new node to the graph with the given node_data.
	 * Note: this method should run in O(1) time.
	 * @param n
	 */
	public void addNode(node_metadata n);
        /**
         * Connect an edge with weight w between node src to node dest.
         * * Note: this method should run in O(1) time.
         * @param edge - the edge with its values.
         */
	public void connect(Edge edge);
	/**
	 * This method return a pointer (shallow copy) for the
	 * collection representing all the nodes in the graph. 
	 * Note: this method should run in O(1) time.
	 * @return Collection<node_data>
	 */
	public Collection<node_metadata> getV();
	/**
	 * This method return a pointer (shallow copy) for the
	 * collection representing all the edges getting out of 
	 * the given node (all the edges starting (source) at the given node). 
	 * Note: this method should run in O(1) time.
	 * @return Collection<edge_data>
	 */
	public Collection<edge_metadata> getE(int node_id);
	
	/**
	 * Delete the node (with the given ID) from the graph -
	 * and removes all edges which starts or ends at this node.
	 * This method should run in O(n), |V|=n, as all the edges should be removed.
	 * @return the data of the removed node (null if none). 
	 * @param key
	 */
	public node_metadata removeNode(int key);
	/**
	 * Delete the edge from the graph, 
	 * Note: this method should run in O(1) time.
	 * @param src
	 * @param dest
	 * @return the data of the removed edge (null if none).
	 */
	public edge_metadata removeEdge(int src, int dest);
	/** return the number of vertices (nodes) in the graph.
	 * Note: this method should run in O(1) time. 
	 * @return
	 */
	public int nodeSize();
	/** 
	 * return the number of edges (assume directional graph).
	 * Note: this method should run in O(1) time.
	 * @return
	 */
	public int edgeSize();
        /**
         * returns the edges in the graph.
         * @return 
         */
        public List<edge_metadata> getEdges();
        /**
         * return the Mode Count - for testing changes in the graph.
         * @return
         */
	public int getMC();
        
        
         /**
	 * return the key by the node_id,
	 * @param id - the node_id
	 * @return the node_data by the node_id, null if none.
	 */
        public int getKeyById(int id);

        
//        /**
//         * Connect an edge by Id
//         * * Note: this method should run in O(1) time.
//         * @param edge - the edge with its values.
//         */
//        public void connectById(Edge edge);
        public List<edge_metadata>[] getArrayOfVertciesWithEdges();
        
        public List<node_metadata> getNeighbors(int node_key);

	
	
}
