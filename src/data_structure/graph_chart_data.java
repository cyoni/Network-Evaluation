/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data_structure;

import java.util.Vector;

/**
 * This class provides a data structure for the graph chart.
 * @author Yoni
 */

public class graph_chart_data {
    protected int year;
    protected int month;
    protected Vector<day_value> data;
    
    public graph_chart_data(int year, int month, String str){
        this.year = year;
        this.month = month;
        this.data = new Vector();
        processData(str);
    }
    
    
    public int getYear(){return year;}
    public int getMonth(){return month;}
    public Vector<day_value> getData(){return this.data;}
    
    private void processData(String str){
        String splitText[] = str.split(";");
        for (int i=0; i< splitText.length; i++){
            String splitTextx2[] = splitText[i].split(",");
            day_value d = new day_value(Integer.parseInt(splitTextx2[0]), Integer.parseInt(splitTextx2[1]));
            data.add(d);
        }
    }
    
   public class day_value{
        protected int day;
        protected double value;
        
        public day_value(int day, double value){
            this.day = day;
            this.value = value;
        }
        
        public int getDay(){return day;}
        public double getValue(){return value;}
        
    }
        
}

