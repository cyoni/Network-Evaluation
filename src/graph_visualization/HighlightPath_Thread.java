/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph_visualization;

import algorithms.Graph_Algo;
import graph.Graph;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import graph.Node;
import java.awt.Color;
import utils.Point2D;
import utils.StdDraw;
/**
 *
 * @author Yoni
 */
public class HighlightPath_Thread extends Thread{
    private Graph g;
    private Queue<Node> q;
    private int src, dest;
    
    public HighlightPath_Thread(Graph g,  int src, int dest){
        this.g = g;

        Graph_Algo ga = new Graph_Algo(g);
        q = ga.shortestPath(src, dest);
        this.src = -1;
        for ( Node tmp : q){
            int d = tmp.getKey();
            System.out.print(d + "->");
        }
        System.out.print("");
    }
    
    public void action(){
        
        this.start();
        
    }
    
    
    	/**
	 * This function returns a point somewhere between a line
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
        
    public void run(){
        try {
            while(!q.isEmpty()){
            sleep(1000);
            
 
                 if (src == -1)  src = q.poll().getKey();
                 dest = q.poll().getKey();
                 
             Point2D p1 = new Point2D(g.getNode(src).getLocation().x(), g.getNode(src).getLocation().y());
             Point2D p2 = new Point2D(g.getNode(dest).getLocation().x(), g.getNode(dest).getLocation().y());
             
            System.out.println("Painting " + src +"->" + dest);
            int percent = 0;
            while (percent != 100){
                sleep(10);
                
                Point2D tmp = getPointOnLine(p1, p2, ++percent);
                
             StdDraw.setPenRadius(0.01);
            StdDraw.setPenColor(Color.yellow);
            
            StdDraw.point(tmp.x(), tmp.y());
                
                
            }
            
            src = dest;
            
            }
            
        } catch (InterruptedException ex) {
            Logger.getLogger(HighlightPath_Thread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
