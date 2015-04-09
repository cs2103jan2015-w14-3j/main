/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma;

import java.util.Stack;

import com.tasma.commands.CommandInterface;
import com.tasma.commands.UndoableCommandInterface;

/**
 * Provides history management (undo/redo) for Controller
 */
public class History {
	
	/**
	 * The undo stack that contains all undoable commands that were executed
	 */
	protected Stack<UndoableCommandInterface> undoStack = new Stack<UndoableCommandInterface>();
	
	/**
	 * The redo stack that contains all commands that were undone.
	 * The stack is reset whenever there is a new command added to the undo stack
	 */
	protected Stack<UndoableCommandInterface> redoStack = new Stack<UndoableCommandInterface>();
	
	/**
	 * The input command stack
	 */
	protected Stack<String> inputStack = new Stack<String>();
	
	/**
	 * Offers a command to the history stack. If the command is an instance of UndoableCommandInterface,
	 * it will be stored into the history stack.
	 * 
	 * When offered with a new Undoable Command, the redo stack commands will be cleared
	 * @param command The command to be placed in the history stack
	 */
	public void offer(String input, CommandInterface command) {
		inputStack.push(input);
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
