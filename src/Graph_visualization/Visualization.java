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
import javax.swing.JCheckBoxMenuItem;


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
       if (loadedNetwork()){
            graphInit();
            draw.drawGraph();
            StdDraw.setGraph(graph);
        }
    }

    public static void filter(JCheckBoxMenuItem[] items) {
        if (loadedNetwork()){
            for(int i=0; i< items.length; i++){
                String Value = RelationshipTypes.relationships[i];
                if (items[i].isSelected()){
                    draw.add_to_filter(Value);
                }
                else{
                    draw.remove_from_filter(Value);
                }
            }
            
            draw.drawGraph();
        }
    }

    private static boolean loadedNetwork() {
        if (Gui_network.network_file.isEmpty()){
            User_Dialog.showAlert("Please load a network file first.");
            return false;
        }
        return true;
    }
        

}
