package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class MainView extends JFrame {

	/**
	 *  generated to supress warnings
	 */
	private static final long serialVersionUID = 1L;

	private JPanel body;
	private JTextArea log;
	
	public MainView() {
		super();
	    this.setTitle("Practica 1 - Juan G-Martinho");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setResizable(true);
		this.setSize(600,500);
		//this.setResizable(false);
		this.setMinimumSize(new Dimension(200,200));
		
		JMenuBar m = new JMenuBar();
		JMenu one = new JMenu("File");
		m.add(one);
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
		
		for(int i = 0; i<2*2; i++) {
			final int n = i;
			JButton b = new JButton();
			b.setText("buttton " + i);
			b.addActionListener(new ActionListener(){

				@Override
				public void actionPerformed(ActionEvent arg0) {
					String sep = System.lineSeparator();
					log.append(sep + "Se ha pulsado: " + n);
					
				}
				
			});
			panel.add(b);
		}
		
		panel.setSize(200,200);
		body.add(panel, BorderLayout.SOUTH);
	}
	private void generateEast() {
		JPanel panel = new JPanel(new GridLayout(1,1));
				
			log = new JTextArea();
			log.setEditable(false);
			log.setText("Iniciando Log del mapa...");

		TitledBorder title = BorderFactory.createTitledBorder("Log");
		JScrollPane scroll = new JScrollPane(log);
		scroll.setBorder(title);
		panel.setSize(400, 300);
		panel.setMinimumSize(new Dimension(400,300));
		panel.add(scroll);
		body.add(panel, BorderLayout.EAST);
	}
	private void generateWest() {
		JLabel west = new JLabel("West", SwingConstants.CENTER);
		body.add(west, BorderLayout.WEST);
	}
	private void generateCenter() {
		
		JPanel center = new JPanel(new GridLayout(10,10));
		
		for(int i = 0; i < 10*10; i++) {
			JLabel label = new JLabel("");
			label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
			double r = Math.random();
		    if(r>=0.4) label.setBackground(Color.white);
		    else label.setBackground(Color.red);
		    label.setOpaque(true);
		    center.add(label);
		}
		center.setMinimumSize(new Dimension(200,200));
		
		body.add(center, BorderLayout.CENTER);
		
	}
}
