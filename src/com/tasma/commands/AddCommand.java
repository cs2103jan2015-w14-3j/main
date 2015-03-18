package com.tasma.commands;

import com.tasma.Parser;
import com.tasma.Task;
import com.tasma.TaskCollection;
import com.tasma.TasmaUserInterface;
import com.tasma.UIMessage;

public class AddCommand extends AbstractCommand {
	
	protected String details;
	protected Task resultTask;
	
	public AddCommand(TasmaUserInterface userInterface, TaskCollection tasks, String details) {
		super(userInterface, tasks);
		this.details = details;
	}

	@Override
	public void execute() throws Exception {
		if (details.equals("")) {
			userInterface.displayMessage(UIMessage.COMMAND_ADD_ARG_EMPTY);
		} else {
			assert !details.equals("");
			
			Parser parser = new Parser();
			Task task = parser.parse(details);
			collection.create(task);
			userInterface.displayMessage(String.format(UIMessage.COMMAND_ADD_SUCCESS, task.getDetails(), task.getTaskId()));
			resultTask = task.clone();
		}
	}

	@Override
	public void undo() throws Exception {
		assert resultTask != null;
		
		collection.delete(resultTask.getTaskId());
	}

}
