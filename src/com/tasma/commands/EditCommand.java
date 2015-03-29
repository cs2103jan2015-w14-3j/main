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

public class EditCommand extends TaskRestorableCommand {

	protected String details;
	protected int index;
	
	public EditCommand(TasmaUserInterface userInterface,
			TaskCollection collection, List<Task> state, int index, String details) {
		super(userInterface, collection, state, index);
		this.details = details;
		this.index = index;
	}

	@Override
	public void execute() throws Exception {
		if (details.equals("")) {
			userInterface.editCmdDisplay(String.format("edit %d %s", index, task.toString()));
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
				userInterface.displayMessage(String.format(UIMessage.COMMAND_EDIT_SUCCESS));
			}
		}
		
		ListCommand listCommand = new ListCommand(userInterface, collection, state);
		listCommand.execute();
	}

}
