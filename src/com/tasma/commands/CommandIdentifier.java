package com.tasma.commands;

public class CommandIdentifier {
	
	/**
	 * Normalizes variation of a command into a specific CommandType for processing later
	 * @param command The command to be interpreted.
	 * @return Returns the command type if the command is valid, otherwise returns the CommandType.INVALID value.
	 */
	public static CommandType normalize(String command) {
		switch(command.trim().toLowerCase()) {
			case "add":
			case "a":
			case "insert":
			case "create":
			case "c":
				return CommandType.ADD;
			case "set":
				return CommandType.SET;
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
			case "h":
			case "help":
				return CommandType.HELP;
			case "t":
			case "tutorial":
				return CommandType.TUTORIAL;
			case "exit":
				return CommandType.EXIT;
			
		}
		return CommandType.INVALID;
	}
}
