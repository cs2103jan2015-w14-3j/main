/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma;

import java.util.LinkedList;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.List;
import org.joda.time.LocalDate;

/**
 * Provides operations on the collection of the user's tasks.
 */
public class TaskCollection {
	
	/**
	 * The storage component to save / load tasks to / from
	 */
	protected Storage storage;
	
	/**
	 * The amazing list of user's tasks, every single one stored here
	 */
	protected LinkedList<Task> tasks;
	
	protected BalloonNotification notification;
	
	public TaskCollection() throws Exception {
		this.storage = new Storage();
	}
	
	public TaskCollection(Storage storage) throws Exception {
		this.storage = storage;
	}
	
	/**
	 * Performs loading of tasks from storage file
	 * @throws Exception
	 */
	public void loadFromFile() throws Exception {
		tasks = storage.load();
	}
	
	/**
	 * Create a new task and register it into the storage
	 * @param task The Task object to was created
	 * @throws Exception
	 */
	public void create(Task task) throws Exception {
		tasks.add(task);
		storage.save(tasks);
		updateNotification();
	}
	
	/**
	 * Perform an update of the task.
	 * 
	 * Note: Since the modifications made to the task object is reflected in the list by reference,
	 * we can just directly save the list to save the changes
	 * @param task The Task object that was updated
	 * @throws Exception
	 */
	public void update(Task task) throws Exception {
		// make sure we don't put in a new task

		storage.save(tasks);
		updateNotification();
	}
	
	/**
	 * Fetch a specific task based on its index in the task list
	 * @param index The index of the task in the list
	 * @return Returns a Task object fetched if the index is valid.
	 */
	public Task get(int index) {
		return tasks.get(index);
	}
	
	/**
	 * Fetch a list of all undone tasks from the past. blast from the past!
	 * @return A list of tasks
	 */
	public List<Task> past() {
		LocalDate today = new LocalDate();
		List<Task> pastList = tasks.stream()
		    .filter(task -> !task.isDone() && task.getEndDateTime() != null
		    	&& task.getEndDateTime().toLocalDate().isBefore(today))
		    .collect(Collectors.toList());

		return pastList;
	}

	/**
	 * Filters the list of task using a predicate
	 * @param predicate The predicate / condition to filter the tasks
	 * @return A list of tasks
	 */
	public List<Task> filter(Predicate<? super Task> predicate) {
		List<Task> result = tasks.stream()
		    .filter(predicate)
		    .collect(Collectors.toList());

		return result;
	}
	
	/**
	 * Fetch a list of all upcoming tasks that are not done yet
	 * @return A list of tasks
	 */
	public List<Task> upcoming() {
		LocalDate yesterday = (new LocalDate()).minusDays(1);
		List<Task> upcomingList = tasks.stream()
		    .filter(task -> !task.isDone() && task.getStartDateTime() != null &&
		    	task.getStartDateTime().toLocalDate().isAfter(yesterday))
		    .collect(Collectors.toList());
		
		return upcomingList;
	}
	
	/**
	 * Deletes a task from the list. Point of no return, period.
	 * @param task The task to be deleted.
	 * @throws Exception
	 */
	public void delete(Task task) throws Exception {
		tasks.remove(task);
		storage.save(tasks);
		updateNotification();
	}
	
	/**
	 * Performs a search on all tasks
	 * @param query The search query
	 * @return A list of tasks
	 */
	public List<Task> search(String query) {
		List<Task> resultList = tasks.stream()
		    .filter(task -> task.getDetails().indexOf(query) != -1)
		    .collect(Collectors.toList());
		
		return resultList;
	}

	/**
	 * Fetch a list of tasks that are not done yet 
	 * @return A list of tasks
	 */
	public List<Task> notDone() {
		List<Task> nodoneList = tasks.stream()
		    .filter(task -> !task.isDone())
		    .collect(Collectors.toList());
		return nodoneList;
	}

	/**
	 * Fetch a list of tasks that are done already
	 * @return A list of tasks
	 */
	public List<Task> done() {
		List<Task> doneList = tasks.stream()
		    .filter(task -> task.isDone())
		    .collect(Collectors.toList());
		return doneList;
	}

	public List<Task> floating() {
		List<Task> doneList = tasks.stream()
			    .filter(task -> !task.isDone() && task.getStartDateTime() == null && task.getEndDateTime() == null)
			    .collect(Collectors.toList());
			return doneList;
	}
	
	//@author A0119434H
	private void updateNotification() {
		notification.updateNotifications(notDone());
	}
}
