/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma.commands;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Predicate;

import com.tasma.Task;
import com.tasma.TaskCollection;
import com.tasma.TaskState;
import com.tasma.TaskType;
import com.tasma.UIMessage;
import com.tasma.ui.Palette;
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
            SearchProcessor processor = new SearchProcessor(query);
            List<Task> resultList = processor.filter(collection);
            TaskListSorter.sort(resultList);

            state.clear();
            state.addAll(resultList);
            userInterface.setHeader(String.format(UIMessage.HEADER_TASK_SEARCH, query));
            userInterface.displayTasks(resultList);
            userInterface.displayMessage(String.format(UIMessage.COMMAND_SEARCH_RESULT, resultList.size(), query), Palette.MESSAGE_INFO);
        }
    }

    protected class SearchProcessor {
        Predicate<Task> predicate;
        public SearchProcessor(String query) {
            predicate = buildConditions(query);
        }
        
        public List<Task> filter(TaskCollection collection) {
            return collection.filter(predicate);
        }
        
        private Predicate<Task> buildConditions(String query) {
            Predicate<Task> conditions;
            LinkedList<Predicate<Task>> subConditions = new LinkedList<Predicate<Task>>();
            
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
                            subConditions.add(task -> task.getState() == TaskState.TODAY);
                            break;
                        case "tomorrow":
                            subConditions.add(task -> task.getState() == TaskState.TOMORROW);
                            break;
                        case "done":
                            subConditions.add(task -> task.isDone());
                            break;
                        case "undone":
                            subConditions.add(task -> !task.isDone());
                            break;
                        case "overdue":
                            subConditions.add(task -> task.getState() == TaskState.OVERDUE);
                            break;
                        case "upcoming":
                            subConditions.add(task -> task.getState() == TaskState.UPCOMING);
                            break;
                        case "floating":
                            subConditions.add(task -> task.getType() == TaskType.FLOATING);
                            break;
                        case "timed":
                            subConditions.add(task -> task.getType() == TaskType.TIMED);
                            break;
                        case "deadline":
                            subConditions.add(task -> task.getType() == TaskType.DEADLINE);
                            break;
                        default:
                            wordMatch += ".*" + word;
                            break;
                    }
                }
                if (inQuote && word.endsWith("\"")){
                    inQuote = false;
                }
            }
            conditions = subConditions.poll();
            if (subConditions.size() > 0) {
                for (Predicate<Task> condition: subConditions) {
                    conditions = conditions.or(condition);
                }
            }
            
            wordMatch += ".*";
            final String regex = wordMatch;
            Predicate<Task> detailsMatcher = task -> task.getDetails().matches(regex);
            if (conditions == null) {
                conditions = detailsMatcher;
            } else {
                conditions = conditions.and(detailsMatcher);
            }
            return conditions;
        }
    }
}
