/**
 * Tasma Task Manager
 */
//@author A0132763
package com.tasma.commands;

import com.tasma.Config;
import com.tasma.TaskCollection;
import com.tasma.TasmaUserInterface;

public class SetCommand extends AbstractCommand implements UndoableCommandInterface {
	
	private String key;
	private String previousValue;
	private String newValue;

	public SetCommand(TasmaUserInterface userInterface,
			TaskCollection collection, String key, String value) {
		super(userInterface, collection);
		this.key = key;
		this.newValue = value;
	}

	@Override
	public void execute() throws Exception {
		Config config = Config.getInstance();
		previousValue = config.getProperty(key);
		config.setProperty(key, newValue);
	}

	@Override
	public void undo() throws Exception {
		Config config = Config.getInstance();
		config.setProperty(key, previousValue);
	}

}
