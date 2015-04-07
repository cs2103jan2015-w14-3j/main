/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma.commands;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import java.util.LinkedList;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.tasma.MockStorage;
import com.tasma.MockUserInterface;
import com.tasma.Task;
import com.tasma.TaskCollection;
import com.tasma.UIMessage;

/**
 * @author Yong Shan Xian <ysx@u.nus.edu>
 */
public class AddCommandTest {

	private AddCommand command;
	
	private MockStorage storage;
	
	private TaskCollection collection;
	
	private MockUserInterface userInterface;
	
	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		userInterface = new MockUserInterface();
		storage = new MockStorage();
		collection = new TaskCollection(storage);
		collection.loadFromFile();
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}
	
	/**
	 * Test method for {@link com.tasma.commands.AddCommand#execute()}.
	 */
	@Test
	public void testExecute() throws Exception {
		assertEquals(0, storage.getTasks().size());
		LinkedList<Task> state = new LinkedList<Task>();
		command = new AddCommand(userInterface, collection, state, "send car to workshop on next tuesday");
		command.execute();
		assertEquals(1, storage.getTasks().size());
		assertThat(storage.getTasks().get(0), instanceOf(Task.class));
	}
	
	/**
	 * Test method for {@link com.tasma.commands.AddCommand#execute()}.
	 */
	@Test
	public void testExecuteEmpty() throws Exception {
		assertEquals(0, storage.getTasks().size());
		LinkedList<Task> state = new LinkedList<Task>();
		command = new AddCommand(userInterface, collection, state, "");
		command.execute();
		assertEquals(0, storage.getTasks().size());
		assertEquals(UIMessage.COMMAND_ADD_ARG_EMPTY, userInterface.getLastDisplayedMessage());
	}

	/**
	 * Test method for {@link com.tasma.commands.AddCommand#undo()}.
	 */
	@Test
	public void testUndo() throws Exception {
		assertEquals(0, storage.getTasks().size());
		LinkedList<Task> state = new LinkedList<Task>();
		command = new AddCommand(userInterface, collection, state, "test");
		command.execute();
		assertEquals(1, storage.getTasks().size());
		assertEquals(String.format(UIMessage.COMMAND_ADD_SUCCESS, "test"), userInterface.getLastDisplayedMessage());
		command.undo();
		assertEquals(0, storage.getTasks().size());
	}

}
