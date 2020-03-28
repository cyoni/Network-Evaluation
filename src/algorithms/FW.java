package algorithms;

import graph.Edge;
import graph.Graph;
import graph.Node;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;



public class FW implements Serializable{

	/**
	 * 
	 */
	
	private static final long serialVersionUID = -731345878701854509L;
	private int infinity = Integer.MAX_VALUE;
	private double mat[][];
	private Graph g;
        private  ArrayList<Node>[][] paths;

	
	public FW(Graph g) {
		this.g = g;
		initMat();
	}       
	

	@SuppressWarnings("unchecked")
	private void initMat() {
		mat = new double[g.nodeSize()][g.nodeSize()];
		paths = new ArrayList[g.nodeSize()][g.nodeSize()];
		for (double[] row : mat)  Arrays.fill(row, infinity);

		List<Edge> edges = g.getEdges();
		
		for (int i = 0; i < mat.length; i++) {
			mat[i][i] = 0;
		}
		
		for (int i = 0; i < edges.size(); i++) {
			int src = edges.get(i).getSrc();
			int dest = edges.get(i).getDest();
			mat[src][dest] = edges.get(i).getWeight();
			mat[dest][src] = edges.get(i).getWeight();
		}

		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				paths[i][j] = new ArrayList<>();
				if (mat[i][j] != infinity) { paths[i][j].add(g.getNode(i)); paths[i][j].add(g.getNode(j));}
			}
		}
		

		// update the mat with FW
		startFW();
		printMat();	
	}
	
        public ArrayList<Node>[][] getPaths(){
            return paths;
        }

	private void printMat() {
		for (int i = 0; i < mat.length; i++) {
			for (int j = 0; j < mat.length; j++) {
				System.out.print(mat[i][j] + "\t");
			}
			System.out.println();
		}
		System.out.println();
	}


	private void startFW(){
		int n = mat.length;
		for (int k = 0; k<n; k++){
			for (int i = 0; i<n; i++){
				for (int j = 0; j<n; j++){
					if (i!=j && mat[i][k] != infinity && mat[k][j] != infinity){
						if (mat[i][j] > mat[i][k] + mat[k][j]){
							mat[i][j] = mat[i][k] + mat[k][j];
							paths[i][j].clear();
							paths[i][j].addAll(paths[i][k]);
							paths[i][j].addAll(paths[k][j]);
						}
					}
				}
			}
		}
	}

	public double[][] getMat() {
		return mat;
	}



	public List<Node> getShortestPath(int src, int dest) {
		ArrayList<Node> shortestPath = paths[src][dest];
		ArrayList<Node> tmpPath = new ArrayList<>();
		
		for (int i = 0; i < shortestPath.size(); i++)  // deep copy
			tmpPath.add(shortestPath.get(i));
		
		if (tmpPath.size() > 1) {
			int last = tmpPath.get(0).getKey();
			for (int i = 1; i < tmpPath.size(); i++) {
				int current = tmpPath.get(i).getKey();
				if (tmpPath.get(i).getKey() == last) {
					tmpPath.remove(i--);
				}
				last = current;
			}
		}
		return tmpPath; 
	}



	// remove duplicates
	private List<Node> shortestPath_without_duplicates(List<Node> shortestPath) {
		List<Node> shortestPath_without_duplicates = new ArrayList<>();
		int visited[] = new int[g.nodeSize()];
		Arrays.fill(visited, -1);
    	for (int i=0; i< shortestPath.size(); i++) {
    		int key = shortestPath.get(i).getKey();
    		if (visited[key] == 1) continue;
    		visited[key] = 1;
    		shortestPath_without_duplicates.add(shortestPath.get(i));
    	}	
		return shortestPath_without_duplicates;
	}



	public List<Node> getShortestPathWithTarget(List<Integer> targets) {
		List<Node> list = new ArrayList<>();
		if (targets.isEmpty()) {System.out.println("targets list is empty"); return null;}
		Node arr[] = new Node[targets.size()];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = g.getNode(targets.get(i));
		}
		Node leader_node = arr[0];  // get the first node in the list
		for (int i=1; i< arr.length; i++) {
		List<Node> shorestPathList = getShortestPath(leader_node.getKey(), arr[i].getKey());
		list.addAll(shorestPathList);
			for (int j = 1; j < shorestPathList.size() ; j++) {
			mat[shorestPathList.get(j).getKey()][shorestPathList.get(j-1).getKey()] = 0;
			mat[shorestPathList.get(j-1).getKey()][shorestPathList.get(j).getKey()] = 0;
			}

			// update the mat with FW
			startFW();

		}

		return shortestPath_without_duplicates(list);
	}
}