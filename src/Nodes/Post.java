/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nodes;

import Graph.Node;
import java.awt.Color;
import Utils.Point2D;

/**
 * This class represents an page-type node
 * @author caron
 */
public class Post extends Node {

    public Post(int key, Point2D location) {
        super(key, location, Color.ORANGE);
    }

    
    
}
