/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph.Algorithms;

import java.util.List;
import java.util.Queue;
import Nodes.node_metadata;

/**
 *
 * @author Yoni
 */
public interface graph_algorithms {
	public void save(String file_name);
	public boolean isConnected();
        public boolean isConnected(int x, int y);
	public double shortestPathDist(int src, int dest);
	public Queue<node_metadata> shortestPath(int src, int dest);
	public List<node_metadata> TSP(List<Integer> targets);
}