package com.tasma;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

import java.awt.Color;
import java.awt.Component;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.Collection;
import java.util.Iterator;

import javax.swing.DefaultListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.ScrollPaneConstants;

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
	@SuppressWarnings("unchecked")
	public TasmaGUI() {
		decorateFrame();
		addWindowEvents();
		
		contentPane = new JPanel();
		//contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setBackground(Color.WHITE);
		contentPane.setLayout(null);
		setContentPane(contentPane);
		
		textCommand = new JTextField();
		textCommand.setBorder(new EmptyBorder(5, 5, 5, 5));
		textCommand.setBounds(0, 262, 480, 30);
		textCommand.setBackground(new Color(41, 171, 226));
		textCommand.setForeground(Color.WHITE);
		textCommand.setCaretColor(Color.WHITE);
		textCommand.setFont(textCommand.getFont().deriveFont(14.0f));
		contentPane.add(textCommand);
		textCommand.setColumns(10);
		JFrame thisFrame = this;
		textCommand.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					thisFrame.setVisible(false);
				} else if (e.getKeyCode() == KeyEvent.VK_ENTER && !textCommand.getText().trim().equals(""))  {
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
		textDisplay.setBackground(Color.WHITE);
		textDisplay.setBounds(10, 210, 460, 22);
		contentPane.add(textDisplay);
		
		list.setSelectionModel(new DisabledItemSelectionModel());
		list.setCellRenderer(new CustomListRenderer());
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		scrollPane.setBounds(6, 6, 460, 200);
		
		contentPane.add(scrollPane);
		scrollPane.setViewportView(list);
	}
	
	//@author A0132763
	private void decorateFrame() {
		setTitle("TASMA");
		setIconImage(createImage("res/logo.png", "icon"));
		setResizable(false);
		// must use HIDE on CLOSE for the TrayIcon to work properly
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setBounds(0, 0, 480, 320);
		
		// sets the window to center of the screen
		setLocationRelativeTo(null);
	}
	
	private void addWindowEvents() {
		JFrame thisFrame = this;
		this.addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
                textCommand.requestFocus();
			}
			
			@SuppressWarnings("deprecation")
			public void windowDeactivated(WindowEvent e) {
                thisFrame.hide();
			}
		});
	}

	@Override
	public void initialize(Controller controller) throws Exception {
		this.controller = controller;
		this.controller.setUserInterface(this);
	}
	
	public class CustomListRenderer implements ListCellRenderer<Object> {

		   @Override
		   public Component getListCellRendererComponent(JList<?> list, Object value, int index,
		        boolean isSelected, boolean cellHasFocus) {

		        JTextArea renderer = new JTextArea(3,10);
		        renderer.setText(value.toString());
		        renderer.setLineWrap(true);
		        return renderer;
		   }
		}

	//For disabling the selection capability of the list
	private class DisabledItemSelectionModel extends DefaultListSelectionModel {
		
		private static final long serialVersionUID = 1L;

		@Override
		public void setSelectionInterval(int index0, int index1) {
			super.setSelectionInterval(-1, -1);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void displayTasks(Collection<Task> tasks) {
		String[] listTasks = new String[tasks.size()];
		
		Iterator<Task> iterator = tasks.iterator();
		int i = 0;
		while(iterator.hasNext()) {
			Task task = iterator.next();
			
			String text = String.format("%d. %s\n", ++i, task.getDetails());
			
			if (task.getEndDateTime() != null) {
				text = text.concat("Date: " + task.getStringEndDateTime() + "\n");
			}
			
			if (task.getLocation().length() != 0) {
				text = text.concat("Location: " + task.getLocation() + "\n");
			}
			
			if (task.isArchived()) {
				text += " Archived";
			}
			
			if (task.isDone()) {
				text += " Done";
			}
			
			listTasks[i-1] = text;
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
	
	 //Obtain the image URL
    protected static Image createImage(String path, String description) {
        URL imageURL = TrayIcon.class.getResource(path);
         
        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }
    
    @SuppressWarnings("deprecation")
	@Override
    public void show() {
    	setExtendedState(JFrame.NORMAL);
    	super.show();
    	toFront();
    	repaint();
    }
}

