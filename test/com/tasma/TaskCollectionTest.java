package com.tasma;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class TaskCollectionTest {

	protected TaskCollection collection;
	
	@Before
	public void setUp() throws Exception {
		collection = new TaskCollection(new MockStorage());
		collection.loadFromFile();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCreate() {
		Task task = new Task();
		assertEquals("", task.getTaskId());
		collection.create(task);
		assertTrue(!task.getTaskId().equals(""));
	}

}
