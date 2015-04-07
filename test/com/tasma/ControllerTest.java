/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ControllerTest {
	
	private Controller controller;
	private MockStorage storage;
	private MockUserInterface ui;
	
	@Before
	public void setUp() throws Exception {
		ui = new MockUserInterface();
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
	public void testExecuteInput1() throws Exception {
		controller.setUserInterface(ui);
		controller.initialize();
		controller.executeInput("add test");
		assertEquals(1, storage.getTasks().size());
	}

	@Test
	public void testExecuteInput2() throws Exception {
		controller.setUserInterface(ui);
		controller.initialize();
		controller.executeInput("add test");
		assertEquals(1, storage.getTasks().size());
		controller.executeInput("undo");
		assertEquals(0, storage.getTasks().size());
	}

	@Test
	public void testExecuteInput3() throws Exception {
		controller.setUserInterface(ui);
		controller.initialize();
		controller.executeInput("add test");
		assertEquals(1, storage.getTasks().size());
		controller.executeInput("undo");
		assertEquals(0, storage.getTasks().size());
		controller.executeInput("redo");
		assertEquals(1, storage.getTasks().size());
	}
}
