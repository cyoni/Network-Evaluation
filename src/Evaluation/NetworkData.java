/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evaluation;

import Data_structure.Ad;
import Data_structure.Category;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import Network.Gui_network;

/**
 *
 * @author caron
 */
public class NetworkData {
    
    private NetworkQueriesCalculations cal;

    // Basic 
    private int numMembers;
    private int numPages;
    private int numLikes;
    private int numShares;
    private int activeMembers;
    private int numGroups;
    private int numAdvertisers;
    private int numAds;

    //Avg
    private double avgShares;
    private double avgLikes;
    private double avgPosts;
    private double avgFriends;
    private double avgTime;

    //Staff
    private int numEmployees;
    private double SumSalaries;
    private double totalExpenses; 
    
    //Traffic 
    private double totalTraffic;
    private double avgTraffic;
    
    //Ads
    private int activeAds;
    private double avgAds;
    private double newAds;
    private double netProfit;
    
    // list of obj
    int advertisers;
    private List<Ad> list_ads;
    private List<Category> list_cats;
    double price_ads;

    // need to add
    private int numPosts;
    private int numViews;
    private double avgViews;


    
    public NetworkData (NetworkQueriesCalculations c) {
        cal = c;
        
        try {
            
         //Basic
         numMembers= c.CalNumOfMembers();
         numPages = c.CalNumOfPages();
         numLikes= c.CalNumOfLikes();
         numShares= c.CalNumOfShares();
         activeMembers = c.CalActiveMembers();
         numGroups = c.CalNumOfGroups();
         numAdvertisers = c.CalNumOfAdvertisers();
         numAds = c.CalNumOfAds();
         //Avg
         avgShares = c.CalAvgShares();
         avgLikes= c.CalAvgLikes();
         avgPosts = c.CalAvgPosts();
         avgFriends = c.CalAvgFriends();
         avgTime = c.CalAvgTime();
         //Staff
         numEmployees = c.CalNumOfEmployees();
         SumSalaries = c.CalSumOfSalaries();
         totalExpenses= c.CalTotalExpenses();
         //Traffic 
         totalTraffic= c.CalTotalTraffic();
         avgTraffic = c.CalAvgTraffic();
    
         //Ads
         activeAds= c.CalActiveAds();
         avgAds = c.CalAvgAds();
         newAds= c.CalNewAds();
         netProfit= c.CalSumOfAdsProfit();
         
         advertisers = c.getAdvertisers();
         list_ads = c.getAds();
         list_cats = c.getCats();
         price_ads = c.getPriceOfAd();

         
         // need to add
          avgViews = c.CalAvgViews();
         
        
                 
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

    public int getNumAdvertisers() {
        return numAdvertisers;
    }

    public int getNumEmployees() {
        return numEmployees;
    }

    public double getSumSalaries() {
        return SumSalaries;
    }

    public double getAvgPosts() {
        return avgPosts;
    }

    public int getNumAds() {
        return numAds;
    }

    public double getAvgFriends() {
        return avgFriends;
    }

    public double getTotalExpenses() {
        return totalExpenses;
    }

    public double getTotalTraffic() {
        return totalTraffic;
    }

    public double getAvgTraffic() {
        return avgTraffic;
    }

    public double getNewAds() {
        return newAds;
    }

    public int getActiveAds() {
        return activeAds;
    }

    public double getNetProfit() {
        return netProfit;
    }

    public double getAvgAds() {
        return avgAds;
    }

    public int getAdvertisers() {
        return advertisers;
    }
    
    public List<Ad> getAds(){
        return list_ads;
    }

    public int getCategories() {
        return cal.getCategories();
    }

    public List<Ad> getList_ads() {
        return list_ads;
    }

    public List<Category> getList_cats() {
        return list_cats;
    }

    public double getPrice_ads() {
        return price_ads;
    }
    
    
    
 
}
