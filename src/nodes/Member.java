/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nodes;

import graph.Node;
import utils.Point2D;

/**
 *
 * @author caron
 */
public class Member extends Node {
   
    private int friends;

    public Member(String name, int friends, int key, int id, Point2D location) {
        super(key, id, location);
        this.name = name;
        this.friends = friends;
    }

   
    
    
    
}
