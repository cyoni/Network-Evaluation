/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nodes;

import graph.Node;
import java.awt.Color;
import utils.Point2D;

/**
 *
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
