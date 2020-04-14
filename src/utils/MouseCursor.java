package utils;

import java.awt.Cursor;
import javax.swing.JFrame;

/**
 * This class changes the cursor of the mouse.
 * @author Yoni
 */
public class MouseCursor {
    
    public static void ChangeMouseCursorBusy(JFrame jframe, boolean status){
        Cursor cursor;
        if (status)
            cursor = new Cursor(Cursor.WAIT_CURSOR);    
        else cursor = new Cursor(Cursor.DEFAULT_CURSOR);     
        jframe.setCursor(cursor);
    }
    
}
