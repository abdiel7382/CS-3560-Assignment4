package gui;

import javax.swing.*;

import api.*;
import domain.*;

import java.awt.*;
import java.sql.Date;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class OrderGui 
{
	private JFrame frame;
	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 600;

	// Generic item fields
    JTextField numberField;
    JTextField dateField;
    JTextField itemField;
    JTextField priceField;
    
 // Choice field
    ButtonGroup orderGroup;
    
    private JButton createButton, readButton, updateButton, deleteButton;
    
    public OrderGui()
	{
		frame = new JFrame("Customer Management");
		frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// Create components and add them to the JFrame
		JPanel panel = new JPanel();
		panel.setLayout(null);
        
        // Add number components
		JLabel numberLabel = new JLabel("Number ");
        JLabel dateLabel = new JLabel("Date ");
        JLabel itemLabel = new JLabel("Item ");
        JLabel priceLabel = new JLabel("Price ");
		
     // Add number components
        numberLabel.setBounds(350, 175, 100, 25);
        numberField.setBounds(350, 200, 300, 25);
        panel.add(numberLabel);
        panel.add(numberField);
        
        // Add item components
        dateLabel.setBounds(675, 175, 100, 25);
        dateField.setBounds(675, 200, 100, 25);
        panel.add(dateLabel);
        panel.add(dateField);
        
        // Add date components
        itemLabel.setBounds(800, 175, 100, 25);
        itemField.setBounds(800, 200, 100, 25);
        panel.add(itemLabel);
        panel.add(itemField);
        
        // Add price components
        priceLabel.setBounds(675, 225, 100, 25);
        priceField.setBounds(675, 250, 225, 25);
        panel.add(priceLabel);
        panel.add(priceField);  
        

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

        
        // Create order
        createButton.addActionListener(e -> {
        	String number = numberField.getText();
            String date = dateField.getText();
            String item = itemField.getText();
            double price = Double.parseDouble(priceField.getText());
            
            OrderDAO.createOrder(number, date, item, price);
            
            clearFields();
            
            JOptionPane.showMessageDialog(null, "Order: [" + number+ "] successfully added.");
        });
        
        // Read customer
        readButton.addActionListener(e -> {
        	String number = numberField.getText();
        	
        	Order tempOrder = OrderDAO.readOrder(number);
        	
        	if (tempOrder == null)
        	{
        		JOptionPane.showMessageDialog(null, "No Order found!");
        		return;
        	}
        	
        	numberField.setText(String.valueOf(tempOrder.getNumber())); 
        	dateField.setText(tempOrder.getDate());
        	itemField.setText(tempOrder.getItem());  
        	priceField.setText(String.valueOf(tempOrder.getPrice())); 
        	
        });
        
        // Update order
        updateButton.addActionListener(e -> {
        	Order tempOrder = new Order();
            
        	tempOrder.setNumber(numberField.getText()); 
        	tempOrder.setDate(dateField.getText());
        	tempOrder.setItem(itemField.getText());
        	tempOrder.setPrice(Double.parseDouble(priceField.getText())); 
            
            OrderDAO.updateOrder(tempOrder);
            
            clearFields();
            
            JOptionPane.showMessageDialog(null, "Order: [" + tempOrder.getNumber() + "] successfully updated.");
        });
        
        // Delete order
        deleteButton.addActionListener(e -> {
        	Order tempOrder = new Order();
            
            tempOrder.setNumber(numberField.getText()); 
        	tempOrder.setDate(dateField.getText());
            tempOrder.setItem(itemField.getText());
            tempOrder.setPrice(Double.parseDouble(priceField.getText()));
     
            boolean success = OrderDAO.deleteOrder(tempOrder);
            
            if (!success)
            {
            	JOptionPane.showMessageDialog(null, "Order: [" + tempOrder.getNumber() + "] can't be deleted as they have an open loan.");
            	return;
            }
            
            clearFields();
            
            JOptionPane.showMessageDialog(null, "Order: [" + tempOrder.getNumber() + "] successfully deleted.");
        });
    }
    public void showWindow()
	{
		frame.setVisible(true);
	}
    
    public void clearFields()
	{

	    numberField.setText("");
	    dateField.setText("");
	    itemField.setText("");
	    priceField.setText("");

	}
    
    public static void main(String[] args) {
        OrderGui orderGui = new OrderGui();
        orderGui.showWindow();
    }
}



