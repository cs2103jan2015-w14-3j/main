package com.tasma;

import com.tasma.commands.AbstractCommand;

public abstract class TaskRestorableCommand extends AbstractCommand {

	protected Task originalTask;
	
	public TaskRestorableCommand(TasmaUserInterface userInterface,
			TaskCollection collection, String taskId) {
		super(userInterface, collection);
		try {
			originalTask = collection.get(taskId).clone();
		} catch (CloneNotSupportedException e) {
		}
	}

	@Override
	public void undo() throws Exception {
		collection.update(originalTask);
	}

}
