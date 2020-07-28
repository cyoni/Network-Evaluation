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
 * This class represents an page-type node
 * @author caron
 */
public class Page extends Node {
    
    public Page(int id, Point2D location) {
        super(id, location, Color.black);
    }
    
}
