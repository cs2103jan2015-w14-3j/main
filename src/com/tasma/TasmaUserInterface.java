/**
 * Tasma Task Manager
 */
package com.tasma;

import java.util.Collection;

public interface TasmaUserInterface {
	public void initialize() throws Exception;
	
	public void displayTasks(Collection<Task> tasks);
	
	public void displayMessage(String message);
}
