/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma.ui;

import java.awt.Point;
import java.util.HashMap;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

import com.tasma.HelpMessage;
import com.tasma.InvalidInputException;
import com.tasma.Palette;
import com.tasma.commands.AliasHandler;
import com.tasma.commands.InputSplitter;
import com.tasma.commands.CommandType;

/**
 * The yellow hint frame that shows up for the user as they type.
 * Used in TasmaGUI
 */
@SuppressWarnings("serial")
public class CommandHintFrame extends JFrame {
	
	/**
	 * The frame's opacity
	 */
	//private static final float FRAME_OPACITY = 0.75f;

	/**
	 * The output text area
	 */
	private JTextArea labelOutput = new JTextArea();
	
	/**
	 * A mapping of a command to its hint message
	 */
	private HashMap<CommandType, String> hintMapping = new HashMap<CommandType, String>();
	{
		hintMapping.put(CommandType.ADD, HelpMessage.HINT_ADD);
		hintMapping.put(CommandType.SEARCH, HelpMessage.HINT_SEARCH);
		hintMapping.put(CommandType.LIST, HelpMessage.HINT_LIST);
		hintMapping.put(CommandType.EDIT, HelpMessage.HINT_EDIT);
		hintMapping.put(CommandType.MARK, HelpMessage.HINT_MARK);
		hintMapping.put(CommandType.DELETE, HelpMessage.HINT_DELETE);
		hintMapping.put(CommandType.SET, HelpMessage.HINT_SET);
		hintMapping.put(CommandType.ALIAS, HelpMessage.HINT_ALIAS);
		hintMapping.put(CommandType.HELP, HelpMessage.HINT_HELP);
		hintMapping.put(CommandType.TUTORIAL, HelpMessage.HINT_TUTORIAL);
		hintMapping.put(CommandType.EXIT, HelpMessage.HINT_EXIT);
	}
	
	public CommandHintFrame() {
		decorateFrame();
		buildLabel();
	}
	
	/**
	 * Decorate everything!
	 */
	protected void decorateFrame() {
		this.setTitle("");
		this.setAlwaysOnTop(true);
		this.setAutoRequestFocus(false);
		this.setUndecorated(true);
		//this.setOpacity(FRAME_OPACITY);
		this.setSize(400, 22);
        setFocusable(false);
        setFocusableWindowState(false);
	}
	
	/**
	 * Decorate and build the label
	 */
	protected void buildLabel() {
		labelOutput.setEditable(false);
		labelOutput.setOpaque(true);
		labelOutput.setBackground(Palette.HINT_FRAME_BACKGROUND);
		labelOutput.setBorder(new EmptyBorder(10, 10, 10, 10));
		this.add(labelOutput);
	}
	
	/**
	 * Show the window if the input has a command
	 * @param input The input string to parse
	 * @param component The text box to attach the frame next to
	 * @throws InvalidInputException
	 */
	public void checkHasHint(String input, JComponent component) throws InvalidInputException {
		InputSplitter splitter = new InputSplitter(input);
		String command = splitter.next();
		CommandType type = AliasHandler.normalize(command);
		String hint = hintMapping.get(type);
		if (hint != null) {
			labelOutput.setText(hint);
			// resize the frame according to the label's preferred size so that
			// it fits
			this.setSize(labelOutput.getPreferredSize());
			Point location = component.getLocationOnScreen();
			location.move(location.x + 20, location.y + component.getHeight());
			this.setLocation(location);
			this.setVisible(true);
		} else {
			this.setVisible(false);
		}
	}

	/**
	 * Close the window
	 */
	public void close() {
		this.setVisible(false);
	}
}
