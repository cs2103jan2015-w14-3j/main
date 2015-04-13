/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma.commands;

import com.tasma.TaskCollection;
import com.tasma.ui.TasmaUserInterface;

/**
 * This is the abstract command where the constructor takes in the
 * userInterface and task collection for usage
 */
public abstract class AbstractCommand implements CommandInterface {

	protected TasmaUserInterface userInterface;
	protected TaskCollection collection;
	
	public AbstractCommand(TasmaUserInterface userInterface,
			TaskCollection collection) {
		this.userInterface = userInterface;
		this.collection = collection;
	}

}
