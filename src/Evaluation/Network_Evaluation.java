package Evaluation;

import Database.PublicDatabase;
import Data_structure.Ad;
import Network.Gui_network;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Utils.User_Dialog;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import Account.UserAccount;
import Data_structure.Category;
import Data_structure.ProductForAdv;
import Graph.Graph;
import Nodes.Member;
import Nodes.node_metadata;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Random;
import java.util.Set;

/**
 * This class contains algorithms that provide an estimation of the network's
 * value.
 *
 * @author Yoni
 */
public class Network_Evaluation {

    private FileWriter myWriter;
    private UserAccount User;
    private Graph g;
    List<Integer> membersId;

    /////////////////The following data should be downloaded from the DB File //////////////////////////////////
//    private double value_like = 0.2;
//    private double value_share = 0.5;
//    private double value_post = 0.2;
    private double value_advertiser = 1.0;
//    private double value_time_spent = 2.0;
//    private double percent_active_ad = 70;
//    private double percent_inactive_ad = 20;
//    private double value_active_ad = 0.3;

    private int num_members;
    private int num_advertisers;
    private int num_ads;
    private int num_active_ads;

    ///////////////////////////////////////////////////////////////////////////////////////////////////////
    private double network_value = 0.0;
    private Gui_network network;
    private NetworkData network_data_from_file;

    public void evaluate(Gui_network network, NetworkData network_data_from_file, Graph g) throws IOException, SQLException {
        User = network.getUser();
        this.network = network;
        this.network_data_from_file = network_data_from_file;
        this.g = g;
        this.membersId = getMembers(new ArrayList<node_metadata>(g.getV())); // all members 

        initEvaluation();
        performEvaluation();
        closeWriter();
        recordResultInDatabase();
        showResult();

    }

    private void recordResultInDatabase() throws SQLException {

        String question = User_Dialog.getInputDialog("Would you like to record the result in the database? [1=yes, 0=no]");
        if (question.equals("1")) {
            int answer_from_server = -1;
            String evaluation_data = "";
            Date date = new Date();
            LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
            int day = localDate.getDayOfMonth();
            int year = localDate.getYear();
            int month = localDate.getMonthValue();

            ResultSet rs = PublicDatabase.query("SELECT * FROM network_value WHERE email='" + User.getEmail() + ""
                    + " AND month=" + month + "' AND year=" + year + "");

            if (rs == null) {
                evaluation_data = day + "," + network_value;
                answer_from_server = PublicDatabase.query_update("INSERT INTO network_value (email, year, month, data) VALUES('" + User.getEmail() + "'"
                        + ", ('" + year + "'), ('" + month + "'), ('" + evaluation_data + "')");
            } else {
                String data_from_database = "";
                while (rs.next()) {
                    data_from_database = rs.getString("data");
                }
                evaluation_data = data_from_database + ";" + day + "," + network_value;

                answer_from_server = PublicDatabase.query_update("UPDATE network_value SET data='" + evaluation_data + "' WHERE email='" + User.getEmail() + "'");
            }
            if (answer_from_server == 1) {
                User_Dialog.showAlert("The result has been successfully recorded!");
            } else {
                User_Dialog.showAlert("There was an error while processing this request.");
            }
        }

    }

    private int convertIntFromString(String s) {
        return Integer.parseInt(s);
    }

    private Double add(double d) {
        network_value += d;
        return network_value;
    }

    private void initEvaluation() throws IOException {
        num_members = convertIntFromString(network.num_members.getText());
        num_advertisers = convertIntFromString(network.num_advertisers.getText());
        num_ads = convertIntFromString(network.num_ads.getText());
        num_active_ads = convertIntFromString(network.active_ads.getText());

        myWriter = new FileWriter("network_evaluation.txt");
    }

    private void performEvaluation() throws IOException {
        write("Started process");

        evaluate_advetisers();
        evaluate_value_of_net();
        // evaluate.. ()...
    }

    private void write(String str) throws IOException {
        myWriter.write(str + "\n");
    }

    private void closeWriter() throws IOException {
        write("The process is completed");
        myWriter.close();
    }

    private void showResult() {
        User_Dialog.showAlert("Estimated network value is " + network_value);
    }

    private void evaluate_advetisers() throws IOException {
        write(">> Each advertiser is worth " + value_advertiser + ". Adding $" + add(value_advertiser * num_advertisers));
    }

    /**
     * The purpose of this function is to estimate the number of people who will
     * be interested in the product (and maybe buy it) To calculate this we use
     * two ways. 1) We will rely on previous advertisements posted on the
     * network and look at the average clicks 2) Take a look at the product
     * category and see what interest in this category is on the net
     *
     */
    private int findInterested(ProductForAdv p) {
        // Way 1: rely on previous advertisements
        List<Ad> list_ads = network_data_from_file.getAds(); // prev ads
        double avg_clicks = calculateAvgClicks(list_ads);

        // Way 2: Take a look at the product category
        int intersting = 0;
        if (p.getCategory() == null) {
            intersting = 0;
        } else {
            List<Integer> p_cats = p.getCategory(); // product categorys
            for (int cat : p_cats) {
                intersting += interstIngCat(cat);
            }
        }
        double w1 = 0.4;
        double w2 = 0.6;
        int sum = (int) (w1 * avg_clicks + w2 * intersting);

        return sum;

    }

    /**
     * The purpose of this function is to estimate the number of people who will
     * share the products, of the people who are interested in the product
     */
    private int findShared(int intersted) {

        if (intersted == 0) {
            return 0;
        }

        double avg_share_per_day = network_data_from_file.getAvgShares();

        double percent = (avg_share_per_day / num_members);
        double random = Math.random(); // Get probability between [0-1)

        double w1 = 0.4;
        double w2 = 0.6;

        int memberShares = (int) ((w1 * percent + w2 * random) * intersted);
        return memberShares;

    }

    /**
     * The purpose of the function is to estimate the number people who will
     * watch a particular advertisement Simulate the ad distribution process on
     * the network including all of its steps and try to predict how many people
     * will see the ad
     *
     * @param p product
     * @return The estimate of the number of people who see the ad
     */
    private int evaluate_number_of_people_who_might_see_an_ad_of_product(ProductForAdv p) {

        Map<Integer, Set<Integer>> memberPerDay = new HashMap(); // day1: (2,4,8) , day2: (6,9,32)

        int expo = p.getExpoForDay(); //Number of exposures of the advertisement per day
        int num_days = p.getDayForAdv(); // Numbers of days

        int interested = findInterested(p); // The number of people on the network who were interested in the product
        int memberShares = findShared(interested); // Within those who were interested how many people will share

        int memberExpo = 0;
        // pass over the days and fill memberPerDay 
        for (int i = 1; i <= num_days; i++) {

            Set<Integer> randomMember = new HashSet<Integer>(randomMembers(membersId, expo));
            memberPerDay.put(i, randomMember); // Add random member
            memberExpo += expo;

            List<Integer> members_who_share = randomMembers(new ArrayList<>(randomMember), memberShares); // TODO 

            for (int member : members_who_share) {
                List<Integer> friends = getFriendsOf(member);
                memberExpo += friends.size();
                memberPerDay.get(i).addAll(friends); // add to map the friend exposed to the ad
            }
        }

        return memberExpo;

    }

    /**
     *
     */
    private void evaluate_value_of_net() {

        // Way 1 :  rely on previous advertisements
        double w1 = 0.4;
        List<Ad> list_ads = network_data_from_file.getAds(); // prev ads
        double avg_view = calculateAvgViews(list_ads);

        // Way 2 : Simulate the ad distribution process
        double w2 = 0.6;
        ListProducts l = new ListProducts();
        ArrayList<ProductForAdv> products = l.products; // The products on which we will do the simulation

        // foor loop over list of products 
        for (ProductForAdv p : products) {
            int members_expo_to_product = evaluate_number_of_people_who_might_see_an_ad_of_product(p);
            members_expo_to_product = (int) (avg_view * w1 + members_expo_to_product * w2);
            double profit_of_p = evaluate_number_of_people_who_might_buy_product(p, members_expo_to_product);
            network_value += profit_of_p;

        }

        // 
//        if (network_data_from_file != null) {
//            int categories_size = network_data_from_file.getCategories();
//            List<Ad> list_ads = network_data_from_file.getAds();
//            cat_item[] arr_ads = new cat_item[list_ads.size()];
//            for (int i = 0; i < arr_ads.length; i++) {
//                arr_ads[i] = new cat_item();
//            }
//            double[] evaluated_profit_from_ad = new double[arr_ads.length];
//
//            for (int i = 0; i < list_ads.size(); i++) {
//
//                if (list_ads.get(i).getIsActive()) {// An in/active ad adds a given % of the expense to the evaluation 
//                    Double random = Math.random(); // Get probability between [0-1)
//                    int expected = list_ads.get(i).getViews(); // The number of people who will see the advertisement
//                    double told_to_others = expected * random; // The number of friends to tell about the advertisement to others.
//                    expected += told_to_others;
//                    random = Math.random(); // get another prob 
//                    // how many people are expected to buy the product
//                    int size_members_to_buy = (int) (expected * random); // how many will buy
//                    evaluated_profit_from_ad[i] = list_ads.get(i).getProductPrice() * size_members_to_buy - list_ads.get(i).getPrice();
//                    write(">> Expected earning from ad " + i + " is $" + add(evaluated_profit_from_ad[i]));
//                } else {
//                    write(">> Added an inactive ad, $" + add(percent_inactive_ad / 100 * list_ads.get(i).getPrice()));
//                }
//            }
//
//        }
    }

    private double evaluate_number_of_people_who_might_buy_product(ProductForAdv p, int memberExpo) {

        int expo = p.getExpoForDay(); //Number of exposures of the advertisement per day
        int num_days = p.getDayForAdv(); // Numbers of days

        Double random = Math.random(); // Get probability between [0-1)
        int memberBuy = (int) (memberExpo * random); // number of member will buy the product
        double revenue = p.getProfit() * memberBuy;

        double ad_price = network_data_from_file.getPrice_ads();
        double expense = ad_price * num_days * expo;

        double profit = revenue - expense;
        return profit;

    }

    // return list of the id of the members
    public List<Integer> getMembers(List<node_metadata> nodes) {
        List<Integer> membersId = new ArrayList();
        for (node_metadata n : nodes) {
            if (n instanceof Member) {
                int id = n.getId();
                membersId.add(id);
            }
        }
        return membersId;
    }

    /**
     * return n member id from the graph
     *
     * @param n
     * @return n member id from the graph
     */
    public List<Integer> randomMembers(List<Integer> stock, int n) {
        List<Integer> randoMembers = new ArrayList();
        for (int i = 0; i < n; i++) {
            Random random = new Random();
            int id = stock.get(random.nextInt(stock.size()));
            randoMembers.add(id);
        }
        return randoMembers;
    }

    /**
     * return the number of interesting people in specific category
     *
     * @param cat_to_check
     * @return the number of interesting people in specific category
     */
    //
    public int interstIngCat(int cat_to_check) {
        List<Category> net_cats = network_data_from_file.getList_cats(); // category in network
        int idx = net_cats.indexOf(new Category(cat_to_check));
        if (idx != -1) {
            return net_cats.get(idx).getMemberInterac();
        } else {
            return 0;
        }
    }

    /**
     *
     * @param id
     * @return list of friend of id
     */
    public List<Integer> getFriendsOf(int id) {
        List<Integer> friends = new ArrayList();
        List<node_metadata> nodes = g.getNeighbors(id);
        friends = getMembers(nodes);
        return friends;
    }

    /**
     *
     * @param ads
     * @return the avg views of ads in the network.
     */
    private double calculateAvgViews(List<Ad> ads) {
        int sum = 0;
        if (!ads.isEmpty()) {
            for (Ad a : ads) {
                double view = a.getViews();
                sum += view;
            }
            return sum / ads.size();
        }
        return sum;
    }

    /**
     *
     * @param ads
     * @return the clicks of ads in the network.
     */
    private double calculateAvgClicks(List<Ad> ads) {
        int sum = 0;
        if (!ads.isEmpty()) {
            for (Ad a : ads) {
                double click = a.getClicks();
                sum += click;
            }
            return sum / ads.size();
        }
        return sum;
    }

    // a data base for the evaluation
    class cat_item {

        double profit = 0;
        int p = 0; // number of people who see an ad
        int counter; // how many ads under the same cat there are

        public cat_item() {
        }

        ;
        public cat_item(int p) {
            this.p = p;
        }

        ;
        public int getCounter() {
            return counter;
        }

        public void setP(int p) {
            this.p = p;
        }

        public int getP() {
            return p;
        }

        public void increaseCounter() {
            counter++;
        }

        public void addProfit(double p) {
            profit += p;
        }

        public double getProfit() {
            return this.profit;
        }
    }

}
