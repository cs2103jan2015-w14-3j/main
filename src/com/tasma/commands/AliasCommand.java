/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma.commands;

import com.tasma.TaskCollection;
import com.tasma.UIMessage;
import com.tasma.ui.Palette;
import com.tasma.ui.TasmaUserInterface;

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
            userInterface.displayMessage(String.format(UIMessage.COMMAND_ALIAS_REMOVE, key), Palette.MESSAGE_SUCCESS);
        } else {
            AliasHandler.setCustomAlias(key, newValue);
            userInterface.displayMessage(String.format(UIMessage.COMMAND_ALIAS_UPDATED, key), Palette.MESSAGE_SUCCESS);
        }
    }

    @Override
    public void undo() throws Exception {
        if (previousValue == null) {
            AliasHandler.removeCustomAlias(key);
            userInterface.displayMessage(String.format(UIMessage.COMMAND_ALIAS_REMOVE, key), Palette.MESSAGE_SUCCESS);
        } else {
            AliasHandler.setCustomAlias(key, previousValue);
            userInterface.displayMessage(String.format(UIMessage.COMMAND_ALIAS_RESTORE, key), Palette.MESSAGE_SUCCESS);
        }
    }

}
