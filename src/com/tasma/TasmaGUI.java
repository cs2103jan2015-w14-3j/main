package com.tasma;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.DefaultListSelectionModel;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

public class TasmaGUI extends JFrame implements TasmaUserInterface {

	private static final long serialVersionUID = 7369112773183099080L;
	
	private JPanel contentPane;
	private JTextField textCommand;
	private JTextArea textDisplay = new JTextArea();
	
	private Controller controller;
	private ZebraJList list = new ZebraJList();
	private JScrollPane scrollPane = new JScrollPane();

	/**
	 * Create the frame.
	 */
	public TasmaGUI() {
		setTitle("TASMA");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textCommand = new JTextField("");
		textCommand.setBounds(10, 237, 424, 23);
		contentPane.add(textCommand);
		textCommand.setColumns(10);
		textCommand.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER && !textCommand.getText().trim().equals(""))  {
					textDisplay.setText("");
					String command = textCommand.getText();
					textCommand.setText("");
					controller.executeInput(command);
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
		textDisplay.setEditable(false);
		textDisplay.setBackground(UIManager.getColor("Button.background"));
		textDisplay.setBounds(10, 204, 414, 22);
		contentPane.add(textDisplay);
		list.setSelectionModel(new DisabledItemSelectionModel());
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBounds(10, 10, 424, 186);
		
		contentPane.add(scrollPane);
		scrollPane.setViewportView(list);
	}

	@Override
	public void initialize(Controller controller) throws Exception {
		this.controller = controller;
		this.controller.setUserInterface(this);
	}
	//For disabling the selection capability of the list
	private class DisabledItemSelectionModel extends DefaultListSelectionModel {
		
		private static final long serialVersionUID = 1L;

		@Override
		public void setSelectionInterval(int index0, int index1) {
			super.setSelectionInterval(-1, -1);
		}
	}
	
	/**
	 * Performs a request for focus on the command box
	 */
	public void requestCommandBoxFocus() {
		textCommand.requestFocus();
	}

	@SuppressWarnings("unchecked")
	@Override
	public void displayTasks(Collection<Task> tasks) {
		String text = "";
		String archiveWord = "";
		String[] listTasks = new String[tasks.size()];
		
		Iterator<Task> iterator = tasks.iterator();
		int i = 0;
		while(iterator.hasNext()) {
			Task task = iterator.next();
			
			text = String.format("%d. Task%-5s \n %s", i++, task.getTaskId(), task.getDetails());
			
			if (task.getEndDateTime() != null) {
				text = text.concat(" on " + task.getEndDateTime());
			}
			
			if (task.getLocation().length() != 0) {
				text = text.concat(" at " + task.getLocation());
			}
			
			if (task.isArchived()) {
				archiveWord = " Archived";
			}
			
			text += archiveWord;
			
			listTasks[i++] = text;
		}
		
		list.setListData(listTasks);
		
	}

	@Override
	public void displayMessage(String message) {
		textDisplay.setText(message);
	}
	
	public void editCmdDisplay (String task) {
		textCommand.setText(task);
	}
	
	@SuppressWarnings("unchecked")
	public void helpCmdDisplay (String helpMsg) {
		String[] helpMsgs = new String[1];
		helpMsgs[0] = helpMsg;
		list.setListData(helpMsgs);
	}
}
