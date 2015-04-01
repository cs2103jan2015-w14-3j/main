package com.tasma.commands;

import com.tasma.TaskCollection;
import com.tasma.TasmaUserInterface;

public class AliasCommand extends AbstractUndoableCommand {
	
	private String key;
	private String previousValue;
	private String newValue;
	private boolean isRemoveOperation = false;

	public AliasCommand(TasmaUserInterface userInterface,
			TaskCollection collection, String key, String value) {
		super(userInterface, collection);
		this.key = key.toLowerCase();
		this.newValue = value;
		if (this.key.equals("remove")) {
			this.key = value;
			isRemoveOperation = true;
		}
	}

	@Override
	public void execute() throws Exception {
		previousValue = AliasHandler.getCustomAlias(key);
		if (isRemoveOperation) {
			AliasHandler.removeCustomAlias(key);
		} else {
			AliasHandler.setCustomAlias(key, newValue);
		}
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
