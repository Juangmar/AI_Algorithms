package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

/**
 * @author Juan Gómez-Martinho González
 *
 */
public class MainView extends JFrame {

	/**
	 *  generated to supress warnings
	 */
	private static final long serialVersionUID = 1L;

	private JPanel body;
	private JTextArea log;
	
	public MainView() {
		super();
	    this.setTitle("Pathfinder - A*");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(true);
		this.setSize(700,500);
		//this.setResizable(false);
		this.setMinimumSize(new Dimension(200,200));
		
		JMenuBar m = new JMenuBar();
		JMenu one = new JMenu("File");
		JMenu two = new JMenu("Map");
		m.add(one);
		m.add(two);
		this.setJMenuBar(m);
		
		body = new JPanel(new BorderLayout());
		
		generateNorth();
		generateSouth();
		generateEast();
		//generateWest();
		generateCenter();
		
		this.add(body);
	}
	
	private void generateNorth() {
		JLabel north = new JLabel("North", SwingConstants.CENTER);
		body.add(north, BorderLayout.NORTH);
	}
	private void generateSouth() {
		JPanel panel = new JPanel(new GridLayout(2,2));
		String sep = System.lineSeparator();
		
		JButton setBegin = new JButton();
		setBegin.setText("Set start");
		setBegin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				log.append(sep + "Press the start cell.");
			}
			
		});
		panel.add(setBegin);
		JButton setGoal = new JButton();
		setGoal.setText("Set goal");
		setGoal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				log.append(sep + "Press the goal cell.");
			}
			
		});
		panel.add(setGoal);
		JButton clearMap = new JButton();
		clearMap.setText("Clear Map");
		clearMap.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				log.append(sep + "Erasing the map...");
			}
			
		});
		panel.add(clearMap);
		JButton rMap = new JButton();
		rMap.setText("Randomize Map");
		rMap.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				log.append(sep + "Randomizing the map...");
			}
			
		});
		panel.add(rMap);
	
		
		panel.setSize(200,200);
		body.add(panel, BorderLayout.SOUTH);
	}
	private void generateEast() {
		JPanel panel = new JPanel(new GridLayout(1,1));
				
			log = new JTextArea();
			log.setEditable(false);
			log.setText("Initializing map log...              ");
			
		TitledBorder title = BorderFactory.createTitledBorder("Log");
		JScrollPane scroll = new JScrollPane(log);
		scroll.setBorder(title);
		
		scroll.setSize(400, 300);
		panel.setMinimumSize(new Dimension(400,300));
		panel.add(scroll);
		body.add(panel, BorderLayout.EAST);
	}
	
	@SuppressWarnings("unused")
	private void generateWest() {
		JLabel west = new JLabel("West", SwingConstants.CENTER);
		body.add(west, BorderLayout.WEST);
	}
	
	private void generateCenter() {
		
		JPanel center = new JPanel(new GridLayout(10,10));
		
		for(int i = 0; i < 10*10; i++) {
			final int n = i;
			JLabel label = new JLabel("");
			label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			double r = Math.random();
		    if(r>=0.4) label.setBackground(Color.white);
		    else label.setBackground(Color.red);
		    label.setOpaque(true);
		    label.addMouseListener(new MouseAdapter() {
		    	public void mouseClicked(MouseEvent e)  
		        {  
		           if(label.getBackground()==Color.white) label.setBackground(Color.red);
		           else label.setBackground(Color.white);
		           String sep = System.lineSeparator();
		           log.append(sep + "You've pressed: cell " + (n/10) + "-" + (n%10));

		        } 
		    });
		    center.add(label);
		}
		center.setMinimumSize(new Dimension(200,200));
		center.setMaximumSize(new Dimension(300,300));
		
		body.add(center, BorderLayout.CENTER);
		
	}
}
