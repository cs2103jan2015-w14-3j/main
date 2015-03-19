package com.tasma.commands;

import com.tasma.TasmaUserInterface;
import com.tasma.TaskCollection;

public abstract class AbstractCommand implements CommandInterface {

	protected TasmaUserInterface userInterface;
	protected TaskCollection collection;
	
	public AbstractCommand(TasmaUserInterface userInterface, TaskCollection collection) {
		this.userInterface = userInterface;
		this.collection = collection;
	}

}
