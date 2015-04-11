/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma.ui;

import java.awt.Color;
import java.util.List;

import com.tasma.Controller;
import com.tasma.Task;
import com.tasma.ui.TasmaUserInterface;

public class MockUserInterface implements TasmaUserInterface {
	
	private List<Task> lastDisplayedTasks;
	private String lastDisplayedMessage;
	private String headerText;

	@Override
	public void initialize(Controller controller) throws Exception {
		// test interface, nothing to initialize for now.
		controller.setUserInterface(this);
	}

	@Override
	public void displayTasks(List<Task> tasks) {
		lastDisplayedTasks = tasks;
	}
	
	@Override
	public void displayMessage(String message) {
		displayMessage(message, null);
	}

	@Override
	public void displayMessage(String message, Color color) {
		lastDisplayedMessage = message;
	}
	
	public List<Task> getLastDisplayedTasks() {
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

	@Override
	public void setHeader(String header) {
		this.headerText = header;
	}
	
	public String getHeader() {
		return headerText;
	}

}
