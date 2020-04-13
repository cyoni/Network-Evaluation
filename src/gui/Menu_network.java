
package gui;

import DB_Connection.AccesConnection;
import algorithms.NetCalculations;
import algorithms.evaluation;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import network.NetworkData;
import org.jfree.ui.RefineryUtilities;

/**
 * This class represents the menu of network gui.
 * @author Yoni
 */
public class Menu_network {
    private JMenuItem m1, m2, m9, m3, m7, m8, m6, m10;
    private JMenuBar menuBar = new JMenuBar(); // Window menu bar
    private final Gui_network gui_network;
    
    public Menu_network(Gui_network gui_network){
       this.gui_network = gui_network;
    }
    
    protected void setMenu(){
        JMenuBar mb; 
        // create a menubar 
        mb = new JMenuBar(); 
        // create a menu 
        JMenu x = new JMenu("File"); 
        JMenu x2 = new JMenu("Help"); 
        JMenu x3 = new JMenu("Graph");
        JMenu x4 = new JMenu("Network");
                 
        m1 = new JMenuItem("Load a network file"); 
        m10 = new JMenuItem("My Account"); 
        m9 = new JMenuItem("Log Out"); 
        m3 = new JMenuItem("Exit");
        
        m6 = new JMenuItem("Graph Visualization");
        m7 = new JMenuItem("Graph Chart");
        
        m8 = new JMenuItem("Evaluate Network");    
        m2 = new JMenuItem("Grant/revoke permission");
        
        JMenuItem m4 = new JMenuItem("How to use this software");
        JMenuItem m5 = new JMenuItem("About");
        // add menu items to menu 
        x.add(m1); 
        x.addSeparator();
        x.add(m10);// my account
        x.add(m9); // logout
        x.addSeparator();
        x.add(m3); //exit
        
        x3.add(m6);
        x3.add(m7);

        x2.add(m4);
        x2.add(m5);

        x4.add(m8);
        x4.add(m2);

        // add menu to menu bar 
        mb.add(x); 
        mb.add(x3);
        mb.add(x4);
        mb.add(x2);
        gui_network.setJMenuBar(mb);  // add menubar to frame 
        startMouseListener();
    }

    private void startMouseListener() {
           
            m8.addActionListener((ActionEvent e) -> { 
                try {
                    // evalutate network
                    evaluation g = new evaluation();
                    g.evaluate(gui_network, gui_network.getNetworkDataFromFile());
                } catch (IOException ex) {
                    Logger.getLogger(Menu_network.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Menu_network.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
           
            m6.addActionListener((ActionEvent e) -> { // open graph visualization
                gui_network.show_graph_visualization();
            });
                    
	    m2.addActionListener((ActionEvent e) -> {// add/change permissions
            Gui_manageUsers g = new Gui_manageUsers();// open gui_manageUsers window
            g.setUser(gui_network.User);
            g.setVisible(true);   
            });
            
            m3.addActionListener((ActionEvent e) -> {// exit
             System.exit(0);
            });
            
            
            m7.addActionListener((ActionEvent e) -> {// open chart graph window
                graph_chart.Gui_graph_chart chart;
                try {
                    chart = new graph_chart.Gui_graph_chart(gui_network.User);
                    chart.getData();
                    chart.pack();
                    RefineryUtilities.centerFrameOnScreen(chart);
                    chart.setVisible(true);    
                } 
                catch (SQLException ex) {
                    Logger.getLogger(Gui_network.class.getName()).log(Level.SEVERE, null, ex);
                }

            });      
            
            
            m9.addActionListener((ActionEvent e) -> {// user log out
            gui_network.dispose();
            File file = new File("user.txt");
            try{
            file.delete();}
            catch(Exception x){System.out.println(x);}
            Gui_login g = new Gui_login();
            g.setVisible(true);   
 
            });
            
            
            // open FileChooser and build NetworkData 
            m1.addActionListener((ActionEvent e) -> {
            JFileChooser fileChooser = new JFileChooser();
            int rVal = fileChooser.showOpenDialog(gui_network);
            if (rVal == JFileChooser.APPROVE_OPTION) { // click open file 
                
            String diraction = fileChooser.getCurrentDirectory().toString()+"\\"+fileChooser.getSelectedFile().getName();
            gui_network.accessConnection_local_database = new AccesConnection(diraction);
            NetCalculations cal = new NetCalculations(gui_network.accessConnection_local_database);
            gui_network.setNetworkDataFromFile(new NetworkData(cal));
            
            // set all the number field of the network
            gui_network.setField();
               
            }
            else { // click cancel 
                System.out.println("You pressed cancel");
            }
            });

    }
    
}

