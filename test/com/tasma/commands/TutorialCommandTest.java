/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma.commands;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tasma.MockStorage;
import com.tasma.MockUserInterface;
import com.tasma.TaskCollection;

public class TutorialCommandTest {

	private TutorialCommand command;
	
	private MockStorage storage;
	
	private TaskCollection collection;
	
	private MockUserInterface userInterface;
	
	@Before
	public void setUp() throws Exception {
		userInterface = new MockUserInterface();
		storage = new MockStorage();
		collection = new TaskCollection(storage);
		collection.loadFromFile();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testExecute() throws Exception {
		command = new TutorialCommand(userInterface, collection);
		command.execute();
		assertNotEquals("", userInterface.getLastDisplayedMessage());
	}

}
