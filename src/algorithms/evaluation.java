/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import data_structure.Ad;
import gui.Gui_network;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import network.NetworkData;
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
    private double percent_inactive_ad = 20; 
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
    private int _int(String s){ return Integer.parseInt(s);}
    
    public void evaluate(Gui_network network, NetworkData net) throws IOException{
        
        myWriter = new FileWriter("network_evaluation.txt");
        write("Started process");   
 
        
        int num_members = _int(network.num_members.getText());
        int num_advertisers = _int(network.num_advertisers.getText());
        int num_ads = _int(network.num_ads.getText());
        int num_active_ads = _int(network.active_ads.getText());
        
                        
        write(">> Each advertiser is worth " + value_advertiser + ". Adding $" + add(value_advertiser*num_advertisers));

        if (net != null){
            int categories_size = net.getCategories();
            List<Ad> list_ads = net.getAds();
            cat_item[] arr_ads = new cat_item[list_ads.size()];
            for(int i=0;i<arr_ads.length; i++) arr_ads[i] = new cat_item();
            double[] evaluated_profit_from_ad = new double[arr_ads.length];
            
             for (int i=0; i<list_ads.size(); i++){
                    
                if (list_ads.get(i).getIsActive()){// An in/active ad adds a given % of the expense to the evaluation 
                    Double random = Math.random(); // Get probability between [0-1)
                    int expected = list_ads.get(i).getInterest(); // The number of people who will see the advertisement
                    double told_to_others = expected*random; // The number of friends to tell about the advertisement to others.
                    expected+= told_to_others;
                    random = Math.random(); // get another prob 
                    // how many people are expected to buy the product
                    int size_members_to_buy = (int)(expected * random); // how many will buy
                    evaluated_profit_from_ad[i] = list_ads.get(i).getProductPrice() * size_members_to_buy - list_ads.get(i).getPrice();
                    write(">> Expected earning from ad "+ i +" is $" + add(evaluated_profit_from_ad[i]));    
                    }
                    else{
                         write(">> Added an inactive ad, $" + add(percent_inactive_ad/100*list_ads.get(i).getPrice()));    
                    }
             }
            
        }
       
        
        write("The process is completed");   
        myWriter.close();
        User_Dialog.showAlert("Estimated network value is " + result);
      }
        
        
    
    
      public static void main(String[] args) throws IOException {

      }

    private Double add(double d) {
        result+=d;
        return result;
    }
    
    
    
    // a data base for the evaluation
    class cat_item{
        double profit = 0;
        int p=0; // number of people who see an ad
        int counter; // how many ads under the same cat there are
        public cat_item(){};
        public cat_item(int p){this.p = p; };
        public int getCounter(){return counter;}
        public void setP(int p){this.p = p;}
        public int getP(){return p;}
        public void increaseCounter(){counter++;}
        public void addProfit(double p){profit+= p;}
        public double getProfit(){return this.profit;}
}

    
}
