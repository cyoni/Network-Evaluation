package Account;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Yoni
 */
public class ConvertUserToSeriable {
    
    
    public ConvertUserToSeriable(String file_name){
        
        
    }
    
    
    
    public static Object read_object(String file_name){
            try{
            FileInputStream fi = new FileInputStream(new File(file_name));
            ObjectInputStream oi = new ObjectInputStream(fi);
            Object p = (Object) oi.readObject();
            oi.close();
            return p;
             }
             catch(Exception e){}
            return null;
    }
    
    public static void write_object(String file_name, Object obj){
                        try{
                   	FileOutputStream f = new FileOutputStream(new File(file_name));
			ObjectOutputStream o = new ObjectOutputStream(f);

			// Write objects to file
			o.writeObject(obj);
                        o.close();
                        }
                        catch(Exception c){System.err.println(c);}

    }
}
