/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package network;

import algorithms.NetCalculations;
import gui.Gui_network;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author caron
 */
public class NetworkData {
    
    private NetCalculations cal;

    private int numMembers;
    private int numGroups;
    private int numPages;
    private int numPosts;
    private int numLikes;
    private int numShares;
    private int numViews;
    private int activeMembers;
    
    private double avgTime;
    private double avgView;


    
    public NetworkData (NetCalculations c) {
        cal = c;
        
        try {
         numMembers= c.CalNumOfMembers();
         numLikes= c.CalNumOfLikes();
         numShares= c.CalNumOfShares();
         numPages = c.CalNumOfPages();
         activeMembers = c.CalActiveMembers();
         avgTime = c.CalAvgTime();
         avgView = c.CalAvgViews();
         numGroups = c.CalNumOfGroups();
                 
        } catch (SQLException ex) {
            Logger.getLogger(Gui_network.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getNumMembers() {
        return numMembers;
    }

    public int getNumPages() {
        return numPages;
    }

    public int getNumPosts() {
        return numPosts;
    }

    public int getNumLikes() {
        return numLikes;
    }

    public int getNumShares() {
        return numShares;
    }

    public int getNumViews() {
        return numViews;
    }

    public int getActiveMembers() {
        return activeMembers;
    }

    public double getAvgTime() {
        return avgTime;
    }

    public int getNumGroups() {
        return numGroups;
    }

    public double getAvgView() {
        return avgView;
    }

    

    
}
