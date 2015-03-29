/**
 * Tasma Task Manager
 */
//@author A0132763
package com.tasma.commands;

import java.util.List;
import com.tasma.Task;
import com.tasma.TaskCollection;
import com.tasma.TasmaUserInterface;
import com.tasma.UIMessage;

public class MarkCommand extends TaskRestorableCommand {
	
	public MarkCommand(TasmaUserInterface userInterface,
			TaskCollection collection, List<Task> state, int index) {
		super(userInterface, collection, state, index);
	}

	@Override
	public void execute() throws Exception {
		task.setDone(true);
		collection.update(task);
		userInterface.displayMessage(String.format(UIMessage.COMMAND_MARK_SUCCESS, task.getDetails()));
		
		ListCommand listCommand = new ListCommand(userInterface, collection, state);
		listCommand.execute();
	}

}
