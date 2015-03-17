package com.tasma;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ControllerTest {
	
	private Controller controller;
	private Storage storage;

	@Before
	public void setUp() throws Exception {
		storage = new MockStorage();
		TaskCollection collection = new TaskCollection(storage);
		controller = new Controller(collection);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = Exception.class)
	public void testInitializeFail() throws Exception {
		// try to initialize without setting the UI, you would die
		controller.initialize();
	}

	@Test
	public void testExecuteInput() {
		
	}
}
