/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import graph.Node;
import java.util.List;
import java.util.Queue;

/**
 *
 * @author Yoni
 */
public interface graph_algorithms {


	

	public void save(String file_name);

	public boolean isConnected();

	public double shortestPathDist(int src, int dest);

	
	public Queue<Node> shortestPath(int src, int dest);

        
	public List<Node> TSP(List<Integer> targets);
	
	
}