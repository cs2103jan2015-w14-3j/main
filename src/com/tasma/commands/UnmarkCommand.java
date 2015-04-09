package com.tasma.commands;

import java.util.List;

import com.tasma.Palette;
import com.tasma.Task;
import com.tasma.TaskCollection;
import com.tasma.TasmaUserInterface;
import com.tasma.UIMessage;

public class UnmarkCommand extends AbstractUndoableCommand {

	private List<Task> state;
	private Task task;

	public UnmarkCommand(TasmaUserInterface userInterface,
			TaskCollection collection, List<Task> state, int index) {
		super(userInterface, collection);
		try {
			this.state = state;
			this.task = state.get(index);
		} catch (NullPointerException ex) {
			userInterface.displayMessage(UIMessage.COMMAND_UNMARK_NOTFOUND, Palette.MESSAGE_WARNING);
		}
	}

	@Override
	public void execute() throws Exception {
		if (task == null) {
			throw new NotExecutedException();
		} else {
			task.setDone(false);
			collection.update(task);
			userInterface.displayMessage(String.format(UIMessage.COMMAND_UNMARK_SUCCESS, task.getDetails()), Palette.MESSAGE_SUCCESS);
			
			ListCommand listCommand = new ListCommand(userInterface, collection, state);
			listCommand.execute();
		}
	}

	@Override
	public void undo() throws Exception {
		if (task == null) {
			throw new NotExecutedException();
		} else {
			task.setDone(true);
			collection.update(task);
	
			userInterface.displayMessage(String.format(UIMessage.COMMAND_UNMARK_UNDO, task.getDetails()), Palette.MESSAGE_SUCCESS);
		}
	}

}
