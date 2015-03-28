/**
 * Tasma Task Manager
 */
//@author A0132763
package com.tasma;

import java.util.Collection;

public interface TasmaUserInterface {
	public void initialize(Controller controller) throws Exception;
	
	public void displayTasks(Collection<Task> tasks);
	
	public void displayMessage(String message);
	
	public void editCmdDisplay(String task);
	
	public void helpCmdDisplay(String helpMsg);
	
	public void show();
	
	public void hide();
	
	public boolean isVisible();
}
