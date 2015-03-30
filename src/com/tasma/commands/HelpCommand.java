/**
 * Tasma Task Manager
 */
//@author A0132763
package com.tasma.commands;

import com.tasma.HelpMessage;
import com.tasma.TaskCollection;
import com.tasma.TasmaUserInterface;

public class HelpCommand extends AbstractCommand {

	protected CommandType command;
	
	public HelpCommand(TasmaUserInterface userInterface,
			TaskCollection collection, String command) {
		super(userInterface, collection);
		this.command = CommandIdentifier.normalize(command);
	}

	@Override
	public void execute() throws Exception {

	}

}
