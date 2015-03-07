package com.tasma;

import java.util.ArrayList;

/**
 * 
 */
public class TaskCollection {
	
	Storage storage = new Storage();
	ArrayList<Task> tasks;
	
	public void loadFromFile() {
		tasks = storage.load();
	}
	
	public void add(Task task) {
		
	}
	
	public void undoCommand() {
		
	}
	
}
