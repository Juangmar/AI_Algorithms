package main;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import view.HGView;
import view.MainView;
import view.WPView;

/**
 * @author Juan Gomez-Martinho Gonzalez
 *
 */
public class AStar {

	/**
	 * Main method. Starts the program with a simple Option Pane. Depending of the option chosen, 
	 * the class creates a different window with the path finder.
	 * @param args default main args
	 */
	public static void main(String[] args) {
		
		String[] options = {"Simple", "Way Points", "Ground Height" }; //These are the current types of maps.
		
		//Creates the Option Pane with the message, the title an the previous options.
		int option = JOptionPane.showOptionDialog(null, "What version do you want to launch?", "A* pathfinder", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, -1);
		
		switch (option){
			case 0:{
				JFrame s = new MainView();
				s.setVisible(true);
			} break;
			case 1:{
				JFrame m = new WPView();
				m.setVisible(true);
			} break;
			case 2:{
				JFrame h = new HGView();
				h.setVisible(true);
			} break;
			default:{
				//Do nothing. Technically it's not possible to reach this part given 
				// the current code.
			}
		}
	}
}
