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
	
	private static final String FILTER_NONE = "";
	private static final String FILTER_PAST = "past";
	
	private String filter;

	public ListCommand(TasmaUserInterface userInterface,
			TaskCollection collection) {
		this(userInterface, collection, "");
	}

	public ListCommand(TasmaUserInterface userInterface,
			TaskCollection collection, String filter) {
		super(userInterface, collection);
		this.filter = filter;
	}

	@Override
	public void execute() throws Exception {
		Collection<Task> list = null;
		switch (filter.toLowerCase()) {
			case FILTER_PAST:
				list = collection.past();
				break;
			default:
				list = collection.upcoming();
				break;
		}
		if (list == null) {
			// TODO handle list null
		} else {
			userInterface.displayTasks(list);
		}
	}

}
