package com.tasma;

import java.util.Collection;
import java.util.Hashtable;
import org.hashids.Hashids;

/**
 * 
 */
public class TaskCollection {
	
	// a random salt taken from random.org :D
	protected static final String HASHIDS_SALT = "cTEBjAzL17k4Vx81t6WPqqzmmN37mvK6UHEZgI8B7UCESsFjksU1LiDwfQ5P";
	
	Storage storage;
	Hashtable<String, Task> tasks;
	
	public TaskCollection() {
		this.storage = new Storage();
	}
	
	public TaskCollection(Storage storage) {
		this.storage = storage;
	}
	
	public void loadFromFile() {
		tasks = storage.load();
	}
	
	public void create(Task task) {
		Hashids hasher = new Hashids(HASHIDS_SALT, 3);
		String uniqueId = hasher.encode((long)(Math.random() * 99), tasks.size());
		int i = 0;
		while (tasks.containsKey(uniqueId)) {
			uniqueId = hasher.encode((long)(Math.random() * 99), ++i);
		}
		task.setTaskId(uniqueId);
		tasks.put(task.getTaskId(), task);
		storage.save(tasks);
	}
	
	public void update(Task task) {
		
	}
	
	public Task get(String taskId) {
		return tasks.get(taskId);
	}
	
	public void delete(String taskId) {
		tasks.remove(taskId);
	}
	
	public Collection<Task> search(String query) {
		return tasks.values();
	}
}
