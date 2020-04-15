
package Network;

import ManageUsers.Gui_manageUsers;
import Login.Gui_login;
import Database.AccesConnection;
import Account.Gui_MyAccount;
import Evaluation.NetCalculations;
import Evaluation.Network_Evaluation;
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
import Evaluation.NetworkData;
import org.jfree.ui.RefineryUtilities;

/**
 * This class represents the menu of network gui.
 * @author Yoni
 */
public class Menu_network {
    private JMenuItem loadNetworkFile, permissions, logOut, exit, graphChart, evaluateNetwork, graphVisualization, myAccount;
    private final Gui_network gui_network;
    
    public Menu_network(Gui_network gui_network){
       this.gui_network = gui_network;
    }
    
    protected void setMenu(){
        JMenuBar menuBar = new JMenuBar(); 

        JMenu file = new JMenu("File"); 
        JMenu help = new JMenu("Help"); 
        JMenu graph = new JMenu("Graph");
        JMenu network = new JMenu("Network");
                 
        loadNetworkFile = new JMenuItem("Load a network file"); 
        myAccount = new JMenuItem("My Account"); 
        logOut = new JMenuItem("Log Out"); 
        exit = new JMenuItem("Exit");
        
        graphVisualization = new JMenuItem("Graph Visualization");
        graphChart = new JMenuItem("Graph Chart");
        
        evaluateNetwork = new JMenuItem("Evaluate Network");    
        permissions = new JMenuItem("Grant/revoke permission");
        
        JMenuItem m4 = new JMenuItem("How to use this software");
        JMenuItem m5 = new JMenuItem("About");

        file.add(loadNetworkFile); 
        file.addSeparator();
        file.add(myAccount);
        file.add(logOut); 
        file.addSeparator();
        file.add(exit); 
        
        graph.add(graphVisualization);
        graph.add(graphChart);

        help.add(m4);
        help.add(m5);

        network.add(evaluateNetwork);
        network.addSeparator();
        network.add(permissions);

        // add menu to menu bar 
        menuBar.add(file); 
        menuBar.add(graph);
        menuBar.add(network);
        menuBar.add(help);
        gui_network.setJMenuBar(menuBar);  // add menubar to frame 
        startMouseListener();
    }

    private void startMouseListener() {
           
            myAccount.addActionListener((ActionEvent e) -> { 
                    // open my account window
                    Gui_MyAccount g = new Gui_MyAccount(gui_network.getUser());
                    g.setVisible(true);
            });
        
            evaluateNetwork.addActionListener((ActionEvent e) -> { 
                try {
                    // evalutate network
                    Network_Evaluation g = new Network_Evaluation();
                    g.evaluate(gui_network, gui_network.getNetworkDataFromFile());
                } catch (IOException ex) {
                    Logger.getLogger(Menu_network.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SQLException ex) {
                    Logger.getLogger(Menu_network.class.getName()).log(Level.SEVERE, null, ex);
                }
            });
           
            graphVisualization.addActionListener((ActionEvent e) -> { // open graph visualization
                gui_network.show_graph_visualization();
            });
                    
	    permissions.addActionListener((ActionEvent e) -> {// add/change permissions
            Gui_manageUsers g = new Gui_manageUsers(gui_network.User);// open gui_manageUsers window
            g.setVisible(true);   
            });
            
            exit.addActionListener((ActionEvent e) -> {// exit
             System.exit(0);
            });
            
            
            graphChart.addActionListener((ActionEvent e) -> {// open chart graph window
                Graph_chart.Gui_graph_chart chart;
                try {
                    chart = new Graph_chart.Gui_graph_chart(gui_network.User);
                    chart.getData();
                    chart.pack();
                    RefineryUtilities.centerFrameOnScreen(chart);
                    chart.setVisible(true);    
                } 
                catch (SQLException ex) {
                    Logger.getLogger(Gui_network.class.getName()).log(Level.SEVERE, null, ex);
                }

            });      
            
            
            logOut.addActionListener((ActionEvent e) -> {// user log out
            gui_network.dispose();
            File file = new File("user.txt");
            try{
            file.delete();}
            catch(Exception x){System.out.println(x);}
            Gui_login g = new Gui_login();
            g.setVisible(true);   
 
            });
            
            
            // open FileChooser and build NetworkData 
            loadNetworkFile.addActionListener((ActionEvent e) -> {
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

