package com.tasma.commands;

public interface CommandInterface {
	public void execute() throws Exception;
	
	public void undo() throws Exception;
}
