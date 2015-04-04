/**
 * Tasma Task Manager
 */
//@author A0132763
package com.tasma.commands;

import java.util.List;
import com.tasma.Palette;
import com.tasma.Task;
import com.tasma.TaskCollection;
import com.tasma.TasmaUserInterface;
import com.tasma.UIMessage;

public class SearchCommand extends AbstractCommand {

	protected List<Task> state;
	protected String query;
	
	public SearchCommand(TasmaUserInterface userInterface,
			TaskCollection collection, List<Task> state, String query) {
		super(userInterface, collection);
		this.query = query;
		this.state = state;
	}

	@Override
	public void execute() throws Exception {
		if (query.equals("")) {
			userInterface.displayMessage(UIMessage.COMMAND_SEARCH_EMPTY_QUERY, Palette.MESSAGE_WARNING);
		} else {
			List<Task> resultList = collection.search(query);
			TaskListSorter.sort(resultList);

			state.clear();
			state.addAll(resultList);
			userInterface.displayTasks(resultList);
			userInterface.displayMessage(String.format(UIMessage.COMMAND_SEARCH_RESULT, resultList.size(), query), Palette.MESSAGE_INFO);
		}
	}

}
