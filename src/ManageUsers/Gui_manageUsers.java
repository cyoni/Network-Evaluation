package ManageUsers;

import Login.Gui_login;
import Database.Database;
import Account.EmailValidation;
import Account.UserAccount;
import java.awt.Cursor;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import Utils.MouseCursor;
import Utils.User_Dialog;

/**
 *
 * @author Yoni
 */
public class Gui_manageUsers extends JFrame {
    private final UserAccount User;
    private static int changeStatusOfUser = -1;
    
    /**
     * Creates new form GuimanageUsers
     * @param User
     */
    public Gui_manageUsers(UserAccount User) {
        initComponents();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(Gui_login.DISPOSE_ON_CLOSE);
        this.User = User;
        loadUserList();
   }


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton5 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        users_list = new javax.swing.JComboBox<>();
        grand_or_revoke_permiss_button = new javax.swing.JButton();
        new_usr_button = new javax.swing.JButton();
        remove_usr_button = new javax.swing.JButton();

        jButton5.setText("New User");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel3.setText("Manage Users");

        jLabel1.setText("Grant/Revoke permissions:");

        users_list.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        grand_or_revoke_permiss_button.setText("Grant/Revoke Permission");
        grand_or_revoke_permiss_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grand_or_revoke_permiss_buttonActionPerformed(evt);
            }
        });

        new_usr_button.setText("New User");
        new_usr_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                new_usr_buttonActionPerformed(evt);
            }
        });

        remove_usr_button.setText("Remove User");
        remove_usr_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                remove_usr_buttonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(users_list, javax.swing.GroupLayout.PREFERRED_SIZE, 353, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addContainerGap()
                            .addComponent(grand_or_revoke_permiss_button)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(remove_usr_button, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(new_usr_button, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel3)
                .addGap(35, 35, 35)
                .addComponent(jLabel1)
                .addGap(30, 30, 30)
                .addComponent(users_list, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(grand_or_revoke_permiss_button)
                    .addComponent(new_usr_button)
                    .addComponent(remove_usr_button))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    private void addUser(){
           Thread thread = new Thread(){
             public void run(){
                String email =  User_Dialog.getInputDialog("Enter the email of the user:");
                if (email==null) return;
                email = email.trim();
                boolean ans = EmailValidation.isValid(email);

                if (!ans) addUser();
                    else{
                        for (int i=0; i< users_list.getItemCount(); i++){
                            if (users_list.getItemAt(i).equals(email)){
                                User_Dialog.showAlert(email + " already exists!");
                                return;
                            }
                        }
                        boolean ok = false;
                            ResultSet result = Database.query("SELECT name FROM users WHERE email='"+email+"'");

                        try {
                            while (result.next()){ok=true;}
                        } catch (SQLException ex) {
                            Logger.getLogger(Gui_manageUsers.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        if (!ok){
                            User_Dialog.showAlert("This email address was not found!");
                        } else if (email.equals(User.getEmail())) {User_Dialog.showAlert("You can't add youself!");}
                        else{

                            Database.query_update("INSERT INTO permissions (owner, usr_email)\n" +
                        "    VALUES ('"+ User.getEmail() +"', '"+ email +"');");
                            users_list.addItem(email + " (unauthorized)");
                        }
                    }
            }  
        };
           thread.start();
    }
    
    private void new_usr_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_new_usr_buttonActionPerformed
     addUser();
    }//GEN-LAST:event_new_usr_buttonActionPerformed

    private void grand_or_revoke_permiss_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grand_or_revoke_permiss_buttonActionPerformed
        String[] arr = getStatusFromList(users_list.getSelectedItem().toString());
        final String email = arr[0];
 
        String status = arr[1];
       
        users_list.setEditable(true);
        int index = users_list.getSelectedIndex();
        users_list.removeItemAt(index);
        String w = "";
        if (status.equals("authorized")) {changeStatusOfUser=-1; w = email+" (unauthorized)"; users_list.addItem(w); }      
        else if (status.equals("unauthorized")) {changeStatusOfUser=1; w=email + " (authorized)"; users_list.addItem(w);  }      
        users_list.setSelectedItem(w);
        users_list.setEditable(false);
        Thread thread = new Thread(){
            public void run(){
                MouseCursor.ChangeMouseCursorBusy(Gui_manageUsers.this, true);
                Database.query_update("UPDATE permissions SET permission="+ changeStatusOfUser +" WHERE owner='"+ User.getEmail() +"' AND usr_email='"+ email +"';");
                MouseCursor.ChangeMouseCursorBusy(Gui_manageUsers.this, false);
            }
        };
        thread.start();
        
    }//GEN-LAST:event_grand_or_revoke_permiss_buttonActionPerformed

    private void remove_usr_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_remove_usr_buttonActionPerformed
        String str = users_list.getSelectedItem().toString();
        String[] arr = getStatusFromList(str);
        String email = arr[0];
        Thread thread = new Thread(){
            public void run(){
                Database.query_update("DELETE from permissions WHERE owner='"+ User.getEmail() +"' AND usr_email='"+ email +"' "
                + "AND permission=1;");
            }
        };
        thread.start();
        

      users_list.removeItem(str);
    }//GEN-LAST:event_remove_usr_buttonActionPerformed

    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton grand_or_revoke_permiss_button;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton new_usr_button;
    private javax.swing.JButton remove_usr_button;
    private javax.swing.JComboBox<String> users_list;
    // End of variables declaration//GEN-END:variables


    private void loadUserList() {
        users_list.removeAllItems();
        Thread thread = new Thread(){
            public void run(){
                MouseCursor.ChangeMouseCursorBusy(Gui_manageUsers.this, true);
               ResultSet rs = Database.query("SELECT usr_email, permission FROM permissions where owner='"+ User.getEmail() +"';");
               parseUsersList(rs);
               MouseCursor.ChangeMouseCursorBusy(Gui_manageUsers.this, false);
            }
        };
        thread.start();
    }
    private void parseUsersList(ResultSet rs){
        try {
            while (rs.next()){
                String email = rs.getString("usr_email");
                int permission = rs.getInt("permission");
               if (permission == -1) email+= " (unauthorized)";
               else if (permission == 1) email += " (authorized)";
               
               users_list.addItem(email);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Gui_manageUsers.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private String[] getStatusFromList(String str) {
       if (str.isEmpty()) return null;
        String what[] = str.split(" \\(");
        String email = what[0].trim();
        String status = what[1];
        status = status.substring(0, status.length()-1);
        return new String[]{email , status};
    }



}
