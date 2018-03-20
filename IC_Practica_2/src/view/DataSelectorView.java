package view;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

@SuppressWarnings("serial")
public class DataSelectorView extends JFrame {

	JButton selectAttrName;
	JButton selectAttrVal;
	JButton go;
	JButton exit;
	
	JLabel fileAttrName;
	JLabel fileAttrVal;
	JLabel titleAttrName;
	JLabel titleAttrVal;
	
	JPanel body;
	
	public DataSelectorView() {
		super();
		
		this.setTitle("ID3 - Decision Tree");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(true);
		this.setSize(500,100);
		this.setResizable(false);
		
		body = new JPanel(new GridLayout(3, 3));

		loadFields();
		
		this.add(body);
		
	}
	
	private void loadFields() {
		selectAttrName = new JButton("Explore...");
		selectAttrVal = new JButton("Explore...");
		
		go = new JButton("Accept");
		exit = new JButton("Exit");
		
		fileAttrName = new JLabel("...");
		fileAttrVal = new JLabel("...");
		
		titleAttrName = new JLabel("Attribute Names:");
		titleAttrVal = new JLabel("Attribute Values:");
		
		body.add(titleAttrName);
		body.add(fileAttrName);
		body.add(selectAttrName);

		
		body.add(titleAttrVal);
		body.add(fileAttrVal);
		body.add(selectAttrVal);
		
		body.add(go);
		body.add(exit);
		
	}
	
}
