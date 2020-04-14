
package MyAccount;

import DB_Connection.database;
import algorithms.KeyGenerator;
import database.user;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import utils.MouseCursor;
import utils.User_Dialog;

/**
 *
 * @author Yoni
 */
public class myAccount extends JFrame{
    protected user User;
    
    public myAccount(user User){
        this.User = User;
    }
        
    protected void verifyUserAndupdatePassword(){
        final String old_password = User_Dialog.getInputDialog("Enter your old password...");
        final String new_password = User_Dialog.getInputDialog("Enter a new password...");
        if (old_password.isEmpty() || new_password.isEmpty()) {
            User_Dialog.showAlert("An invalid password was entered.");
            return;
        }
        
        String encoded_old_password = KeyGenerator.encodeString(old_password);
        
        Thread thread = new Thread(){
            public void run(){
                
                MouseCursor.ChangeMouseCursorBusy(myAccount.this, true);
                
                String sql = "SELECT password FROM users where email='"+User.getEmail()+"'";
                 ResultSet rs = database.query(sql); 
                try {
                    String password_from_server = "";
                    if(rs.next()){
                        password_from_server = rs.getString("password");
                    }
                    if (password_from_server.equals(encoded_old_password)){
                        String encoded_new_password = KeyGenerator.encodeString(new_password);
                        sql = "UPDATE users SET password='"+ encoded_new_password +"' WHERE email='" + User.getEmail() + "';";
                        int result = database.query_update(sql);   
                        if (result != -1)
                            User_Dialog.showAlert("Your password has been changed sucessfully.");
                        else 
                            User_Dialog.showAlert("There was an error in the server.");
                    }
                    else
                        User_Dialog.showAlert("Authentication was failed.");
                    MouseCursor.ChangeMouseCursorBusy(myAccount.this, false);
                } catch (SQLException ex) {
                    Logger.getLogger(myAccount.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        };
        thread.start();
    }
    
}
