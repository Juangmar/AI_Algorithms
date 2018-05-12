package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * @author Juan Gómez-Martinho
 *
 */
public class SelectorFrame extends JFrame {

	protected JPanel body;
	protected JPanel methodInfo;
	protected JPanel dataPanel;
	
	protected JLabel fileHeader;
	protected JLabel fileName;
	protected JLabel methodHeader;
	protected JComboBox<String> methods;
	protected JButton fileSelect;
	protected JButton go;
	protected JButton exit;
	
	//Common
	protected JLabel toleranceBase_label;
	protected JLabel exponent_label;
	protected JLabel iterations_label;
	protected JLabel constant_label;
	
	//Fuzzy 
	//private JLabel Fuzzy_toleranceBase_label;
	protected JTextField fuzzy_toleranceBase_field;
	
	//private JLabel fuzzy_exponent_label;
	protected JTextField fuzzy_exponen_field;
	
	protected JLabel fuzzy_weigth_label;
	protected JTextField fuzzy_weight_field;
	
	//Lloyd
	//private JLabel lloyd_toleranceBase_label;
	protected JTextField lloyd_toleranceBase_field;

	//private JLabel lloyd_exponet_label;
	protected JTextField lloyd_exponent_field;
	
	//private JLabel lloyd_iterations_label;
	protected JTextField lloyd_iterations_field;
	
	//ptivate JLabel lloyd_constant_label;
	protected JTextField lloyd_constant_field;
	
	
	//SOM
	//private JLabel som_toleranceBase_label;
	protected JTextField som_toleranceBase_field;
	
	//private JLabel som_exponent_label;
	protected JTextField som_exponent_field;
	
	//private JLabel som_iterations_label;
	protected JTextField som_iterations_field;
	
	//private JLabel som_constant_label;
	protected JTextField som_constant_field;
	
	protected JLabel som_alpha_init_label;
	protected JTextField som_alpha_init_field;
	
	protected JLabel som_alpha_end_label;
	protected JTextField som_alpha_end_field;
	
	protected JLabel som_t_label;
	protected JTextField som_t_field;
	protected JTextField som_tExp_field;
	
	File data; //To store the selected file (attribute's names).
	
	/**
	 *  Default serial version
	 */
	private static final long serialVersionUID = 1L;

	public SelectorFrame() {
		super();
		this.setTitle("Clasificador");
		this.setSize(500,300);
		this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		/*
		 * As a design choice, the window will be displayed in the middle of the screen.
		 * To do so:
		 */
		
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); //We get the screen size
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2); //The window is set to be in half the screen size (the middle) 
		
		loadWindow();
	}
	
	private void loadWindow() {
		body = new JPanel(new BorderLayout());
		dataPanel = new JPanel(new GridLayout(3,1));
		
		loadFields();
		
		loadNorth();
		
		loadDataFuzzy();
		
		this.add(body);
	}

	private void loadFields() {
		fileHeader = new JLabel("Data file:");
		fileName = new JLabel ("(No file selected yet)");
		fileName.setHorizontalAlignment(JLabel.CENTER);
		
		methodHeader = new JLabel("Select method:");
		
		//Common fields
		String[] met = {"Fuzzy","Bayes (Not implemented)", "Lloyd (Not implemented)", "SOM (Not implemented)"};
		methods = new JComboBox<String>(met);
		methods.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getItem().equals(met[0])) {
					loadDataFuzzy();
					System.out.println("1");
				}
				else if (e.getItem().equals(met[1])) {
					loadDataBayes();
					System.out.println("2");
				}
				else if (e.getItem().equals(met[2])) {
					loadDataLloyd();
					System.out.println("3");
				}
				else if (e.getItem().equals(met[3])) {
					loadDataSOM();
					System.out.println("4");
				}
			}
			
		});

		toleranceBase_label = new JLabel ("Tolerance: ");
		exponent_label = new JLabel (" ^ ");
		iterations_label = new JLabel ("Iterations: ");
		constant_label = new JLabel("Learning constant:");
		//Fuzzy fields
		fuzzy_toleranceBase_field = new JTextField("0.01");
		fuzzy_exponen_field = new JTextField("1");
		
		fuzzy_weigth_label = new JLabel("Exponential weigth (b):");
		fuzzy_weight_field = new JTextField("2");
		
		//Lloyd fields
		lloyd_toleranceBase_field = new JTextField("10");
		lloyd_exponent_field = new JTextField("-10");
		lloyd_iterations_field = new JTextField("10");
		lloyd_constant_field = new JTextField("0.1");
				
		//SOM
		som_toleranceBase_field = new JTextField("10");
		som_exponent_field = new JTextField("-6");
		som_iterations_field = new JTextField("1000");
		som_constant_field = new JTextField("0.1");
		som_alpha_init_label = new JLabel("Initial Alpha:");
		som_alpha_init_field = new JTextField("0.1");
		som_alpha_end_label = new JLabel("Final Alpha:");
		som_alpha_end_field = new JTextField("0.01");
		som_t_label = new JLabel("Update center when K-a(k) > T; T = ");
		som_t_field = new JTextField("10");
		som_tExp_field = new JTextField("-5");
		
		final JFileChooser fc = new JFileChooser(); 
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Plain text files (.txt)", "txt", "text"); //Only .txt files will be allowed
		fc.setFileFilter(filter); //The .txt filter is set to the File Chooser
		
		fileSelect = new JButton("Select...");
		fileSelect.addActionListener(new ActionListener() {

			/* (non-Javadoc)
			 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
			 */
			@Override
			public void actionPerformed(ActionEvent arg0) {
				//In response to a button click:
				int returnVal = fc.showOpenDialog(body);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
		            data = fc.getSelectedFile(); //The selected file is stored in the global variable
		            fileName.setText(data.getName()); //The label with the file is updated
		            //If the data file is selected, the execute button is enabled.
		            if(data!= null) {
		            		go.setEnabled(true);
		            }
		        } else {
		           System.out.println("Open command cancelled by user.");
		        }
			}
		});
		go = new JButton("Execute");
		go.setEnabled(false);
		go.addActionListener(new DataListener(this));
		
		exit = new JButton("Exit");
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
			
		});
	}
	
	private void loadNorth() {
		
		
		methodInfo = new JPanel(new GridLayout(2, 1));
		
		JPanel file = new JPanel(new GridLayout(1,3));
			file.add(fileHeader);
			file.add(fileName);
			file.add(fileSelect);
		methodInfo.add(file);
		JPanel method = new JPanel (new GridLayout(1,2));
			method.add(methodHeader);
			method.add(methods);
		methodInfo.add(method);
		
		body.add(methodInfo, BorderLayout.NORTH);
	}
	
	private void loadDataFuzzy() {
		body.remove(dataPanel);
		dataPanel = new JPanel(new GridLayout(3,2));
		
		dataPanel.add(toleranceBase_label);
		JPanel tolerance = new JPanel(new GridLayout(1,2));
			tolerance.add(fuzzy_toleranceBase_field);
			tolerance.add(exponent_label);
			tolerance.add(fuzzy_exponen_field);
		dataPanel.add(tolerance);
		dataPanel.add(this.fuzzy_weigth_label);
		dataPanel.add(this.fuzzy_weight_field);
		dataPanel.add(go);
		dataPanel.add(exit);
		body.add(dataPanel, BorderLayout.CENTER);
		this.setSize(500,170);
	
	}
	
	private void loadDataBayes() {
		body.remove(dataPanel);
		dataPanel = new JPanel(new GridLayout(1,2));
		dataPanel.add(go);
		dataPanel.add(exit);
		body.add(dataPanel, BorderLayout.CENTER);
		this.setSize(500,110);
	}

	private void loadDataLloyd() {
		body.remove(dataPanel);
		dataPanel = new JPanel(new GridLayout(4,2));
		dataPanel.add(toleranceBase_label);
		JPanel tolerance = new JPanel(new GridLayout(1,2));
			tolerance.add(lloyd_toleranceBase_field);
			tolerance.add(exponent_label);
			tolerance.add(lloyd_exponent_field);
		dataPanel.add(tolerance);
		dataPanel.add(iterations_label);
		dataPanel.add(lloyd_iterations_field);
		dataPanel.add(constant_label);
		dataPanel.add(lloyd_constant_field);
		dataPanel.add(go);
		dataPanel.add(exit);
		body.add(dataPanel, BorderLayout.CENTER);
		this.setSize(500,190);
	}
	
	private void loadDataSOM() {
		body.remove(dataPanel);
		dataPanel = new JPanel(new GridLayout(6,2));
		dataPanel.add(toleranceBase_label);
		JPanel tolerance = new JPanel(new GridLayout(1,2));
			tolerance.add(som_toleranceBase_field);
			tolerance.add(exponent_label);
			tolerance.add(som_exponent_field);
		dataPanel.add(tolerance);
		dataPanel.add(iterations_label);
		dataPanel.add(som_iterations_field);
		dataPanel.add(constant_label);
		dataPanel.add(som_constant_field);
		JPanel alphaI = new JPanel(new GridLayout(1,2));
			alphaI.add(som_alpha_init_label);
			alphaI.add(som_alpha_init_field);
		JPanel alphaF = new JPanel(new GridLayout(1,2));			
			alphaF.add(som_alpha_end_label);
			alphaF.add(som_alpha_end_field);
		dataPanel.add(alphaI);
		dataPanel.add(alphaF);
		dataPanel.add(som_t_label);
		JPanel k = new JPanel(new GridLayout(1, 3));
			k.add(som_t_field);
			k.add(exponent_label);
			k.add(som_tExp_field);
		dataPanel.add(k);
		dataPanel.add(go);
		dataPanel.add(exit);
		body.add(dataPanel, BorderLayout.CENTER);
		this.setSize(500,230);
	}

	
}
