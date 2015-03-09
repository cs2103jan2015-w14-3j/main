package com.tasma;

import java.util.Hashtable;

public final class MockStorage extends Storage {
	@Override
	public void save(Hashtable<String, Task> tasks) {
	}

	@Override
	public Hashtable<String, Task> load() {
		Hashtable<String, Task> tasks = new Hashtable<String, Task>();
		return tasks;
	}
}
