package com.tasma.commands;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.util.LinkedList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tasma.MockStorage;
import com.tasma.Task;
import com.tasma.TaskCollection;
import com.tasma.MockUserInterface;

public class CommandFactoryTest {

	private CommandFactory factory;
	private MockUserInterface userInterface;
	private MockStorage storage;
	private TaskCollection collection;
	private LinkedList<Task> state;
	
	@Before
	public void setUp() throws Exception {
		userInterface = new MockUserInterface();
		storage = new MockStorage();
		collection = new TaskCollection(storage);
		factory = new CommandFactory(userInterface, collection);
		state = new LinkedList<Task>();
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testGetCommand1() {
		CommandInterface command = factory.getCommand("add test", state);
		assertThat(command, instanceOf(AddCommand.class));
	}

	@Test
	public void testGetCommand2() {
		CommandInterface command = factory.getCommand("list", state);
		assertThat(command, instanceOf(ListCommand.class));
	}

	@Test
	public void testGetCommand3() {
		CommandInterface command = factory.getCommand("search test", state);
		assertThat(command, instanceOf(SearchCommand.class));
	}

	@Test
	public void testGetCommand4() {
		CommandInterface command = factory.getCommand("list", state);
		assertThat(command, instanceOf(ListCommand.class));
	}

	@Test
	public void testGetCommand5() {
		CommandInterface command = factory.getCommand("exit", state);
		assertThat(command, instanceOf(ExitCommand.class));
	}

}
