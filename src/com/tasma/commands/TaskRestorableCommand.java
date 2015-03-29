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

public abstract class TaskRestorableCommand extends AbstractUndoableCommand {

	protected Task task;
	protected Task originalTask;
	protected List<Task> state;
	
	public TaskRestorableCommand(TasmaUserInterface userInterface,
			TaskCollection collection, List<Task> state, int index) {
		super(userInterface, collection);
		this.state = state;
		try {
			task = state.get(index);
			originalTask = task.clone();
		} catch (CloneNotSupportedException e) {
		}
	}

	// TODO fix bug
	@Override
	public void undo() throws Exception {
		collection.update(originalTask);
		userInterface.displayMessage(String.format(UIMessage.COMMAND_EDIT_UNDO));
	}

}
