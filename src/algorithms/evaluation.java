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
import DB_Connection.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import database.user;

/**
 * This class contains algorithms that provide an estimation of the network's value.
 * @author Yoni
 */
public class evaluation {
    private FileWriter myWriter;
    private user User;
    
    /////////////////The following data should be downloaded from the DB File //////////////////////////////////
    private double value_like = 0.2;
    private double value_share = 0.5;
    private double value_post = 0.2;
    private double value_advertiser = 1.0;
    private double value_time_spent = 2.0; 
    private double percent_active_ad = 70; 
    private double percent_inactive_ad = 20; 
    private double value_active_ad = 0.3; 
    
    private int num_members;
    private int num_advertisers;
    private int num_ads;
    private int num_active_ads;
    
    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    private double network_value = 0.0;
    private Gui_network network;
    private NetworkData network_data_from_file;
        
    public void evaluate(Gui_network network, NetworkData network_data_from_file) throws IOException, SQLException{
        User = network.getUser();
        this.network = network;
        this.network_data_from_file = network_data_from_file;
        
        initEvaluation();
        performEvaluation();
        closeWriter();
        recordResultInDatabase();
        showResult();

      }
    
    private void recordResultInDatabase() throws SQLException{
        
        String question = User_Dialog.getInputDialog("Would you like to record the result in the database? [1=yes, 0=no]");
        if (question.equals("1")){
            int answer_from_server = -1;
            String evaluation_data = "";
            Date date = new Date();
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int day  = localDate.getDayOfMonth();
            int year  = localDate.getYear();
            int month = localDate.getMonthValue();

            ResultSet rs = database.query("SELECT * FROM network_value WHERE email='"+ User.getEmail() +""
                    + " AND month="+ month +"' AND year="+ year +"");

            if (rs == null){
                evaluation_data = day + "," + network_value;
               answer_from_server = database.query_update("INSERT INTO network_value (email, year, month, data) VALUES('"+ User.getEmail() +"'"
                        + ", ('"+ year +"'), ('"+ month +"'), ('"+ evaluation_data +"')");
            }
            else{
                String data_from_database = "";
                while (rs.next()){
                    data_from_database = rs.getString("data");
                }
                evaluation_data = data_from_database + ";" + day + "," + network_value;

               answer_from_server = database.query_update("UPDATE network_value SET data='"+ evaluation_data +"' WHERE email='"+ User.getEmail() +"'");
            }
            if (answer_from_server == 1){
                User_Dialog.showAlert("The result has been successfully recorded!");
            }
            else{
                User_Dialog.showAlert("There was an error while processing this request.");
            }
        }
        
    }
    
    private int convertIntFromString(String s){ return Integer.parseInt(s);}

    private Double add(double d) {
        network_value+=d;
        return network_value;
    }

    private void initEvaluation() throws IOException {
        num_members = convertIntFromString(network.num_members.getText());
        num_advertisers = convertIntFromString(network.num_advertisers.getText());
        num_ads = convertIntFromString(network.num_ads.getText());
        num_active_ads = convertIntFromString(network.active_ads.getText());
        
        myWriter = new FileWriter("network_evaluation.txt");

    }

    private void performEvaluation() throws IOException {
        write("Started process"); 
        
        evaluate_advetisers();
        evaluate_number_of_people_who_might_see_an_ad();
       // evaluate.. ()...


    }
    
    private void write(String str) throws IOException{
            myWriter.write(str + "\n");
    }
    
    private void closeWriter() throws IOException {
        write("The process is completed");   
        myWriter.close();
    }

    private void showResult() {
        User_Dialog.showAlert("Estimated network value is " + network_value);
    }

    private void evaluate_advetisers() throws IOException {
    write(">> Each advertiser is worth " + value_advertiser + ". Adding $" + add(value_advertiser*num_advertisers));
    }

    private void evaluate_number_of_people_who_might_see_an_ad() throws IOException {
        if (network_data_from_file != null){
            int categories_size = network_data_from_file.getCategories();
            List<Ad> list_ads = network_data_from_file.getAds();
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
