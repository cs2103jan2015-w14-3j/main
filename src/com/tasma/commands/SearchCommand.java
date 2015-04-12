/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma.commands;

import java.util.List;

import com.tasma.Palette;
import com.tasma.Task;
import com.tasma.TaskCollection;
import com.tasma.UIMessage;
import com.tasma.ui.TasmaUserInterface;

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
			userInterface.setHeader(String.format(UIMessage.HEADER_TASK_SEARCH, query));
			userInterface.displayTasks(resultList);
			userInterface.displayMessage(String.format(UIMessage.COMMAND_SEARCH_RESULT, resultList.size(), query), Palette.MESSAGE_INFO);
		}
	}

	protected class SearchProcessor {
		protected String query;
		public SearchProcessor(String query) {
			buildConditions(query);
		}
		
		private void buildConditions(String query) {
			InputSplitter splitter = new InputSplitter(query);
			String wordMatch = "";
			boolean inQuote = false;
			while (splitter.hasNext()) {
				String word = splitter.next();
				if (word.startsWith("\"")) {
					inQuote = true;
				}
				if (inQuote) {
					wordMatch += ".+" + word;
				} else {
					switch (word) {
						case "today":
							break;
						case "tomorrow":
							break;
						case "done":
							break;
						case "undone":
							break;
						case "overdue":
							break;
						case "upcoming":
							break;
					}
				}
				if (inQuote && word.endsWith("\"")){
					inQuote = false;
				}
			}
			wordMatch += ".+";
		}
		
		private String extractQuotedQuery(String word, InputSplitter splitter) {
			String result = word + "";
			while (splitter.hasNext()) {
				splitter.
			}
			return result.trim();
		}
	}
}
