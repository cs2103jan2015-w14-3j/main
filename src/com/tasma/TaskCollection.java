/**
 * Tasma Task Manager
 */
//@author A0132763
package com.tasma;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.stream.Collectors;
import java.util.List;

/**
 * 
 */
public class TaskCollection {
	
	Storage storage;
	LinkedList<Task> tasks;
	
	public TaskCollection() throws Exception {
		this.storage = new Storage();
	}
	
	public TaskCollection(Storage storage) throws Exception {
		this.storage = storage;
	}
	
	public void loadFromFile() throws Exception {
		tasks = storage.load();
	}
	
	public void create(Task task) throws Exception {
		// the following code will generate a nice short ID that should be unique in the collection.
		// this will allow the user to identify tasks through the use of these IDs.
		tasks.add(task);
		storage.save(tasks);
	}
	
	public void update(Task task) throws Exception {
		// make sure we don't put in a new task

		storage.save(tasks);
	}
	
	public Task get(int index) {
		return tasks.get(index);
	}
	
	public List<Task> past() {
		List<Task> pastList = tasks.stream()
		    .filter(task -> !task.isDone() && !task.isArchived() && task.getEndDateTime() != null && task.getEndDateTime().isBeforeNow()).collect(Collectors.toList());

		return pastList;
	}
	
	public List<Task> upcoming() {
		List<Task> upcomingList = tasks.stream()
		    .filter(task -> !task.isDone() && !task.isArchived()).collect(Collectors.toList());
		
		Collections.sort(upcomingList, new Comparator<Task>() {
		    @Override
		    public int compare(Task t1, Task t2) {
		    	if (t1.getStartDateTime() == null && t2.getStartDateTime() == null) {
		    		return 0;
		    	} else if (t1.getStartDateTime() == null) {
		    		return -1;
		    	} else if (t2.getStartDateTime() == null) {
		    		return 1;
		    	}
		    	return t1.getStartDateTime().compareTo(t2.getStartDateTime());
		    }
		}); 
		
		return upcomingList;
	}
	
	public void delete(Task task) throws Exception {
		tasks.remove(task);
		storage.save(tasks);
	}
	
	public List<Task> search(String query) {
		List<Task> resultList = tasks.stream()
			    .filter(task -> !task.isArchived() && task.getDetails().indexOf(query) != -1).collect(Collectors.toList());
		
		return resultList;
	}

	public List<Task> done() {
		List<Task> doneList = tasks.stream()
			    .filter(task -> task.isDone() && !task.isArchived()).collect(Collectors.toList());
		return doneList;
	}
}
