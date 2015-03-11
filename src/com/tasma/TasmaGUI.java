package com.tasma;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

public class TasmaGUI extends JFrame implements TasmaUserInterface {

	private static final long serialVersionUID = 7369112773183099080L;
	
	private JPanel contentPane;
	private JTextField textCommand;
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
		
		textCommand = new JTextField("");
		textCommand.setBounds(10, 237, 414, 23);
		contentPane.add(textCommand);
		textCommand.setColumns(10);
		textCommand.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER && !textCommand.getText().trim().equals(""))  {
					textDisplay.setText("");
					controller.executeInput(textCommand.getText());
					textCommand.setText("");
				}
				
			}

			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 11, 414, 189);
		contentPane.add(scrollPane);
		
		textTasks.setLineWrap(true);
		textTasks.setEditable(false);
		scrollPane.setViewportView(textTasks);
		textDisplay.setEditable(false);
		
		textDisplay.setBackground(UIManager.getColor("Button.background"));
		textDisplay.setBounds(10, 204, 414, 22);
		contentPane.add(textDisplay);
	}

	@Override
	public void initialize() throws Exception {
		controller.setUserInterface(this);
		controller.initialize();
		controller.executeInput("list");
	}
	
	/**
	 * Performs a request for focus on the command box
	 */
	public void requestCommandBoxFocus() {
		textCommand.requestFocus();
	}

	@Override
	public void displayTasks(Collection<Task> tasks) {
		String text = "";
		
		Iterator<Task> iterator = tasks.iterator();
		int i = 0;
		while(iterator.hasNext()) {
			Task task = iterator.next();
			text = text.concat((++i) + ". " + task.getTaskId() + " " + task.getDetails() + "on" + task.getEndDateTime()
					+ "at" + task.getLocation() + "\n");
		}
		
		textTasks.setText(text);
		
	}

	@Override
	public void displayMessage(String message) {
		textDisplay.setText(message);
	}
}
