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
		Task t = new Task();
		DateTime dt = new DateTime(2015, 5, 6, 18, 20);
		t.setEndDateTime(dt);
		String s = t.getStringEndDateTime();
		assertEquals(s, "06-05-15 at 6:20pm");
	}

	@Test
	public void testGetStringEndDateTime2() {
		Task t = new Task();
		DateTime dt = new DateTime(2015, 5, 6, 0, 0);
		t.setEndDateTime(dt);
		String s = t.getStringEndDateTime();
		assertEquals(s, "06-05-15 at 12:00am"); // not implemented yet?
	}

	@Test
	public void testGetStringEndDateTime4() {
		Task t = new Task();
		DateTime dt = new DateTime(2015, 5, 6, 1, 10);
		t.setEndDateTime(dt);
		String s = t.getStringEndDateTime();
		assertEquals(s, "06-05-15 at 1:10am"); // not implemented yet?
	}
	
	@Test
	public void testGetStringEndDateTime3() {
		Task t = new Task();
		DateTime dt = new DateTime(2015, 12, 13, 12, 30);
		t.setEndDateTime(dt);
		String s = t.getStringEndDateTime();
		assertEquals(s, "13-12-15 at 12:30pm");
	}
	
	@Test
	public void testToString() {
		Task t = new Task("aabc", "buy snacks");
		t.setStartDateTime(new DateTime(2015, 5, 6, 11, 0));
		t.setEndDateTime(new DateTime(2016, 11, 30, 23, 59));
		String s = t.toString();
		System.out.println(s);
	}
}
