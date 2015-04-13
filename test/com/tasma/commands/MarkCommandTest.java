/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma.commands;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tasma.MockStorage;
import com.tasma.Task;
import com.tasma.TaskCollection;
import com.tasma.ui.MockUserInterface;

public class MarkCommandTest {

	private MarkCommand command;
	
	private MockStorage storage;
	
	private TaskCollection collection;
	
	private MockUserInterface userInterface;
	
	private Task task;
	
	@Before
	public void setUp() throws Exception {
		userInterface = new MockUserInterface();
		storage = new MockStorage();
		collection = new TaskCollection(storage);
		collection.loadFromFile();
		
		task = new Task();
		storage.getTasks().add(task);
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testExecute() throws Exception {
		LinkedList<Task> state = new LinkedList<Task>();
		state.add(task);
		
		command = new MarkCommand(userInterface, collection, state, Arrays.asList(0));
		command.execute();
		assertEquals(true, storage.getTasks().get(0).isDone());
	}

	@Test
	public void testUndo() throws Exception {
		LinkedList<Task> state = new LinkedList<Task>();
		state.add(task);
		
		command = new MarkCommand(userInterface, collection, state, Arrays.asList(0));
		command.execute();
		assertEquals(true, storage.getTasks().get(0).isDone());
		command.undo();
		assertEquals(false, storage.getTasks().get(0).isDone());
	}

}
