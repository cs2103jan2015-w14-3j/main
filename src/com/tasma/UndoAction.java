/**
 * Tasma Task Manager
 */
//@author A0132763
package com.tasma;

public class UndoAction {
	private CommandType command;
	private Task task;
	
	public UndoAction(CommandType command, Task task) {
		this.command = command;
		this.task = task;
	}
	
	public CommandType getCommand() {
		return command;
	}
	
	public Task getTask() {
		return task;
	}
}
