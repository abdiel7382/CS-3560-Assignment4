package gui;

import api.*;
import domain.*;

import javax.swing.*;

public class CustomerGUI
{
	private JFrame frame;
	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 600;
	
	JTextField nameField;
	JTextField phoneField;
    JTextField emailField;
	
	public CustomerGUI()
	{
		frame = new JFrame("Customer Management");
		frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// Create components and add them to the JFrame
		JPanel panel = new JPanel();
		panel.setLayout(null);
        
        // Create labels
        JLabel phoneLabel = new JLabel("Phone");
		JLabel emailLabel = new JLabel("Email");
        JLabel nameLabel = new JLabel("Name");
        
        // Create text fields
        phoneField = new JTextField(20);
        emailField = new JTextField(20);
        nameField = new JTextField(20);
        
        // Create buttons
        JButton createButton = new JButton("Create");
        JButton readButton = new JButton("Read");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        
        // Add library id components
        phoneLabel.setBounds(25, 25, 100, 25);
        phoneField.setBounds(25, 50, 300 ,25);
        panel.add(phoneLabel);
        panel.add(phoneField);
        
        // Add name components
        nameLabel.setBounds(25, 100, 100, 25);
        nameField.setBounds(25, 125, 925, 25);
        panel.add(nameLabel);
        panel.add(nameField);
        
        // Add broncoId components
        emailLabel.setBounds(25, 175, 100, 25);
        emailField.setBounds(25, 200, 300, 25);
        panel.add(emailLabel);
        panel.add(emailField);

        // Add buttons to panel
        createButton.setBounds(575, 500, 75, 30);
        readButton.setBounds(675, 500, 75, 30);
        updateButton.setBounds(775, 500, 75, 30);
        deleteButton.setBounds(875, 500, 75, 30);
        panel.add(createButton);
        panel.add(readButton);
        panel.add(updateButton);
        panel.add(deleteButton);
        
        frame.getContentPane().add(panel);

        
        // Create customer
        createButton.addActionListener(e -> {
        	String phone = phoneField.getText();
            String name = nameField.getText();
            String email = emailField.getText();
            
            CustomerDAO.createCustomer(phone, name, email);
            
            clearFields();
            
            JOptionPane.showMessageDialog(null, "Customer: [" + name + "] successfully added.");
        });
        
        // Read customer
        readButton.addActionListener(e -> {
        	String name = nameField.getText();
        	
        	Customer tempCustomer = CustomerDAO.readCustomer(name);
        	
        	if (tempCustomer == null)
        	{
        		JOptionPane.showMessageDialog(null, "No customer found!");
        		return;
        	}
        	
        	phoneField.setText(String.valueOf(tempCustomer.getPhone())); 
        	emailField.setText(tempCustomer.getEmail());
        	nameField.setText(tempCustomer.getName());   	
        });
        
        // Update customer
        updateButton.addActionListener(e -> {
        	Customer tempCustomer = new Customer();
            
        	tempCustomer.setPhone(phoneField.getText()); 
        	tempCustomer.setEmail(emailField.getText());
        	tempCustomer.setName(nameField.getText());
            
            CustomerDAO.updateCustomer(tempCustomer);
            
            clearFields();
            
            JOptionPane.showMessageDialog(null, "Customer: [" + tempCustomer.getName() + "] successfully updated.");
        });
        
        // Delete customer
        deleteButton.addActionListener(e -> {
        	Customer tempCustomer = new Customer();
            
            tempCustomer.setPhone(phoneField.getText()); 
        	tempCustomer.setEmail(emailField.getText());
            tempCustomer.setName(nameField.getText());
     
            boolean success = CustomerDAO.deleteCustomer(tempCustomer);
            
            if (!success)
            {
            	JOptionPane.showMessageDialog(null, "Customer: [" + tempCustomer.getName() + "] can't be deleted as they have an open loan.");
            	return;
            }
            
            clearFields();
            
            JOptionPane.showMessageDialog(null, "Customer: [" + tempCustomer.getName() + "] successfully deleted.");
        });
    }
	
	public void showWindow()
	{
		frame.setVisible(true);
	}
	
	public void clearFields()
	{
		phoneField.setText("");
		emailField.setText("");
        nameField.setText("");
	}

	
	public static void main(String[] args)
	{
		CustomerGUI customerGUI = new CustomerGUI();
		customerGUI.showWindow();
	}
}