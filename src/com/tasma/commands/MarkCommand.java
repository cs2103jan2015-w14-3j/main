package com.tasma.commands;

import com.tasma.Task;
import com.tasma.TaskCollection;
import com.tasma.TasmaUserInterface;

public class MarkCommand extends TaskRestorableCommand {
	
	protected Task task;

	public MarkCommand(TasmaUserInterface userInterface,
			TaskCollection collection, String taskId) {
		super(userInterface, collection, taskId);
		task = collection.get(taskId);
	}

	@Override
	public void execute() throws Exception {
		task.setDone(true);
		collection.update(task);
	}

}
