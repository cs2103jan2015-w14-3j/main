package com.tasma.config;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.tasma.config.Config;



public class ConfigTest {

	private Config config;
	
	@Before
	public void setUp() throws Exception {
		config = Config.getInstance("test");
	}
	
	@After
	public void tearDown() throws Exception {
		
	}

	@Test
	public void testSetNGet() throws Exception {
		config.setProperty("k1", "v1");
		assertEquals(config.getProperty("k1"), "v1");
	}
	
	@Test
	public void testDuplicateSet() throws Exception {
		config.setProperty("k2", "v2");
		config.setProperty("k2", "v22");
		assertEquals(config.getProperty("k2"), "v22");
	}
}
