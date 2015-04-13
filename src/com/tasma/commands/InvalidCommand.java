/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma.commands;

import com.tasma.TaskCollection;
import com.tasma.UIMessage;
import com.tasma.ui.Palette;
import com.tasma.ui.TasmaUserInterface;

public class InvalidCommand extends AbstractCommand {
	
	private String input = "";

	public InvalidCommand(TasmaUserInterface userInterface,
			TaskCollection collection, String input) {
		super(userInterface, collection);
		this.input = input;
	}

	@Override
	public void execute() throws Exception {
		userInterface.editCmdDisplay(input);
		userInterface.displayMessage(UIMessage.COMMAND_INVALID, Palette.MESSAGE_DANGER);
	}

}
