package com.tasma;

import java.util.Collection;

public class TestUserInterface implements TasmaUserInterface {
	
	private Collection<Task> lastDisplayedTasks;
	private String lastDisplayedMessage;

	@Override
	public void initialize() throws Exception {
		// test interface, nothing to initialize for now.
	}

	@Override
	public void displayTasks(Collection<Task> tasks) {
		lastDisplayedTasks = tasks;
	}

	@Override
	public void displayMessage(String message) {
		lastDisplayedMessage = message;
	}
	
	public Collection<Task> getLastDisplayedTasks() {
		return lastDisplayedTasks;
	}
	
	public String getLastDisplayedMessage() {
		return lastDisplayedMessage;
	}

}
