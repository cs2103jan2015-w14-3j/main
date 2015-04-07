/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma.commands;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import com.tasma.InvalidInputException;

/**
 * @author Yong Shan Xian <ysx@u.nus.edu>
 */
public class AliasHandlerTest {

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@After
	public void tearDown() throws Exception {
	}

	/**
	 * Test cases for various kinds of invalid commands
	 * Test method for {@link com.tasma.commands.AliasHandler#normalize(java.lang.String)}.
	 */
	@Test(expected = InvalidInputException.class)
	public void testNormalizeInvalid() throws Exception {
		assertEquals(CommandType.INVALID, AliasHandler.normalize(""));
		assertEquals(CommandType.INVALID, AliasHandler.normalize("TODAY"));
		assertEquals(CommandType.INVALID, AliasHandler.normalize("tmr"));
	}

	/**
	 * Test cases for various add commands
	 * Test method for {@link com.tasma.commands.AliasHandler#normalize(java.lang.String)}.
	 */
	@Test
	public void testNormalizeAdd() throws Exception {
		assertEquals(CommandType.ADD, AliasHandler.normalize("add"));
		assertEquals(CommandType.ADD, AliasHandler.normalize("a"));
		assertEquals(CommandType.ADD, AliasHandler.normalize("create"));
		assertEquals(CommandType.ADD, AliasHandler.normalize("c"));
		assertEquals(CommandType.ADD, AliasHandler.normalize("ADD"));
		assertEquals(CommandType.ADD, AliasHandler.normalize("Add"));
		assertEquals(CommandType.ADD, AliasHandler.normalize("Create"));
	}

	/**
	 * Test cases for various set commands
	 * Test method for {@link com.tasma.commands.AliasHandler#normalize(java.lang.String)}.
	 */
	@Test
	public void testNormalizeSet() throws Exception {
		assertEquals(CommandType.SET, AliasHandler.normalize("set"));
		assertEquals(CommandType.SET, AliasHandler.normalize("Set"));
		assertEquals(CommandType.SET, AliasHandler.normalize("SET"));
	}

	/**
	 * Test cases for various search commands
	 * Test method for {@link com.tasma.commands.AliasHandler#normalize(java.lang.String)}.
	 */
	@Test
	public void testNormalizeSearch() throws Exception {
		assertEquals(CommandType.SEARCH, AliasHandler.normalize("find"));
		assertEquals(CommandType.SEARCH, AliasHandler.normalize("f"));
		assertEquals(CommandType.SEARCH, AliasHandler.normalize("FIND"));
		assertEquals(CommandType.SEARCH, AliasHandler.normalize("search"));
		assertEquals(CommandType.SEARCH, AliasHandler.normalize("s"));
		assertEquals(CommandType.SEARCH, AliasHandler.normalize("q"));
	}

	/**
	 * Test cases for various edit commands
	 * Test method for {@link com.tasma.commands.AliasHandler#normalize(java.lang.String)}.
	 */
	@Test
	public void testNormalizeEdit() throws Exception {
		assertEquals(CommandType.EDIT, AliasHandler.normalize("edit"));
		assertEquals(CommandType.EDIT, AliasHandler.normalize("update"));
		assertEquals(CommandType.EDIT, AliasHandler.normalize("e"));
		assertEquals(CommandType.EDIT, AliasHandler.normalize("E"));
		assertEquals(CommandType.EDIT, AliasHandler.normalize("Edit"));
		assertEquals(CommandType.EDIT, AliasHandler.normalize("EDIT"));
	}

	/**
	 * Test cases for various mark commands
	 * Test method for {@link com.tasma.commands.AliasHandler#normalize(java.lang.String)}.
	 */
	@Test
	public void testNormalizeMark() throws Exception {
		assertEquals(CommandType.MARK, AliasHandler.normalize("mark"));
		assertEquals(CommandType.MARK, AliasHandler.normalize("Mark"));
		assertEquals(CommandType.MARK, AliasHandler.normalize("MARK"));
		assertEquals(CommandType.MARK, AliasHandler.normalize("done"));
		assertEquals(CommandType.MARK, AliasHandler.normalize("do"));
		assertEquals(CommandType.MARK, AliasHandler.normalize("DONE"));
	}

	/**
	 * Test cases for various delete commands
	 * Test method for {@link com.tasma.commands.AliasHandler#normalize(java.lang.String)}.
	 */
	@Test
	public void testNormalizeDelete() throws Exception {
		assertEquals(CommandType.DELETE, AliasHandler.normalize("delete"));
		assertEquals(CommandType.DELETE, AliasHandler.normalize("DEL"));
		assertEquals(CommandType.DELETE, AliasHandler.normalize("Delete"));
		assertEquals(CommandType.DELETE, AliasHandler.normalize("DELETE"));
		assertEquals(CommandType.DELETE, AliasHandler.normalize("remove"));
		assertEquals(CommandType.DELETE, AliasHandler.normalize("r"));
	}

	/**
	 * Test cases for various help commands
	 * Test method for {@link com.tasma.commands.AliasHandler#normalize(java.lang.String)}.
	 */
	@Test
	public void testNormalizeHelp() throws Exception {
		assertEquals(CommandType.HELP, AliasHandler.normalize("help"));
		assertEquals(CommandType.HELP, AliasHandler.normalize("HELP"));
		assertEquals(CommandType.HELP, AliasHandler.normalize("h"));
		assertEquals(CommandType.HELP, AliasHandler.normalize("Help"));
		assertEquals(CommandType.HELP, AliasHandler.normalize("H"));
	}

	/**
	 * Test cases for various tutorial commands
	 * Test method for {@link com.tasma.commands.AliasHandler#normalize(java.lang.String)}.
	 */
	@Test
	public void testNormalizeTutorial() throws Exception {
		assertEquals(CommandType.TUTORIAL, AliasHandler.normalize("T"));
		assertEquals(CommandType.TUTORIAL, AliasHandler.normalize("tutorial"));
		assertEquals(CommandType.TUTORIAL, AliasHandler.normalize("Tutorial"));
		assertEquals(CommandType.TUTORIAL, AliasHandler.normalize("TUTORIAL"));
	}

	/**
	 * Test cases for various undo/redo commands
	 * Test method for {@link com.tasma.commands.AliasHandler#normalize(java.lang.String)}.
	 */
	@Test
	public void testNormalizeUndoRedo() throws Exception {
		assertEquals(CommandType.UNDO, AliasHandler.normalize("undo"));
		assertEquals(CommandType.UNDO, AliasHandler.normalize("Undo"));
		assertEquals(CommandType.UNDO, AliasHandler.normalize("UNDO"));
		assertEquals(CommandType.REDO, AliasHandler.normalize("REDO"));
		assertEquals(CommandType.REDO, AliasHandler.normalize("Redo"));
		assertEquals(CommandType.REDO, AliasHandler.normalize("redo"));
	}

	/**
	 * Test method for {@link com.tasma.commands.AliasHandler#aliases(com.tasma.commands.CommandType)}.
	 */
	@Test
	public void testAliases() throws Exception {
		List<String> result;
		result = AliasHandler.aliases(CommandType.ADD);
		assertFalse(result.isEmpty());
		result = AliasHandler.aliases(CommandType.SEARCH);
		assertFalse(result.isEmpty());
	}

	/**
	 * Invalid input test case
	 * Test method for {@link com.tasma.commands.AliasHandler#aliases(com.tasma.commands.CommandType)}.
	 */
	@Test
	public void testAliasesInvalid() throws Exception {
		List<String> result;
		result = AliasHandler.aliases(CommandType.INVALID);
		assertTrue(result.isEmpty());
		result = AliasHandler.aliases(null);
		assertTrue(result.isEmpty());
	}

}
