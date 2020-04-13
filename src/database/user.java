/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import DB_Connection.database;
import java.io.Serializable;
import java.sql.ResultSet;
import utils.User_Dialog;

/**
 * This class represents a user
 * @author Yoni
 */
public class user implements Serializable{
    
    private final String name, email;
    private String private_key;
    
    public user(String name, String email, String private_key){
      this.name = name;
      this.email = email;        
      this.private_key = private_key;
    }
    
    public user getUser(){return this;} 
    public String getName(){return name;}
    public String getEmail(){return email;}

    public String getPrivate_key(){return private_key;}
    public String getKey() {
        return private_key;
    }

    public void validateUser(user User) {
          try{
                String key = User.getKey();
               // User_Dialog.showAlert(key);
                ResultSet rs = database.query("SELECT email FROM login_instance WHERE private_key='"+ key +"';");
                boolean ans = false;
                while(rs.next()){
                    ans = true;
                    
                }
                if (!ans) {
                    User_Dialog.showAlert("The system was unable to validate this User account."); 
                    User = null;
                }
        }
        catch(Exception e){System.out.println(e);}

    }
    
}
