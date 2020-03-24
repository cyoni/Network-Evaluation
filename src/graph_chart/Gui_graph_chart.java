/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graph_chart;
import javax.swing.JFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;

import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import DB_Connection.database;
import algorithms.evaluation;
import database.user;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import utils.User_Dialog;

/**
 * This class presents the value of the network by dates.
 * @author Yoni
 */
public class Gui_graph_chart extends JFrame {
    protected List<graph_chart_data> data_structure;
    protected graph_chart_data latestData;
    protected user User;
    protected JMenuItem m1, m2, m9, m3, m7, m8, m6;


    public Gui_graph_chart(user User) {
      super("Chart Graph");
      this.User = User;
      build();
   }
   // to work on the menu
    protected void build(){
      data_structure = new ArrayList<>();
      initializeGraph();
    }
    
    
    protected void setMenu(){
        JMenuBar mb; 
        // create a menubar 
        mb = new JMenuBar(); 
  
        // create a menu 
        JMenu x = new JMenu("File"); 
         
        
        m1 = new JMenuItem("Change month/year");
        m2 = new JMenuItem("Exit"); 

        // add menu items to menu 
        x.add(m1); 
        x.addSeparator();
        x.add(m2);
        
        mb.add(x); 
        // add menubar to frame 
        setJMenuBar(mb);  
        
        startMouseListener();
    }
       
    protected void startMouseListener() {
            m1.addActionListener((ActionEvent e) -> { // open month/year dialog
                Dialog_choose_month_year d = new Dialog_choose_month_year();
                d.setVisible(true);
                d.setData(this);
            });
            m2.addActionListener((ActionEvent e) -> { // exit
                this.dispose();
            });
    }
    
   protected void initializeGraph(){
                 
     getData();
     String title = "Network Value";
     String months[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
     if (latestData != null)
        title+= " of " + months[latestData.getMonth()-1];
     else title = " (no data.....yet)";
     
     JFreeChart lineChart = ChartFactory.createLineChart(
         title,
         "" , "Value in $",
         createDataset(),
         PlotOrientation.VERTICAL,
         true,true,false);
         
      ChartPanel chartPanel = new ChartPanel( lineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
      setContentPane( chartPanel );
   }

 protected void getData(){
       List<graph_chart_data> list = new ArrayList<>();
       // fetch data
       ResultSet rs = database.query("SELECT email, year, month, data FROM network_value WHERE email='"+ User.getEmail() +"' "
               + "ORDER BY year DESC, month DESC");
       try {
           while (rs.next()){
               int year = rs.getInt("year");
               int month = rs.getInt("month");
               String dataWithSpace = rs.getString("data");
               String data = dataWithSpace.replaceAll("\\s+",""); // eliminate whitespaces
               // int year, int month, String str
               graph_chart_data gcd = new graph_chart_data(year, month, data); // don't worry my friend it's not what you're thinking about 
               list.add(gcd);
           }
           
             data_structure = list; // save data 

           if (!data_structure.isEmpty()){
                latestData = data_structure.get(0); // set latest data
                setMenu();
           }
           
       } catch (SQLException ex) {
           Logger.getLogger(Gui_graph_chart.class.getName()).log(Level.SEVERE, null, ex);
       }
 }

   protected DefaultCategoryDataset createDataset() {
      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
      // [email, year, month , (day, val; dat, val)]
              if (latestData != null)
                for (int i=0; i< latestData.getData().size(); i++){
                     graph_chart_data.day_value tmp = latestData.getData().get(i); // get the last element (latest data)
                    dataset.addValue(tmp.getValue(), "value" , tmp.getDay()+"");      
                  }
      return dataset;
   }    
}