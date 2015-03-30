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

public class MarkCommand extends AbstractUndoableCommand {
	
	private List<Task> state;
	private Task task;
	
	public MarkCommand(TasmaUserInterface userInterface,
			TaskCollection collection, List<Task> state, int index) {
		super(userInterface, collection);
		this.state = state;
		this.task = state.get(index);
	}

	@Override
	public void execute() throws Exception {
		task.setDone(true);
		collection.update(task);
		userInterface.displayMessage(String.format(UIMessage.COMMAND_MARK_SUCCESS, task.getDetails()));
		
		ListCommand listCommand = new ListCommand(userInterface, collection, state);
		listCommand.execute();
	}

	@Override
	public void undo() throws Exception {
		task.setDone(false);
		collection.update(task);
	}
}
