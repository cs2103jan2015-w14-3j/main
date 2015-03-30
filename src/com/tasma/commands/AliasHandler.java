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
		CommandType result = CommandType.INVALID;
		String commandLower = command.toLowerCase();
		if (builtInMapping.containsKey(commandLower)) {
			result = builtInMapping.get(commandLower);
		}
		return result;
	}
}
