package com.tasma;

import java.util.Stack;

import com.tasma.commands.CommandInterface;
import com.tasma.commands.UndoableCommandInterface;

public class History {
	protected Stack<UndoableCommandInterface> undoStack = new Stack<UndoableCommandInterface>();
	protected Stack<UndoableCommandInterface> redoStack = new Stack<UndoableCommandInterface>();
	
	public void offer(CommandInterface command) {
		if (command instanceof UndoableCommandInterface) {
			undoStack.push((UndoableCommandInterface) command);
			redoStack.clear();
		}
	}
	
	public void undo() throws Exception {
		if (undoStack.size() > 0) {
			UndoableCommandInterface command = undoStack.pop();
			command.undo();
			redoStack.push(command);
		}
	}
	
	public void redo() throws Exception {
		if (redoStack.size() > 0) {
			UndoableCommandInterface command = redoStack.pop();
			command.execute();
			undoStack.push(command);
		}
	}
	
}
