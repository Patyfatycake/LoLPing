package mayuso.LoLPing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JFrame {

    private static final long serialVersionUID = 1L;
    private JButton start;
    private JComboBox<String> serverList;
    private JLabel pingLabel = new JLabel();

    public UI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocation(100, 100);
        setContentPane(mainPanel());
        setTitle("LoLPing v0.1.1");
        pack();
        addListeners();
        setVisible(true);
    }

    private Container mainPanel() {
        JPanel panel = new JPanel(new GridLayout(1, 2));
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
        JPanel panel = new JPanel(new GridLayout(3, 1));

        panel.setBorder(BorderFactory.createEmptyBorder(40, 20, 40, 20));

        serverList = new JComboBox(LoLServers.values());
        start = new JButton("START");

        panel.add(serverList);
        panel.add(start);
        return panel;
    }


    private void addListeners() {

        start.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                start();
            }
        });
    }

    private void start() {
        LoLServers server = LoLServers.valueOf(serverList.getSelectedItem().toString());

        Ping ping = new Ping();
        int pingValue = ping.getPing(server.getIp());
        pingLabel.setText("<html>Ping(" + String.valueOf(serverList.getSelectedItem()).replace(" ", "") + "): ");

        if (pingValue < 30) {
            setTextWithColor(pingValue, "blue");
        } else if (pingValue < 75) {
            setTextWithColor(pingValue, "green");
        } else if (pingValue < 125) {
            setTextWithColor(pingValue, "yellow");
        } else {
            setTextWithColor(pingValue, "red");
        }
        this.pack();
    }

    private void setTextWithColor(int pingValue, String color) {
        pingLabel.setText(pingLabel.getText() + "<font color='" + color + "'>" + String.valueOf(pingValue) + "</font></html>");
    }
}
