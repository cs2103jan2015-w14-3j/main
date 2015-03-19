package com.tasma.commands;

import com.tasma.TaskCollection;
import com.tasma.TasmaUserInterface;
import com.tasma.UIMessage;

public class MarkCommand extends TaskRestorableCommand {
	
	public MarkCommand(TasmaUserInterface userInterface,
			TaskCollection collection, String taskId) {
		super(userInterface, collection, taskId);
	}

	@Override
	public void execute() throws Exception {
		task.setDone(true);
		collection.update(task);
		userInterface.displayMessage(String.format(UIMessage.COMMAND_MARK_SUCCESS, task.getTaskId(), task.getDetails()));
		
		ListCommand listCommand = new ListCommand(userInterface, collection);
		listCommand.execute();
	}

}
