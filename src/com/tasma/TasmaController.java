/**
 * Tasma Task Manager
 */
package com.tasma;

/**
 * @author Yong Shan Xian <ysx@u.nus.edu>
 */
public class TasmaController {
	
	public void initialize() {
		
	}
	
	public void execute(String input) {
		String[] inputParts = splitInput(input);
		String command = inputParts[0];
		String argument = inputParts[1];
		
		CommandType commandType = normalizeCommand(command);
		switch (commandType) {
			case ADD:
				break;
			case SEARCH:
				break;
			case LIST:
				break;
			case MARK:
				break;
			case EDIT:
				break;
			case ARCHIVE:
				break;
			default:
				break;
		}
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
	
	private static String[] splitInput(String input) {
		int intSpacePos = input.indexOf(" ");
		if (intSpacePos == -1) {
			return new String[] {
				input.substring(0, intSpacePos),
				input.substring(intSpacePos + 1)
			};
		} else {
			return new String[] {
				input.trim(),
				""
			};
		}
	}
}
