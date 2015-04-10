/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma.commands;

import java.util.LinkedList;
import java.util.List;

import com.tasma.Palette;
import com.tasma.Task;
import com.tasma.TaskCollection;
import com.tasma.UIMessage;
import com.tasma.ui.TasmaUserInterface;

public class UnmarkCommand extends AbstractUndoableCommand {

	private List<Task> state;
	private List<Task> tasks;

	public UnmarkCommand(TasmaUserInterface userInterface,
			TaskCollection collection, List<Task> state, List<Integer> indices) {
		super(userInterface, collection);
		this.state = state;
		tasks = new LinkedList<Task>();
		try {
			for (int index: indices) {
				if (state.get(index) != null) {
					tasks.add(state.get(index));
				}
			}
		} catch (IndexOutOfBoundsException ex) {
			
		}
		if (tasks.size() == 0) {
			userInterface.displayMessage(UIMessage.COMMAND_UNMARK_NOTFOUND, Palette.MESSAGE_WARNING);
		}
	}

	@Override
	public void execute() throws Exception {
		if (tasks.size() == 0) {
			throw new NotExecutedException();
		} else {
			if (tasks.size() == 1) {
				Task task = tasks.get(0);
				task.setDone(false);
				collection.update(task);
				userInterface.displayMessage(String.format(UIMessage.COMMAND_UNMARK_SUCCESS, task.getDetails()), Palette.MESSAGE_SUCCESS);
			} else {
				for (Task task : tasks) {
					task.setDone(false);
					collection.update(task);
				}
				userInterface.displayMessage(String.format(UIMessage.COMMAND_UNMARK_MULTIPLE_SUCCESS, tasks.size()), Palette.MESSAGE_SUCCESS);
			}
			ListCommand listCommand = new ListCommand(userInterface, collection, state);
			listCommand.execute();
		}
	}

	@Override
	public void undo() throws Exception {
		if (tasks.size() == 0) {
			throw new NotExecutedException();
		} else {
			if (tasks.size() == 1) {
				Task task = tasks.get(0);
				task.setDone(true);
				collection.update(task);
				userInterface.displayMessage(String.format(UIMessage.COMMAND_UNMARK_UNDO, task.getDetails()), Palette.MESSAGE_SUCCESS);
			} else {
				for (Task task : tasks) {
					task.setDone(true);
					collection.update(task);
				}
				userInterface.displayMessage(String.format(UIMessage.COMMAND_UNMARK_MULTIPLE_UNDO, tasks.size()), Palette.MESSAGE_SUCCESS);
			}
		}
	}

}
