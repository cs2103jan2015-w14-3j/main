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
	
	public void get(String taskId) {
		
	}
	
	public void delete(String taskId) {
		
	}
	
	public ArrayList<Task> search(String query) {
		return tasks;
	}
}
