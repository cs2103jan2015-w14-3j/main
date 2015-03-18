package com.tasma.commands;

import java.util.ArrayList;
import java.util.logging.Level;

import com.tasma.CommandType;
import com.tasma.TaskCollection;
import com.tasma.TasmaUserInterface;
import com.tasma.UIMessage;

public class CommandFactory {

	protected TasmaUserInterface userInterface;
	protected TaskCollection collection;
	
	public CommandFactory(TasmaUserInterface userInterface, TaskCollection collection) {
		this.userInterface = userInterface;
		this.collection = collection;
	}

	public CommandInterface getCommand(String input) {
		CommandInterface result = null;
		InputSplitter splitter = new InputSplitter(input);
		String command = splitter.next();

		CommandType commandType = normalizeCommand(command);
		switch (commandType) {
			case ADD:
				result = new AddCommand(userInterface, collection, splitter.remainder());
				break;
			case SEARCH:
				result = new SearchCommand(userInterface, collection, splitter.remainder());
				break;
			case LIST:
				result = new ListCommand(userInterface, collection);
				break;
			case MARK:
				result = new MarkCommand(userInterface, collection, splitter.remainder());
				break;
			case EDIT:
				result = new EditCommand(userInterface, collection, splitter.next(), splitter.remainder());
				break;
			case ARCHIVE:
				result = new ArchiveCommand(userInterface, collection, splitter.remainder());
				break;
			case HELP:
				result = new SearchCommand(userInterface, collection, splitter.remainder());
				break;
			case TUTORIAL:
				result = new TutorialCommand(userInterface, collection);
				break;
			case EXIT:
				result = new ExitCommand(userInterface, collection);
				break;
			default:
				// probably an invalid command
				result = new InvalidCommand(userInterface, collection);
				break;
		}
		return result;
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
