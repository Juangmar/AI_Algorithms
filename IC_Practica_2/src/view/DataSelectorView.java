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

/**
 * Main view. Manages all program's data
 * @author Juan Gomez-Martinnho Gonzalez
 *
 */
@SuppressWarnings("serial")
public class DataSelectorView extends JFrame {

	JButton selectAttrName; //A button to select the file containing the attribute's names.
	JButton selectAttrVal; //A button to select the file containing the attribute's values.
	JButton go; //The button to execute the algorithm
	JButton exit; //The button to close the program
	
	File name; //To store the selected file (attribute's names).
	File val; //To store the selected file (attribute's values)
	
	//Labels to identify the selected file's path
	JLabel fileAttrName; 
	JLabel fileAttrVal;
	
	//Labels to identify the file's type (names/values)
	JLabel titleAttrName;
	JLabel titleAttrVal;
	
	//The panel for the DataSelectorView window
	JPanel body;
	
	/**
	 * Default constructor.
	 * Initializes the attributes and all the window's components.
	 * This constructor DOASN'T set the JFrame as visible.
	 */
	public DataSelectorView() {
		super(); //The original JFrame default constructor is invoked.
		
		this.setTitle("ID3 - Decision Tree"); //The window's name.
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); //When the window is closed, the program is stopped.
		this.setSize(500,150); //A default size is setted.
		this.setResizable(false); //The user can't (false) resize de window.
		
		/*
		 * As a design choice, the window will be displayed in the middle of the screen.
		 * To do so:
		 */
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); //We get the screen size
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2); //The window is set to be in half the screen size (the middle) 
		
		
		body = new JPanel(new GridLayout(3, 3)); //The window's body is created as a Grid Layout with 3x3 fields.

		loadFields(); //All the body fields are loaded
		
		this.add(body); //when the fields are loaded, the JPanel is added to the current JFrame (this)
		
	}
	
	private void loadFields() {
		
		name = null; //the File containing the names is null
		val = null; //the File containing the values is null
		
		/*
		 * The window has a unique FileChooser.
		 * Using one FileChooser is used to save the path between various uses.
		 * Usually, both files will be in the same directory, so it's very useful to
		 * save the path. The user won't have to navigate 2 times to get to the same
		 * path.
		 */
		final JFileChooser fc = new JFileChooser(); 
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Plain text files (.txt)", "txt", "text"); //Only .txt files will be allowed
		fc.setFileFilter(filter); //The .txt filter is set to the File Chooser
		
		selectAttrName = new JButton("Explore..."); //New JButton for the Name File chooser
		selectAttrName.addActionListener(new ActionListener() {

			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//In response to a button click:
				int returnVal = fc.showOpenDialog(body);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
		            name = fc.getSelectedFile(); //The selected file is stored in the global variable
		            fileAttrName.setText(name.getName()); //The label with the file is updated
		            //If the values and names files are both selected, the execute button is enabled.
		            if(val!=null && name!= null) {
		            		go.setEnabled(true);
		            }
		        } else {
		           System.out.println("Open command cancelled by user.");
		        }
			}
		});
		
		selectAttrVal = new JButton("Explore...");
		selectAttrVal.addActionListener(new ActionListener() {

			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				//In response to a button click:
				int returnVal = fc.showOpenDialog(body);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
		            val = fc.getSelectedFile(); //The selected file is stored in the global variable
		            fileAttrVal.setText(val.getName()); //The label with the file is updated
		            //If the values and names files are both selected, the execute button is enabled.
		            if(val!=null && name!= null) {
	            			go.setEnabled(true);
		            }
		        } else {
		        	System.out.println("Open command cancelled by user.");
		        }
			}
		});
				
		go = new JButton("Accept"); //The execute button is initialized
		go.setEnabled(false); //By default, the execute button is disabled.
		go.addActionListener(new ActionListener() {

			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//Only if both files are selected the action is performed.
				if(name!=null && val!=null) {
					ApplicationController c = new ApplicationController(); //The controlled is created.
					Node tree = c.decitionTree(name, val); //The decision tree is generated by the controller, providing both files
					if(tree != null) {
						printResut(tree); //When the three is created, it's displayed to the user.
					}
				}
			}
		});
		
		exit = new JButton("Exit"); //Button to close the program
		exit.addActionListener(new ActionListener() {

			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0); //The program is closed.
			}
			
		});
		
		fileAttrName = new JLabel("..."); //The default attribute name file is null ("...")
		fileAttrVal = new JLabel("..."); //The default attribute values file is null ("...")
		
		titleAttrName = new JLabel("Attribute Names:"); //Label to select the Name File
		titleAttrVal = new JLabel("Attribute Values:"); //Label to select the Value File
		
		//All the components are added to the body
		
		body.add(titleAttrName);
		body.add(fileAttrName);
		body.add(selectAttrName);

		
		body.add(titleAttrVal);
		body.add(fileAttrVal);
		body.add(selectAttrVal);
		
		
		body.add(go);
		body.add(exit);
		
	}
	
	/**
	 * Function to show a decision tree graphically (using Swing) displaying a new window
	 * @param tree Node containing a decision tree. A null tree can cause a program malfunction
	 */
	private void printResut(Node tree) {
		JFrame result = new JFrame("Result"); //New window created.
		JPanel p = new JPanel(new GridLayout(2,1)); //A body to paint the tree
		printTree(p,tree); //Aux function to print the tree into the body
		
		result.add(p); //Body added to the window
		result.setDefaultCloseOperation(DISPOSE_ON_CLOSE); //When the window is disposed, end the program
		result.setSize(new Dimension(500,400)); //Set default size. Redimensionable
		
		//The screen will show up in the middle of the screen
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); //We get the screen size
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2); //The window is set to be in half the screen size (the middle)
		
		result.setVisible(true);
	}
	
	/**
	 * Given a JPanel and a Node, the method will paint the tree into the panel
	 * @param panel Initialized JPanel that will contain the tree.
	 * @param tree Node containing a decision tree. A null tree will cause no action.
	 */
	private void printTree(JPanel panel, Node tree) {
		if(tree!=null) { //Only execute when the tree isn't null
			JLabel mainNodeLabel = new JLabel(); //New head JLabel with the node name
			if(tree.getName().equals("si")) { //Special case: the node is the end of a branch (+)
				mainNodeLabel.setBackground(Color.GREEN); //To represent the positive end of the branch, the label's background is green
				mainNodeLabel.setOpaque(true); //The color is displayed
			}
			else if((tree.getName().equals("no"))) { //Special case: the node is the end of a branch (-)
				mainNodeLabel.setBackground(Color.RED); //To represent the negative end of the branch, the label's background is green
				mainNodeLabel.setOpaque(true); //The color is displayed
			}
			else {
				Font font = new Font(mainNodeLabel.getFont().getName(),Font.ITALIC+Font.BOLD,mainNodeLabel.getFont().getSize()); //A regular node name is showed with a special font
				mainNodeLabel.setFont(font); //Font is applied
				mainNodeLabel.setText(tree.getName() +  ":"); //After the node name, a : is added
			}
			mainNodeLabel.setHorizontalAlignment(JLabel.CENTER); //The name of the label will be center-aligned 
			mainNodeLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //The JLabel has a black border
			panel.add(mainNodeLabel); //The JLabel is added to the provided panel
			if(tree.getSons()!=null) { //If the node has more vertex, the next step is prepared. If not, the method is over.
				JPanel sonsPanel = new JPanel(new GridLayout(2, tree.getSons().size())); //After the node's cell, a new panel is created to contain it's sons
				for(Vertex e : tree.getSons()) { //For every vertex of the node
					JLabel sonLabel = new JLabel(e.getValue()); //New lavel with the name of the vertex
					sonLabel.setHorizontalAlignment(JLabel.CENTER); //Name displayed in the center
					sonLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK)); //Black border to the label
					sonsPanel.add(sonLabel); //Label added to the new panel
				}
				for(Vertex e : tree.getSons()) {  //For every vertex of the node
					JPanel next = new JPanel (new GridLayout(2,1)); //New panel to add it's next node
					printTree(next, e.getNext()); //Paint into the new panel the next node
					sonsPanel.add(next); //Add into the son's panel the resultant panel
				}
				panel.add(sonsPanel); //Add into the original panel the generated sons panel and method's end
			}
		}
	}
	
	/**
	 * Given a JPanel and a Node, the method will paint the level of the tree into the panel
	 * @param textArea Initialized JTextArea that will contain the tree.
	 * @param tree Node containing a decision tree. A null tree will cause no action.
	 * @param level Level of the node that will be displayed. Initially, it's 0
	 */
	@SuppressWarnings("unused") //Currently, the program uses the graphic printTree, so the warning is ignored.
	private void printTree(JTextArea textArea, Node tree, int level) {
		String next = System.lineSeparator(); //To properly show the line separator, it's obtained from System.
		if(tree !=  null) { //If the node is null, nothing is executed
			for(int i = 0; i < level; i++) { //The level is show as a chain of "---"
				textArea.append("-");
			}
			if(tree.getName().equals("si")||tree.getName().equals("no")) textArea.append(tree.getName() + "." + next); //If it's the end of the tree, it's ended with a point
			else textArea.append("¿" + tree.getName() + "?" + next); //If it's not the end, question marks are added to the node name (since it's a decision)
			if(tree.getSons()!= null) { // If the node has sons (vertexes)
				for(Vertex e : tree.getSons()) { //For every vertex
					for(int i = 0; i < level+1; i++) { //A new level it's added
						textArea.append("-");
					}
					textArea.append("(" + e.getValue() + ")"+next); //The name of the vertex is added (the answer to the previous question)
					printTree(textArea, e.getNext() ,level+2); //The next level it's printed. Since the vertex name is showed as the level+1, the next node is the level+2
				}
			}
		}
	}
	
	
	/**
	 * Given a Node, the method will paint the level of the tree into the console.
	 * @param tree Node containing a decision tree. A null tree will cause no action.
	 * @param level Level of the node that will be displayed. Initially, it's 0
	 */
	@SuppressWarnings("unused")
	private void printTree(Node tree, int level) {
		if(tree !=  null) { //If the node is null, nothing is executed
			for(int i = 0; i < level; i++) { //The level is represented as a chain of "---"
				System.out.print("-"); 
			}
			if(tree.getName().equals("si")||tree.getName().equals("no")) System.out.println(tree.getName() + "."); //If it's the end of the tree, it's ended with a point
			else System.out.println("¿" + tree.getName() + "?"); //If it's not the end, question marks are added to the node name (since it's a decision)
			if(tree.getSons()!= null) { // If the node has sons (vertexes)
				for(Vertex e : tree.getSons()) { //For every vertex
					for(int i = 0; i < level+1; i++) { //A new level it's added
						System.out.print("-");
					}
					System.out.println("(" + e.getValue() + ")"); //The name of the vertex is added (the answer to the previous question)
					printTree(e.getNext() ,level+2); //The next level it's printed. Since the vertex name is showed as the level+1, the next node is the level+2
				}
			}
		}
	}
	
	//End of class
}
