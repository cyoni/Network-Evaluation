/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Graph_visualization;

import Database.LocalDatabase;
import Graph.Algorithms.ConstructGraph;
import Graph.Algorithms.Graph_Algo;
import Graph.Graph;
import Network.Gui_network;
import java.awt.Color;
import Utils.StdDraw;
import javax.swing.JFrame;
import Relationship.RelationshipTypes;
import Utils.ScreenSize;
import Utils.User_Dialog;


/**
 *
 * @author Yoni
 */
public class Visualization{
    
   
    static Graph graph;
   
    static Color[] colors = {Color.YELLOW};
    static int color_index = 0;
    private static Graph_Algo graphAlgo;
    private static Draw draw;
    
        
    private static void graphInit() {
        ConstructGraph c = new ConstructGraph(Gui_network.accessConnection_local_database, ScreenSize.WIDTH, ScreenSize.HEIGHT);
        graph = c.getGraph();
        draw = new Draw(graph, Gui_network.accessConnection_local_database);
    }


    public static Color changeColor(){
        color_index= (color_index+1)%colors.length;
        return colors[color_index];
    }

   
    public static void drawGraph() {   
        if (Gui_network.network_file.isEmpty()){
            User_Dialog.showAlert("Please load a network file first.");
        }
        else{
            graphInit();
            draw.drawGraph();
            StdDraw.setGraph(graph);
        }
    }


    private void filter() {                                         
     
      FilterRelationship fr = new FilterRelationship("Filter...", RelationshipTypes.relationships, draw);
      fr.setSize(200, 300);
      fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
      fr.addComponentsToPane(fr.getContentPane());
      fr.setVisible(true);
    
    }

}
