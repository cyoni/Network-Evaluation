/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph;

import java.awt.Color;
import java.io.Serializable;
import Nodes.node_metadata;
import Utils.Point2D;

public class Node implements node_metadata, Serializable {

    /**
     * This class contains data of a node.
     *
     * @author Yoni
     */
    private static final long serialVersionUID = -5098381326948746081L;
    private Point2D location;
    private String info;
    private int key, id;
    public static int GLOBAL_ID = 0;
    protected String name = "";
    private Color node_color;
    
    public Node(int id, Point2D location, Color color) {
        this.location = location;
        this.id = id;
        this.node_color = color;
        this.key = GLOBAL_ID++;
    }

    @Override
    public int getKey() {
        return key;
    }

    @Override
    public Point2D getLocation() {
        return location;
    }

    @Override
    public void setLocation(Point2D p) {
        this.location = p;
    }

    @Override
    public String getInfo() {
        // TODO Auto-generated method stub
        return info;
    }

    @Override
    public void setInfo(String s) {
        // TODO Auto-generated method stub
        this.info = s;
    }

    @Override
    public int getTag() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public void setTag(int t) {
        // TODO Auto-generated method stub
    }
    
    @Override
    public int getId() {
        return id;
    }
    
    @Override
    public String getName(){
        return name;
    }

    @Override
    public Color getColor() {
        return node_color;
    }

}
