package com.tasma.commands;

import com.tasma.TaskCollection;
import com.tasma.TasmaUserInterface;

public class HelpCommand extends AbstractCommand {

	protected String command;
	
	public HelpCommand(TasmaUserInterface userInterface,
			TaskCollection collection, String command) {
		super(userInterface, collection);
		this.command = command;
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
