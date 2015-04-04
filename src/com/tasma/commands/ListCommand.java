/**
 * Tasma Task Manager
 */
//@author A0132763
package com.tasma.commands;

import java.util.List;

import com.tasma.Task;
import com.tasma.TaskCollection;
import com.tasma.TasmaUserInterface;

public class ListCommand extends AbstractCommand {

	private static final String FILTER_DONE = "done";
	private static final String FILTER_PAST = "past";
	private static final String FILTER_OVERDUE = "overdue";
	private static final String FILTER_UPCOMING = "upcoming";
	
	private String filter;
	private List<Task> state;

	public ListCommand(TasmaUserInterface userInterface,
			TaskCollection collection, List<Task> state) {
		this(userInterface, collection, state, "");
	}

	public ListCommand(TasmaUserInterface userInterface,
			TaskCollection collection, List<Task> state, String filter) {
		super(userInterface, collection);
		this.filter = filter;
		this.state = state;
	}

	@Override
	public void execute() throws Exception {
		List<Task> list = null;
		switch (filter.toLowerCase()) {
			case FILTER_UPCOMING:
				list = collection.upcoming();
				break;
			case FILTER_OVERDUE:
			case FILTER_PAST:
				list = collection.past();
				break;
			case FILTER_DONE:
				list = collection.done();
				break;
			default:
				list = collection.notDone();
				break;
		}
		TaskListSorter.sort(list);
		state.clear();
		state.addAll(list);
		if (list == null) {
			// TODO handle list null
		} else {
			userInterface.displayTasks(list);
		}
	}

}
