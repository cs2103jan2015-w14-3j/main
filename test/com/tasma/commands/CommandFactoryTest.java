package com.tasma.commands;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.tasma.MockStorage;
import com.tasma.TaskCollection;
import com.tasma.MockUserInterface;

public class CommandFactoryTest {

	private CommandFactory factory;
	private MockUserInterface userInterface;
	private MockStorage storage;
	private TaskCollection collection;
	
	@Before
	public void setUp() throws Exception {
		userInterface = new MockUserInterface();
		storage = new MockStorage();
		collection = new TaskCollection(storage);
		factory = new CommandFactory(userInterface, collection);
	}

	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test the cases of add commands
	 * @throws Exception
	 */
	@Test
	public void testGetCommand1() throws Exception {
		CommandInterface command;
		command = factory.getCommand("add test");
		assertThat(command, instanceOf(AddCommand.class));
		command = factory.getCommand("a test");
		assertThat(command, instanceOf(AddCommand.class));
		command = factory.getCommand("create test");
		assertThat(command, instanceOf(AddCommand.class));
		command = factory.getCommand("c test");
		assertThat(command, instanceOf(AddCommand.class));
	}

	/**
	 * Test the cases of list commands
	 * @throws Exception
	 */
	@Test
	public void testGetCommand2() throws Exception {
		CommandInterface command;
		command = factory.getCommand("list");
		assertThat(command, instanceOf(ListCommand.class));
		command = factory.getCommand("l");
		assertThat(command, instanceOf(ListCommand.class));
		command = factory.getCommand("up");
		assertThat(command, instanceOf(ListCommand.class));
		command = factory.getCommand("upcoming");
		assertThat(command, instanceOf(ListCommand.class));
	}

	/**
	 * Test the cases of search commands
	 * @throws Exception
	 */
	@Test
	public void testGetCommand3() throws Exception {
		CommandInterface command;
		command = factory.getCommand("search test");
		assertThat(command, instanceOf(SearchCommand.class));
		command = factory.getCommand("s test");
		assertThat(command, instanceOf(SearchCommand.class));
		command = factory.getCommand("find test");
		assertThat(command, instanceOf(SearchCommand.class));
		command = factory.getCommand("q test");
		assertThat(command, instanceOf(SearchCommand.class));
	}

	/**
	 * Test the cases of quit commands
	 * @throws Exception
	 */
	@Test
	public void testGetCommand4() throws Exception {
		CommandInterface command;
		command = factory.getCommand("exit");
		assertThat(command, instanceOf(ExitCommand.class));
		command = factory.getCommand("quit");
		assertThat(command, instanceOf(ExitCommand.class));
	}

	/**
	 * Test the cases of set commands
	 * @throws Exception
	 */
	@Test
	public void testGetCommand5() throws Exception {
		CommandInterface command;
		command = factory.getCommand("set");
		assertThat(command, instanceOf(SetCommand.class));
		command = factory.getCommand("set two test");
		assertThat(command, instanceOf(SetCommand.class));
	}
}
