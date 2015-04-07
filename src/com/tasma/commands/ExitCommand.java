/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma.commands;

import com.tasma.TaskCollection;
import com.tasma.TasmaUserInterface;

class ExitCommand extends AbstractCommand {

	public ExitCommand(TasmaUserInterface userInterface,
			TaskCollection collection) {
		super(userInterface, collection);
	}

	@Override
	public void execute() throws Exception {
		System.exit(0);
	}

}
