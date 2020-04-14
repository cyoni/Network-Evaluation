/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package login;

import DB_Connection.database;
import algorithms.login;
import database.seriable;
import database.user;
import gui.Gui_network;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import utils.MouseCursor;
import utils.User_Dialog;

/**
 *
 * @author Yoni
 */
public class Login extends javax.swing.JFrame {
    
    
        protected void connect(String email, String encoded_password) {
        String sql = "SELECT name, password FROM users where email='"+email+"'";
        Thread thread = new Thread(){
            
            public void run(){
                   MouseCursor.ChangeMouseCursorBusy(Login.this, true);
                   ResultSet rs = database.query(sql); 

            try {
                if(rs.next()){ 
                    String name = rs.getString("name");
                    String passInDB = rs.getString("password");
                    if(encoded_password.equals(passInDB)) { // Checks that the entered password is the same as the password in the database
                        user User = login.setNewInstance(new user(name, email, "-1")); // get a new private key
                        dispose();
                        Gui_network g = new Gui_network(User);
                        g.setVisible(true);
                    }
                    else{
                        User_Dialog.showAlert("The password is wrong.");
                    }
                }
                else { // email was not found
                        User_Dialog.showAlert("This email address is not registered.");
                }
                MouseCursor.ChangeMouseCursorBusy(Login.this, false);
            } catch (SQLException ex) {
                Logger.getLogger(Gui_login.class.getName()).log(Level.SEVERE, null, ex);      }
            }};   
         thread.start();
    }
        
         
    
    protected void checkForUserInstance() {
        try{
            File file = new File("user.txt");
            boolean exists = file.exists();
            if (exists){
                    user User = (user)seriable.read_object("user.txt");
                    if (User != null){ 
                        this.dispose();
                        Gui_network g = new Gui_network(User);
                        g.validateUser();
                        g.setVisible(true);
                        return;
                }
            }                 
        }
        catch(Exception e){System.out.println(e);}
        
                this.setVisible(true);

    }
    
}
