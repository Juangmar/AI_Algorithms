package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import controller.ApplicationController;
import data.Node;
import data.Vertex;

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
		this.setSize(500,150);
		this.setResizable(false);
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		
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
					Node tree = c.decitionTree(name, val);
					if(tree != null) {
						printResut(tree);
					}
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
	
	private void printResut(Node tree) {
		JFrame result = new JFrame("Result");
		
		/*
		 * USED TO PRINT ON LOG SCREEN 
		 *
		JPanel p = new JPanel(new GridLayout(1, 1));
		JTextArea log = new JTextArea();
		log.setEditable(false);
		printTree(log, tree, 0);
		p.add(log);
		*/
		
		JPanel p = new JPanel(new GridLayout(2,1));
		printTree(p,tree);
		
		result.add(p);
		result.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		result.setSize(new Dimension(500,400));
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		result.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
		
		result.setVisible(true);
	}
	
	private void printTree(JPanel a, Node tree) {
		if(tree!=null) {
			JLabel n = new JLabel();
			if(tree.getName().equals("si")) {
				n.setBackground(Color.GREEN);
				n.setOpaque(true);
			}
			else if((tree.getName().equals("no"))) {
				n.setBackground(Color.RED);
				n.setOpaque(true);
			}
			else {
				Font font = new Font(n.getFont().getName(),Font.ITALIC+Font.BOLD,n.getFont().getSize());
				n.setFont(font);
				n.setText(tree.getName() +  ":");
			}
			n.setHorizontalAlignment(JLabel.CENTER);
			n.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			a.add(n);
			if(tree.getSons()!=null) {
				JPanel p = new JPanel(new GridLayout(2, tree.getSons().size()));
				for(Vertex e : tree.getSons()) {
					JLabel v = new JLabel(e.getValue());
					v.setHorizontalAlignment(JLabel.CENTER);
					v.setBorder(BorderFactory.createLineBorder(Color.BLACK));
					p.add(v);
				}
				for(Vertex e : tree.getSons()) { 
					JPanel rec = new JPanel (new GridLayout(2,1));
					printTree(rec, e.getNext());
					p.add(rec);
				}
				a.add(p);
			}
		}
	}
	
	/*
	 *  To show on JTextArea
	 */
	@SuppressWarnings("unused")
	private void printTree(JTextArea a, Node tree, int level) {
		String next = System.lineSeparator();
		if(tree !=  null) {
		for(int i = 0; i < level; i++) {
			a.append("-");
		}
		if(tree.getName().equals("si")||tree.getName().equals("no")) a.append(tree.getName() + "." + next);
		else a.append("¿" + tree.getName() + "?" + next);
		if(tree.getSons()!= null) {
		for(Vertex e : tree.getSons()) {
			for(int i = 0; i < level+1; i++) {
				a.append("-");
			}
			a.append("(" + e.getValue() + ")"+next);
			printTree(a, e.getNext() ,level+2);
		}
		}
		}
	}
	
	/*
	 *  To show on console
	 */
	@SuppressWarnings("unused")
	private void printTree(Node tree, int level) {
		if(tree !=  null) {
		for(int i = 0; i < level; i++) {
			System.out.print("-");
		}
		if(tree.getName().equals("si")||tree.getName().equals("no")) System.out.println(tree.getName() + ".");
		else System.out.println("¿" + tree.getName() + "?");
		if(tree.getSons()!= null) {
		for(Vertex e : tree.getSons()) {
			for(int i = 0; i < level+1; i++) {
				System.out.print("-");
			}
			System.out.println("(" + e.getValue() + ")");
			printTree(e.getNext() ,level+2);
		}
		}
		}
	}
	
}
