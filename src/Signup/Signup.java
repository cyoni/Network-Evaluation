package Signup;

import Database.PublicDatabase;
import Account.UserAccount;
import Network.Gui_network;
import java.sql.ResultSet;
import javax.swing.JFrame;
import Utils.User_Dialog;

/**
 *
 * @author Yoni
 */
public class Signup extends JFrame{

    protected int isOwner;
    
    protected void signUp(String name, String email, String password, int owner, String network_name) {
        Thread thread = new Thread(){
            public void run(){
                    int result  = -1;
                    ResultSet ans = PublicDatabase.query("SELECT email FROM users WHERE email='"+ email +"';");
                    try{
                        while (ans.next()){
                            User_Dialog.showAlert("There is already an account with this email address.");
                            return;
                        }

                        result = PublicDatabase.query_alter_db("INSERT INTO users (name, email, password, isOwner)" +
                                "VALUES ('"+ name +"', '"+ email +"', '" + password + "', '"+ owner +"')");

                        if (isOwner==1){
                          result =  PublicDatabase.query_alter_db("INSERT INTO owners (email, network_name)" +
                                    "VALUES ('"+ email +"', '"+ network_name +"')");
                        }

                        if (result == -1){
                            User_Dialog.showAlert("There was an error while creating the account.");
                        }
                        else{
                            UserAccount u = Login.Login.setNewInstance(new UserAccount(name, email, "-1"));
                            dispose();
                            Gui_network g = new Gui_network(u);
                            g.setVisible(true);
                        }
                    }
                    catch(Exception e){} 
            }
        };
        thread.start();
       
    }
}
