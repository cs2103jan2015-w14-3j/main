/**
 * Tasma Task Manager
 */
//@author A0132763
package com.tasma.commands;

import com.tasma.Parser;
import com.tasma.Task;
import com.tasma.TaskCollection;
import com.tasma.TasmaUserInterface;
import com.tasma.UIMessage;

public class EditCommand extends TaskRestorableCommand {

	protected String details;
	
	public EditCommand(TasmaUserInterface userInterface,
			TaskCollection collection, String taskId, String details) {
		super(userInterface, collection, taskId);
		this.details = details;
	}

	@Override
	public void execute() throws Exception {
		if (details.equals("")) {
			userInterface.editCmdDisplay(String.format("edit %s %s", task.getTaskId(), task.toString()));
		} else {
			Parser parser = new Parser();
			Task updatedTask = parser.parse(details);
			if (updatedTask == null) {
				// TODO handle when the parser cannot parse the details
			} else {
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
				userInterface.displayMessage(String.format(UIMessage.COMMAND_EDIT_SUCCESS, task.getTaskId()));
			}
		}
		
		ListCommand listCommand = new ListCommand(userInterface, collection);
		listCommand.execute();
	}

}
