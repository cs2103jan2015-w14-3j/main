package com.tasma;

import com.tasma.*;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.util.ArrayList;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

public class TasmaGUI extends JFrame implements TasmaUserInterface {

	private static final long serialVersionUID = 7369112773183099080L;
	
	private JPanel contentPane;
	private JTextField textField;
	
	private Controller controller = new Controller();

	/**
	 * Create the frame.
	 */
	public TasmaGUI() {
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
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 11, 414, 189);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);
	}

	@Override
	public void initialize() throws Exception {
		controller.setUserInterface(this);
		controller.initialize();
	}

	@Override
	public void displayTasks(ArrayList<Task> tasks) {
		for (int i=0; i<tasks.size(); i++) {
			System.out.println((i+1) + ". " + tasks.get(i).getTaskId() + " " + tasks.get(i).getDetails());
		}
	}

	@Override
	public void displayMessage(String message) {
		
	}
}
