package com.tasma;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.awt.Button;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import javax.swing.JList;
import javax.swing.JButton;


public class TasmaUI extends JFrame {

	private JPanel contentPane;
	private JTextField txtInputTaskHere;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TasmaUI frame = new TasmaUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TasmaUI() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtInputTaskHere = new JTextField();
		txtInputTaskHere.setForeground(Color.BLACK);
		txtInputTaskHere.setBounds(10, 197, 414, 23);
		txtInputTaskHere.setText("Input task here");
		contentPane.add(txtInputTaskHere);
		txtInputTaskHere.setColumns(10);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 414, 175);
		contentPane.add(scrollPane);
		
		JList list = new JList();
		scrollPane.setViewportView(list);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.setBounds(111, 229, 89, 23);
		contentPane.add(btnDelete);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.setBounds(10, 229, 89, 23);
		contentPane.add(btnSubmit);
	}
}
