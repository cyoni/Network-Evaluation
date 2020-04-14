/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * @author Yoni
 */
public class Point2D {
    
    private double x, y;
    
    public Point2D(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Point2D(Point2D p) { // copy constructor
        this.x = p.x;
        this.y = p.y;
    }
    
    public double x(){ return x;}
    public double y(){ return y;}
    
    
    
}
