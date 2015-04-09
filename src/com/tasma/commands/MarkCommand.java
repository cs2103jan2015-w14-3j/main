/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma.commands;

import java.util.List;

import com.tasma.Palette;
import com.tasma.Task;
import com.tasma.TaskCollection;
import com.tasma.TasmaUserInterface;
import com.tasma.UIMessage;

public class MarkCommand extends AbstractUndoableCommand {
	
	private static final String MARK_AS_UNDONE = "undone";
	private static final String MARK_AS_DONE = "done";
	
	private String markAs = MARK_AS_DONE;
	private List<Task> state;
	private boolean lastState;
	private Task task;
	
	public MarkCommand(TasmaUserInterface userInterface,
			TaskCollection collection, List<Task> state, int index, String markAs) {
		super(userInterface, collection);
		this.state = state;
		try {
			if (markAs.toLowerCase().equals(MARK_AS_UNDONE)) {
				this.markAs = MARK_AS_UNDONE;
			}
			this.task = state.get(index);
		} catch(Exception ex) {
			userInterface.displayMessage(String.format(UIMessage.COMMAND_MARK_ARG_EMPTY, this.markAs), Palette.MESSAGE_WARNING);
		}
	}

	@Override
	public void execute() throws Exception {
		if (task == null) {
			// prevent from being added to the History stack
			throw new NotExecutedException();
		} else {
			lastState = task.isDone();
			switch (markAs) {
				case MARK_AS_UNDONE:
					task.setDone(false);
					break;
				case MARK_AS_DONE:
				default:
					task.setDone(true);
					break;
			}
			collection.update(task);
			userInterface.displayMessage(String.format(UIMessage.COMMAND_MARK_SUCCESS, task.getDetails(), markAs), Palette.MESSAGE_SUCCESS);
			
			ListCommand listCommand = new ListCommand(userInterface, collection, state);
			listCommand.execute();
		}
	}

	@Override
	public void undo() throws Exception {
		task.setDone(lastState);
		collection.update(task);

		userInterface.displayMessage(String.format(UIMessage.COMMAND_MARK_UNDO, task.getDetails(), (task.isDone() ? MARK_AS_DONE : MARK_AS_UNDONE)), Palette.MESSAGE_SUCCESS);
	}
}
