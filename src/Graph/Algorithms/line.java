/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph.Algorithms;

import Utils.Point2D;

/**
 *
 * @author Yoni
 */
public class line {
        
    	/**
	 * This function returns a point somewhere between a line
         * @param p1
         * @param p2
         * @param percent 
	 * @return vector
	 **/
	public static Point2D getPointOnLine(Point2D p1, Point2D p2, int percent) {
		double x1 = p1.x(), y1 = p1.y();
		double x2 = p2.x(), y2 = p2.y();
			
		double v[] = {x2-x1, y2-y1};
		double length = Math.sqrt(v[0]*v[0]+v[1]*v[1]);
		double u[] = {1/length*v[0], 1/length*v[1]}; // normalized vector
		double distance = Math.sqrt(Math.pow((x2-x1), 2) + Math.pow(y2-y1, 2));
		double x = x1 + u[0] * distance*((double)percent/100); 
		double y = y1 + u[1] * distance*((double)percent/100);
		return new Point2D(x,y);
	}
         
        
        public static double distance(Point2D p1, Point2D p2){
            return Math.sqrt(Math.pow((p2.y()-p1.y()), 2) + Math.pow((p2.x()-p1.x()), 2));
        }
}
