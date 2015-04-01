package com.tasma.commands;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.tasma.InvalidInputException;
import com.tasma.config.Config;

public class AliasHandler {
	
	private static final String ALIAS_CONFIG_NAME = "aliases";
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
    	builtInMapping.put("alias", CommandType.ALIAS);
    	
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
    	builtInMapping.put("do", CommandType.MARK);
    	builtInMapping.put("done", CommandType.MARK);

    	builtInMapping.put("del", CommandType.DELETE);
    	builtInMapping.put("delete", CommandType.DELETE);
    	builtInMapping.put("r", CommandType.DELETE);
    	builtInMapping.put("remove", CommandType.DELETE);
    	
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
	 * @throws InvalidInputException 
	 */
	public static CommandType normalize(String command) throws InvalidInputException {
		CommandType result = CommandType.INVALID;

		String commandLower = command.toLowerCase();
		String alias;
		try {
			alias = getCustomAlias(command);
			if (alias != null) {
				commandLower = alias.toLowerCase();
			}
		} catch (Exception e) {
			throw new InvalidInputException();
		}
		
		if (builtInMapping.containsKey(commandLower)) {
			result = builtInMapping.get(commandLower);
		}
		return result;
	}
	
	public static List<String> aliases(CommandType commandType) {
		List<String> keys = new LinkedList<String>(); 
		for (Map.Entry<String, CommandType> entry : builtInMapping.entrySet()) {
			if (entry.getValue().equals(commandType)) {
			    keys.add(entry.getKey());
			}
		}
		return keys;
	}
	
	public static String getCustomAlias(String customAlias) throws Exception {
		if (customAlias == null || customAlias.equals("")) {
			throw new IllegalArgumentException();
		}
		Config settings = Config.getInstance(ALIAS_CONFIG_NAME);
		return settings.getProperty(customAlias);
	}
	
	public static void setCustomAlias(String newCustomAlias, String command) throws Exception {
		if (newCustomAlias == null || newCustomAlias.equals("")) {
			throw new IllegalArgumentException();
		}
		Config settings = Config.getInstance(ALIAS_CONFIG_NAME);
		CommandType type = normalize(command);
		settings.setProperty(newCustomAlias, type.toString());
	}
	
	public static void removeCustomAlias(String customAlias) throws Exception {
		if (customAlias == null || customAlias.equals("")) {
			throw new IllegalArgumentException();
		}
		Config settings = Config.getInstance(ALIAS_CONFIG_NAME);
		settings.removeProperty(customAlias);
	}
}
