package com.tasma.commands;

import com.tasma.TaskCollection;
import com.tasma.TasmaUserInterface;

public class TutorialCommand extends AbstractCommand {

	public TutorialCommand(TasmaUserInterface userInterface,
			TaskCollection collection) {
		super(userInterface, collection);
	}

	@Override
	public void execute() throws Exception {
		// TODO to implement tutorial
	}

	@Override
	public void undo() throws Exception {
		throw new UndoNotSupportedException();
	}

}
