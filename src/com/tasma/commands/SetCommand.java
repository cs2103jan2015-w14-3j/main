/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma.commands;

import com.tasma.Palette;
import com.tasma.TaskCollection;
import com.tasma.TasmaUserInterface;
import com.tasma.UIMessage;
import com.tasma.config.Config;

public class SetCommand extends AbstractUndoableCommand {
	
	private String key;
	private String previousValue;
	private String newValue;

	public SetCommand(TasmaUserInterface userInterface,
			TaskCollection collection, String key, String value) {
		super(userInterface, collection);
		this.key = key.toLowerCase();
		this.newValue = value;
	}

	@Override
	public void execute() throws Exception {
		Config config = Config.getInstance();
		previousValue = config.getProperty(key);
		if (newValue.equals("")) {
			userInterface.editCmdDisplay(String.format("set %s %s", key, previousValue));
		} else {
			config.setProperty(key, newValue);
			userInterface.displayMessage(String.format(UIMessage.COMMAND_SET_SUCCESS, key), Palette.MESSAGE_SUCCESS);
		}
	}

	@Override
	public void undo() throws Exception {
		Config config = Config.getInstance();
		config.setProperty(key, previousValue);
		userInterface.displayMessage(String.format(UIMessage.COMMAND_SET_UNDO, key), Palette.MESSAGE_SUCCESS);
	}

}
