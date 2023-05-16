package gui;

import api.*;
import domain.*;

import javax.swing.*;
import java.sql.Date;
import java.util.List;

public class AddressGui
{
	private JFrame frame;
	private static final int WINDOW_WIDTH = 1000;
	private static final int WINDOW_HEIGHT = 600;
	
	// Generic item fields
    JTextField streetField;
    JTextField cityField;
    JTextField stateField;
    JTextField zip_codeField;
    
    // Choice field
    ButtonGroup addressGroup;
    
	public AddressGui()
	{
		frame = new JFrame("Item Management");
		frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		// Create components and add them to the JFrame
		JPanel panel = new JPanel();
		panel.setLayout(null);
        
        // Create labels
        JLabel streetLabel = new JLabel("Street ");
        JLabel cityLabel = new JLabel("City ");
        JLabel stateLabel = new JLabel("State ");
        JLabel zip_codeLabel = new JLabel("Zip_Code ");
        
        
        // Create text fields
        streetField = new JTextField(20);
        cityField = new JTextField(20);
        stateField = new JTextField(20);
        zip_codeField = new JTextField(20);

        // Create buttons
        JButton createButton = new JButton("Create");
        JButton readButton = new JButton("Read");
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");
        
        // Add number components
        streetLabel.setBounds(350, 175, 100, 25);
        streetField.setBounds(350, 200, 300, 25);
        panel.add(streetLabel);
        panel.add(streetField);
        
        // Add item components
        cityLabel.setBounds(675, 175, 100, 25);
        cityField.setBounds(675, 200, 100, 25);
        panel.add(cityLabel);
        panel.add(cityField);
        
        // Add date components
        stateLabel.setBounds(800, 175, 100, 25);
        stateField.setBounds(800, 200, 100, 25);
        panel.add(stateLabel);
        panel.add(stateField);
        
        // Add price components
        zip_codeLabel.setBounds(675, 225, 100, 25);
        zip_codeField.setBounds(675, 250, 225, 25);
        panel.add(zip_codeLabel);
        panel.add(zip_codeField);      
        
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

        
        // Create item
        createButton.addActionListener(e -> {
        	String street = streetField.getText();
            String city = cityField.getText();
            String state = stateField.getText();
            String zip_code = zip_codeField.getText();;
            
        });
        
        // read item
        readButton.addActionListener(e -> {
        	String title = streetField.getText();
        	
        	clearFields();
        	
        });
        
        // Update item
        updateButton.addActionListener(e -> {
        	
        
        });
        
        // Delete item
        deleteButton.addActionListener(e -> {
        	
           
        });
    }
	
	public void showWindow()
	{
		frame.setVisible(true);
	}
	
	public void clearFields()
	{

	    streetField.setText("");
	    cityField.setText("");
	    stateField.setText("");
	    zip_codeField.setText("");

	}

	
	public static void main(String[] args)
	{
		AddressGui addressGui = new AddressGui();
		addressGui.showWindow();
	}
}