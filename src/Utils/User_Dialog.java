/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

import javax.swing.JOptionPane;

/**
 *
 * @author Yoni
 */
public class User_Dialog {
	
	/**
	 * This method pops up a dialog where it asks the user to fill up something according to 'content'
	 * @param content
	 * @return
	 */
	public static String getInputDialog(String content) {
		return JOptionPane.showInputDialog(content);
	}
	
	
	/*
	 * This method alerts a message to the user.
	 */
	public static void showAlert(String content) {
		JOptionPane.showMessageDialog(null, content);
	}

}

