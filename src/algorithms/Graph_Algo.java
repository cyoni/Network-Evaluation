/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import graph.Graph;
import graph.Node;
import graph.Edge;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Yoni
 */
public class Graph_Algo implements graph_algorithms , Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6772723883559432038L;
	
	
	private Graph g;
	private FW fw;
	
	public Graph_Algo(Graph graph) {
		this.g = graph;
		fw = new FW(g);
		}



	@Override
	public void save(String file_name) {
		//_file.saveFile(file_name);
	}

	@Override
	public boolean isConnected() {
		Collection<Node> nodes = g.getV();
		boolean[] visited = new boolean[g.nodeSize()]; 
		int parent[] = new int[g.nodeSize()];
		LinkedList<Node> queue = new LinkedList<>();
		Node[] nodesArray = nodes.toArray(size -> new Node[size]); 	//	node_data[] nodesArray = nodes.toArray(node_data[]::new);
		Node current = nodesArray[0];
		
		// Mark the current node as visited and enqueue it 
			visited[current.getKey()]=true; 
			queue.add(nodesArray[current.getKey()]); 
			parent[current.getKey()] = -1;
			while (queue.size() != 0) 
			{ 
				// Dequeue a vertex from queue and print it 
				current = queue.poll(); 
				
				// Get all adjacent vertices of the dequeued vertex s 
				// If a adjacent has not been visited, then mark it 
				// visited and enqueue it 
				 for (Edge neighbor : g.getE(current.getKey())) { 
					 int id_of_neighbor = neighbor.getDest(); // source(current) ------> (id_of_neighbor)
						if (visited[id_of_neighbor]==false) 
						{ 
							//dis[n] = dis[s] + 1;
							parent[id_of_neighbor] = id_of_neighbor;
							visited[id_of_neighbor] = true; 
							queue.add(g.getNode(id_of_neighbor)); 
						} 
				 }
			
			} 
			for (boolean what : visited) if (!what) return false;
		return true;
	}

	@Override
	public double shortestPathDist(int src, int dest) {
		double mat[][] = fw.getMat();
		return mat[src][dest];
	}

	@Override
	public Queue<Node> shortestPath(int src, int dest) {
	//	Stack<node_data> stack = new Stack<node_data>();
		Queue<Node> q = new LinkedList<Node>();
		List<Node> list = fw.getShortestPath(src, dest);
/*		
		for(int i=list.size()-1; i>-1; i--) {
			stack.push(list.get(i));
		}*/
		for (int i = 0; i < list.size(); i++) {
			q.add(list.get(i));
		}
		System.out.println("size: " + q.size());
		
		return q;
	}

	@Override
	public List<Node> TSP(List<Integer> targets) {
		return fw.getShortestPathWithTarget(targets);
	}


	
}

