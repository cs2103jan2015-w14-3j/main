/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma.commands;

import com.tasma.TaskCollection;
import com.tasma.UIMessage;
import com.tasma.config.Config;
import com.tasma.ui.HotKeyHandler;
import com.tasma.ui.Palette;
import com.tasma.ui.TasmaUserInterface;

public class SetCommand extends AbstractUndoableCommand {
    
    private String key;
    private String previousValue;
    private String newValue;

    public SetCommand(TasmaUserInterface userInterface,
            TaskCollection collection, String key, String value) {
        super(userInterface, collection);
        this.key = key.toLowerCase();
        this.newValue = value;
    }

    @Override
    public void execute() throws Exception {
        if (key.equals("")) {
            throw new NotExecutedException();
        } else {
            Config config = Config.getInstance();
            previousValue = config.getProperty(key);
            if (newValue.equals("")) {
                userInterface.editCmdDisplay(
                		String.format("set %s %s", key, previousValue));
            } else {
                config.setProperty(key, newValue);

                HotKeyHandler handler = new HotKeyHandler(userInterface);
                handler.setHotKey();
                userInterface.displayMessage(
                		String.format(UIMessage.COMMAND_SET_SUCCESS, key),
                		Palette.MESSAGE_SUCCESS);
            }
        }
    }

    @Override
    public void undo() throws Exception {
        if (key.equals("")) {
            throw new NotExecutedException();
        } else {
            Config config = Config.getInstance();
            config.setProperty(key, previousValue);
            userInterface.displayMessage(
            		String.format(UIMessage.COMMAND_SET_UNDO, key),
            		Palette.MESSAGE_SUCCESS);
        }
    }

}
