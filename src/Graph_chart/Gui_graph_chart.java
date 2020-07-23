package Graph_chart;

import javax.swing.JFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import Database.PublicDatabase;
import Account.UserAccount;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * This class presents the value of the network by dates.
 * @author Yoni
 */
public class Gui_graph_chart extends JFrame {
    protected List<graph_chart_data> data_structure;
    protected UserAccount User;
    protected JMenuItem m1, m2, m9, m3, m7, m8, m6;
    private String getDataFromEmail;


    public Gui_graph_chart(UserAccount User) throws SQLException {
      super("Chart Graph");
      this.User = User;
      build();
   }

    protected void build() throws SQLException{
      data_structure = new ArrayList<>();
      setMenu();
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
    
   protected void initializeGraph(graph_chart_data latestData){
       System.out.println("^^^^^^");
      Rectangle r = getBounds();

      setSize(r.width+1 , r.height); // refresh window
      setLocationRelativeTo(null);
            
     String title = "Network Value";
     String months[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
     if (latestData != null)
        title+= " of " + months[latestData.getMonth()-1] +", " + latestData.getYear();
     else title = " (no data.....yet)";
     
     JFreeChart lineChart = ChartFactory.createLineChart(
         title,
         "" , "Value in $",
         createDataset(latestData),
         PlotOrientation.VERTICAL,
         true,true,false);
         
      ChartPanel chartPanel = new ChartPanel(lineChart);
      chartPanel.setPreferredSize(new java.awt.Dimension(560, 367));
      setContentPane(chartPanel);

   }

    public void getData() throws SQLException{
       if (getDataFromEmail == null) getDataFromEmail = User.getEmail();
       List<graph_chart_data> list = new ArrayList<>();
       Thread thread = new Thread(){
           public void run(){
            // fetch data
            ResultSet  rs = PublicDatabase.query("SELECT email, year, month, data FROM network_value WHERE email='"+ getDataFromEmail +"' "
               + "ORDER BY year DESC, month DESC");   
            System.out.println("@@@@@@@@@@@@");
            try{
                while (rs.next()){
                    System.out.println("%%%%%%%%%");
                    int year = rs.getInt("year");
                    int month = rs.getInt("month");
                    String dataWithSpace = rs.getString("data");
                    String data = dataWithSpace.replaceAll("\\s+",""); // eliminate whitespaces
                    // int year, int month, String str
                    graph_chart_data gcd = new graph_chart_data(year, month, data); 
                    list.add(gcd);
                }
                data_structure = list; // save data 
                if (!data_structure.isEmpty()){
                   initializeGraph(data_structure.get(0));  // set latest data
                }
              setSize(560, 367); 
              setLocationRelativeTo(null);
            }
            catch(Exception e){
                System.err.println("error " + e.getMessage());}
            }
       };
       thread.start();


 }
/**
 *  This method builds the graph.
 * @return 
 */
   private DefaultCategoryDataset createDataset(graph_chart_data latestData) {
      DefaultCategoryDataset dataset = new DefaultCategoryDataset( );
      // [email, year, month , (day, val; dat, val)]
              if (latestData != null)
                for (int i=0; i< latestData.getData().size(); i++){
                     graph_chart_data.day_value tmp = latestData.getData().get(i); // get the last element (latest data)
                     dataset.addValue(tmp.getValue(), "value" , tmp.getDay()+"");      
                  }
      return dataset;
   }    

    public void setEmailOfAnOwner(String owner_email) {
        this.getDataFromEmail = owner_email;
    }
}
