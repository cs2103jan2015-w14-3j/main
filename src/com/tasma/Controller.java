/**
 * Tasma Task Manager
 */
package com.tasma;

import java.util.ArrayList;

/**
 * @author Yong Shan Xian <ysx@u.nus.edu>
 */
public class Controller {
	
	TasmaUserInterface userInterface;
	TaskCollection collection = new TaskCollection();
	Parser parser = new Parser();
	
	public void initialize() throws Exception {
		if (userInterface == null) {
			throw new Exception("The user interface for the controller has not been set.");
		}
		collection.loadFromFile();
	}
	
	public void setUserInterface(TasmaUserInterface ui) {
		userInterface = ui;
	}
	
	public void executeInput(String input) {
		String[] inputParts = splitArguments(input);
		String command = inputParts[0];
		String argument = inputParts[1];
		
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
				String[] argumentParts = splitArguments(argument, 1);
				String taskId = argumentParts[0];
				argument = argumentParts[1];
				doCommandEdit(taskId, argument);
				break;
			case ARCHIVE:
				doCommandArchive(argument);
				break;
			case EXIT:
				System.exit(0);
				break;
			default:
				// probably an invalid command, display invalid command back to user.
				userInterface.displayMessage(UIMessage.COMMAND_INVALID);
				break;
		}
	}
	
	protected void doCommandAdd(String details) {
		Task task = parser.parse(details);
		collection.create(task);
		userInterface.displayMessage(String.format(UIMessage.COMMAND_ADD_SUCCESS, task.getDetails(), task.getTaskId()));
	}
	
	protected void doCommandSearch(String query) {
		
	}
	
	protected void doCommandList() {
		
	}
	
	protected void doCommandMark(String taskId) {
		Task task = collection.get(taskId);
		task.setDone(true);
		collection.update(task);
		// TODO: display message
	}
	
	protected void doCommandEdit(String taskId, String details) {
		
	}
	
	protected void doCommandArchive(String taskId) {
		
	}
	
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
			case "m":
			case "mark":
			case "do":
			case "done":
				return CommandType.MARK;
			case "arc":
			case "archive":
				return CommandType.ARCHIVE;
			
		}
		return CommandType.INVALID;
	}
	
	private static String[] splitArguments(String input) {
		return splitArguments(input, 1);
	}
	
	private static String[] splitArguments(String input, int argumentCount) {
		ArrayList<String> arguments = new ArrayList<String>();
		for (int i = 0; i < argumentCount; ++i) {
			int intSpacePos = input.indexOf(" ");
			if (intSpacePos == -1) {
				arguments.add(input);
				break;
			} else {
				arguments.add(input.substring(0, intSpacePos));
				input = input.substring(intSpacePos + 1);
			}
		}
		return (String[])arguments.toArray();
	}
}
