package com.tasma.config;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.tasma.config.Config;

public class ConfigTest {

	private Config config;
	
	@Before
	public void setUp() throws Exception {
		File file = new File("test.config");
		if (file.exists()) {
			file.delete();
		}
		config = Config.getInstance("test");
	}
	
	@After
	public void tearDown() throws Exception {
		File file = new File("test.config");
		file.delete();
	}

	@Test
	public void testDefaults() throws Exception {
		assertTrue(config.isFirstRun());
		assertEquals(System.getProperty("user.dir"), config.getProperty("storage"));
	}

	@Test
	public void testSetNGet() throws Exception {
		config.setProperty("k1", "v1");
		assertEquals("v1", config.getProperty("k1"));
	}
	
	@Test
	public void testDuplicateSet() throws Exception {
		config.setProperty("k2", "v2");
		config.setProperty("k2", "v22");
		assertEquals("v22", config.getProperty("k2"));
	}
}
