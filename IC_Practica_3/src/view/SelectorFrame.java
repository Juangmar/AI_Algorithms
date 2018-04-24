package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * @author Juan Gómez-Martinho
 *
 */
public class SelectorFrame extends JFrame {

	private JPanel body;
	private JPanel methodInfo;
	private JPanel dataPanel;
	
	private JLabel fileHeader;
	private JLabel fileName;
	private JLabel methodHeader;
	private JComboBox<String> methods;
	private JButton fileSelect;
	private JButton go;
	private JButton exit;
	
	//Common
	private JLabel toleranceBase_label;
	private JLabel exponent_label;
	private JLabel iterations_label;
	private JLabel constant_label;
	
	//Fuzzy 
	//private JLabel Fuzzy_toleranceBase_label;
	private JTextField fuzzy_toleranceBase_field;
	
	//private JLabel fuzzy_exponent_label;
	private JTextField fuzzy_exponen_field;
	
	private JLabel fuzzy_weigth_label;
	private JTextField fuzzy_weight_field;
	
	//Lloyd
	//private JLabel lloyd_toleranceBase_label;
	private JTextField lloyd_toleranceBase_field;

	//private JLabel lloyd_exponet_label;
	private JTextField lloyd_exponent_field;
	
	//private JLabel lloyd_iterations_label;
	private JTextField lloyd_iterations_field;
	
	//ptivate JLabel lloyd_constant_label;
	private JTextField lloyd_constant_field;
	
	
	//SOM
	//private JLabel som_toleranceBase_label;
	private JTextField som_toleranceBase_field;
	
	//private JLabel som_exponent_label;
	private JTextField som_exponent_field;
	
	//private JLabel som_iterations_label;
	private JTextField som_iterations_field;
	
	//private JLabel som_constant_label;
	private JTextField som_constant_field;
	
	private JLabel som_alpha_init_label;
	private JTextField som_alpha_init_field;
	
	private JLabel som_alpha_end_label;
	private JTextField som_alpha_end_field;
	
	private JLabel som_t_label;
	private JTextField som_t_field;
	
	private JTextField som_tExp_field;
	
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
		loadWindow();
	}
	
	private void loadWindow() {
		body = new JPanel(new BorderLayout());
		dataPanel = new JPanel(new GridLayout(3,1));
		
		loadFields();
		
		loadNorth();
		
		loadDataFuzzy();
		
		//Load static north fields
		
		//Load initial center/data fields
		
		this.add(body);
	}

	private void loadFields() {
		fileHeader = new JLabel("Data file:");
		fileName = new JLabel ("(No file selected yet)");
		fileName.setHorizontalAlignment(JLabel.CENTER);
		
		methodHeader = new JLabel("Select method:");
		
		//Common fields
		String[] met = {"Fuzzy","Bayes", "Lloyd", "SOM"};
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
		som_t_label = new JLabel("T:");
		som_t_field = new JTextField("10");
		som_tExp_field = new JTextField("-5");
		
		
		fileSelect = new JButton("Select...");
		go = new JButton("Execute");
		exit = new JButton("Exit");
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
		dataPanel = new JPanel(new GridLayout(3,2));
		
		body.add(dataPanel, BorderLayout.CENTER);
		this.setSize(500,170);
	}

	
}
