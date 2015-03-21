/**
 * Tasma Task Manager
 */
//@author A0132763
package com.tasma.commands;

import java.util.Collection;

import com.tasma.Task;
import com.tasma.TaskCollection;
import com.tasma.TasmaUserInterface;

public class ListCommand extends AbstractCommand {

	public ListCommand(TasmaUserInterface userInterface,
			TaskCollection collection) {
		super(userInterface, collection);
	}

	@Override
	public void execute() throws Exception {
		Collection<Task> upcomingList = collection.upcoming();
		userInterface.displayTasks(upcomingList);
	}

}
