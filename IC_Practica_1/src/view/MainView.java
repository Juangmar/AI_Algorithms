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

import business.Map;
import business.SimpleMap;

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
	private JPanel center;

	private Map map;
	
	public MainView() {
		super();
	    this.setTitle("Pathfinder - A*");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(true);
		this.setSize(700,550);
		this.setResizable(false);
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
	
		//generateWest();
		generateCenter();
		generateEast();
		
		this.add(body);
	}
	
	private void generateNorth() {
		JLabel north = new JLabel("A* pathfinding map.", SwingConstants.CENTER);
		body.add(north, BorderLayout.NORTH);
	}
	
	
	private void generateSouth() {
		JPanel panel = new JPanel(new GridLayout(2,2));
		String sep = System.lineSeparator();
		
		JButton setBegin = new JButton();
		setBegin.setText("Instant GO");
		setBegin.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				log.append(sep + "Path will be found as fast as possible.");
			}
			
		});
		panel.add(setBegin);
		JButton setGoal = new JButton();
		setGoal.setText("Step-by-step GO");
		setGoal.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				log.append(sep + "Path will be displayed step by step.");
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
				map = new SimpleMap(map.width(),map.height());
				redrawMap();
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
		
		map = new SimpleMap(10,10);
		
		drawMap();
		
		center.setMinimumSize(new Dimension(200,200));
		center.setMaximumSize(new Dimension(300,300));
		
		body.add(center, BorderLayout.CENTER);
	}
		



	public void drawMap() {
		center = new JPanel(new GridLayout(map.width(),map.height()));
		for(int i = 0; i< map.width(); i++) {
			for(int j = 0; j < map.height(); j++) {
				final int x = i;
				final int y = j;
				JLabel label = new JLabel("");
				label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				if(map.getCell(i, j).isWalkable()) label.setBackground(Color.white);
				else  label.setBackground(Color.red);
				label.setOpaque(true);
			    label.addMouseListener(new MouseAdapter() {
			    	public void mouseClicked(MouseEvent evt) { 
			    		String sep = System.lineSeparator();
			    		if (evt != null && javax.swing.SwingUtilities.isLeftMouseButton(evt)) {
			           if(label.getBackground()==Color.white) label.setBackground(Color.red);
			           else label.setBackground(Color.white);
			           
			           log.append(sep + "You've pressed: cell " + x + "-" + y);

			        } else {
			        	 	log.append(sep + "You've right-pressed: cell " + x + "-" + y);
			        		label.setBackground(Color.green);
			        }
			       }
			    });
			    center.add(label);
			}
		}
		
		center.setMinimumSize(new Dimension(200,200));
		center.setMaximumSize(new Dimension(300,300));
		body.add(center, BorderLayout.CENTER);
		this.revalidate();
		this.repaint();
		
	}
	
	public void redrawMap() {
		body.remove(center);
		center = new JPanel(new GridLayout(map.width(),map.height()));
		for(int i = 0; i< map.width(); i++) {
			for(int j = 0; j < map.height(); j++) {
				final int x = i;
				final int y = j;
				JLabel label = new JLabel("");
				label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				if(map.getCell(i, j).isWalkable()) label.setBackground(Color.white);
				else  label.setBackground(Color.red);
				label.setOpaque(true);
			    label.addMouseListener(new MouseAdapter() {
			    	public void mouseClicked(MouseEvent evt) { 
			    		String sep = System.lineSeparator();
			    		if (evt != null && javax.swing.SwingUtilities.isLeftMouseButton(evt)) {
			           if(label.getBackground()==Color.white) label.setBackground(Color.red);
			           else label.setBackground(Color.white);
			           
			           log.append(sep + "You've pressed: cell " + x + "-" + y);

			        } else {
			        	 	log.append(sep + "You've right-pressed: cell " + x + "-" + y);
			        		label.setBackground(Color.green);
			        }
			       }
			    });
			    center.add(label);
			}
		}
		
		center.setMinimumSize(new Dimension(200,200));
		center.setMaximumSize(new Dimension(300,300));
		body.add(center, BorderLayout.CENTER);
		this.revalidate();
		this.repaint();
		
	}
	
}
