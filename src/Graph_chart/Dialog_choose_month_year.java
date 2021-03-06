package Graph_chart;

import javax.swing.DefaultComboBoxModel;
import Utils.User_Dialog;

/**
 *
 * @author Yoni
 */
public class Dialog_choose_month_year extends javax.swing.JFrame {
    private Gui_graph_chart g;
    DefaultComboBoxModel model_years = new DefaultComboBoxModel();
    DefaultComboBoxModel model_months = new DefaultComboBoxModel();

    /**
     * Creates new form Dialog_choose_month_year
     */
    public Dialog_choose_month_year() {
        initComponents();
        setLocationRelativeTo(null);
        model_years = new DefaultComboBoxModel();
        model_months = new DefaultComboBoxModel();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        years = new javax.swing.JComboBox<>();
        months = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jButton1.setText("OK");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Cancel");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel1.setText("Year:");

        jLabel2.setText("Month:");

        years.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                yearsMouseClicked(evt);
            }
        });
        years.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearsActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addGap(30, 30, 30)
                        .addComponent(months, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(42, 42, 42)
                        .addComponent(years, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jButton2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 140, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(65, 65, 65))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(years, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(months, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        String str_year = model_years.getSelectedItem().toString();
        String str_month = model_months.getSelectedItem().toString();
        boolean flag = true;
        for (int i=0; i<g.data_structure.size() && flag; i++){
            if (g.data_structure.get(i).getYear() == Integer.parseInt(str_year) &&
                g.data_structure.get(i).getMonth() == Integer.parseInt(str_month)){
                graph_chart_data targetData = g.data_structure.get(i); // get the required item from the data structure
                g.initializeGraph(targetData); // set it and refresh the graph
                this.dispose();
                flag = false;
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void yearsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearsActionPerformed
      if (model_years.getSize() > 0){ 
        String getyear = model_years.getSelectedItem().toString();
        setMonths(Integer.parseInt(getyear));}
    }//GEN-LAST:event_yearsActionPerformed

    private void yearsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_yearsMouseClicked
  
    }//GEN-LAST:event_yearsMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Dialog_choose_month_year.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Dialog_choose_month_year.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Dialog_choose_month_year.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Dialog_choose_month_year.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Dialog_choose_month_year().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JComboBox<Integer> months;
    private javax.swing.JComboBox<Integer> years;
    // End of variables declaration//GEN-END:variables

    
    /**
     * This method extracts the years and puts them into year jcombo list.
     */
    private void setYears() {
        if (g.data_structure.isEmpty()) {User_Dialog.showAlert("Nothing to show."); return;}
        
        years.setModel(model_years);
        months.setModel(model_months); 
        years.removeAllItems();
        months.removeAllItems();
        
        g.data_structure.forEach((item) -> {
            if (model_years.getIndexOf(item.getYear()) == -1){
                 model_years.addElement(item.getYear()); // add years to the list
            }
        });
        
        int latest_year = g.data_structure.get(0).getYear(); // get the most recent year
        setMonths(latest_year);  // add the months of the specific year      
    }

    void setData(Gui_graph_chart g) {
         this.g = g;
         if (g.data_structure.isEmpty()) jButton1.setEnabled(false);
         setYears();
    }

    private void setMonths(Integer target_year) {
        model_months.removeAllElements(); // clean list
        for (graph_chart_data item : g.data_structure ){
            if (item.getYear() == target_year){
                model_months.addElement(item.getMonth());
            }
        }
    }
}
