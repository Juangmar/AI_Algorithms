package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import business.Cell;
import business.CellHeight;
import business.CellHeightWayPoint;
import business.ThreeDMap;

/**
 * @author Juan Gómez-Martinho González
 *
 */
public class HGView extends JFrame {

	/**
	 *  generated to supress warnings
	 */
	private static final long serialVersionUID = 1L;

	private JPanel body;
	private JTextArea log;
	private JPanel center;

	private ThreeDMap map;
	
	
	public HGView() {
		super();
	    this.setTitle("Pathfinder - A*");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(true);
		this.setSize(700,550);
		this.setResizable(false);
		this.setMinimumSize(new Dimension(200,200));
		/*
		JMenuBar m = new JMenuBar();
		JMenu one = new JMenu("File");
		JMenu two = new JMenu("Map");
		m.add(one);
		m.add(two);
		this.setJMenuBar(m);
		*/
		body = new JPanel(new BorderLayout());
		
		//generateNorth();
		generateSouth();
	
		//generateWest();
		generateCenter();
		generateEast();
		
		this.add(body);
	}
	
	@SuppressWarnings("unused")
	private void generateNorth() {
		JLabel north = new JLabel("A* pathfinding map.", SwingConstants.CENTER);
		body.add(north, BorderLayout.NORTH);
	}
	
	
	private void generateSouth() {
		JPanel panel = new JPanel(new GridLayout(2,2));
		String sep = System.lineSeparator();
		
		JButton instGo = new JButton();
		instGo.setText("Instant GO");
		instGo.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				log.append(sep + "Calculating path...");
				/* ArrayList<Cell> e = WayMapController.stepAStar(map);
				if(e == null) {log.append(sep + "No path avilable!");}else {
					for(int i = 0; i < e.size(); i++) {
						log.append(sep + e.get(i).getX() + "-" + e.get(i).getY());
					}
					redrawMap(e);
				}*/
				
			}
			
		});
		panel.add(instGo);
		JButton steps = new JButton();
		steps.setText("Step-by-step GO");
		steps.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				log.append(sep + "Path will be displayed step by step.");
			}
			
		});
		steps.setEnabled(false);
		panel.add(steps);
		JButton clearMap = new JButton();
		clearMap.setText("Clear Map");
		clearMap.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				log.append(sep + "Erasing the map...");
				map.clearMap();
				redrawMap(null);
			}
			
		});
		panel.add(clearMap);
		JButton rMap = new JButton();
		rMap.setText("Randomize Map");
		rMap.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				log.append(sep + "Randomizing the map...");
				map = new ThreeDMap(map.width(),map.height());
				redrawMap(null);
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
		
		map = new ThreeDMap(10,10);
		
		drawMap(null);
		
		center.setMinimumSize(new Dimension(200,200));
		center.setMaximumSize(new Dimension(300,300));
		
		body.add(center, BorderLayout.CENTER);
	}
		

	
	public void redrawMap(ArrayList<Cell> path) {
		body.remove(center);
		drawMap(path);
		
	}
	public void drawMap(ArrayList<Cell> path) {
		center = new JPanel(new GridLayout(map.width(),map.height()));
		
		
		for(int i = 0; i< map.width(); i++) {
			for(int j = 0; j < map.height(); j++) {
				final int x = i;
				final int y = j;
				JLabel label = new JLabel("");
				label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				
				if(path!=null&&path.contains(map.getCell(i,j))) label.setBackground(Color.black);
				double height = (double)(((CellHeight) map.getCell(i, j)).getHeight())/100;
				label.setBackground(new Color (0,255,(int) (255*height)));
				label.setText(Integer.toString((int) (height*100)));
				label.setHorizontalAlignment(JLabel.CENTER);
				

				if(map.getCell(i,j) instanceof CellHeightWayPoint) {
					label.setBackground(Color.MAGENTA);
					label.setText(Integer.toString(map.getIndexOfPoint((CellHeightWayPoint)map.getCell(i,j)))
							+ " - " +Integer.toString((int) (height*100)));
					label.setHorizontalAlignment(JLabel.CENTER);
				}
				
				label.setOpaque(true);
			    label.addMouseListener(new MouseAdapter() {
			    	public void mouseClicked(MouseEvent evt) { 
			    		String sep = System.lineSeparator();
			    		if (evt != null && javax.swing.SwingUtilities.isLeftMouseButton(evt)) {
			    			log.append(sep + "You've right-pressed: cell " + x + "-" + y);
			        	 	if(map.getCell(x, y) instanceof CellHeightWayPoint) {
			        	 			map.deleteWayPoint(x,y);
			        	 	}
			        	 	else if (map.getCell(x, y).isWalkable()){
			        	 			map.addWayPoint(x,y);
			        	 	}
			        	 	else {
			        	 		log.append(sep + "You can't set that there!");
			        	 	}
			        } 
			    		redrawMap(null);
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