package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import business.Kmean;
import controller.ApplicationController;

public class DataListener implements ActionListener {

	private SelectorFrame frame; //View associated with the listener
	
	public DataListener(SelectorFrame window) {
		this.frame = window;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		switch(frame.methods.getSelectedIndex()) {
			case(0):{
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
			ApplicationController controller = new ApplicationController();
			Kmean result = controller.kmeans(frame.data, tolerance, weigth);
			if(result != null) {
				frame.fuzzySuccess(result);
			}else {
				Double[] e = {1.2, 3.0, 9.1123123, 6.112, 1.0};
				result = new Kmean();
				result.setMeans(e);
				frame.fuzzySuccess(result);
				//JOptionPane.showMessageDialog(frame, "Error: there's no solution. Please try changing the tolerance or the weigth.", "Solution not found", JOptionPane.ERROR_MESSAGE);
			}
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
