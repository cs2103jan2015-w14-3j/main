/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma.commands;

import java.util.List;

import com.tasma.Task;
import com.tasma.TaskCollection;
import com.tasma.TaskState;
import com.tasma.UIMessage;
import com.tasma.ui.TasmaUserInterface;

public class ListCommand extends AbstractCommand {

	private static final String FILTER_FLOATING = "floating";
	private static final String FILTER_DONE = "done";
	private static final String FILTER_UNDONE = "undone";
	private static final String FILTER_TODAY = "today";
	private static final String FILTER_TMR = "tmr";
	private static final String FILTER_TOMORROW = "tomorrow";
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
			case FILTER_TMR:
			case FILTER_TOMORROW:
				userInterface.setHeader(UIMessage.HEADER_TASK_TOMORROW);
				list = collection.filter(task -> task.getState() == TaskState.TOMORROW);
				break;
			case FILTER_TODAY:
				userInterface.setHeader(UIMessage.HEADER_TASK_TODAY);
				list = collection.filter(task -> task.getState() == TaskState.TODAY);
				break;
			case FILTER_FLOATING:
				userInterface.setHeader(UIMessage.HEADER_TASK_FLOATING);
				list = collection.floating();
				break;
			case FILTER_UPCOMING:
				userInterface.setHeader(UIMessage.HEADER_TASK_UPCOMING);
				list = collection.upcoming();
				break;
			case FILTER_OVERDUE:
			case FILTER_PAST:
				userInterface.setHeader(UIMessage.HEADER_TASK_OVERDUE);
				list = collection.past();
				break;
			case FILTER_DONE:
				userInterface.setHeader(UIMessage.HEADER_TASK_DONE);
				list = collection.done();
				break;
			case FILTER_UNDONE:
			default:
				userInterface.setHeader(UIMessage.HEADER_TASK_UNDONE);
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
