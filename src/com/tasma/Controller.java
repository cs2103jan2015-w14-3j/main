/**
 * Tasma Task Manager
 */
//@author A0132763
package com.tasma;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Yong Shan Xian <ysx@u.nus.edu>
 */
public class Controller {
	private static final Logger logger = Log.getLogger( Controller.class.getName() );
	private static final String ARGUMENT_SPACE = " ";
	private static final int ARGUMENT_SPACE_NOT_FOUND = -1;
	
	/**
	 * The user interface to call the output methods from
	 */
	protected TasmaUserInterface userInterface;
	
	/**
	 * The task collection to work with
	 */
	protected TaskCollection collection;
	
	/**
	 * The parser to parse the natural language syntax
	 */
	protected Parser parser = new Parser();
	
	/**
	 * The last action to undo
	 */
	protected Stack<UndoAction> undoStack = new Stack<UndoAction>();
	
	public Controller() {
		this(new TaskCollection());
	}
	
	public Controller(TaskCollection collection) {
		this.collection = collection;
	}
	
	/**
	 * Performs initialization of the controller.
	 * The user interface (i.e. TasmaUserInterface implementation) must have been set via the setUserInterface method prior to calling this method.
	 * @throws Exception Thrown when the user interface for the controller is not set before initializing.
	 */
	public void initialize() throws Exception {
		if (userInterface == null) {
			Exception exception = new Exception("The user interface for the controller has not been set.");
			logger.log(Level.FINER, exception.toString(), exception);
			throw exception;
		}
		collection.loadFromFile();
	}
	
	/**
	 * Set the user interface to be used by the controller.
	 * @param ui The interface to be called by the controller.
	 */
	public void setUserInterface(TasmaUserInterface ui) {
		userInterface = ui;
		logger.log(Level.FINE, "Using {0} as the user interface now.", ui.getClass().getName());
	}
	
	/**
	 * Execute operations based on the user's input
	 * @param input The input string of the user
	 */
	public void executeInput(String input) {
		logger.log(Level.FINER, "Executing command {0}", input);
		
		String[] inputParts = splitArguments(input);
		String command = inputParts[0];
		String argument = "";
		if (inputParts.length == 2) {
			argument = inputParts[1];
		}
		
		CommandType commandType = normalizeCommand(command);
		switch (commandType) {
			case ADD:
				doCommandAdd(argument);
				break;
			case SEARCH:
				doCommandSearch(argument);
				break;
			case LIST:
				doCommandList();
				break;
			case MARK:
				doCommandMark(argument);
				break;
			case EDIT:
				// let's split the task ID from the edit details of the task
				String[] argumentParts = splitArguments(argument);
				String taskId = argumentParts[0];
				argument = argumentParts[1];
				doCommandEdit(taskId, argument);
				break;
			case ARCHIVE:
				doCommandArchive(argument);
				break;
			case UNDO:
				doCommandUndo();
				break;
			case EXIT:
				System.exit(0);
				break;
			default:
				// probably an invalid command, display invalid command back to user.
				userInterface.displayMessage(UIMessage.COMMAND_INVALID);
				logger.log(Level.FINER, "Command \"{0}\" is invalid", input);
				break;
		}
	}
	
	/**
	 * Performs the add command that adds a new task to the list
	 * @param details The details of the task to be added
	 */
	protected void doCommandAdd(String details) {
		if (details.trim().equals("")) {
			userInterface.displayMessage(UIMessage.COMMAND_ADD_ARG_EMPTY);
		} else {
			try {
				Task task = parser.parse(details);
				collection.create(task);
				userInterface.displayMessage(String.format(UIMessage.COMMAND_ADD_SUCCESS, task.getDetails(), task.getTaskId()));
				undoStack.push(new UndoAction(CommandType.ADD, task.clone()));
			} catch (Exception e) {
				displayException(e);
			}
		}
		// refresh the list on the window
		doCommandList();
	}
	
	/**
	 * Performs the search command that finds tasks that match the query
	 * @param query The query string to search for
	 */
	protected void doCommandSearch(String query) {
		Collection<Task> resultList = collection.search(query);
		userInterface.displayTasks(resultList);
		userInterface.displayMessage(String.format(UIMessage.COMMAND_SEARCH_RESULT, resultList.size(), query));
	}
	
	/**
	 * Performs the list command that shows the list of upcoming tasks
	 */
	protected void doCommandList() {
		Collection<Task> upcomingList = collection.upcoming();
		userInterface.displayTasks(upcomingList);
	}
	
	/**
	 * Performs the mark done command that marks a task as completed
	 * @param taskId The ID of the task that is to be mark as completed.
	 */
	protected void doCommandMark(String taskId) {
		if (taskId.equals("")) {
			userInterface.displayMessage(UIMessage.COMMAND_MARK_ARG_EMPTY);
		} else {
			try {
				Task task = collection.get(taskId);
				if (task == null) {
					logger.log(Level.FINER, String.format(UIMessage.COMMAND_MARK_NOTFOUND, taskId));
					userInterface.displayMessage(String.format(UIMessage.COMMAND_MARK_NOTFOUND, taskId));
				} else {
					undoStack.push(new UndoAction(CommandType.MARK, task.clone()));
					task.setDone(true);
					collection.update(task);
					userInterface.displayMessage(String.format(UIMessage.COMMAND_MARK_SUCCESS, task.getTaskId(), task.getDetails()));
				}
			} catch (Exception e) {
				displayException(e);
			}
		}
		// refresh the list on the window
		doCommandList();
	}
	
	/**
	 * Performs the edit command that edits a single task
	 * @param taskId The ID of the task to edit
	 * @param details The new details to replace the task.
	 */
	protected void doCommandEdit(String taskId, String details) {
		if (taskId.equals("") || details.equals("")) {
			userInterface.displayMessage(UIMessage.COMMAND_EDIT_ARG_EMPTY);
		} else {
			Task task = collection.get(taskId);
			if (task == null) {
				logger.log(Level.FINER, String.format(UIMessage.COMMAND_EDIT_NOTFOUND, taskId));
				userInterface.displayMessage(String.format(UIMessage.COMMAND_EDIT_NOTFOUND, taskId));
			} else {
				try {
					undoStack.push(new UndoAction(CommandType.EDIT, task.clone()));
					Task updatedTask = parser.parse(details);
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
					userInterface.displayMessage(String.format(UIMessage.COMMAND_EDIT_SUCCESS, task.getTaskId()));
				} catch (Exception e) {
					displayException(e);
				}
			}
		}
		// refresh the list on the window
		doCommandList();
	}
	
	/**
	 * Performs the archive command to mark a single task as done.
	 * @param taskId The task ID of the task to mark
	 */
	protected void doCommandArchive(String taskId) {
		if (taskId.equals("")) {
			userInterface.displayMessage(UIMessage.COMMAND_ARCHIVE_ARG_EMPTY);
		} else {
			try {
				Task task = collection.get(taskId);
				if (task == null) {
					logger.log(Level.FINER, String.format(UIMessage.COMMAND_ARCHIVE_NOTFOUND, taskId));
					userInterface.displayMessage(String.format(UIMessage.COMMAND_ARCHIVE_NOTFOUND, taskId));
				} else {
					undoStack.push(new UndoAction(CommandType.ARCHIVE, task.clone()));
					task.setArchived(true);
					collection.update(task);
					userInterface.displayMessage(String.format(UIMessage.COMMAND_ARCHIVE_SUCCESS, task.getTaskId(), task.getDetails()));
				}
			} catch (Exception e) {
				displayException(e);
			}
		}
		// refresh the list on the window
		doCommandList();
	}
	
	/**
	 * Performs the undo command to reverse the tasks list to the previous state.
	 */
	protected void doCommandUndo() {
		try {
			if (undoStack.size() == 0) {
				// TODO: show no more undo message
			} else {
				UndoAction undoAction = undoStack.pop();
				switch(undoAction.getCommand()) {
					case ADD:
						collection.delete(undoAction.getTask().getTaskId());
						break;
					case EDIT:
						collection.update(undoAction.getTask());
						break;
					case MARK:
						collection.update(undoAction.getTask());
						break;
					case ARCHIVE:
						collection.update(undoAction.getTask());
						break;
					default:
						break;
				}
			}
		} catch (Exception e) {
			displayException(e);
		}
		// refresh the list on the window
		doCommandList();
	}
	
	/**
	 * Displays an exception message to the user
	 * @param exception The exception to show
	 */
	protected void displayException(Exception exception) {
		logger.log(Level.FINE, exception.toString(), exception);
		userInterface.displayMessage(String.format(UIMessage.COMMAND_EXCEPTION, exception.getMessage()));
	}
	
	/**
	 * Normalizes variation of a command into a specific CommandType for processing later
	 * @param command The command to be interpreted.
	 * @return Returns the command type if the command is valid, otherwise returns the CommandType.INVALID value.
	 */
	protected static CommandType normalizeCommand(String command) {
		switch(command.trim().toLowerCase()) {
			case "add":
			case "a":
			case "insert":
			case "create":
			case "c":
			case "set":
				return CommandType.ADD;
			case "s":
			case "search":
			case "find":
			case "f":
			case "q":
			case "query":
				return CommandType.SEARCH;
			case "e":
			case "edit":
			case "update":
			case "change":
				return CommandType.EDIT;
			case "u":
			case "upcoming":
			case "up":
			case "l":
			case "list":
				return CommandType.LIST;
			case "undo":
				return CommandType.UNDO;
			case "m":
			case "mark":
			case "do":
			case "done":
				return CommandType.MARK;
			case "arc":
			case "archive":
				return CommandType.ARCHIVE;
			case "exit":
				return CommandType.EXIT;
			
		}
		return CommandType.INVALID;
	}
	
	private static String[] splitArguments(String input) {
		return splitArguments(input, 1);
	}
	
	private static String[] splitArguments(String input, int argumentCount) {
		ArrayList<String> arguments = new ArrayList<String>();
		for (int i = 0; i < argumentCount; ++i) {
			int intSpacePos = input.indexOf(ARGUMENT_SPACE);
			if (intSpacePos == ARGUMENT_SPACE_NOT_FOUND) {
				break;
			} else {
				arguments.add(input.substring(0, intSpacePos));
				input = input.substring(intSpacePos + 1);
			}
		}
		arguments.add(input);
		return arguments.toArray(new String[arguments.size()]);
	}
}
