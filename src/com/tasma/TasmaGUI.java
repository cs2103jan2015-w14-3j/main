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
import javax.swing.JList;
import javax.swing.JPopupMenu;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import org.joda.time.LocalDate;

public class TasmaGUI extends JFrame implements TasmaUserInterface {

	private static final long serialVersionUID = 7369112773183099080L;

	private static final int WINDOW_DEFAULT_WIDTH = 520;
	private static final int WINDOW_DEFAULT_HEIGHT = 200;

	private JPanel contentPane;
	private PlaceholderTextField textCommand;
	private JTextArea textDisplay = new JTextArea();

	private Controller controller;
	private ZebraJList list = new ZebraJList();
	private JScrollPane scrollPane = new JScrollPane();
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
				if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
					thisFrame.setVisible(false);
				} else if (e.getKeyCode() == KeyEvent.VK_ENTER && !textCommand.getText().trim().equals(""))  {
					textDisplay.setVisible(false);
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
		textDisplay.setEditable(false);
		textDisplay.setBackground(Color.WHITE);
		textDisplay.setRows(1);
		textDisplay.setLineWrap(true);
		textDisplay.setVisible(false);
		textDisplay.setWrapStyleWord(true);
		textDisplay.setTabSize(3);
		textDisplay.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.add(textDisplay, BorderLayout.PAGE_END);

		list.setSelectionModel(new DisabledItemSelectionModel());
		list.setCellRenderer(new CustomListRenderer());
		scrollPane.getVerticalScrollBar().setUI(new MyScrollBarUI());
		scrollPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		scrollPane.setPreferredSize(new Dimension(480, 220));

		contentPane.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(list);
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
		LinkedList<Object> listFloating = new LinkedList<Object>();
		LinkedList<Object> listOverdue = new LinkedList<Object>();
		LinkedList<Object> listToday = new LinkedList<Object>();
		LinkedList<Object> listTomorrow = new LinkedList<Object>();
		LinkedList<Object> listRemaining = new LinkedList<Object>();

		LocalDate dateNow = new LocalDate();
		LocalDate dateTmr = dateNow.plusDays(1);
		int taskIndex = 0;
		for (Task task: tasks) {
			Map.Entry<Integer, Task> entry = new AbstractMap.SimpleEntry<Integer, Task>(taskIndex, task);
			if (task.getStartDateTime() == null) {
				listFloating.add(entry);
			} else if (task.getEndDateTime().isBeforeNow()) {
				listOverdue.add(entry);
			} else if (task.getEndDateTime().equals(dateNow)) {
				listToday.add(entry);
			} else if (task.getEndDateTime().equals(dateTmr)) {
				listTomorrow.add(entry);
			} else {
				listRemaining.add(entry);
			}
			++taskIndex;
		}

		LinkedList<Object> finalList = new LinkedList<Object>();
		if (listFloating.size() > 0) {
			listFloating.add(0, String.format(UIMessage.SECTION_HEADER_FLOATING, listFloating.size()));
			finalList.addAll(listFloating);
		}
		if (listOverdue.size() > 0) {
			listOverdue.add(0, String.format(UIMessage.SECTION_HEADER_OVERDUE, listOverdue.size()));
			finalList.addAll(listOverdue);
		}
		if (listToday.size() > 0) {
			listToday.add(0, String.format(UIMessage.SECTION_HEADER_TODAY, listToday.size()));
			finalList.addAll(listToday);
		}

		if (listTomorrow.size() > 0) {
			listTomorrow.add(0, String.format(UIMessage.SECTION_HEADER_TOMORROW, listTomorrow.size()));
			finalList.addAll(listTomorrow);
		}

		if (listRemaining.size() > 0) {
			listRemaining.add(0, String.format(UIMessage.SECTION_HEADER_REMAINING, listRemaining.size()));
			finalList.addAll(listRemaining);
		}
		
		// since the list is empty, we should show some message to indicate
		if (finalList.size() == 0) {
			finalList.add(UIMessage.TASK_LIST_EMPTY);
		}

		list.setVisibleRowCount(Math.min(5, finalList.size()));;
		list.setListData(finalList.toArray());
		updateWindowHeight();
	}

	@Override
	public void displayMessage(String message) {
		displayMessage(message, Palette.MESSAGE_DEFAULT);
	}

	@Override
	public void displayMessage(String message, Color color) {
		if (message.equals("")) {
			textDisplay.setVisible(false);
		} else {
			textDisplay.setText(message);
			textDisplay.setForeground(color);
			textDisplay.setVisible(true);
		}
		updateWindowHeight();
	}

	public void editCmdDisplay (String task) {
		textCommand.setText(task);
	}

	protected void updateWindowHeight() {
		int height = WINDOW_DEFAULT_HEIGHT;
		height += list.getPreferredScrollableViewportSize().height;
		if (textDisplay.isVisible()) {
			height += textDisplay.getPreferredSize().height;
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

		@SuppressWarnings("serial")
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

			    JTextArea textIndex = new JTextArea();
			    textIndex.setBorder(new EmptyBorder(5, 5, 5, 5));
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
			}
	        return panel;
	    }
	}
}
