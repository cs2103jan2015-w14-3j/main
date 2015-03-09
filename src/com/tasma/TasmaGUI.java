package com.tasma;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import java.util.ArrayList;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import java.awt.Color;
import javax.swing.UIManager;

public class TasmaGUI extends JFrame implements TasmaUserInterface {

	private static final long serialVersionUID = 7369112773183099080L;
	
	private JPanel contentPane;
	private JTextField textField;
	private JTextArea textTasks = new JTextArea();
	private JTextArea textDisplay = new JTextArea();
	
	private Controller controller = new Controller();

	/**
	 * Create the frame.
	 */
	public TasmaGUI() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField("Input task here");
		textField.setBounds(10, 237, 414, 23);
		contentPane.add(textField);
		textField.setColumns(10);
		String command = textField.getText();
		controller.executeInput(command);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 11, 414, 189);
		contentPane.add(scrollPane);
		
		textTasks.setLineWrap(true);
		textTasks.setEditable(false);
		scrollPane.setViewportView(textTasks);
		
		textDisplay.setBackground(UIManager.getColor("Button.background"));
		textDisplay.setBounds(10, 204, 414, 22);
		contentPane.add(textDisplay);
	}

	@Override
	public void initialize() throws Exception {
		controller.setUserInterface(this);
		controller.initialize();
	}

	@Override
	public void displayTasks(ArrayList<Task> tasks) {
		String text = "";
		
		for (int i=0; i<tasks.size(); i++) {
			text.concat((i+1) + ". " + tasks.get(i).getTaskId() + " " + tasks.get(i).getDetails() + "\n");
		}
		
		textTasks.setText(text);
		
	}
	

	@Override
	public void displayMessage(String message) {
		textDisplay.setText(message);
	}
}
