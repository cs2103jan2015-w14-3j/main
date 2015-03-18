package com.tasma;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import junit.framework.TestCase;

public class TasmaGUITest extends TestCase {
	
	TasmaGUI window;
	
	@Before
	public void setUp() throws Exception{
		window = new TasmaGUI();
		window.setVisible(true);
	}
	
	@After
	public void tearDown() throws Exception{
		window.dispose();
	}

	@Test
	public void testIsShowing() {
		assertTrue(window.isShowing());
	}

}
