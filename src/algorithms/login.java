/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package algorithms;

import DB_Connection.database;
import database.seriable;
import database.user;

/**
 *
 * @author Yoni
 */
public class login {

    public static user setNewInstance(user User) {
    String name = User.getName();
    String email = User.getEmail();
    String key = KeyGenerator.getKey(32); // private key - so the system will remember the user and he wont have to enter his password.
    User = new user(name, email, key);          
    String sql = "INSERT INTO login_instance (private_key, email)\n" +
                 "VALUES('"+ key +"', '"+ email +"')\n" +
                 "ON DUPLICATE KEY UPDATE private_key = VALUES(private_key)";
        
    database.query_update(sql); // Excucute the operation
    seriable.write_object("user.txt", User);
       return User;
    }
    
    
    public void Login(){
        
        
    }
    
    
    
    
    
}
