package com.tasma;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;

public class TasmaUI extends JFrame {

	private static final long serialVersionUID = 7369112773183099080L;
	
	private JPanel contentPane;
	private JTextField textField;
	private JPanel panel;
	
	private TasmaController controller;

	/**
	 * Create the frame.
	 */
	public TasmaUI(TasmaController controller) {
		this.controller = controller;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setText("Input task here");
		textField.setBounds(10, 216, 322, 23);
		contentPane.add(textField);
		textField.setColumns(10);
	
	
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.setBounds(335, 216, 89, 23);
		contentPane.add(btnNewButton);
		
		panel = new JPanel();
		panel.setBounds(10, 11, 414, 188);
		contentPane.add(panel);
	}
}
