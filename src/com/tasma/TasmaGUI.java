package com.tasma;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import java.util.AbstractMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.DefaultListSelectionModel;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.KeyStroke;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

import org.joda.time.LocalDate;

public class TasmaGUI extends JFrame implements TasmaUserInterface {

	private static final long serialVersionUID = 7369112773183099080L;

	private static final int WINDOW_DEFAULT_WIDTH = 520;
	private static final int WINDOW_DEFAULT_HEIGHT = 200;
	private static final int MAX_TASK_DISPLAY_COUNT = 6;

	private JPanel contentPane;
	private PlaceholderTextField textCommand;
	private JTextArea textMessage = new JTextArea();

	private Controller controller;
	private ZebraJList listTasks = new ZebraJList();
	private JScrollPane listTasksScrollPane = new JScrollPane();
	private JPopupMenu popupCmdHint;

	/**
	 * Create the frame.
	 */
	@SuppressWarnings("unchecked")
	public TasmaGUI() {
		decorateFrame();
		addWindowEvents();

		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		BorderLayout contentPaneLayout = new BorderLayout();
		contentPane.setLayout(contentPaneLayout);
		setContentPane(contentPane);

		textCommand = new PlaceholderTextField();
		textCommand.setPlaceholder("What would you like to do?");
		textCommand.setPlaceholderColor(Palette.PLACEHOLDER_TEXT);
		textCommand.setBorder(new EmptyBorder(10, 10, 10, 10));
		textCommand.setSize(new Dimension(480, 40));
		textCommand.setBackground(Palette.THEME_BLUE);
		textCommand.setForeground(Color.WHITE);
		textCommand.setCaretColor(Color.WHITE);
		textCommand.setFont(textCommand.getFont().deriveFont(14.0f));
		textCommand.setDocument(new JTextFieldLimit(70));
		contentPane.add(textCommand, BorderLayout.PAGE_START);
		textCommand.setColumns(10);
		JFrame thisFrame = this;
		textCommand.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) { // Pressing the ESC key
					thisFrame.setVisible(false);
				} else if (e.getKeyCode() == KeyEvent.VK_ENTER && !textCommand.getText().trim().equals(""))  { // Pressing the ENTER key
					textMessage.setVisible(false);
					thisFrame.setSize(WINDOW_DEFAULT_WIDTH, WINDOW_DEFAULT_HEIGHT);
					String command = textCommand.getText();
					textCommand.setText("");
					controller.executeInput(command);
				}

			}

			@Override
			public void keyTyped(KeyEvent e) {}

			@Override
			public void keyReleased(KeyEvent e) {}
		});
		textMessage.setEditable(false);
		textMessage.setBackground(Color.WHITE);
		textMessage.setRows(1);
		textMessage.setLineWrap(true);
		textMessage.setVisible(false);
		textMessage.setWrapStyleWord(true);
		textMessage.setTabSize(3);
		textMessage.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(textMessage, BorderLayout.PAGE_END);

		listTasks.setSelectionModel(new DisabledItemSelectionModel());
		listTasks.setCellRenderer(new CustomListRenderer());
		listTasksScrollPane.getVerticalScrollBar().setUI(new MyScrollBarUI());
		listTasksScrollPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		listTasksScrollPane.setPreferredSize(new Dimension(480, 220));
		
		// provide scrolling support for list scroll pane's scroll bar
		JScrollBar vertical = listTasksScrollPane.getVerticalScrollBar();
		InputMap im = vertical.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		im.put(KeyStroke.getKeyStroke("DOWN"), "positiveUnitIncrement");
		im.put(KeyStroke.getKeyStroke("UP"), "negativeUnitIncrement");

		contentPane.add(listTasksScrollPane, BorderLayout.CENTER);
		listTasksScrollPane.setViewportView(listTasks);
	}

	//@author A0132763
	private void decorateFrame() {
		setTitle("TASMA");
		setIconImage(createImage("res/logo.png", "icon"));
		setAlwaysOnTop(true);
		// must use HIDE on CLOSE for the TrayIcon to work properly
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		updateWindowHeight();

		// sets the window to center of the screen
		setLocationRelativeTo(null);
		this.setLocation(this.getLocation().x, (int)(0.5*this.getLocation().y));
	}

	private void addWindowEvents() {
		JFrame thisFrame = this;
		this.addWindowListener(new WindowAdapter() {
			public void windowActivated(WindowEvent e) {
                textCommand.requestFocus();
			}
		});
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

	@SuppressWarnings("unchecked")
	@Override
	public void displayTasks(List<Task> tasks) {
		List<Object> finalList = UITaskListSorter.sort(tasks);

		listTasks.setVisibleRowCount(Math.min(MAX_TASK_DISPLAY_COUNT, finalList.size()));;
		listTasks.setListData(finalList.toArray());
		updateWindowHeight();
	}

	@Override
	public void displayMessage(String message) {
		displayMessage(message, Palette.MESSAGE_DEFAULT);
	}

	@Override
	public void displayMessage(String message, Color color) {
		if (message.equals("")) {
			textMessage.setVisible(false);
		} else {
			textMessage.setText(message);
			textMessage.setForeground(color);
			textMessage.setVisible(true);
		}
		updateWindowHeight();
	}

	public void editCmdDisplay (String task) {
		textCommand.setText(task);
	}

	protected void updateWindowHeight() {
		int height = WINDOW_DEFAULT_HEIGHT;
		height += listTasks.getPreferredScrollableViewportSize().height;
		if (textMessage.isVisible()) {
			height += textMessage.getPreferredSize().height;
		}
		setSize(WINDOW_DEFAULT_WIDTH, height);
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
    	super.show();
    	setState(NORMAL);
    	toFront();
    }

	public class CustomListRenderer implements ListCellRenderer<Object> {

		@Override
		public Component getListCellRendererComponent(JList<?> list, Object value, int index,
			boolean isSelected, boolean cellHasFocus) {
		    JPanel panel = new JPanel();
			if (value instanceof String) {
				JTextArea textSectionHeader = new JTextArea();
				textSectionHeader.setText(value.toString());
				textSectionHeader.setFont(textSectionHeader.getFont().deriveFont(20.0f));
				textSectionHeader.setBackground(null);
				panel.add(textSectionHeader);
			} else if (value instanceof Map.Entry) {
				@SuppressWarnings("unchecked")
				Map.Entry<Integer, Task> entry = (Map.Entry<Integer, Task>)value;
				int taskIndex = entry.getKey();
			    Task task = entry.getValue();

			    GridBagLayout layout = new GridBagLayout();
			    panel.setLayout(layout);

			    GridBagConstraints c = new GridBagConstraints();
			    
			    LocalDate dateNow = new LocalDate();
			    LocalDate dateTmr = dateNow.plusDays(1);

			    JTextArea textIndex = new JTextArea();
			    textIndex.setBorder(new EmptyBorder(5, 5, 5, 5));
			    if (task.getEndDateTime().equals(null))
			    	textIndex.setForeground(Color.GREEN);
			    else if (task.getEndDateTime().equals(dateNow))
			    	textIndex.setForeground(Color.RED);
			    else if (task.getEndDateTime().equals(dateTmr))
			    	textIndex.setForeground(Color.ORANGE);
			    textIndex.setText(Integer.toString(taskIndex + 1));
			    textIndex.setBackground(null);
			    c.fill = GridBagConstraints.HORIZONTAL;
			    c.gridx = 0;
			    c.gridy = 0;
			    panel.add(textIndex, c);

			    JTextArea textDetails = new JTextArea();
			    textDetails.setBorder(new EmptyBorder(5, 5, 5, 5));
			    textDetails.setText(task.getDetails());
			    textDetails.setLineWrap(true);
			    textDetails.setBackground(null);
			    textDetails.setFont(textDetails.getFont().deriveFont(16.0f));
			    c = new GridBagConstraints();
		        c.fill = GridBagConstraints.HORIZONTAL;
		        c.gridx = 1;
		        c.gridy = 0;
		        c.weightx = 1;
		        panel.add(textDetails, c);

		        JTextArea textDateTime = new JTextArea();
		        textDateTime.setBorder(new EmptyBorder(5, 5, 5, 5));
		        textDateTime.setText(task.getStringStartDateTime());
		        textDateTime.setLineWrap(true);
		        textDateTime.setBackground(null);
		        textDateTime.setFont(textDateTime.getFont().deriveFont(12.0f));
		        c = new GridBagConstraints();
		        c.fill = GridBagConstraints.HORIZONTAL;
		        c.gridx = 1;
		        c.gridy = 1;
		        panel.add(textDateTime, c);
		        
		        JCheckBox mkDone = new JCheckBox();
		        if (task.isDone())
		        	mkDone.setSelected(true);
		        else mkDone.setSelected(false);
		        c = new GridBagConstraints();
		        c.fill = GridBagConstraints.HORIZONTAL;
		        c.gridx = 2;
		        c.gridy = 0;
		        panel.add(mkDone, c);
		        
			}
	        return panel;
	    }
	}
}
