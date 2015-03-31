package com.tasma;

import java.util.Collection;

public class MockUserInterface implements TasmaUserInterface {
	
	private Collection<Task> lastDisplayedTasks;
	private String lastDisplayedMessage;

	@Override
	public void initialize(Controller controller) throws Exception {
		// test interface, nothing to initialize for now.
		controller.setUserInterface(this);
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

	@Override
	public void editCmdDisplay(String task) {
		lastDisplayedMessage = task;
	}

	@Override
	public void show() {
		
	}

	@Override
	public void hide() {
		
	}

	@Override
	public boolean isVisible() {
		return false;
	}

}
