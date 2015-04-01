package com.tasma;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.joda.time.DateTime;

public class TaskTest {

	Task task;
	
	@Before
	public void setUp() throws Exception {
		task = new Task();
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testGetStringEndDateTime1() {
		Task t1 = new Task();
		DateTime dt = new DateTime(2015, 5, 6, 18, 20);
		t1.setEndDateTime(dt);
		String s = t1.getStringEndDateTime();
		assertEquals(s, "06-05-15 at 6:20pm");
		System.out.println(s);
	}

}
