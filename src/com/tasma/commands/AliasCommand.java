package com.tasma.commands;

import com.tasma.TaskCollection;
import com.tasma.TasmaUserInterface;

public class AliasCommand extends AbstractUndoableCommand {
	
	private String key;
	private String previousValue;
	private String newValue;

	public AliasCommand(TasmaUserInterface userInterface,
			TaskCollection collection, String key, String value) {
		super(userInterface, collection);
		this.key = key.toLowerCase();
		this.newValue = value;
	}

	@Override
	public void execute() throws Exception {
		previousValue = AliasHandler.getCustomAlias(key);
		AliasHandler.setCustomAlias(key, newValue);
	}

	@Override
	public void undo() throws Exception {
		if (previousValue == null) {
			AliasHandler.removeCustomAlias(key);
		} else {
			AliasHandler.setCustomAlias(key, previousValue);
		}
	}

}
