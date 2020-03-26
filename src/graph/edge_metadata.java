/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;


import java.io.Serializable;

public class edge_metadata implements Edge, Serializable {

	/**
	 * This class contains information about an edge.
	 */

	private static final long serialVersionUID = 4269760787650059514L;
	private double weight;
	private int src, dest, tempData;
	private String info;
	
	public edge_metadata(int x, int y, double weight) {
		src = x;
		dest = y;src = x;
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
	public int getTag() {
		return tempData;
	}

	@Override
	public void setTag(int t) {
		this.tempData = t;
	}



}
