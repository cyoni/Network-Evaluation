/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Evaluation;

import Database.AccesConnection;
import Data_structure.Ad;
import Data_structure.Category;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import Evaluation.Evaluation_Advertiser;
import Graph_chart.dataStructure;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;
import org.apache.commons.lang3.time.DateUtils;

/**
 *
 * @author caron
 */
public class NetCalculations {

    Connection conn;
    Statement statment;

    public NetCalculations(AccesConnection acc) {

        conn = acc.getConn();
        statment = acc.getStatment();

    }

    /**
     * This method calculate the number of members in the network
     *
     * @return
     * @throws SQLException
     */
    public int CalNumOfMembers() throws SQLException {
        
        try {
            getRegisterByMonth(2020);
        } catch (ParseException ex) {
            Logger.getLogger(NetCalculations.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            getRegisterByMonth(2020,1);
        } catch (ParseException ex) {
            Logger.getLogger(NetCalculations.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ResultSet rs = statment.executeQuery("SELECT Count(*) AS [Members] FROM [T_Members]");
        if (rs.next()) {
            return rs.getInt("Members");
        } else {
            return 0;
        }
    }

    /**
     * This method calculate the number of posts in the network
     *
     * @return
     * @throws SQLException
     */
    public int CalNumOfPosts() throws SQLException {
        ResultSet rs = statment.executeQuery("SELECT Count(*) AS [Posts] FROM [T_Posts]");
        if (rs.next()) {
            return rs.getInt("Posts");
        } else {
            return 0;
        }
    }

    /**
     * This method calculate the number of groups in the network
     *
     * @return
     * @throws SQLException
     */
    public int CalNumOfGroups() throws SQLException {
        ResultSet rs = statment.executeQuery("SELECT Count(*) AS [Groups] FROM [T_Groups]");
        if (rs.next()) {
            return rs.getInt("Groups");
        } else {
            return 0;
        }
    }

    /**
     * This method calculate the number of Advertisers in the network
     *
     * @return
     * @throws SQLException
     */
    public int CalNumOfAdvertisers() throws SQLException {
        ResultSet rs = statment.executeQuery("SELECT Count(*) AS [Advertisers] FROM [T_Advertisers]");
        if (rs.next()) {
            return rs.getInt("Advertisers");
        } else {
            return 0;
        }
    }

    /**
     * This method calculate the number of advertisements in the network
     *
     * @return
     * @throws SQLException
     */
    public int CalNumOfAds() throws SQLException {
        ResultSet rs = statment.executeQuery("SELECT Count(*) AS [ads] FROM [T_advertisements]");
        if (rs.next()) {
            return rs.getInt("ads");
        } else {
            return 0;
        }
    }

    /**
     * This method calculate the number of likes in the network
     *
     * @return
     * @throws SQLException
     */
    public int CalNumOfLikes() throws SQLException {
        ResultSet rs = statment.executeQuery("SELECT Count(*) AS [Likes] FROM [T_Likes]");
        if (rs.next()) {
            return rs.getInt("Likes");
        } else {
            return 0;
        }
    }

    /**
     * This method calculate the number of employees in the network
     *
     * @return
     * @throws SQLException
     */
    public int CalNumOfEmployees() throws SQLException {
        ResultSet rs = statment.executeQuery("SELECT Count(*) AS [employees] FROM [T_Employees]");
        if (rs.next()) {
            return rs.getInt("employees");
        } else {
            return 0;
        }
    }

    /**
     * This method calculate the sum of salaries in the network
     *
     * @return
     * @throws SQLException
     */
    public double CalSumOfSalaries() throws SQLException {
        ResultSet rs = statment.executeQuery("SELECT sum(salary) AS [salaries] FROM [T_Employees]");
        if (rs.next()) {
            return rs.getDouble("salaries");
        } else {
            return 0;
        }
    }

    /**
     * This method calculate the number of shares in the network
     *
     * @return
     * @throws SQLException
     */
    public int CalNumOfShares() throws SQLException {
        ResultSet rs = statment.executeQuery("SELECT Count(*) AS [Shares] FROM [T_Shares]");
        if (rs.next()) {
            return rs.getInt("Shares");
        } else {
            return 0;
        }
    }

    /**
     * This method calculate the number of pages in the network
     *
     * @return
     * @throws SQLException
     */
    public int CalNumOfPages() throws SQLException {
        ResultSet rs = statment.executeQuery("SELECT Count(*) AS [Pages] FROM [T_Pages]");
        if (rs.next()) {
            return rs.getInt("Pages");
        } else {
            return 0;
        }
    }

    /**
     * This method calculate the number of active members in the network
     *
     * @return
     * @throws SQLException
     */
    public int CalActiveMembers() throws SQLException {
        ResultSet rs = statment.executeQuery("SELECT Count (*) AS [active_members] FROM [T_Members] WHERE ((active)=Yes);");
        if (rs.next()) {
            return rs.getInt("active_members");
        } else {
            return 0;
        }
    }

    /**
     * This method calculate the number of active advertisements a in the
     * network
     *
     * @return
     * @throws SQLException
     */
    public int CalActiveAds() throws SQLException {
        ResultSet rs = statment.executeQuery("SELECT Count (*) AS [active_ads] FROM [T_Advertisements] WHERE ((active)=Yes);");
        if (rs.next()) {
            return rs.getInt("active_ads");
        } else {
            return 0;
        }
    }

    /**
     * This method calculate the avg of time use per day a in the network
     *
     * @return
     * @throws SQLException
     */
    public double CalAvgTime() throws SQLException {
        ResultSet rs = statment.executeQuery("SELECT Avg (time_spent) AS [avg_time] FROM [T_Members];");
        if (rs.next()) {
            return rs.getDouble("avg_time");
        } else {
            return 0;
        }
    }

    /**
     * This method calculate the avg of friends in the network
     *
     * @return
     * @throws SQLException
     */
    public double CalAvgFriends() throws SQLException {
        ResultSet rs = statment.executeQuery("SELECT Avg (friends) AS [avg_friends] FROM [T_Members];");
        if (rs.next()) {
            return rs.getDouble("avg_friends");
        } else {
            return 0;
        }
    }

    /**
     * This method calculate the avg of view per post a in the network
     *
     * @return
     * @throws SQLException
     */
    public double CalAvgViews() throws SQLException {
        ResultSet rs = statment.executeQuery("SELECT Avg (countOfDay) AS [avg_views] "
                + "FROM( SELECT Count(view_id) AS [countOfDay], view_date FROM [T_Views] GROUP BY view_date);");
        if (rs.next()) {
            return rs.getDouble("avg_views");
        } else {
            return 0;
        }
    }

    /**
     * This method calculate the avg of like per post a in the network
     *
     * @return
     * @throws SQLException
     */

    public double CalAvgLikes() throws SQLException {
        ResultSet rs = statment.executeQuery("SELECT Avg (countOfDay) AS [avg_likes] "
                + "FROM( SELECT Count(like_id) AS [countOfDay], like_date FROM [T_Likes] GROUP BY like_date);");
        if (rs.next()) {
            return rs.getDouble("avg_likes");
        } else {
            return 0;
        }
    }

    /**
     * This method calculate the avg of share per post a in the network
     *
     * @return
     * @throws SQLException
     */
    public double CalAvgShares() throws SQLException {
        ResultSet rs = statment.executeQuery("SELECT Avg (countOfDay) AS [avg_shares] "
                + "FROM( SELECT Count(share_id) AS [countOfDay], share_date FROM [T_Shares] GROUP BY share_date);");
        if (rs.next()) {
            return rs.getDouble("avg_shares");
        } else {
            return 0;
        }
    }

    /**
     * This method calculate the avg of posts a in the network
     *
     * @return
     * @throws SQLException
     */
    public double CalAvgPosts() throws SQLException {
        ResultSet rs = statment.executeQuery(" SELECT  Avg (countOfDay) AS [avg_posts] FROM "
                + "(SELECT  Count(T_Posts.post_id) AS [countOfDay], T_Components.creation_date FROM [T_Posts]"
                + " INNER JOIN T_Components ON T_Posts.post_id = T_Components.compoment_id GROUP BY creation_date ) ; ");
        if (rs.next()) {
            return rs.getDouble("avg_posts");
        } else {
            return 0;
        }
    }

    /**
     * This method calculate the total expenses of the network
     *
     * @return
     * @throws SQLException
     */
    public double CalTotalExpenses() throws SQLException {
        ResultSet rs = statment.executeQuery("SELECT [total] FROM [T_Expenses];");
        if (rs.next()) {
            return rs.getInt("total");
        } else {
            return 0;
        }
    }

    /**
     * This method calculate the avg traffic per day of the network
     *
     * @return
     * @throws SQLException
     */
    public double CalAvgTraffic() throws SQLException {
        ResultSet rs = statment.executeQuery("SELECT Avg (traffic_diversity) AS [avg_traffic] FROM [T_Traffic];");
        if (rs.next()) {
            return rs.getInt("avg_traffic");
        } else {
            return 0;
        }
    }

    /**
     * This method calculate the total traffic in the network
     *
     * @return
     * @throws SQLException
     */
    public double CalTotalTraffic() throws SQLException {
        ResultSet rs = statment.executeQuery("SELECT Sum (traffic_diversity) AS [sum_traffic] FROM [T_Traffic];");
        if (rs.next()) {
            return rs.getInt("sum_traffic");
        } else {
            return 0;
        }
    }

    /**
     * This method calculate new Ads in the network
     *
     * @return
     * @throws SQLException
     */
    public double CalNewAds() throws SQLException {
        ResultSet rs = statment.executeQuery("SELECT Avg (ads_m) AS [avg_ads] FROM (SELECT Count(person_id) AS [ads_m], Format([join_date],\"mm\") AS [new_ads] FROM [T_Advertisers] GROUP BY Format([join_date],\"mm\"));");
        if (rs.next()) {
            return rs.getInt("avg_ads");
        } else {
            return 0;
        }
    }

    public double CalAvgAds() throws SQLException {
        ResultSet rs = statment.executeQuery("SELECT Avg (month) AS [avg_ads] FROM [T_AdsByMonth];");
        if (rs.next()) {
            return rs.getDouble("avg_ads");
        } else {
            return 0;
        }

    }

    public double CalSumOfAdsProfit() throws SQLException {
        ResultSet rs = statment.executeQuery("SELECT Sum(price) AS [profit] FROM [T_Advertisements]");
        if (rs.next()) {
            return rs.getDouble("profit");
        } else {
            return 0;
        }
    }

    public int getAdvertisers() throws SQLException {
        ResultSet rs = statment.executeQuery("SELECT COUNT(person_id) as x FROM [T_Advertisers]");
        if (rs.next()) {
            return rs.getInt("x");
        } else {
            return 0;
        }
    }

    public ArrayList<Evaluation_Advertiser> _getAdvertisers() throws SQLException {
        ArrayList<Evaluation_Advertiser> advertisers = new ArrayList<Evaluation_Advertiser>();
        ResultSet rs = statment.executeQuery("SELECT T_Advertisers.person_id, Sum(T_Advertisements.price) AS [SumOfprice]"
                + " FROM T_Advertisers INNER JOIN T_Advertisements ON T_Advertisers.person_id = T_Advertisements.advertisers_id"
                + " GROUP BY T_Advertisers.person_id;");
        while (rs.next()) {
            int advertisersID = rs.getInt("person_id");
            int expenses = rs.getInt("SumOfprice");
            Evaluation_Advertiser advertiser = new Evaluation_Advertiser(advertisersID, expenses);
            advertisers.add(advertiser);
        }
        return advertisers;
    }

    public ArrayList<Ad> getAds() throws SQLException {
        ArrayList<Ad> ads = new ArrayList<>();
        ResultSet rs = statment.executeQuery("SELECT * FROM T_Advertisements INNER JOIN T_Categories ON T_Advertisements.Category = T_Categories.ID");
        while (rs.next()) {
            boolean isActive = rs.getBoolean("active");
            double price = rs.getDouble("price");
            double product_price = rs.getDouble("productPrice");
            int category = rs.getInt("Category");
            int views = rs.getInt("views");
            int clicks = rs.getInt("clicks");

            ads.add(new Ad(isActive, price, product_price, category, views, clicks));
        }
        return ads;
    }

    public int getCategories() {
        ResultSet rs;
        try {
            rs = statment.executeQuery("SELECT COUNT(id) as s FROM T_Categories;");
            while (rs.next()) {
                return rs.getInt("s");
            }

        } catch (SQLException ex) {
            Logger.getLogger(NetCalculations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public ArrayList<Category> getCats() throws SQLException {
        ArrayList<Category> cats = new ArrayList<>();
        ResultSet rs = statment.executeQuery("SELECT Count(T_Likes.member_id) AS num_members, T_Pages.category_id, T_Categories.cat_name"
                + " FROM (T_Likes INNER JOIN T_Pages ON T_Likes.compoment_id = T_Pages.page_id) "
                + "INNER JOIN T_Categories ON T_Pages.category_id = T_Categories.ID GROUP BY T_Pages.category_id, T_Categories.cat_name;");
        while (rs.next()) {
            int category = rs.getInt("category_id");
            int interest = rs.getInt("num_members");
            String name = rs.getString("cat_name");

            cats.add(new Category(category, name, interest));
        }
        return cats;
    }

    public double getPriceOfAd() {
        ResultSet rs;
        try {
            rs = statment.executeQuery("SELECT [price_for_day] FROM [T_Advertising_prices];");
            while (rs.next()) {
                return rs.getDouble("price_for_day");
            }

        } catch (SQLException ex) {
            Logger.getLogger(NetCalculations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return -1;
    }

    public List<dataStructure> getGender() {
        ResultSet rs;
        int male = 0;
        int female = 0;
        List<dataStructure> genderList = new ArrayList<>();

        try {
            rs = statment.executeQuery("SELECT [T_Persons.gender] FROM [T_Members] INNER JOIN T_Persons ON T_Members.person_id = T_Persons.person_id;");
            while (rs.next()) {
                int gender = rs.getInt("gender");
                if (gender == 0) {
                    male++;
                } else {
                    female++;
                }
            }
            genderList.add(new dataStructure("male", male));
            genderList.add(new dataStructure("female", female));

        } catch (SQLException ex) {
            Logger.getLogger(NetCalculations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return genderList;
    }

    public List<dataStructure> getRegisterByMonth(int year) throws ParseException {
        ResultSet rs;

        List<dataStructure> registerList = new ArrayList<>();
        String low_year = "#01/01/"+ String.valueOf(year)+"#";
        String high_year = "#01/01/"+ String.valueOf(year+1)+"#";
        String query = "SELECT Count(T_Members.person_id) AS CountOfperson_id, Format([register_date],\"mmmm yyyy\") AS [month] FROM T_Members WHERE (((T_Members.register_date)>="+low_year+") And ((T_Members.register_date) <"+high_year+") ) GROUP BY Format([register_date],\"mmmm yyyy\");";
        try {
            rs = statment.executeQuery(query);
            while (rs.next()) {
                String m = rs.getString("month");
                int num_registers = rs.getInt("CountOfperson_id");
                DateFormat format = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
                Date date = format.parse(m);
                int month = date.getMonth() +1;
                registerList.add(new dataStructure(String.valueOf(month), num_registers));

                  
            }

        } catch (SQLException ex) {
            Logger.getLogger(NetCalculations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return registerList;
    }
    
      public List<dataStructure> getRegisterByMonth(int year, int month) throws ParseException {
        ResultSet rs;

        List<dataStructure> trafficList = new ArrayList<>();
        if ( month == 12){ // handle !!!! 
         
        }
        String low_date = "#"+String.valueOf(month)+"/01/"+ String.valueOf(year)+"#";
        String high_date = "#"+String.valueOf(month+1)+"/01/"+ String.valueOf(year)+"#";

        String query = "SELECT [date], [traffic_diversity] FROM T_Traffic WHERE ((((T_Traffic.date)>=" +low_date+ ") And ((T_Traffic.date)<" +high_date+"))) ORDER BY T_Traffic.Date;";
        try {
            rs = statment.executeQuery(query);
            while (rs.next()) {
                java.sql.Date dbSqlDate = rs.getDate("date");
                int traffic = (int ) rs.getDouble("traffic_diversity");
                LocalDate localDate = dbSqlDate.toLocalDate();
                int cur_day = localDate.getDayOfMonth();
                trafficList.add(new dataStructure(String.valueOf(cur_day), traffic));

            }

        } catch (SQLException ex) {
            Logger.getLogger(NetCalculations.class.getName()).log(Level.SEVERE, null, ex);
        }
        return trafficList;
    }



}
