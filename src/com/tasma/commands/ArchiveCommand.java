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

public class ArchiveCommand extends AbstractUndoableCommand  {

	private List<Task> state;
	private Task task;
	
	public ArchiveCommand(TasmaUserInterface userInterface,
			TaskCollection collection, List<Task> state, int index) {
		super(userInterface, collection);
		this.state = state;
		this.task = state.get(index);
	}

	@Override
	public void execute() throws Exception {
		task.setArchived(true);
		collection.update(task);
		userInterface.displayMessage(String.format(UIMessage.COMMAND_ARCHIVE_SUCCESS, task.getDetails()));
		
		ListCommand listCommand = new ListCommand(userInterface, collection, state);
		listCommand.execute();
	}
	
	@Override
	public void undo() throws Exception {
		task.setArchived(false);
		collection.update(task);
	}
}
