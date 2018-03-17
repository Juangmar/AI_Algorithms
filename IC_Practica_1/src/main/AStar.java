package main;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import view.HGView;
import view.MainView;
import view.WPView;

/**
 * @author Juan Gómez-Martinho González
 *
 */
public class AStar {

	public static void main(String[] args) {
		String[] options = {"Simple", "Way Points", "Ground Height" };
		int option = JOptionPane.showOptionDialog(null, "What version do you want to launch?", "A* pathfinder", JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, -1);
		
		System.out.println(option);
		
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
			
		}
		
		}
		
		
	}

}
