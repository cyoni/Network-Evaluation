/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import java.util.Base64;

/**
 *
 * @author Yoni
 */
  
public class KeyGenerator { 
  
    // function to generate a random string of length n 
  public  static String getKey(int size) 
    { 
  
        // chose a Character random from this String 
        String AlphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
                                    + "0123456789"
                                    + "abcdefghijklmnopqrstuvxyz"; 
  
        // create StringBuffer size of AlphaNumericString 
        StringBuilder sb = new StringBuilder(size); 
  
        for (int i = 0; i < size; i++) { 
  
            // generate a random number between 
            // 0 to AlphaNumericString variable length 
            int index 
                = (int)(AlphaNumericString.length() 
                        * Math.random()); 
  
            // add Character one by one in end of sb 
            sb.append(AlphaNumericString 
                          .charAt(index)); 
        } 
  
        return sb.toString(); 
    } 
  
  
    public static String encodeString(String str){
    String encodedString = Base64.getEncoder().withoutPadding().encodeToString(str.getBytes());
    String encodedStringx2 = Base64.getEncoder().encodeToString(encodedString.getBytes());
        return encodedStringx2;
    }
 
} 
