package com.tasma;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TaskCollectionTest {

	protected TaskCollection collection;
	
	protected MockStorage storage;
	
	@Before
	public void setUp() throws Exception {
		storage = new MockStorage();
		collection = new TaskCollection(storage);
		collection.loadFromFile();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreate() throws Exception {
		Task task = new Task();
		collection.create(task);
		assertEquals(1, storage.getTasks().size());
	}

}
