
package Account;    

import Database.PublicDatabase;
import Utils.KeyGenerator;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import Utils.MouseCursor;
import Utils.User_Dialog;

/**
 *
 * @author Yoni
 */
public class MyAccount extends JFrame{
    protected UserAccount User;
    
    public MyAccount(UserAccount User){
        this.User = User;
    }
        
    protected void verifyUserAndupdatePassword(){
        final String old_password = User_Dialog.getInputDialog("Enter your old password...");
        final String new_password = User_Dialog.getInputDialog("Enter a new password...");
        
        if (old_password == null || new_password == null) return;
        else
            if (old_password.isEmpty() || new_password.isEmpty()) {
                User_Dialog.showAlert("An invalid password was entered.");
                return;
            }
        
        String encoded_old_password = KeyGenerator.encodeString(old_password);
        
        Thread thread = new Thread(){
            public void run(){
                
                MouseCursor.ChangeMouseCursorBusy(MyAccount.this, true);
                
                String sql = "SELECT password FROM users where email='"+User.getEmail()+"'";
                 ResultSet rs = PublicDatabase.query(sql); 
                try {
                    String password_from_server = "";
                    if(rs.next()){
                        password_from_server = rs.getString("password");
                    }
                    if (password_from_server.equals(encoded_old_password)){
                        String encoded_new_password = KeyGenerator.encodeString(new_password);
                        sql = "UPDATE users SET password='"+ encoded_new_password +"' WHERE email='" + User.getEmail() + "';";
                        int result = PublicDatabase.query_alter_db(sql);   
                        if (result != -1)
                            User_Dialog.showAlert("Your password has been changed sucessfully.");
                        else 
                            User_Dialog.showAlert("There was an error in the server.");
                    }
                    else
                        User_Dialog.showAlert("Authentication was failed.");
                    MouseCursor.ChangeMouseCursorBusy(MyAccount.this, false);
                } catch (SQLException ex) {
                    Logger.getLogger(MyAccount.class.getName()).log(Level.SEVERE, null, ex);
                }

            }
        };
        thread.start();
    }
    
}
