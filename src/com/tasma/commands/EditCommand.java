/**
 * Tasma Task Manager
 */
//@author A0132763
package com.tasma.commands;

import java.util.List;
import com.tasma.Parser;
import com.tasma.Task;
import com.tasma.TaskCollection;
import com.tasma.TasmaUserInterface;
import com.tasma.UIMessage;

public class EditCommand extends AbstractUndoableCommand {

	protected String details;
	protected int index;
	protected List<Task> state;
	protected Task task;
	protected Task oldTaskDetails;
	
	public EditCommand(TasmaUserInterface userInterface,
			TaskCollection collection, List<Task> state, int index, String details) {
		super(userInterface, collection);
		this.state = state;
		this.details = details;
		this.index = index;
		this.task = state.get(index);
	}

	@Override
	public void execute() throws Exception {
		if (details.equals("")) {
			userInterface.editCmdDisplay(String.format("edit %d %s", index + 1, task.toString()));
		} else {
			Parser parser = new Parser();
			Task updatedTask = parser.parse(details);
			if (updatedTask == null) {
				// TODO handle when the parser cannot parse the details
			} else {
				oldTaskDetails = task.clone();
				if (updatedTask.getDetails() != null) {
					task.setDetails(updatedTask.getDetails());
				}
	
				if (updatedTask.getLocation() != null) {
					task.setLocation(updatedTask.getLocation());
				}
	
				if (updatedTask.getStartDateTime() != null) {
					task.setStartDateTime(updatedTask.getStartDateTime());
				}
	
				if (updatedTask.getEndDateTime() != null) {
					task.setEndDateTime(updatedTask.getEndDateTime());
				}
				userInterface.displayMessage(String.format(UIMessage.COMMAND_EDIT_SUCCESS, task.getDetails()));
			}
		}
		
		ListCommand listCommand = new ListCommand(userInterface, collection, state);
		listCommand.execute();
	}

	@Override
	public void undo() throws Exception {
		task.setDetails(oldTaskDetails.getDetails());
		task.setStartDateTime(oldTaskDetails.getStartDateTime());
		task.setEndDateTime(oldTaskDetails.getEndDateTime());
		task.setLocation(oldTaskDetails.getLocation());
		collection.update(task);
		
		userInterface.displayMessage(String.format(UIMessage.COMMAND_EDIT_UNDO, task.getDetails()));
	}
}
