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

public class ArchiveCommand extends TaskRestorableCommand {

	public ArchiveCommand(TasmaUserInterface userInterface,
			TaskCollection collection, List<Task> state, int number) {
		super(userInterface, collection, state, number);
	}

	@Override
	public void execute() throws Exception {
		task.setArchived(true);
		collection.update(task);
		userInterface.displayMessage(String.format(UIMessage.COMMAND_ARCHIVE_SUCCESS, task.getDetails()));
		
		ListCommand listCommand = new ListCommand(userInterface, collection, state);
		listCommand.execute();
	}

}
