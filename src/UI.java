
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

public class UI extends JFrame{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JButton start;
	JComboBox<String> serverList, numberOfPackages;
	JLabel pingLabel= new JLabel();
	public UI(){
		/*try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException | IllegalAccessException
				| UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocation(100,100);
		this.setContentPane(mainPanel());
		this.setTitle("LoLPing v0.1.0");
		addListeners();
		this.pack();
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
			ip= "185.40.65.1"; //EUW
			break;
		case 1: 
			ip="104.160.131.1"; //NA
			break;
		case 2:
			ip= "31.186.224.42";//EUNE
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
		
		if(valid){
			pingLabel.setText("Ping(" + String.valueOf(serverList.getSelectedItem()).replace(" ", "")+ "): " + String.valueOf(ping.getPing(ip, (numberOfPackages.getSelectedIndex()+1))));
		}else{
			JOptionPane.showMessageDialog(null, String.valueOf(serverList.getSelectedItem()).replace(" ", "").replace("(*)", "")+" is not available for the current LoLPing Version.");
		}
		this.pack();	
	}
}
