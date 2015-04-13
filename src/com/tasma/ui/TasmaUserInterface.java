/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma.ui;

import java.awt.Color;
import java.util.List;

import com.tasma.Controller;
import com.tasma.Task;

/**
 * An interface of the User Interface API
 */
public interface TasmaUserInterface {
	public void initialize(Controller controller) throws Exception;
	
	public void displayTasks(List<Task> tasks);
	
	public void displayMessage(String message);
	
	public void displayMessage(String message, Color color);
	
	public void editCmdDisplay(String task);
	
	public void setHeader(String header);
	
	public void show();
	
	public void hide();
	
	public boolean isVisible();
}
