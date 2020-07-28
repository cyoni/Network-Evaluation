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
 * This class represents an member-type node
 * @author caron
 */
public class Member extends Node {
   
    private int friends_size;

    public Member(String name, int friends_size, int key, Point2D location) {
        super(key, location, Color.RED);
        this.name = name;
        this.friends_size = friends_size;
    }

   public int getFriendSize(){return friends_size;}
    
    
    
}
