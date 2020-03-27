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
    
    public void run(){

        try {
            while(!q.isEmpty()){
            sleep(1000);
            
 
                 if (src == -1)  src = q.poll().getKey();
                 dest = q.poll().getKey();
                 
             
             
            StdDraw.setPenRadius(0.01);
            StdDraw.setPenColor(Color.yellow);
                System.out.println("Paiting " + src +"->" + dest);
            StdDraw.line(g.getNode(src).getLocation().x(), g.getNode(src).getLocation().y(), g.getNode(dest).getLocation().x(), 
                    g.getNode(dest).getLocation().y());
            
            src = dest;
            
            }
            
        } catch (InterruptedException ex) {
            Logger.getLogger(HighlightPath_Thread.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
