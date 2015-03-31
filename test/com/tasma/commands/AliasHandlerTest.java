/**
 * 
 */
package com.tasma.commands;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Sam Yong
 *
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
	 * Test method for {@link com.tasma.commands.AliasHandler#normalize(java.lang.String)}.
	 */
	@Test
	public void testNormalizeInvalid() {
		assertEquals(CommandType.INVALID, AliasHandler.normalize(""));
		assertEquals(CommandType.INVALID, AliasHandler.normalize("TODAY"));
		assertEquals(CommandType.INVALID, AliasHandler.normalize("tmr"));
	}

	/**
	 * Test method for {@link com.tasma.commands.AliasHandler#normalize(java.lang.String)}.
	 */
	@Test
	public void testNormalizeAdd() {
		assertEquals(CommandType.ADD, AliasHandler.normalize("add"));
		assertEquals(CommandType.ADD, AliasHandler.normalize("a"));
		assertEquals(CommandType.ADD, AliasHandler.normalize("create"));
		assertEquals(CommandType.ADD, AliasHandler.normalize("c"));
		assertEquals(CommandType.ADD, AliasHandler.normalize("ADD"));
		assertEquals(CommandType.ADD, AliasHandler.normalize("Add"));
		assertEquals(CommandType.ADD, AliasHandler.normalize("Create"));
	}

	/**
	 * Test method for {@link com.tasma.commands.AliasHandler#normalize(java.lang.String)}.
	 */
	@Test
	public void testNormalizeSet() {
		assertEquals(CommandType.SET, AliasHandler.normalize("set"));
		assertEquals(CommandType.SET, AliasHandler.normalize("Set"));
		assertEquals(CommandType.SET, AliasHandler.normalize("SET"));
	}

	/**
	 * Test method for {@link com.tasma.commands.AliasHandler#aliases(com.tasma.commands.CommandType)}.
	 */
	@Test
	public void testAliases() {
		fail("Not yet implemented");
	}

}
