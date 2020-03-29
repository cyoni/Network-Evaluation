/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import gui.Gui_network;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This class contains algorithms that provide an estimation of the network's value.
 * @author Yoni
 */
public class evaluation {
    FileWriter myWriter;
    
    private void write(String str){
        try {
            myWriter.write(str + "\n");
            
        } catch (IOException ex) {
            Logger.getLogger(evaluation.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void evaluate(Gui_network network) throws IOException{
        myWriter = new FileWriter("filename.txt");
        write("Started process");   
        
       // network.num_pages1.getText();
        
        
        write("The process is complete");   
        myWriter.close();
      }
        
        
    
    
      public static void main(String[] args) throws IOException {

      }
    
}
