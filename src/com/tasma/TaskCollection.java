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
	
	public void create(Task task) {
		
	}
	
	public void update(Task task) {
		
	}
	
	public Task get(String taskId) {
		return null;
	}
	
	public void delete(String taskId) {
		
	}
	
	public ArrayList<Task> search(String query) {
		return tasks;
	}
}
