package com.tasma;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tasma.commands.AddCommand;
import com.tasma.ui.MockUserInterface;

public class HistoryTest {
	
	private History history;
	private MockUserInterface userInterface;
	private TaskCollection collection;
	private MockStorage storage;
	private LinkedList<Task> state;

	@Before
	public void setUp() throws Exception {
		history = new History();
		userInterface = new MockUserInterface();
		storage = new MockStorage();
		collection = new TaskCollection(storage);
		collection.loadFromFile();
		state = new LinkedList<Task>();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testOffer() throws Exception {
		AddCommand command = new AddCommand(userInterface, collection, state, "test");
		command.execute();
		assertEquals(1, collection.tasks.size());
		history.offer("add test", command);
		assertEquals(1, history.undoStack.size());
		assertEquals(command, history.undoStack.peek());
	}

	@Test
	public void testUndo() throws Exception {
		AddCommand command = new AddCommand(userInterface, collection, state, "test");
		command.execute();
		assertEquals(1, collection.tasks.size());
		history.offer("add test", command);
		history.undo();
		assertEquals(0, collection.tasks.size());
		assertEquals(1, history.redoStack.size());
		assertEquals(command, history.redoStack.peek());
	}

	@Test
	public void testRedo() throws Exception {
		AddCommand command = new AddCommand(userInterface, collection, state, "test");
		command.execute();
		assertEquals(1, collection.tasks.size());
		history.offer("add test", command);
		history.undo();
		history.redo();
		assertEquals(1, collection.tasks.size());
		assertEquals(1, history.undoStack.size());
		assertEquals(command, history.undoStack.peek());
	}

	@Test
	public void testPopLastInput() throws Exception {
		AddCommand command = new AddCommand(userInterface, collection, state, "test");
		command.execute();
		assertEquals(1, collection.tasks.size());
		history.offer("add test", command);
		assertEquals(1, history.inputStack.size());
		assertEquals("add test", history.popLastInput());
		assertEquals(0, history.inputStack.size());
	}

}
