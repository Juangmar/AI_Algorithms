package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

public class DataListener implements ActionListener {

	private SelectorFrame frame; //View associated with the listener
	
	public DataListener(SelectorFrame window) {
		this.frame = window;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(frame.methods.getSelectedIndex()) {
			case(0):{
				JOptionPane.showMessageDialog(frame, "You selected option number " + 1 + " (" + frame.methods.getSelectedItem() + ")", "Fatal error", JOptionPane.INFORMATION_MESSAGE);
			}break;
			case(1):{
				JOptionPane.showMessageDialog(frame, "You selected option number " + 2 + " (" + frame.methods.getSelectedItem() + ")", "Fatal error", JOptionPane.INFORMATION_MESSAGE);
			}break;
			case(2):{
				JOptionPane.showMessageDialog(frame, "You selected option number " + 3 + " (" + frame.methods.getSelectedItem() + ")", "Fatal error", JOptionPane.INFORMATION_MESSAGE);
			}break;
			case(3):{
				JOptionPane.showMessageDialog(frame, "You selected option number " + 4 + " (" + frame.methods.getSelectedItem() + ")", "Fatal error", JOptionPane.INFORMATION_MESSAGE);
			}break;
			default:{
				JOptionPane.showMessageDialog(frame, "Error getting the selected method", "Fatal error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}

}
