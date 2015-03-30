package com.tasma.commands;

import java.util.HashMap;
import java.util.Map;

public class AliasHandler {
	
	private static final Map<String, CommandType> builtInMapping;
    static
    {
    	builtInMapping = new HashMap<String, CommandType>();
    	builtInMapping.put("a", CommandType.ADD);
    	builtInMapping.put("add", CommandType.ADD);
    	builtInMapping.put("insert", CommandType.ADD);
    	builtInMapping.put("create", CommandType.ADD);
    	builtInMapping.put("c", CommandType.ADD);
    	
    	builtInMapping.put("set", CommandType.SET);
    	
    	builtInMapping.put("s", CommandType.SEARCH);
    	builtInMapping.put("search", CommandType.SEARCH);
    	builtInMapping.put("f", CommandType.SEARCH);
    	builtInMapping.put("find", CommandType.SEARCH);
    	builtInMapping.put("q", CommandType.SEARCH);
    	builtInMapping.put("query", CommandType.SEARCH);

    	builtInMapping.put("e", CommandType.EDIT);
    	builtInMapping.put("edit", CommandType.EDIT);
    	builtInMapping.put("update", CommandType.EDIT);
    	builtInMapping.put("change", CommandType.EDIT);
    	
    	builtInMapping.put("l", CommandType.LIST);
    	builtInMapping.put("list", CommandType.LIST);
    	builtInMapping.put("u", CommandType.LIST);
    	builtInMapping.put("up", CommandType.LIST);
    	builtInMapping.put("upcoming", CommandType.LIST);
   
    	builtInMapping.put("undo", CommandType.UNDO);
    	builtInMapping.put("redo", CommandType.REDO);
    	
    	builtInMapping.put("m", CommandType.MARK);
    	builtInMapping.put("mark", CommandType.MARK);
    	builtInMapping.put("d", CommandType.MARK);
    	builtInMapping.put("do", CommandType.MARK);
    	builtInMapping.put("done", CommandType.MARK);

    	builtInMapping.put("arc", CommandType.ARCHIVE);
    	builtInMapping.put("archive", CommandType.ARCHIVE);
    	
    	builtInMapping.put("h", CommandType.HELP);
    	builtInMapping.put("help", CommandType.HELP);
    	
    	builtInMapping.put("t", CommandType.TUTORIAL);
    	builtInMapping.put("tutorial", CommandType.TUTORIAL);
    	
    	builtInMapping.put("exit", CommandType.EXIT);
    	builtInMapping.put("quit", CommandType.EXIT);
    }
	
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
