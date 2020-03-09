/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.ui.ApplicationFrame;
import org.jfree.ui.RefineryUtilities;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;


/**
 *
 * @author Yoni
 */
public class Gui_graph_chart extends ApplicationFrame {
    
    
   public Gui_graph_chart( String applicationTitle , String chartTitle ) {
      super(applicationTitle);
      JFreeChart lineChart = ChartFactory.createLineChart(
         chartTitle,
         "" , "Value in $",
         createDataset(),
         PlotOrientation.VERTICAL,
         true,true,false);
         
      ChartPanel chartPanel = new ChartPanel( lineChart );
      chartPanel.setPreferredSize( new java.awt.Dimension( 560 , 367 ) );
      setContentPane( chartPanel );
   }

   private DefaultCategoryDataset createDataset( ) {
      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
      dataset.addValue( 15 , "value" , "Jan" );
      dataset.addValue( 30 , "value" , "Feb" );
      dataset.addValue( 60 , "value" ,  "Mar" );
      dataset.addValue( 120 , "value" , "Apr" );
      dataset.addValue( 240 , "value" , "May" );
      dataset.addValue( 300 , "value" , "Jun" );
      dataset.addValue( 350 , "value" , "Jul" ); 
      dataset.addValue( 250 , "value" , "Aug" );
      dataset.addValue( 300 , "value" , "Sep" );
      dataset.addValue( 400 , "value" , "Oct" );
      dataset.addValue( 420 , "value" , "Nov" );
      dataset.addValue( 400 , "value" , "Dec" );
      
      
      return dataset;
   }
   
   public static void main( String[ ] args ) {
      Gui_graph_chart chart = new Gui_graph_chart(
         "Chart Graph" ,
         "Graph Chart of the Network");

      chart.pack( );
      RefineryUtilities.centerFrameOnScreen( chart );
      chart.setVisible( true );
   }

    
    
}
