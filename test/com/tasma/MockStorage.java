/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma;

import java.util.LinkedList;

public final class MockStorage extends Storage {
	
	public MockStorage() throws Exception {
		super();
		// TODO Auto-generated constructor stub
	}

	protected LinkedList<Task> tasks = new LinkedList<Task>();
	
	@Override
	public void save(LinkedList<Task> tasks) {
		this.tasks = tasks;
	}

	@Override
	public LinkedList<Task> load() {
		return tasks;
	}
	
	public LinkedList<Task> getTasks() {
		return tasks;
	}
}
