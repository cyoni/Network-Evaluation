/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.awt.Color;
import java.io.Serializable;

    /**
    * This class represents an edge and its relevant values.
    */

public class Edge implements edge_metadata, Serializable {
    
    private static final long serialVersionUID = 4269760787650059514L;
    private double weight;
    private int src, dest;
    private String info, tag;
    private Color edge_color;
        
    public Edge(int src_id, int dest_id, double weight, Color edge_color) {
        this.src = src_id;
        this.dest = dest_id;
        this.weight = weight;
        this.edge_color = edge_color;
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

    @Override
    public Color getColor() {
        return edge_color;
    }
}
