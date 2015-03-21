package com.tasma;

import java.util.Stack;

import com.tasma.commands.CommandInterface;
import com.tasma.commands.UndoableCommandInterface;

public class History {
	protected Stack<UndoableCommandInterface> undoStack = new Stack<UndoableCommandInterface>();
	protected Stack<UndoableCommandInterface> redoStack = new Stack<UndoableCommandInterface>();
	
	/**
	 * Offers a command to the history stack. If the command is an instance of UndoableCommandInterface,
	 * it will be stored into the history stack.
	 * 
	 * When offered with a new Undoable Command, the redo stack commands will be cleared
	 * @param command The command to be placed in the history stack
	 */
	public void offer(CommandInterface command) {
		if (command instanceof UndoableCommandInterface) {
			undoStack.push((UndoableCommandInterface) command);
			redoStack.clear();
		}
	}
	
	/**
	 * Performs an undo operation on the history.
	 * @throws Exception Forwards the exception thrown from the command.
	 */
	public void undo() throws Exception {
		if (undoStack.size() > 0) {
			UndoableCommandInterface command = undoStack.pop();
			command.undo();
			redoStack.push(command);
		}
	}
	
	/**
	 * Performs a redo operation on the history.
	 * @throws Exception Forwards the exception thrown from the command.
	 */
	public void redo() throws Exception {
		if (redoStack.size() > 0) {
			UndoableCommandInterface command = redoStack.pop();
			command.execute();
			undoStack.push(command);
		}
	}
	
}
