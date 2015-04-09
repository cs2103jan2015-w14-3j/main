/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma.commands;

import java.util.List;
import java.util.LinkedList;
import com.tasma.Palette;
import com.tasma.Task;
import com.tasma.TaskCollection;
import com.tasma.TasmaUserInterface;
import com.tasma.UIMessage;


public class DeleteCommand extends AbstractUndoableCommand  {

	private List<Task> state;
	private List<Task> tasks;
	
	public DeleteCommand(TasmaUserInterface userInterface,
			TaskCollection collection, List<Task> state, List<Integer> indices) {
		super(userInterface, collection);
		this.state = state;
		tasks = new LinkedList<Task>();
		for (int index: indices) {
			if (state.get(index) != null) {
				tasks.add(state.get(index));
			}
		}
		if (tasks.size() == 0) {
			userInterface.displayMessage(UIMessage.COMMAND_DELETE_NOTFOUND, Palette.MESSAGE_WARNING);
		}
	}

	@Override
	public void execute() throws Exception {
		if (tasks.size() == 0) {
			throw new NotExecutedException();
		} else {
			if (tasks.size() == 1) {
				Task task = tasks.get(0);
				collection.delete(task);

				userInterface.displayMessage(String.format(UIMessage.COMMAND_DELETE_SUCCESS, task.getDetails()), Palette.MESSAGE_SUCCESS);
			} else {
				for (Task task : tasks) {
					collection.delete(task);
				}
				userInterface.displayMessage(String.format(UIMessage.COMMAND_DELETE_MULTIPLE_SUCCESS, tasks.size()), Palette.MESSAGE_SUCCESS);
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
				collection.create(task);
		
				userInterface.displayMessage(String.format(UIMessage.COMMAND_DELETE_UNDO, task.getDetails()), Palette.MESSAGE_SUCCESS);
			} else {
				for (Task task : tasks) {
					collection.create(task);
				}
				userInterface.displayMessage(String.format(UIMessage.COMMAND_DELETE_MULTIPLE_UNDO, tasks.size()), Palette.MESSAGE_SUCCESS);
			}
		}
	}
}
