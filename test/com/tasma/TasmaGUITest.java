package com.tasma;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TasmaGUITest extends TestCase {
	
	TasmaGUI window;
	
	public TasmaGUITest( String testName ) {
		super(testName);
	}
	
	public static Test suite() {
		return (Test) new TestSuite(TasmaGUITest.class);
	}
	
	//Rigorous Test
	public void testTasmaGUI() {
		assertTrue(true);
	}
	
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
