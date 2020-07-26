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

    private int num_members;
    private int num_advertisers;
    private int num_ads;
    private int num_active_ads;

    private double network_value = 0.0;
    private Gui_network network;
    private NetworkData network_data_from_file;
    private UserAccount user;

    public Network_Evaluation(UserAccount User) {
        this.user = User;
    }

    Network_Evaluation() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void evaluate(Gui_network network, NetworkData network_data_from_file, Graph g) throws IOException, SQLException {
        User = network.getUser();
        this.network = network;
        this.network_data_from_file = network_data_from_file;
        this.g = g;
        this.membersId = getMembers(new ArrayList<node_metadata>(g.getV())); // all members 

        initEvaluation();
        startWriter();
        avg_value_of_net();
        closeWriter();
        showResult();
    }

    private void initEvaluation() throws IOException {
        num_members = convertIntFromString(network.num_members.getText());
        num_advertisers = convertIntFromString(network.num_advertisers.getText());
        num_ads = convertIntFromString(network.num_ads.getText());
        num_active_ads = convertIntFromString(network.active_ads.getText());
    }

    private void startWriter() throws IOException {
        myWriter = new FileWriter("network_evaluation.txt");
    }

    private void writeNewLine(String str) throws IOException {
        myWriter.write(str + "\n");
    }

    private void closeWriter() throws IOException {
        writeNewLine("The Network value is: " + network_value);
        myWriter.close();
    }

    private void showResult() {
        Eval_gui eval_gui = new Eval_gui(user, network_value);
        eval_gui.setVisible(true);
        eval_gui.pack();
    }

    private void avg_value_of_net() throws IOException {
        double sum = 0;
        int iteration = 10;
        for (int i = 0; i < iteration; i++) {
            double tmp_network_value = evaluate_value_of_net();
            sum += tmp_network_value;
        }

        network_value = sum / iteration;
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

        // pass over the days and fill memberPerDay 
        for (int i = 1; i <= num_days; i++) {

            Set<Integer> randomMember = new HashSet<Integer>(randomMembers(membersId, expo));
            memberPerDay.put(i, randomMember); // Add random member

            List<Integer> members_who_share = randomMembers(new ArrayList<>(randomMember), memberShares); // TODO 

            for (int member : members_who_share) {
                List<Integer> friends = getFriendsOf(member);
                memberPerDay.get(i).addAll(friends); // add to map the friend exposed to the ad
            }
        }
        int memberExpo = deleteIdenticalID(memberPerDay).size(); // delete People who have seen the commercial several times
        return memberExpo;

    }

    /**
     *
     */
    private double evaluate_value_of_net() throws IOException {

        double tmp_network_value = 0;

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
//            writeNewLine(p.toString());

            int expo = p.getExpoForDay(); //Number of exposures of the advertisement per day
            int num_days = p.getDayForAdv(); // Numbers of days

            int members_expo_to_product = evaluate_number_of_people_who_might_see_an_ad_of_product(p);
            members_expo_to_product = (int) (avg_view * w1 + members_expo_to_product * w2); // members_expo_to_product after Inclusion 
            int member_buy_product = evaluate_number_of_people_who_might_buy_product(p, members_expo_to_product);

//            writeNewLine("members_expo_to_product : " + members_expo_to_product);
//            writeNewLine("member_buy_product : " + member_buy_product);

            double revenue = p.getProfit() * member_buy_product;
            double ad_price = network_data_from_file.getPrice_ads(); // The price of an advertisement on the network
            double expense = ad_price * num_days * expo;
//            writeNewLine("revenue : " + revenue);
//            writeNewLine("expense : " + expense);

            double profit_of_p = revenue - expense;
//            writeNewLine("profit of product : " + profit_of_p);

            tmp_network_value += profit_of_p;

        }
        return tmp_network_value;

    }

    /**
     * This method return the number of the people who will buy the product out
     * of all the people who saw the advertisement
     *
     * @param p product
     * @param memberExpo people who saw the advertisement
     * @return the number of the people who will buy the product
     */
    private int evaluate_number_of_people_who_might_buy_product(ProductForAdv p, int memberExpo) {
        double w1 = 0.8;
        double inter_percent = findInterested(p) / membersId.size();

        double w2 = 0.2;
        Double random = Math.random(); // Get probability between [0-1)
        int memberBuy = (int) (memberExpo * (random * w2 + inter_percent * w1)); // number of member will buy the product

        return memberBuy;
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

    public Set deleteIdenticalID(Map<Integer, Set<Integer>> memberPerDay) {
        Set<Integer> newSet = new HashSet<Integer>();

        for (Set<Integer> set : memberPerDay.values()) {
            newSet.addAll(set);
        }

        return newSet;
    }

    /**
     * return member id from the graph
     *
     * @param n
     * @return member id from the graph
     */
    public List<Integer> randomMembers(List<Integer> stock, int n) {
        List<Integer> randoMembers = new ArrayList();
        for (int i = 0; i < n; i++) {
            Random random = new Random();
            if (!stock.isEmpty()) {
                int id = stock.get(random.nextInt(stock.size()));
                randoMembers.add(id);
            }
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

    private int convertIntFromString(String s) {
        return Integer.parseInt(s);
    }
}
