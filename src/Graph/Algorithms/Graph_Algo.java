/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph.Algorithms;

import Graph.Edge;
import Graph.Graph;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import Graph.edge_metadata;
import Nodes.node_metadata;
import java.util.Iterator;
import Utils.User_Dialog;

/**
 * This graph contains basic algorithms of graph.
 * @author Yoni
 */
public class Graph_Algo implements graph_algorithms , Serializable{
    
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
        int s = 0;
        // Mark all the vertices as not visited(By default 
        // set as false) 
        List<edge_metadata> adj[]; //Adjacency Lists 
        
        int V = g.nodeSize();
        boolean visited[] = new boolean[V]; 
        adj = g.getArrayOfVertciesWithEdges();
        
         for (int i=0; i<adj.length; i++) if (adj[i].size() > 0) {s = i; break;}
        
        // Create a queue for BFS 
        LinkedList<Integer> queue = new LinkedList<Integer>(); 
  
        // Mark the current node as visited and enqueue it 
        visited[s]=true; 
        queue.add(s); 
  
        while (!queue.isEmpty()) 
        { 
            // Dequeue a vertex from queue and print it 
            s = queue.poll(); 
            
  
            // Get all adjacent vertices of the dequeued vertex s 
            // If a adjacent has not been visited, then mark it 
            // visited and enqueue it 
            
            for (int i=0;i<adj[s].size(); i++)
            { 
                int n = adj[s].get(i).getDest();
                if (!visited[n]) 
                { 
                    visited[n] = true; 
                    queue.add(n); 
                } 
            } 
        } 
    
	for (boolean what : visited) if (!what) return false;
        return true;
	}

	@Override
	public double shortestPathDist(int src, int dest) {
            int mat[][] = fw.getMat();
            return mat[src][dest];
	}

	@Override
	public Queue<node_metadata> shortestPath(int src, int dest) {
		Queue<node_metadata> q = new LinkedList<node_metadata>();
		List<node_metadata> list = fw.getShortestPath(src, dest);
		for (int i = 0; i < list.size(); i++) {
			q.add(list.get(i));
		}
		System.out.println("size: " + q.size());
		return q;
	}
        
        /**
         * This method returns the longest path. If there are a few longest paths with the same size it will return a list.
         * @return list that is composed of queue.
         */
       	public List<Queue> longestPath() { // TOFIX !
            List<Queue> q = new  ArrayList<>();
            ArrayList<node_metadata>[][] paths = fw.getPaths();
            int max = -1;
            for (int i=0; i < paths.length; i++){
                for (int j=0; j < paths.length; j++){
                if (max < paths[i][j].size()) max = paths[i][j].size();                
                }
            }
            for (int i=0; i < paths.length; i++){
                for (int j=0; j < paths.length; j++){
                  if (max == paths[i][j].size()) q.add(strToQueue(paths[i][j]));
                }
            }
            
            /*
            // remove duplicates:
            for (int i=0; i<q.size(); i++){
                boolean flag = true;
                for (int j=0; j<q.get(i).size() && flag; j++){
                    if ( i!=j && q.get(i).size()!=q.get(j).size()) continue;
                    Queue<node_metadata> _q1 = q.get(i);
                    Queue<node_metadata> _q2 = q.get(j);
                    List<node_metadata> list1 = new ArrayList<>(_q1);
                    List<node_metadata> list2 = new ArrayList<>(_q2);
                    if (list1.get(0).getKey() == list2.get(list2.size()-1).getKey()) {q.remove(i); flag=false;}
                }
            }
            */
            return q;
        }
        
	@Override
	public List<node_metadata> TSP(List<Integer> targets) {
		return fw.getShortestPathWithTarget(targets);
	}

        private Queue strToQueue(ArrayList<node_metadata> list) {
            Queue<node_metadata> q = new LinkedList<>();
            for (node_metadata curr : list){
                q.add(curr);
            }
            return q;
        }

        @Override
        public boolean isConnected(int src, int dest) {
            if (src < 0 || dest < 0 || fw.getMat().length <= src || fw.getMat().length <= dest) return false;
            return (fw.getMat()[src][dest] != Integer.MAX_VALUE);
        }



}

