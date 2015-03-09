package com.tasma;

import java.util.Collection;
import java.util.Hashtable;

/**
 * 
 */
public class TaskCollection {
	
	Storage storage = new Storage();
	Hashtable<String, Task> tasks;
	
	public void loadFromFile() {
		tasks = storage.load();
	}
	
	public void create(Task task) {
		
	}
	
	public void update(Task task) {
		
	}
	
	public Task get(String taskId) {
		return null;
	}
	
	public void delete(String taskId) {
		
	}
	
	public Collection<Task> search(String query) {
		return tasks.values();
	}
}
