package main;

import javax.swing.JFrame;
import view.DataSelectorView;

/**
 * Main class. Creates a view and makes it visible.
 * @author Juan Gomez-Martinho Gonzalez
 *
 */
public class ID3 {
	public static void main(String[] args) {
		JFrame m = new DataSelectorView(); //The program window is created.
		m.setVisible(true); //The window is now visible.
	}
}
