package com.tasma.commands;

import com.tasma.TaskCollection;
import com.tasma.TasmaUserInterface;

class ExitCommand extends AbstractCommand {

	public ExitCommand(TasmaUserInterface userInterface,
			TaskCollection collection) {
		super(userInterface, collection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void execute() throws Exception {
		System.exit(0);
	}

	@Override
	public void undo() throws Exception {
		throw new UndoNotSupportedException();
	}

}
