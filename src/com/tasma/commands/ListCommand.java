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
		state.clear();
		state.addAll(list);
		if (list == null) {
			// TODO handle list null
		} else {
			userInterface.displayTasks(list);
		}
	}

}
