import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
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
		this.pack();
		addListeners();
		this.setVisible(true);
	}


	private Container mainPanel() {
		JPanel panel = new JPanel(new GridLayout(2,4));
		panel.setBorder(BorderFactory.createEmptyBorder(40, 20, 40, 20));
		
		serverList = new JComboBox<String>();
		serverList.addItem("EUW    ");
		
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
				String ip;
				switch(serverList.getSelectedIndex()){
				case 0:
					ip= "185.40.65.1";
					break;
				default:
						ip="null";
						break;
				}
				
				Ping ping= new Ping();
				
				System.out.println("Ping: "+ ping.getPing(ip, (numberOfPackages.getSelectedIndex()+1)));
			}
		});
	}
}
