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
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import utils.Point2D;
import utils.StdDraw;
/**
 *
 * @author Yoni
 */
public class HighlightPath_Thread extends Thread{
    private Graph g;
    private int src=-1, dest;
    private boolean animation;
    private List<Queue> list_of_queues;
    private boolean pause = false;
    
    public HighlightPath_Thread(Graph g, int src, int dest, boolean animation){
        this.g = g;
        this.animation = animation;
        list_of_queues = new ArrayList<>();
        Graph_Algo ga = new Graph_Algo(g);
        Queue<Node> q = ga.shortestPath(src, dest);
        list_of_queues.add(q);
       
    }

    HighlightPath_Thread(Graph g, List<Queue> list_q, boolean animation) {
        list_of_queues = list_q;
        this.animation = animation;
        this.g = g;
    }
    
    public void action(){
        pause = false;
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
        
        private Point2D getPoint(int x){
            return new Point2D(g.getNode(x).getLocation().x(), g.getNode(x).getLocation().y());
        }
        
    public void run(){
 
            Queue<Node> q = new LinkedList<>();
            for (int _i=0; _i<list_of_queues.size(); _i++){        
                StdDraw.setPenRadius(0.01);
                StdDraw.setPenColor(Gui_visualization.changeColor());
                q = list_of_queues.get(_i);
                System.out.println("Drawing path number " + (_i+1));
                for ( Node tmp : q){
                  int d = tmp.getKey();
                  System.out.print(d + "->");
                }
                while(!q.isEmpty()){

                    if (!animation){
                         draw(q);
                        }
                         else{
                            draw(q);
                         }
                     }
                }

            System.out.println("Done.");
    }

    private void draw(Queue<Node> q) {
        if (src == -1)  src = q.poll().getKey();
        dest = q.poll().getKey();

        Point2D p1 = getPoint(src);
        Point2D p2 = getPoint(dest);

        System.out.println("Painting " + src +"->" + dest);

        
        if (animation){
        int percent = -1;
        
        while (percent != 100 && !pause){
            try {  
                sleep(10);
            } catch (InterruptedException ex) {
                Logger.getLogger(HighlightPath_Thread.class.getName()).log(Level.SEVERE, null, ex);
            }
            Point2D tmp = getPointOnLine(p1, p2, ++percent);
            StdDraw.point(tmp.x(), tmp.y());
            }
        }
        else{
           StdDraw.line(p1.x(), p1.y(), p2.x(), p2.y());
        }
        src = dest;
    }

    // not working yet
  public void pauseOrResume() {
      if (pause) pause=false;
      else pause=true;
    }    
}
