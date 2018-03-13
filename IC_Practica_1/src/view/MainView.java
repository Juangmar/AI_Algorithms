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
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import business.Cell;
import business.CellEnd;
import business.CellGround;
import business.CellStart;
import business.CellWall;
import business.SimpleMap;
import controllers.SimpleMapController;

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

	private SimpleMap map;
	
	private boolean setStartNow;
	
	
	public MainView() {
		super();
	    this.setTitle("Pathfinder - A*");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(true);
		this.setSize(700,550);
		this.setResizable(false);
		this.setMinimumSize(new Dimension(200,200));
		this.setStartNow = true;
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
				ArrayList<Cell> e = SimpleMapController.aStar(map);
				if(e == null) {log.append(sep + "No path avilable!");}else {
					for(int i = 0; i < e.size(); i++) {
						log.append(sep + e.get(i).getX() + "-" + e.get(i).getY());
					}
					redrawMap(e);
				}
				
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
				redrawMap();
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
				JLabel label = new JLabel(i + "-" + j);
				label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				if(map.getCell(i, j) instanceof CellEnd) {
					label.setText(i + "-" + j + "- E");
					label.setBackground(Color.green);
				}
				else if (map.getCell(i, j) instanceof CellStart) {
					label.setText(i + "-" + j + "- S");
					label.setBackground(Color.green);
				}
				else if(map.getCell(i, j).isWalkable()) label.setBackground(Color.white);
				else label.setBackground(Color.red);
				label.setOpaque(true);
			    label.addMouseListener(new MouseAdapter() {
			    	public void mouseClicked(MouseEvent evt) { 
			    		String sep = System.lineSeparator();
			    		Cell c = map.getCell(x, y);
			    		if (evt != null && javax.swing.SwingUtilities.isLeftMouseButton(evt)) {
			    			if((!(c instanceof CellStart))&&(!(c instanceof CellEnd))) {
			    				if(map.getCell(x, y) instanceof CellGround) {
			    					CellWall w = new CellWall(x,y);
			    					map.setCell(w, x, y);
			    				}
			    				else {
			    					CellGround g = new CellGround(x,y);
			    					map.setCell(g, x, y);
			    				}
			           
			    				log.append(sep + "You've pressed: cell " + x + "-" + y);
			    			}
			    			else {
			    				log.append(sep + "You can't place walls or ground there!");
			    			}

			        } else {
			        	 	log.append(sep + "You've right-pressed: cell " + x + "-" + y);
			        	 	if(map.getCell(x, y).isWalkable()) {
			        	 		if(setStartNow) {
			        	 			CellStart s = map.getStart();
			        	 			if(s==null) {
			        	 				map.setStart(x, y);
			        	 			}
			        	 			else {
			        	 				CellGround g = new CellGround(s.getX(), s.getY());
			        	 				map.setCell(g, s.getX(), s.getY());
			        	 				map.setStart(x, y);
			        	 			}
			        	 			setStartNow=false;
			        	 		}
			        	 		else {
			        	 			CellEnd s = map.getEnd();
			        	 			if(s==null) {
			        	 				map.setEnd(x, y);
			        	 			}
			        	 			else {
			        	 				CellGround g = new CellGround(s.getX(), s.getY());
			        	 				map.setCell(g, s.getX(), s.getY());
			        	 				map.setEnd(x, y);
			        	 			}
			        	 			setStartNow = true;
			        	 		}
			        	 	}else {
			        	 		log.append(sep + "You can't set that in a wall!");
			        	 	}
			        }
			    		redrawMap();
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
		drawMap();
		
	}
	public void redrawMap(ArrayList<Cell> path) {
		body.remove(center);
		center = new JPanel(new GridLayout(map.width(),map.height()));
		CellStart s = map.getStart();
		CellEnd e = map.getEnd();
		int sX = 0, sY=0, eX=0, eY=0;
		boolean hasS = false, hasE = false;
		if(s!=null) {
			sX = s.getX();
			sY = s.getY();
			hasS = true;
		}
		if(e!=null) {
			eX = e.getX();
			eY = e.getY();
			hasE = true;
		}
		
		for(int i = 0; i< map.width(); i++) {
			for(int j = 0; j < map.height(); j++) {
				final int x = i;
				final int y = j;
				JLabel label = new JLabel(i + "-" + j);
				label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
				
				if(path.contains(map.getCell(i,j))) label.setBackground(Color.black);
				else if(map.getCell(i, j).isWalkable()) label.setBackground(Color.white);
				else  label.setBackground(Color.red);
				

				
				if(hasS&&(i==sX)&&(j==sY)) {
					label.setBackground(Color.green);
					label.setText(i + "-" + j + "- S");
				}
				else if (hasE&&(i==eX)&&(j==eY)) {
					label.setBackground(Color.green);
					label.setText(i + "-" + j + "- E");
				}
				
				label.setOpaque(true);
			    label.addMouseListener(new MouseAdapter() {
			    	public void mouseClicked(MouseEvent evt) { 
			    		String sep = System.lineSeparator();
			    		Cell c = map.getCell(x, y);
			    		if (evt != null && javax.swing.SwingUtilities.isLeftMouseButton(evt)) {
			    			if((!(c instanceof CellStart))&&(!(c instanceof CellEnd))) {
			    				if(map.getCell(x, y) instanceof CellGround) {
			    					CellWall w = new CellWall(x,y);
			    					map.setCell(w, x, y);
			    				}
			    				else {
			    					CellGround g = new CellGround(x,y);
			    					map.setCell(g, x, y);
			    				}
			           
			    				log.append(sep + "You've pressed: cell " + x + "-" + y);
			    			}
			    			else {
			    				log.append(sep + "You can't place walls or ground there!");
			    			}

			        } else {
			        	 	log.append(sep + "You've right-pressed: cell " + x + "-" + y);
			        	 	if(map.getCell(x, y).isWalkable()) {
			        	 		if(setStartNow) {
			        	 			CellStart s = map.getStart();
			        	 			if(s==null) {
			        	 				label.setBackground(Color.green);
			        	 				label.setText("S");
			        	 				label.setHorizontalAlignment(SwingConstants.CENTER);
			        	 				map.setStart(x, y);
			        	 			}
			        	 			else {
			        	 				CellGround g = new CellGround(s.getX(), s.getY());
			        	 				map.setCell(g, s.getX(), s.getY());
			        	 				map.setStart(x, y);
			        	 			}
			        	 			setStartNow=false;
			        	 		}
			        	 		else {
			        	 			CellEnd s = map.getEnd();
			        	 			if(s==null) {
			        	 				label.setBackground(Color.green);
			        	 				label.setText("E");
			        	 				label.setHorizontalAlignment(SwingConstants.CENTER);
			        	 				map.setEnd(x, y);
			        	 			}
			        	 			else {
			        	 				CellGround g = new CellGround(s.getX(), s.getY());
			        	 				map.setCell(g, s.getX(), s.getY());
			        	 				map.setEnd(x, y);
			        	 			}
			        	 			setStartNow = true;
			        	 		}
			        	 	}else {
			        	 		log.append(sep + "You can't set that in a wall!");
			        	 	}
			        }
			    		redrawMap();
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
