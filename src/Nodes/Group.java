/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Nodes;

import Graph.Node;
import Utils.Point2D;
import java.awt.Color;

/**
 *
 * @author caron
 */
public class Group extends Node {
    
    public Group(int id, Point2D location) {
        super(id, location, Color.MAGENTA);
    }
    
}
