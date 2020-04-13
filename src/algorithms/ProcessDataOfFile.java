package algorithms;

import gui.Gui_network;

/**
 * This class processes the data that was loaded by the user.
 * @author Yoni
 */
public class ProcessDataOfFile {

    private final Gui_network gui_network;

    public ProcessDataOfFile(Gui_network gui_network) {
        this.gui_network = gui_network;
    }

    public void process() {
       
	Thread th = new Thread(new Runnable() { 
            public void run() {
            
            //Basic
            gui_network.num_members.setText(String.valueOf(gui_network.getNetworkDataFromFile().getNumMembers()));
            gui_network.num_pages.setText(String.valueOf(gui_network.getNetworkDataFromFile().getNumPages()));
            gui_network.num_likes.setText(String.valueOf(gui_network.getNetworkDataFromFile().getNumLikes()));
            gui_network.num_shares.setText(String.valueOf(gui_network.getNetworkDataFromFile().getNumShares()));
            gui_network.num_active.setText(String.valueOf(gui_network.getNetworkDataFromFile().getActiveMembers()));
            gui_network.num_groups.setText(String.valueOf(gui_network.getNetworkDataFromFile().getNumGroups()));
            gui_network.num_advertisers.setText(String.valueOf(gui_network.getNetworkDataFromFile().getAdvertisers()));
            gui_network.num_ads.setText(String.valueOf(gui_network.getNetworkDataFromFile().getNumAds()));

            //Avg
            gui_network.avg_shares.setText(String.valueOf(gui_network.getNetworkDataFromFile().getAvgShares()));
            gui_network.avg_likes.setText(String.valueOf(gui_network.getNetworkDataFromFile().getAvgLikes()));
            gui_network.avg_posts.setText(String.valueOf(gui_network.getNetworkDataFromFile().getAvgPosts()));
            gui_network.avg_friends.setText(String.valueOf(gui_network.getNetworkDataFromFile().getAvgFriends()));
            gui_network.avg_time.setText(String.valueOf(gui_network.getNetworkDataFromFile().getAvgTime()));
            
            //Staff
            gui_network.num_employees.setText(String.valueOf(gui_network.getNetworkDataFromFile().getNumEmployees()));
            gui_network.sum_salaries.setText(String.valueOf(gui_network.getNetworkDataFromFile().getSumSalaries()));
            gui_network.net_expenses.setText(String.valueOf(gui_network.getNetworkDataFromFile().getTotalExpenses()));
            
            //Traffic
            gui_network.total_traffic.setText(String.valueOf(gui_network.getNetworkDataFromFile().getTotalTraffic()));
            gui_network.avg_traffic.setText(String.valueOf(gui_network.getNetworkDataFromFile().getAvgTraffic()));
            
            //Ads
            gui_network.active_ads.setText(String.valueOf(gui_network.getNetworkDataFromFile().getActiveAds()));
            gui_network.avg_ads.setText(String.valueOf(gui_network.getNetworkDataFromFile().getAvgAds()));
            gui_network.new_ads.setText(String.valueOf(gui_network.getNetworkDataFromFile().getNewAds()));
            gui_network.net_profit.setText(String.valueOf(gui_network.getNetworkDataFromFile().getNetProfit()));
	
			}
		});
		th.start();     

    }
    
}
