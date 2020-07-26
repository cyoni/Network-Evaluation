/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evaluation;

import Data_structure.Ad;
import Data_structure.Category;
import Database.LocalDatabase;
import Graph_chart.dataStructure;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author caron
 */
public class NetworkQueriesCalculationsTest {

    LocalDatabase lDB = new LocalDatabase("input/testDB.accdb");

    public NetworkQueriesCalculationsTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of CalNumOfMembers method, of class NetworkQueriesCalculations.
     */
    @Test
    public void testCalNumOfMembers() throws Exception {
        System.out.println("CalNumOfMembers");
        NetworkQueriesCalculations instance = new NetworkQueriesCalculations(lDB);
        int expResult = 28;
        int result = instance.CalNumOfMembers();
        assertEquals(expResult, result);
    }

    /**
     * Test of CalNumOfPosts method, of class NetworkQueriesCalculations.
     */
    @Test
    public void testCalNumOfPosts() throws Exception {
        System.out.println("CalNumOfPosts");
        NetworkQueriesCalculations instance = new NetworkQueriesCalculations(lDB);
        int expResult = 8;
        int result = instance.CalNumOfPosts();
        assertEquals(expResult, result);
    }

    /**
     * Test of CalNumOfGroups method, of class NetworkQueriesCalculations.
     */
    @Test
    public void testCalNumOfGroups() throws Exception {
        System.out.println("CalNumOfGroups");
        NetworkQueriesCalculations instance = new NetworkQueriesCalculations(lDB);
        int expResult = 1;
        int result = instance.CalNumOfGroups();
        assertEquals(expResult, result);
    }

    /**
     * Test of CalNumOfAdvertisers method, of class NetworkQueriesCalculations.
     */
    @Test
    public void testCalNumOfAdvertisers() throws Exception {
        System.out.println("CalNumOfAdvertisers");
        NetworkQueriesCalculations instance = new NetworkQueriesCalculations(lDB);
        int expResult = 2;
        int result = instance.CalNumOfAdvertisers();
        assertEquals(expResult, result);
      
    }

    /**
     * Test of CalNumOfAds method, of class NetworkQueriesCalculations.
     */
    @Test
    public void testCalNumOfAds() throws Exception {
        System.out.println("CalNumOfAds");
        NetworkQueriesCalculations instance = new NetworkQueriesCalculations(lDB);
        int expResult = 3;
        int result = instance.CalNumOfAds();
        assertEquals(expResult, result);
        
    }

    /**
     * Test of CalNumOfLikes method, of class NetworkQueriesCalculations.
     */
    @Test
    public void testCalNumOfLikes() throws Exception {
        System.out.println("CalNumOfLikes");
        NetworkQueriesCalculations instance = new NetworkQueriesCalculations(lDB);
        int expResult = 10;
        int result = instance.CalNumOfLikes();
        assertEquals(expResult, result);
    }

    /**
     * Test of CalNumOfEmployees method, of class NetworkQueriesCalculations.
     */
    @Test
    public void testCalNumOfEmployees() throws Exception {
        System.out.println("CalNumOfEmployees");
        NetworkQueriesCalculations instance = new NetworkQueriesCalculations(lDB);
        int expResult = 10;
        int result = instance.CalNumOfEmployees();
        assertEquals(expResult, result);
    }

    /**
     * Test of CalSumOfSalaries method, of class NetworkQueriesCalculations.
     */
    @Test
    public void testCalSumOfSalaries() throws Exception {
        System.out.println("CalSumOfSalaries");
        NetworkQueriesCalculations instance = new NetworkQueriesCalculations(lDB);
        double expResult = 172794;
        double result = instance.CalSumOfSalaries();
        assertEquals(expResult, result, 0.0);
      
    }

    /**
     * Test of CalNumOfShares method, of class NetworkQueriesCalculations.
     */
    @Test
    public void testCalNumOfShares() throws Exception {
        System.out.println("CalNumOfShares");
        NetworkQueriesCalculations instance = new NetworkQueriesCalculations(lDB);
        int expResult = 2;
        int result = instance.CalNumOfShares();
        assertEquals(expResult, result);
    }

    /**
     * Test of CalNumOfPages method, of class NetworkQueriesCalculations.
     */
    @Test
    public void testCalNumOfPages() throws Exception {
        System.out.println("CalNumOfPages");
        NetworkQueriesCalculations instance = new NetworkQueriesCalculations(lDB);
        int expResult = 2;
        int result = instance.CalNumOfPages();
        assertEquals(expResult, result);
    }

    /**
     * Test of CalActiveMembers method, of class NetworkQueriesCalculations.
     */
    @Test
    public void testCalActiveMembers() throws Exception {
        System.out.println("CalActiveMembers");
        NetworkQueriesCalculations instance = new NetworkQueriesCalculations(lDB);
        int expResult = 24;
        int result = instance.CalActiveMembers();
        assertEquals(expResult, result);
    }

    /**
     * Test of CalActiveAds method, of class NetworkQueriesCalculations.
     */
    @Test
    public void testCalActiveAds() throws Exception {
        System.out.println("CalActiveAds");
        NetworkQueriesCalculations instance = new NetworkQueriesCalculations(lDB);
        int expResult = 2;
        int result = instance.CalActiveAds();
        assertEquals(expResult, result);
    }

    /**
     * Test of CalAvgTime method, of class NetworkQueriesCalculations.
     */
    @Test
    public void testCalAvgTime() throws Exception {
        System.out.println("CalAvgTime");
        NetworkQueriesCalculations instance = new NetworkQueriesCalculations(lDB);
        double expResult = 231;
        double result = instance.CalAvgTime();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of CalAvgFriends method, of class NetworkQueriesCalculations.
     */
    @Test
    public void testCalAvgFriends() throws Exception {
        System.out.println("CalAvgFriends");
        NetworkQueriesCalculations instance = new NetworkQueriesCalculations(lDB);
        double expResult = 1;
        double result = instance.CalAvgFriends();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of CalAvgViews method, of class NetworkQueriesCalculations.
     */
    @Test
    public void testCalAvgViews() throws Exception {
        System.out.println("CalAvgViews");
        NetworkQueriesCalculations instance = new NetworkQueriesCalculations(lDB);
        double expResult = 1;
        double result = instance.CalAvgViews();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of CalAvgLikes method, of class NetworkQueriesCalculations.
     */
    @Test
    public void testCalAvgLikes() throws Exception {
        System.out.println("CalAvgLikes");
        NetworkQueriesCalculations instance = new NetworkQueriesCalculations(lDB);
        double expResult = 1;
        double result = instance.CalAvgLikes();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of CalAvgShares method, of class NetworkQueriesCalculations.
     */
    @Test
    public void testCalAvgShares() throws Exception {
        System.out.println("CalAvgShares");
        NetworkQueriesCalculations instance = new NetworkQueriesCalculations(lDB);
        double expResult = 1;
        double result = instance.CalAvgShares();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of CalAvgPosts method, of class NetworkQueriesCalculations.
     */
    @Test
    public void testCalAvgPosts() throws Exception {
        System.out.println("CalAvgPosts");
        NetworkQueriesCalculations instance = new NetworkQueriesCalculations(lDB);
        double expResult = 1;
        double result = instance.CalAvgPosts();
        assertEquals(expResult, result, 0.0);

    }

    /**
     * Test of CalTotalExpenses method, of class NetworkQueriesCalculations.
     */
    @Test
    public void testCalTotalExpenses() throws Exception {
        System.out.println("CalTotalExpenses");
        NetworkQueriesCalculations instance = new NetworkQueriesCalculations(lDB);
        double expResult = 181094;
        double result = instance.CalTotalExpenses();
        assertEquals(expResult, result, 0.0);
       
    }

    /**
     * Test of CalAvgTraffic method, of class NetworkQueriesCalculations.
     */
    @Test
    public void testCalAvgTraffic() throws Exception {
        System.out.println("CalAvgTraffic");
        NetworkQueriesCalculations instance = new NetworkQueriesCalculations(lDB);
        double expResult = 250;
        double result = instance.CalAvgTraffic();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of CalTotalTraffic method, of class NetworkQueriesCalculations.
     */
    @Test
    public void testCalTotalTraffic() throws Exception {
        System.out.println("CalTotalTraffic");
        NetworkQueriesCalculations instance = new NetworkQueriesCalculations(lDB);
        double expResult = 8019;
        double result = instance.CalTotalTraffic();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of CalNewAds method, of class NetworkQueriesCalculations.
     */
    @Test
    public void testCalNewAds() throws Exception {
        System.out.println("CalNewAds");
        NetworkQueriesCalculations instance = new NetworkQueriesCalculations(lDB);
        double expResult = 1;
        double result = instance.CalNewAds();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of CalAvgAds method, of class NetworkQueriesCalculations.
     */
    @Test
    public void testCalAvgAds() throws Exception {
        System.out.println("CalAvgAds");
        NetworkQueriesCalculations instance = new NetworkQueriesCalculations(lDB);
        double expResult = 2;
        double result = instance.CalAvgAds();
        assertEquals(expResult, result, 0.0);
    }

    /**
     * Test of CalSumOfAdsProfit method, of class NetworkQueriesCalculations.
     */
    @Test
    public void testCalSumOfAdsProfit() throws Exception {
        System.out.println("CalSumOfAdsProfit");
        NetworkQueriesCalculations instance = new NetworkQueriesCalculations(lDB);
        double expResult = 18000;
        double result = instance.CalSumOfAdsProfit();
        assertEquals(expResult, result, 0.0);
    }
}
