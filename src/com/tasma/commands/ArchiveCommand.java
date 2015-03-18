package com.tasma.commands;

import com.tasma.TaskCollection;
import com.tasma.TasmaUserInterface;

public class ArchiveCommand extends TaskRestorableCommand {

	public ArchiveCommand(TasmaUserInterface userInterface,
			TaskCollection collection, String taskId) {
		super(userInterface, collection, taskId);
	}

	@Override
	public void execute() throws Exception {
		task.setArchived(true);
		collection.update(task);
	}

}
