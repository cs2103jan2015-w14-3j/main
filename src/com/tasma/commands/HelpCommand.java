package com.tasma.commands;

import com.tasma.TaskCollection;
import com.tasma.TasmaUserInterface;

public class HelpCommand extends AbstractCommand {

	public HelpCommand(TasmaUserInterface userInterface,
			TaskCollection collection) {
		super(userInterface, collection);
	}

	@Override
	public void execute() throws Exception {
		// TODO work on help command

	}

	@Override
	public void undo() throws Exception {
		throw new UndoNotSupportedException();
	}

}
