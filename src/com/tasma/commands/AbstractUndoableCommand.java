/**
 * Tasma Task Manager
 */
//@author A0132763
package com.tasma.commands;

import com.tasma.TaskCollection;
import com.tasma.TasmaUserInterface;

public abstract class AbstractUndoableCommand extends AbstractCommand implements
		UndoableCommandInterface {

	public AbstractUndoableCommand(TasmaUserInterface userInterface,
			TaskCollection collection) {
		super(userInterface, collection);
		// TODO Auto-generated constructor stub
	}
}
