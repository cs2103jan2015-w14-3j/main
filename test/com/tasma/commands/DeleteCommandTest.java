/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma.commands;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tasma.MockStorage;
import com.tasma.MockUserInterface;
import com.tasma.Task;
import com.tasma.TaskCollection;

public class DeleteCommandTest {

	private DeleteCommand command;
	
	private MockStorage storage;
	
	private TaskCollection collection;
	
	private MockUserInterface userInterface;

	@Before
	public void setUp() throws Exception {
		userInterface = new MockUserInterface();
		storage = new MockStorage();
		storage.getTasks().add(new Task("test"));
		collection = new TaskCollection(storage);
		collection.loadFromFile();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testExecute() throws Exception {
		LinkedList<Task> state = new LinkedList<Task>();
		state.add(storage.getTasks().get(0));
		command = new DeleteCommand(userInterface, collection, state, 0);
		command.execute();
		assertEquals(0, state.size());
		assertEquals(0, storage.getTasks().size());
	}

	@Test
	public void testUndo() throws Exception {
		LinkedList<Task> state = new LinkedList<Task>();
		Task task = storage.getTasks().get(0);
		state.add(task);
		command = new DeleteCommand(userInterface, collection, state, 0);
		command.execute();
		assertEquals(0, state.size());
		assertEquals(0, storage.getTasks().size());
		command.undo();
		assertEquals(1, storage.getTasks().size());
		assertEquals(task, storage.getTasks().get(0));
	}

}
