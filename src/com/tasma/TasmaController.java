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
