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
	JComboBox<String> serverList, numberOfPackages;
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
		serverList.addItem("EUNE(*)    ");
		serverList.addItem("OCE(*)     ");
		numberOfPackages = new JComboBox<String>();
		numberOfPackages.addItem("1");
		numberOfPackages.addItem("2");
		numberOfPackages.addItem("3");
		numberOfPackages.addItem("4");
		
		start = new JButton("   START  ");
		
		panel.add(serverList);
		panel.add(numberOfPackages);
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
		boolean valid=true;;
		String ip;
		switch(serverList.getSelectedIndex()){
		case 0:
			ip= "riot.de"; //EUW
			break;
		case 1: 
			ip="104.160.131.1"; //NA
			break;
		case 2:
			ip= "riot.pl";//EUNE
			valid=false;
			break;
		case 3:
			ip="103.240.227.5"; //OCE
			valid=false;
			break;
		default:
			ip="null";
			valid=false;
			break;
		}
		Ping ping= new Ping();
		float pingValue = ping.getPing(ip, (numberOfPackages.getSelectedIndex()+1));
		pingLabel.setText("<html>Ping(" + String.valueOf(serverList.getSelectedItem()).replace(" ", "")+ "): "); 
		if(valid){
			if(pingValue<25){
				pingLabel.setText(pingLabel.getText() + "<font color='blue'>"+String.valueOf(pingValue)+"</font></html>");
				
			}else if(pingValue<75){
				pingLabel.setText(pingLabel.getText() + "<font color='green'>"+String.valueOf(pingValue)+"</font></html>");
			}else if(pingValue<100){
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
