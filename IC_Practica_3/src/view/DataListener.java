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
				JOptionPane.showMessageDialog(frame, "You selected option number " + 1 + " (" + frame.methods.getSelectedItem() + ")", "Info", JOptionPane.INFORMATION_MESSAGE);
				executeFuzzy();
			}break;
			case(1):{
				JOptionPane.showMessageDialog(frame, "You selected option number " + 2 + " (" + frame.methods.getSelectedItem() + ")", "Info", JOptionPane.INFORMATION_MESSAGE);
				executeBayes();
			}break;
			case(2):{
				JOptionPane.showMessageDialog(frame, "You selected option number " + 3 + " (" + frame.methods.getSelectedItem() + ")", "Info", JOptionPane.INFORMATION_MESSAGE);
				executeLloyd();
			}break;
			case(3):{
				JOptionPane.showMessageDialog(frame, "You selected option number " + 4 + " (" + frame.methods.getSelectedItem() + ")", "Info", JOptionPane.INFORMATION_MESSAGE);
				executeSOM();
			}break;
			default:{
				JOptionPane.showMessageDialog(frame, "Error getting the selected method", "Fatal error", JOptionPane.ERROR_MESSAGE);
			}
		}
	}
	private void executeFuzzy() {
		try {
			double base = Double.parseDouble(frame.fuzzy_toleranceBase_field.getText());
			double exp = Double.parseDouble(frame.fuzzy_exponen_field.getText());
			double tolerance = Math.pow(base, exp);
			double weigth = Double.parseDouble(frame.fuzzy_weight_field.getText());
		} catch(NumberFormatException one) {
			JOptionPane.showMessageDialog(frame, "Insert valid numeric data", "Fatal error", JOptionPane.ERROR_MESSAGE);		
		}
	}
	private void executeBayes() {
		
	}
	private void executeLloyd() {
		
	}
	private void executeSOM() {
		
	}

}
