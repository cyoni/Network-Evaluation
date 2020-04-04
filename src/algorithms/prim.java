/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import graph.Edge;
import graph.Graph;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import utils.User_Dialog;
import java.util.Arrays; 

/**
 *
 * @author Yoni
 */
public class prim {

    private final Graph g;
    private final FW fw;
    private int V;

    public prim(graph.Graph g, FW fw){
        this.g = g;
        this.fw = fw;
    }
    
// A utility function to find the vertex with minimum key 
	// value, from the set of vertices not yet included in MST 
	int minKey(int key[], Boolean mstSet[]) 
	{ 
		// Initialize min value 
		int min = Integer.MAX_VALUE, min_index = -1; 

		for (int v = 0; v < V; v++) {
			if (mstSet[v] == false && key[v] < min) { 
				min = key[v]; 
				min_index = v; 
			} }
                
                User_Dialog.showAlert(min_index + "$$" + Arrays.toString(key));
		return min_index; 
	} 

	// A utility function to print the constructed MST stored in 
	// parent[] 
	private Queue<Edge> printMST(int parent[], int graph[][]) 
	{ 
            Queue<Edge> list_edges = new LinkedList<>();
                for (int i = 1; i < V; i++) 
                    list_edges.add(new Edge(i, graph[i][parent[i]], 1)); 
                return list_edges;
	} 

	// Function to construct and print MST for a graph represented 
	// using adjacency matrix representation 
	public Queue<Edge> primMST() 
	{ 
                int graph[][] = fw.getMat();
                V = g.nodeSize();
                
		// Array to store constructed MST 
		int parent[] = new int[V]; 

		// Key values used to pick minimum weight edge in cut 
		int key[] = new int[V]; 

		// To represent set of vertices not yet included in MST 
		Boolean mstSet[] = new Boolean[V]; 

		// Initialize all keys as INFINITE 
		for (int i = 0; i < V; i++) { 
			key[i] = Integer.MAX_VALUE; 
			mstSet[i] = false; 
		} 

		// Always include first 1st vertex in MST. 
		key[0] = 0; // Make key 0 so that this vertex is 
		// picked as first vertex 
		parent[0] = -1; // First node is always root of MST 

		// The MST will have V vertices 
		for (int count = 0; count < V - 1; count++) { 
			// Pick thd minimum key vertex from the set of vertices 
			// not yet included in MST 
			int u = minKey(key, mstSet); 

			// Add the picked vertex to the MST Set 
			mstSet[u] = true; 

			// Update key value and parent index of the adjacent 
			// vertices of the picked vertex. Consider only those 
			// vertices which are not yet included in MST 
			for (int v = 0; v < V; v++) 

				// graph[u][v] is non zero only for adjacent vertices of m 
				// mstSet[v] is false for vertices not yet included in MST 
				// Update the key only if graph[u][v] is smaller than key[v] 
				if (graph[u][v] != 0 && mstSet[v] == false && graph[u][v] < key[v]) { 
					parent[v] = u; 
					key[v] = graph[u][v]; 
				} 
		}
                
		return printMST(parent, graph); 
	} 

}
