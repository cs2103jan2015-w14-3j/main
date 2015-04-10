/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma.commands;

import com.tasma.TaskCollection;
import com.tasma.ui.TasmaUserInterface;

/**
 * Provides abstraction of an undoable command
 */
public abstract class AbstractUndoableCommand
	extends AbstractCommand
	implements UndoableCommandInterface {

	public AbstractUndoableCommand(TasmaUserInterface userInterface,
			TaskCollection collection) {
		super(userInterface, collection);
	}
}
