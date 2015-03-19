package com.tasma.commands;

import com.tasma.TaskCollection;
import com.tasma.TasmaUserInterface;
import com.tasma.UIMessage;

public class InvalidCommand extends AbstractCommand {

	public InvalidCommand(TasmaUserInterface userInterface,
			TaskCollection collection) {
		super(userInterface, collection);
	}

	@Override
	public void execute() throws Exception {
		userInterface.displayMessage(UIMessage.COMMAND_INVALID);
	}

}
