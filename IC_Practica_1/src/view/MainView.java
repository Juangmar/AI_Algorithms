package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class MainView extends JFrame {

	/**
	 *  generated to supress warnings
	 */
	private static final long serialVersionUID = 1L;

	private JPanel body;
	
	public MainView() {
		super();
	    this.setTitle("Practica 1 - Juan G-Martinho");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(true);
		this.setSize(500,500);
		this.setResizable(false);
		this.setMinimumSize(new Dimension(200,200));
		
		JMenuBar m = new JMenuBar();
		JMenu one = new JMenu("File");
		m.add(one);
		this.setJMenuBar(m);
		
		body = new JPanel(new BorderLayout());
		
		generateNorth();
		generateSouth();
		generateEast();
		generateWest();
		generateCenter();
		
		this.add(body);
	}
	
	private void generateNorth() {
		JLabel north = new JLabel("North", SwingConstants.CENTER);
		body.add(north, BorderLayout.NORTH);
	}
	private void generateSouth() {
		JLabel south = new JLabel("South", SwingConstants.CENTER);
		body.add(south, BorderLayout.SOUTH);
	}
	private void generateEast() {
		JLabel east = new JLabel("East", SwingConstants.CENTER);
		body.add(east, BorderLayout.EAST);
	}
	private void generateWest() {
		JLabel west = new JLabel("West", SwingConstants.CENTER);
		body.add(west, BorderLayout.WEST);
	}
	private void generateCenter() {
		
		JPanel center = new JPanel(new GridLayout(6,6));
		
		for(int i = 0; i < 6*6; i++) {
			JLabel label = new JLabel("");
		    label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		    label.setBackground(Color.white);
		    label.setOpaque(true);
		    center.add(label);
		}
		center.setMinimumSize(new Dimension(200,200));
		
		body.add(center, BorderLayout.CENTER);
		
	}
}
