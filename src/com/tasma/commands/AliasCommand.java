package com.tasma.commands;

import com.tasma.Palette;
import com.tasma.TaskCollection;
import com.tasma.TasmaUserInterface;
import com.tasma.UIMessage;

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
			userInterface.displayMessage(UIMessage.COMMAND_ALIAS_REMOVE, Palette.MESSAGE_SUCCESS);
		} else {
			AliasHandler.setCustomAlias(key, newValue);
			userInterface.displayMessage(UIMessage.COMMAND_ALIAS_UPDATED, Palette.MESSAGE_SUCCESS);
		}
	}

	@Override
	public void undo() throws Exception {
		if (previousValue == null) {
			AliasHandler.removeCustomAlias(key);
			userInterface.displayMessage(UIMessage.COMMAND_ALIAS_REMOVE, Palette.MESSAGE_SUCCESS);
		} else {
			AliasHandler.setCustomAlias(key, previousValue);
			userInterface.displayMessage(UIMessage.COMMAND_ALIAS_RESTORE, Palette.MESSAGE_SUCCESS);
		}
	}

}
