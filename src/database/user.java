/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.io.Serializable;

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
    
}
