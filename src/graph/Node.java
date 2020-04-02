/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import java.io.Serializable;
import utils.Point2D;

public class Node implements node_metadata, Serializable {

    /**
     * This class contains data of a node.
     *
     * @author Yoni
     */
    private static final long serialVersionUID = -5098381326948746081L;
    private Point2D location;
    private String info;
    private int key;
    private int id;

    public Node(int key, int id, Point2D location) {
        this.location = location;
        this.key = key;
        this.id = id;
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

}
