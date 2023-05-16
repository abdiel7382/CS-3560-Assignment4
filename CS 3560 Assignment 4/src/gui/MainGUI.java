package gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainGUI {
    private JFrame frame;
    private JPanel panel;

    private JButton addressButton;
    private JButton orderButton;
    private JButton customerButton;

    public MainGUI() {
        frame = new JFrame("Assignment 4 System");
        frame.setSize(500, 500);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();

        addressButton = new JButton("Address");
        addressButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddressGui addressGUI = new AddressGui();
                addressGUI.showWindow();
            }
        });

        orderButton = new JButton("Order");
        orderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                OrderGui orderGui = new OrderGui();
                orderGui.showWindow();
            }
        });

        customerButton = new JButton("Customer");
        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerGUI customerGUI = new CustomerGUI();
                customerGUI.showWindow();
            }
        });

        panel.add(orderButton);
        panel.add(addressButton);
        panel.add(customerButton);

        frame.add(panel);

        frame.setVisible(true);
    }

    public void showWindow()
	{
		frame.setVisible(true);
	}
    
    public static void main(String[] args) {
        MainGUI mainGUI = new MainGUI();
        mainGUI.showWindow();
    }
}