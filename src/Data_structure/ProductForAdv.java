/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Data_structure;

import java.util.ArrayList;

/**
 * 
 * This class represents a product intended for advertising.
 * Product Advertising Process:
 * The advertisement is of type "Push Marketing" and the price is of type "Cost per mille"
 * If you want your ad to reach targeted people, you'll need to add the basic price.
 */
public class ProductForAdv {
    
    private int product_id; 
    private double profit;
    private ArrayList<Integer> category;   // list of category  arl = new ArrayList<Integer>();
    private int expoForDay; // Number of exposures of the advertisement per day
    private int dayForAdv; // Number of days for advertising.

    public ProductForAdv(int product_id, double profit, ArrayList<Integer> category, int expoForDay, int dayForAdv) {
        this.product_id = product_id;
        this.profit = profit;
        this.category = category;
        this.expoForDay = expoForDay;
        this.dayForAdv = dayForAdv;
    }

    public int getProduct_id() {
        return product_id;
    }

    public double getProfit() {
        return profit;
    }

    public ArrayList<Integer> getCategory() {
        return category;
    }

    public int getExpoForDay() {
        return expoForDay;
    }

    public int getDayForAdv() {
        return dayForAdv;
    }
   
    
    
}