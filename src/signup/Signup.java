package signup;

import DB_Connection.database;
import algorithms.login;
import database.user;
import gui.Gui_network;
import java.sql.ResultSet;
import javax.swing.JFrame;
import utils.User_Dialog;

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
                    ResultSet ans = database.query("SELECT email FROM users WHERE email='"+ email +"';");
                    try{
                        while (ans.next()){
                            User_Dialog.showAlert("There is already an account with this email address.");
                            return;
                        }

                        result = database.query_update("INSERT INTO users (name, email, password, isOwner)" +
                                "VALUES ('"+ name +"', '"+ email +"', '" + password + "', '"+ owner +"')");

                        if (isOwner==1){
                          result =  database.query_update("INSERT INTO owners (email, network_name)" +
                                    "VALUES ('"+ email +"', '"+ network_name +"')");
                        }

                        if (result == -1){
                            User_Dialog.showAlert("There was an error while creating the account.");
                        }
                        else{
                            user u = login.setNewInstance(new user(name, email, "-1"));
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
