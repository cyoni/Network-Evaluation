package Account;

import Database.Database;
import java.io.Serializable;
import java.sql.ResultSet;
import Utils.User_Dialog;

/**
 * This class represents the user
 * @author Yoni
 */
public class UserAccount implements Serializable{
    
    private final String name, email;
    private String private_key;
    
    public UserAccount(String name, String email, String private_key){
      this.name = name;
      this.email = email;        
      this.private_key = private_key;
    }
    
    public UserAccount getUser(){return this;} 
    public String getName(){return name;}
    public String getEmail(){return email;}

    public String getPrivateKey(){return private_key;}
    public String getKey() {
        return private_key;
    }

    public void validateUser(UserAccount User) {
          try{
                String key = User.getKey();
               // User_Dialog.showAlert(key);
                ResultSet rs = Database.query("SELECT email FROM login_instance WHERE private_key='"+ key +"';");
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
