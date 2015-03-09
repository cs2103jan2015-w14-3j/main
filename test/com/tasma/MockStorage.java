package com.tasma;

import java.util.Hashtable;

public final class MockStorage extends Storage {
	protected Hashtable<String, Task> tasks = new Hashtable<String, Task>();
	
	@Override
	public void save(Hashtable<String, Task> tasks) {
		this.tasks = tasks;
	}

	@Override
	public Hashtable<String, Task> load() {
		return tasks;
	}
}
