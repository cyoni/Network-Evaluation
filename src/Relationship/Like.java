/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Relationship;

import Graph.Edge;
import java.awt.Color;

/**
 *
 * @author Yoni
 */
public class Like extends Edge{
        public Like(int x, int y, double weight){
        super(x, y, weight, Color.BLUE);
        setTag("Like");
    }
}
