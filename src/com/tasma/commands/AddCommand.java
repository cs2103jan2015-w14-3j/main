/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma.commands;

import java.util.List;

import com.tasma.Palette;
import com.tasma.Parser;
import com.tasma.Task;
import com.tasma.TaskCollection;
import com.tasma.UIMessage;
import com.tasma.ui.TasmaUserInterface;

public class AddCommand extends AbstractUndoableCommand {
	
	protected String details;
	protected Task resultTask;
	protected List<Task> state;
	
	public AddCommand(TasmaUserInterface userInterface, TaskCollection tasks, List<Task> state, String details) {
		super(userInterface, tasks);
		this.details = details;
		this.state = state;
	}

	@Override
	public void execute() throws Exception {
		if (details.equals("")) {
			userInterface.displayMessage(UIMessage.COMMAND_ADD_ARG_EMPTY, Palette.MESSAGE_WARNING);
		} else {
			assert !details.equals("");
			
			Parser parser = new Parser();
			Task task = parser.parse(details);
			if (task == null) {
				
			} else {
				collection.create(task);
				userInterface.displayMessage(String.format(UIMessage.COMMAND_ADD_SUCCESS, task.getDetails()), Palette.MESSAGE_SUCCESS);
				resultTask = task;
			}
		}
		
		ListCommand listCommand = new ListCommand(userInterface, collection, state);
		listCommand.execute();
	}

	@Override
	public void undo() throws Exception {
		assert resultTask != null;
		
		collection.delete(resultTask);
		userInterface.displayMessage(String.format(UIMessage.COMMAND_ADD_UNDO, resultTask.getDetails()), Palette.MESSAGE_SUCCESS);
	}

}
