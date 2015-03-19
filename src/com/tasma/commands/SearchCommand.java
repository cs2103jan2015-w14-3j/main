package com.tasma.commands;

import java.util.Collection;

import com.tasma.Task;
import com.tasma.TaskCollection;
import com.tasma.TasmaUserInterface;
import com.tasma.UIMessage;

public class SearchCommand extends AbstractCommand {

	protected String query;
	
	public SearchCommand(TasmaUserInterface userInterface,
			TaskCollection collection, String query) {
		super(userInterface, collection);
		this.query = query;
	}

	@Override
	public void execute() throws Exception {
		if (query.equals("")) {
			// TODO: to handle empty search terms?
		} else {
			Collection<Task> resultList = collection.search(query);
			userInterface.displayTasks(resultList);
			userInterface.displayMessage(String.format(UIMessage.COMMAND_SEARCH_RESULT, resultList.size(), query));
		}
	}

}
