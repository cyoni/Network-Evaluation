/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.io.Serializable;

    /**
    * This class represents an edge and its relevant values.
    */

public class Edge implements edge_metadata, Serializable {
    
	private static final long serialVersionUID = 4269760787650059514L;
	private double weight;
	private int src, dest;
	private String info, tag;
	
	public Edge(int src, int dest, double weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
	}
	
	@Override
	public int getSrc() {
            return src;
	}

	@Override
	public int getDest() {
		return dest;
	}

	@Override
	public double getWeight() {
		return weight;
	}

	@Override
	public String getInfo() {
		return info;
	}

	@Override
	public void setInfo(String s) {
		this.info = s;
		
	}

	@Override
	public String getTag() {
		return tag;
	}

	@Override
	public void setTag(String t) {
		this.tag = t;
	}
}
