package main.java.mayuso.LoLPing;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class UI extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton start;
	JComboBox<String> serverList;
	JLabel pingLabel= new JLabel();
	public UI(){
		
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(100,100);
		this.setContentPane(mainPanel());
		this.setTitle("LoLPing v0.1.1");
		this.pack();
		addListeners();
		this.setVisible(true);
	}


	private Container mainPanel() {
		JPanel panel = new JPanel(new GridLayout(1,2));
		panel.add(leftPanel());
		panel.add(rightPanel());
		
		return panel;
	}
	private Component rightPanel() {
		JPanel panel = new JPanel();
		panel.setBorder(BorderFactory.createEmptyBorder(60, 20, 40, 20));
		panel.add(pingLabel);
		return panel;
	}


	private Component leftPanel() {
		JPanel panel = new JPanel(new GridLayout(3,1));
		
		panel.setBorder(BorderFactory.createEmptyBorder(40, 20, 40, 20));
		
		

			
		
		serverList = new JComboBox<String>();
		serverList.addItem("EUW        ");
		serverList.addItem("NA         ");
		serverList.addItem("EUNE       ");
		//serverList.addItem("OCE(*)     ");
		serverList.addItem("BR         ");
		//serverList.addItem("LAN(*)        ");	
		//serverList.addItem("LAS(*)        ");
		
		
		
		start = new JButton("   START  ");
		
		panel.add(serverList);
		panel.add(start);
		return panel;
	}


	private void addListeners() {
		
		start.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				start();
			}
		});
	}
	private void start() {
		boolean valid=true;
		String ip = null;
		switch(serverList.getSelectedItem().toString().replace(" ", "")){
		case "EUW":
			ip= "riot.de";
			break;
		case "NA": 
			ip="riot.us";
			break;
		case "EUNE":
			ip= "riot.pl";
			break;
		/*case "OCE":
			ip="103.240.227.5"; 
			valid=false;
			break;*/
		case "BR":
			ip="8.23.24.100"; 
			break;		
		/*case "LAN":
			ip="66.151.33.33"; 
			valid=false;
			break;*/
		/*case "LAS":
			ip="200.73.71.100"; //LAS
			valid=false;
			break;*/
		}
		Ping ping= new Ping();
		if(valid){
			float pingValue = ping.getPing(ip, 3);
			pingLabel.setText("<html>Ping(" + String.valueOf(serverList.getSelectedItem()).replace(" ", "")+ "): "); 
	
			if(pingValue<30){
				pingLabel.setText(pingLabel.getText() + "<font color='blue'>"+String.valueOf(pingValue)+"</font></html>");
			}else if(pingValue<75){
				pingLabel.setText(pingLabel.getText() + "<font color='green'>"+String.valueOf(pingValue)+"</font></html>");
			}else if(pingValue<125){
				pingLabel.setText(pingLabel.getText() + "<font color='yellow'>"+String.valueOf(pingValue)+"</font></html>");
			}else{
				pingLabel.setText(pingLabel.getText() + "<font color='red'>"+String.valueOf(pingValue)+"</font></html>");
			}
		}else{
			JOptionPane.showMessageDialog(null, String.valueOf(serverList.getSelectedItem()).replace(" ", "").replace("(*)", "")+" is not available for the current LoLPing Version.");
		}
		this.pack();	
	}
}
