/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph_visualization;

import algorithms.Graph_Algo;
import algorithms.line;
import graph.Graph;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import utils.Point2D;
import utils.StdDraw;
import nodes.node_metadata;
/**
 *
 * @author Yoni
 */
public class HighlightPath extends Thread{
    private Graph g;
    private int src, dest;
    private boolean animation;
    private List<Queue> list_of_queues;
    private boolean pause = false;
    
    public HighlightPath(Graph g, int src, int dest, boolean animation){
        this.g = g;
        this.animation = animation;
        list_of_queues = new ArrayList<>();
        Graph_Algo ga = new Graph_Algo(g);
        Queue<node_metadata> q = ga.shortestPath(src, dest);
        list_of_queues.add(q);
    }

    HighlightPath(Graph g, List<Queue> list_q, boolean animation) {
        list_of_queues = list_q;
        this.animation = animation;
        this.g = g;
    }
    
    public void action(){
        pause = false;
        this.start();
    }
    

        
    private Point2D getPoint(int x){
        return new Point2D(g.getNode(x).getLocation().x(), g.getNode(x).getLocation().y());
    }
        
    @Override
    public void run(){
 
            Queue<node_metadata> q = new LinkedList<>();
            for (int _i=0; _i<list_of_queues.size(); _i++){    
                src = -1;
                StdDraw.setPenRadius(0.01);
                StdDraw.setPenColor(Gui_visualization.changeColor());
                q = list_of_queues.get(_i);
                System.out.println("Drawing path number " + (_i+1));
                for ( node_metadata tmp : q){
                  int d = tmp.getKey();
                  System.out.print(d + "->");
                }
                System.out.println();
                        
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

    private void draw(Queue<node_metadata> q) {
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
                Logger.getLogger(HighlightPath.class.getName()).log(Level.SEVERE, null, ex);
            }
            Point2D tmp = line.getPointOnLine(p1, p2, ++percent);
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
