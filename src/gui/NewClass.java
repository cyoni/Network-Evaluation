/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import algorithms.line;
import utils.Point2D;
import utils.StdDraw;

/**
 *
 * @author Yoni
 */
public class NewClass {
    public static void main(String[] args) {
        StdDraw.setCanvasSize(400, 400);
        StdDraw.setScale(0, 100);
        StdDraw.setPenRadius(0.01);
        
        Point2D p1 = new Point2D(5, 95);
        Point2D p2 = new Point2D(99, 3);
        
        Point2D p3 = line.getPointOnLine(p1, p2, 50);
        StdDraw.line(p1.x(), p1.y(), p2.x(), p2.y());
        
        double xx = Math.atan2(p2.y()-p1.y(),p2.x()-p1.x());
        double deg = Math.toDegrees(xx);
        System.out.println(deg);
        
        StdDraw.text(p3.x(), p3.y()+5, "shalom", deg); // -44.3839
        
                
    }
}
