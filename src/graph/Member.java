/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph;

import utils.Point2D;

/**
 *
 * @author caron
 */
public class Member extends Node {
    
    private String name;
    private int friends;

    public Member(String name, int friends, int key, Point2D location) {
        super(key, location);
        this.name = name;
        this.friends = friends;
    }
    
    
    
}
