package com.tasma;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ControllerTest {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test(expected = Exception.class)
	public void testInitializeFail() throws Exception {
		Controller controller = new Controller();
		controller.initialize();
	}

	@Test
	public void testExecute() {
		fail("Not yet implemented");
	}
}
