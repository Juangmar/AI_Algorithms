package view;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.ApplicationController;

@SuppressWarnings("serial")
public class DataSelectorView extends JFrame {

	JButton selectAttrName;
	JButton selectAttrVal;
	JButton go;
	JButton exit;
	
	File name;
	File val;
	
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
		
		name = null;
		val = null;
		final JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Plain text files (.txt)", "txt", "text");
		fc.setFileFilter(filter);
		
		selectAttrName = new JButton("Explore...");
		selectAttrName.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				//In response to a button click:
				int returnVal = fc.showOpenDialog(body);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
		            name = fc.getSelectedFile();
		            fileAttrName.setText(name.getName());
		            //This is where a real application would open the file.
		            if(val!=null && name!= null) {
		            		go.setEnabled(true);
		            }
		            System.out.println("Opening: " + name.getAbsolutePath());
		        } else {
		           System.out.println("Open command cancelled by user.");
		        }
				
			}
			
		});
		
		selectAttrVal = new JButton("Explore...");
		selectAttrVal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				//In response to a button click:
				int returnVal = fc.showOpenDialog(body);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
		            val = fc.getSelectedFile();
		            fileAttrVal.setText(val.getName());
		            //This is where a real application would open the file.
		            if(val!=null && name!= null) {
	            			go.setEnabled(true);
		            }
		        } else {}
			}
			
		});
		
		
		
		go = new JButton("Accept");
		go.setEnabled(false);
		go.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				if(name!=null && val!=null) {
					ApplicationController c = new ApplicationController();
					c.decitionTree(name, val);
				}
			}
			
		});
		exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
			
		});
		
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
