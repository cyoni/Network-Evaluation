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
    private int advertisers;
    
    private double avgTime;
    private double avgViews;
    private double avgShares;
    private double avgLikes;

        
private int numEmployees;
private double SumSalaries;

    
    public NetworkData (NetCalculations c) {
        cal = c;
        
        try {
         numMembers= c.CalNumOfMembers();
         numLikes= c.CalNumOfLikes();
         numShares= c.CalNumOfShares();
         numPages = c.CalNumOfPages();
         activeMembers = c.CalActiveMembers();
         advertisers = c.CalNumOfAdvertisers();
         numGroups = c.CalNumOfGroups();


         avgTime = c.CalAvgTime();
         avgViews = c.CalAvgViews();
         avgShares = c.CalAvgShares();
         avgLikes= c.CalAvgLikes();
         numGroups = c.CalNumOfGroups();
         
         numEmployees = c.CalNumOfEmployees();
         SumSalaries = c.CalSumOfSalaries();
                 
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

    public double getAvgViews() {
        return avgViews;
    }

    public double getAvgShares() {
        return avgShares;
    }

    public double getAvgLikes() {
        return avgLikes;
    }

    public int getAdvertisers() {
        return advertisers;
    }

    public int getNumEmployees() {
        return numEmployees;
    }

    public double getSumSalaries() {
        return SumSalaries;
    }
    
    

    

    
}
