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

public class DeleteCommand extends AbstractUndoableCommand  {

	private List<Task> state;
	private Task task;
	
	public DeleteCommand(TasmaUserInterface userInterface,
			TaskCollection collection, List<Task> state, int index) {
		super(userInterface, collection);
		this.state = state;
		this.task = state.get(index);
	}

	@Override
	public void execute() throws Exception {
		collection.delete(task);
		userInterface.displayMessage(String.format(UIMessage.COMMAND_DELETE_SUCCESS, task.getDetails()));
		
		ListCommand listCommand = new ListCommand(userInterface, collection, state);
		listCommand.execute();
	}
	
	@Override
	public void undo() throws Exception {
		collection.create(task);

		userInterface.displayMessage(String.format(UIMessage.COMMAND_DELETE_UNDO, task.getDetails()));
	}
}
