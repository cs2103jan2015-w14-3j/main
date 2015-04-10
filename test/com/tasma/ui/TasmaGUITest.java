package com.tasma.ui;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;

import com.tasma.ui.TasmaGUI;

import junit.framework.TestCase;
import junit.framework.TestSuite;

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
	public void testIsShowing() {//If window is showing
		assertTrue(window.isShowing());
	}

}
