/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma.commands;

import com.tasma.Palette;
import com.tasma.TaskCollection;
import com.tasma.TasmaUserInterface;
import com.tasma.UIMessage;

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
