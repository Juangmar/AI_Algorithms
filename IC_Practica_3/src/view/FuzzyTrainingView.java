package view;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;

import business.Kmean;
import controller.ApplicationController;

public class FuzzyTrainingView extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3743034997389944336L;
	private JPanel body;
	private HashMap<Integer, File> trainingSets;
	private HashMap<Integer, JLabel> filenames;
	private HashMap<Integer, JButton> fileselectors, filedeleters;
	private JButton go, cancel, addFile;
	private int nextFile;
	protected JFileChooser fc = new JFileChooser(); 
	private Kmean manager;
	public FuzzyTrainingView instance;
	public FuzzyTrainingView(Kmean e, JFileChooser fchooser) {
		super();
		manager = e;
		fc = fchooser;
		this.setTitle("K-Means testing set");
		this.setSize(550,105);
		//this.setResizable(false);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		/*
		 * As a design choice, the window will be displayed in the middle of the screen.
		 * To do so:
		 */
		instance = this;
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); //We get the screen size
		this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2); //The window is set to be in half the screen size (the middle) 
		initialize();
	}
	private void initialize() {
		trainingSets = new HashMap<Integer, File>();
		filenames = new HashMap<Integer, JLabel>();
		fileselectors = new HashMap<Integer, JButton>();
		filedeleters = new HashMap<Integer, JButton>();
		nextFile = 0;
		go = new JButton("Test Accuracy");
		go.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				execute();
			}
			
		});
		cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				instance.dispose();
			}
			
		});
		addFile = new JButton("Add another testing set");
		addFile.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				addFile();
			}
			
		});
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Plain text files (.txt)", "txt", "text"); //Only .txt files will be allowed
		fc.setFileFilter(filter); //The .txt filter is set to the File Chooser
		
		
		JLabel firstL = new JLabel("File not selected yet");
		firstL.setHorizontalAlignment(JLabel.CENTER);
		filenames.put(0, firstL);
		final int index = nextFile;
		JButton firstB = new JButton("Explore...");
		firstB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//In response to a button click:
				int returnVal = fc.showOpenDialog(body);
				if (returnVal == JFileChooser.APPROVE_OPTION) {
		            File data = fc.getSelectedFile(); //The selected file is stored in the global variable
		            trainingSets.put(index, data);
		            filenames.get(index).setText(data.getName()); //The label with the file is updated
		        } else {
		           System.out.println("Open command cancelled by user.");
		        }
			}	
		});
		
		JButton firstD = new JButton("Delete");
		firstD.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				deleteFile(index);
			}
			
		});
		filedeleters.put(0,  firstD);
		fileselectors.put(0, firstB);
		nextFile++;
		
		
		body = new JPanel(new GridLayout((nextFile+2),3));
		for(int i = 0; i < nextFile; i++) {
			body.add(new JLabel("Training set 1"));
			body.add(filenames.get(i));
			body.add(fileselectors.get(i));
		}
		body.add(addFile);
		body.add(new JLabel());
		body.add(new JLabel());
		body.add(cancel);
		body.add(new JLabel());
		body.add(go);
		
		
		this.add(body);
	}

	private void addFile() {
		final int index = nextFile;
		this.remove(body);
		body = new JPanel();
		JLabel firstL = new JLabel("(File not selected yet)");
		firstL.setHorizontalAlignment(JLabel.CENTER);
		filenames.put(index, firstL);
		
		JButton firstB = new JButton("Explore...");
		firstB.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
					//In response to a button click:
					int returnVal = fc.showOpenDialog(body);
					if (returnVal == JFileChooser.APPROVE_OPTION) {
			            File data = fc.getSelectedFile(); //The selected file is stored in the global variable
			            trainingSets.put(index, data);
			            filenames.get(index).setText(data.getName()); //The label with the file is updated
			        } else {
			           System.out.println("Open command cancelled by user.");
			        }
			}	
		});
		fileselectors.put(index, firstB);
		JButton firstD = new JButton("Delete");
		firstD.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				deleteFile(index);
			}
		});
		filedeleters.put(index, firstD);
		nextFile++;
		
		body = new JPanel(new GridLayout((fileselectors.size()+2),3));
		for(int i = 0; i < nextFile; i++) {
			if(filenames.get(i)!=null) {
				body.add(new JLabel("Train set "+(i+1)));
				body.add(filenames.get(i));
				JPanel p = new JPanel(new GridLayout(1,2));
				p.add(fileselectors.get(i));
				p.add(filedeleters.get(i));
				body.add(p);
			}
		}
		body.add(addFile);
		body.add(new JLabel());
		body.add(new JLabel());
		body.add(cancel);
		body.add(new JLabel());
		body.add(go);
		
		this.add(body);
		this.setSize(550,((fileselectors.size()+3)*30));
	}
	
	private void deleteFile(int in) {
		this.remove(body);
		body = new JPanel();

		filenames.remove(in);
		trainingSets.remove(in);
		fileselectors.remove(in);
		filedeleters.remove(in);
		
		body = new JPanel(new GridLayout((fileselectors.size()+2),3));
		for(int i = 0; i < nextFile; i++) {
			if(filenames.get(i)!=null) {
				body.add(new JLabel("Train set "+(i+1)));
				body.add(filenames.get(i));
				JPanel p = new JPanel(new GridLayout(1,2));
				p.add(fileselectors.get(i));
				p.add(filedeleters.get(i));
				body.add(p);
			}
		}
		body.add(addFile);
		body.add(new JLabel());
		body.add(new JLabel());
		body.add(cancel);
		body.add(new JLabel());
		body.add(go);
		
		this.add(body);
		this.setSize(550,((fileselectors.size()+3)*30));
	}

	
	private void execute() {
		if(trainingSets.isEmpty()) {
			
		} else {
			ArrayList<File> trainingCases = new ArrayList<File>();
			Iterator<Entry<Integer, File>> it = trainingSets.entrySet().iterator();
			while(it.hasNext()) {
				Map.Entry<Integer, File> pair = (Map.Entry<Integer, File>) it.next();
				if((!trainingCases.contains(pair.getValue())&&(pair.getValue().isFile()))) trainingCases.add(pair.getValue());
			}
			
			if(trainingCases.isEmpty()) {
				
			}else {
				ApplicationController a = new ApplicationController();
				HashMap<Double[], String> data = a.loadManyData(trainingCases);
				double accuracy = manager.test(data);
				JOptionPane.showMessageDialog(this, "For the previous training cases and the given test data, the accuracy is:\n" + accuracy*100 + "%", "Accuracy of KMeans", JOptionPane.INFORMATION_MESSAGE);
			}
		}
	}
	
}
