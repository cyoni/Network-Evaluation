package Login;

import Utils.KeyGenerator;
import Database.PublicDatabase;
import Account.ConvertUserToSeriable;
import Account.UserAccount;
import Network.Gui_network;
import java.io.File;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import Utils.MouseCursor;
import Utils.User_Dialog;

/**
 * This class handle in login to the system
 * @author Yoni
 */
public class Login extends javax.swing.JFrame {
    
    
        protected void connect(String email, String encoded_password) {
        String sql = "SELECT name, password FROM users where email='"+email+"'";
        Thread thread = new Thread(){
            
            public void run(){
                   MouseCursor.ChangeMouseCursorBusy(Login.this, true);
                   ResultSet rs = PublicDatabase.query(sql); 

            try {
                if(rs.next()){ 
                    String name = rs.getString("name");
                    String passInDB = rs.getString("password");
                    if(encoded_password.equals(passInDB)) { // Checks that the entered password is the same as the password in the database
                        UserAccount User = setNewInstance(new UserAccount(name, email, "-1")); // get a new private key
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

         
    public static UserAccount setNewInstance(UserAccount User) {
    String name = User.getName();
    String email = User.getEmail();
    String key = KeyGenerator.getKey(32); // private key - so the system will remember the user and he won't have to enter his password again.
    User = new UserAccount(name, email, key);          
    String sql = "INSERT INTO login_instance (private_key, email)\n" +
                 "VALUES('"+ key +"', '"+ email +"')\n" +
                 "ON DUPLICATE KEY UPDATE private_key = VALUES(private_key)";
        
    PublicDatabase.query_alter_db(sql); // Excucute the operation
    ConvertUserToSeriable.write_object("user.txt", User);
       return User;
    }
    
    protected void checkForUserInstance() {
        try{
            File file = new File("user.txt");
            boolean exists = file.exists();
            if (exists){
                    UserAccount User = (UserAccount)ConvertUserToSeriable.read_object("user.txt");
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
