
package Graph_chart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.swing.DefaultComboBoxModel;

/**
 *
 * @author Yoni
 */

public class Gui_chart_data_analysis extends javax.swing.JFrame {
    String[] options = {"Males vs Females", "Member Sign Ups Dates", "Audience For Posts", "Traffic", "Advertisers Profit"};
    DefaultComboBoxModel model_list = new DefaultComboBoxModel();
    private final String networkFile;


    public Gui_chart_data_analysis(String networkFile) {
        initComponents();
        setLocationRelativeTo(null);
        this.networkFile = networkFile;
        loadList();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        set_option = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        list_options = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        set_option.setText("OK");
        set_option.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                set_optionActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Data Analysis");

        list_options.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel2.setText("Choose the desired data chart to view:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(0, 201, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(list_options, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(set_option, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2)
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(set_option)
                    .addComponent(list_options, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void set_optionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_set_optionActionPerformed
        String option = model_list.getSelectedItem().toString();
        if (option.equals("Males vs Females"))
            MalesAndFemales();
        else if(option.equals("Member Sign Ups Dates"))
            MemberSignUpDates();
        else if (option.equals("Audience For Posts"))
            audienceForPosts();
        else if (option.equals("Traffic"))
            traffic();
        else if (option.equals("Advertisers Profit"))
            AdvertiserProfits();
    }//GEN-LAST:event_set_optionActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JComboBox<String> list_options;
    private javax.swing.JButton set_option;
    // End of variables declaration//GEN-END:variables

    private void loadList() {
        list_options.setModel(model_list);
        for (String current : options){
            model_list.addElement(current);
        }
    }

    private void MalesAndFemales() {
        List<dataStructure> gender = new ArrayList<>(); 
        gender.add(new dataStructure("male", 600));
        gender.add(new dataStructure("female", 450));
        
        Chart_data_analysis_algo analysis = new Chart_data_analysis_algo("Males vs Females");
        analysis.setChart(gender);
    }

    private void MemberSignUpDates() {
        // "Enter a year...";
        
        List<dataStructure> months = new ArrayList<>(); 
        months.add(new dataStructure("1", 500));
        months.add(new dataStructure("2", 800));
        months.add(new dataStructure("3", 700));
        months.add(new dataStructure("4", 500));
        months.add(new dataStructure("5", 550));
        months.add(new dataStructure("6", 220));
        months.add(new dataStructure("7", 900));
        months.add(new dataStructure("8", 845));
        months.add(new dataStructure("9", 852));
        months.add(new dataStructure("10", 740));
        months.add(new dataStructure("11", 350));
        months.add(new dataStructure("12", 688));
        
        Chart_data_analysis_algo analysis = new Chart_data_analysis_algo("Sign up dates for 2020");
        analysis.setChart(months);
    }

    private void audienceForPosts() { // how many friends saw a member's post in a month(?).
        
        List<dataStructure> members = new ArrayList<>(); 
        members.add(new dataStructure("1", 500)); // [number of posts, number of members]
        members.add(new dataStructure("2", 800));
        members.add(new dataStructure("3", 700));
        members.add(new dataStructure("4", 500));
        members.add(new dataStructure("5", 550));
        members.add(new dataStructure("6", 220));
        members.add(new dataStructure("7", 900));
        members.add(new dataStructure("8", 845));
        members.add(new dataStructure("9", 852));
        members.add(new dataStructure("10", 740));
        members.add(new dataStructure("11", 350));
        members.add(new dataStructure("12", 688));
        
        Chart_data_analysis_algo analysis = new Chart_data_analysis_algo("Sign up dates for 2020");
        analysis.setChart(members);

    }

    private void traffic() {
        // Choose a month...
        List<dataStructure> user_traffic = new ArrayList<>(); 

        for (int i=1; i<=31; i++){ 
            user_traffic.add(new dataStructure(i+"", i)); //[day/ traffic in mb]
        }
        
        Chart_data_analysis_algo analysis = new Chart_data_analysis_algo("Traffic for Jan");
        analysis.setChart(user_traffic);
    }

    private void AdvertiserProfits() {
        // Choose a month...
        List<dataStructure> ad_profit = new ArrayList<>(); 

        for (int i=1; i<=31; i++){ 
            ad_profit.add(new dataStructure(i+"", i*1000)); //[day/ profit in $]
        }
        
        Chart_data_analysis_algo analysis = new Chart_data_analysis_algo("Advertisers profit");
        analysis.setChart(ad_profit);
    }
}
