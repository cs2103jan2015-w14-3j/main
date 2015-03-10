/**
 * Tasma Task Manager
 */
package com.tasma;

import java.util.Collection;
import java.util.Hashtable;
import java.util.stream.Collectors;
import java.util.List;
import org.hashids.Hashids;

/**
 * 
 */
public class TaskCollection {
	
	// a random salt taken from random.org :D
	protected static final String HASHIDS_SALT = "cTEBjAzL17k4Vx81t6WPqqzmmN37mvK6UHEZgI8B7UCESsFjksU1LiDwfQ5P";
	
	// a small number that allows the generation of a small random number so that the Hashids generated is small
	protected static final int HASHIDS_RANDOM_MULTIPLIER = 99;
	
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
		// the following code will generate a nice short ID that should be unique in the collection.
		// this will allow the user to identify tasks through the use of these IDs.
		Hashids hasher = new Hashids(HASHIDS_SALT, 3);
		String uniqueId = hasher.encode((long)(Math.random() * HASHIDS_RANDOM_MULTIPLIER), tasks.size());
		int i = 0;
		while (tasks.containsKey(uniqueId)) {
			uniqueId = hasher.encode((long)(Math.random() * HASHIDS_RANDOM_MULTIPLIER), ++i);
		}
		task.setTaskId(uniqueId);
		tasks.put(task.getTaskId(), task);
		storage.save(tasks);
	}
	
	public void update(Task task) {
		tasks.put(task.getTaskId(), task);
		storage.save(tasks);
	}
	
	public Task get(String taskId) {
		return tasks.get(taskId);
	}
	
	public Collection<Task> upcoming() {
		List<Task> upcomingList = tasks.values().stream()
		    .filter(task -> !task.isDone() && !task.isArchived()).collect(Collectors.toList());

		return upcomingList;
	}
	
	public void delete(String taskId) {
		tasks.remove(taskId);
	}
	
	public Collection<Task> search(String query) {
		List<Task> resultList = tasks.values().stream()
			    .filter(task -> task.getDetails().indexOf(query) != -1).collect(Collectors.toList());
		
		return resultList;
	}
}
