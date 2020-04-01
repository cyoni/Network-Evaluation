/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import gui.Gui_network;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.User_Dialog;

/**
 * This class contains algorithms that provide an estimation of the network's value.
 * @author Yoni
 */
public class evaluation {
    FileWriter myWriter;
    /////////////////this kind of data should be downloaded from the DB!//////////////////////////////////
    private double value_like = 0.2;
    private double value_share = 0.5;
    private double value_post = 0.2;
    private double value_advertiser = 1.0;
    private double value_time_spent = 2.0; 
    private double percent_active_ad = 70; 
    private double value_active_ad = 0.3; 

    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    private double result = 0.0;
    
    private void write(String str){
        try {
            myWriter.write(str + "\n");
            
        } catch (IOException ex) {
            Logger.getLogger(evaluation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private double Double(String s){ return Double.parseDouble(s);}
    
    public void evaluate(Gui_network network) throws IOException{
        
        myWriter = new FileWriter("network_evaluation.txt");
        write("Started process");   
 
        
        Double num_members = Double(network.num_members.getText());
        Double num_advertisers = Double(network.num_advertisers.getText());
        Double num_ads = Double(network.num_ads.getText());
        Double num_active_ads = Double(network.active_ads.getText());

                
        List<String> categories = new ArrayList<>();
        categories.add("Food");
        categories.add("Sport");
        categories.add("Clothing");  // This list should be loaded from the DB
        
        write(">> Each advertiser is worth " + value_advertiser + ". Adding $" + add(value_advertiser*num_advertisers));
        
       
        
        write("The process is completed");   
        myWriter.close();
        User_Dialog.showAlert("Processed is completed.");
      }
        
        
    
    
      public static void main(String[] args) throws IOException {

      }

    private Double add(double d) {
        result+=d;
        return result;
    }
    
}
