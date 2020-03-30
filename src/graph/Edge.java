/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;
/**
 * This interface represents the set of operations applicable on a 
 * directional edge(src, dest) in a (directional) weighted graph.
 * @author Yoni
 *
 */
public interface Edge {
	/**
	 * The id of the source node of this edge.
	 * @return
	 */
	public int getSrc();
	/**
	 * The id of the destination node of this edge
	 * @return
	 */
	public int getDest();
	/**
	 * @return the weight of this edge (positive value).
	 */
	public double getWeight();
	/**
	 * return the remark (meta data) associated with this edge.
	 * @return
	 */
	public String getInfo();
	/**
	 * Allows changing the remark (meta data) associated with this edge.
	 * @param s
	 */
	public void setInfo(String s);
	/**
	 * Temporal data (aka color: e,g, white, gray, black) 
	 * which can be used be algorithms 
	 * @return
	 */ 
	public int getTag();
	/** 
	 * Allow setting the "tag" value for temporal marking an edge - common 
	 * practice for marking by algorithms.
	 * @param t - the new value of the tag
	 */
	public void setTag(int t);
}
