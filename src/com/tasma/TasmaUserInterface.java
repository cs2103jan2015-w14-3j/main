package com.tasma;

import java.util.ArrayList;

public interface TasmaUserInterface {
	public void initialize();
	
	public void displayTasks(ArrayList<Task> tasks);
	
	public void displayMessage(String message);
}
