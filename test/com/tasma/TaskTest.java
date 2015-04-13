/**
 * Tasma Task Manager
 */
//@author A0118888J
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
	public void testGetStringEndDateTime() {
		Task t = new Task();
		DateTime dt = new DateTime(2015, 5, 6, 18, 20);
		t.setEndDateTime(dt);
		String s = t.getStringEndDateTime();
		assertEquals("06-05-15, 6:20PM", s);
	}

	@Test
	public void testGetStringStartDateTime() {
		Task t = new Task();
		DateTime dt = new DateTime(2015, 5, 6, 12, 0);
		t.setStartDateTime(dt);
		String s = t.getStringStartDateTime();
		assertEquals("06-05-15, 12:00PM", s); // not implemented yet?
	}

	@Test
	public void testGetFormattedEndDateTime() {
		Task t = new Task();
		DateTime dt = new DateTime();
		dt = dt.withHourOfDay(1);
		dt = dt.withMinuteOfHour(0);
		t.setEndDateTime(dt);
		String s = t.getFormattedEndDateTime();
		assertEquals("today, 1:00AM", s);
	}
	
	@Test
	public void testGetFormattedStartDateTime() {
		Task t = new Task();
		DateTime dt = new DateTime(2016, 5, 6, 22, 30);
		t.setStartDateTime(dt);
		String s = t.getFormattedStartDateTime();
		assertEquals("6 May, 2016, 10:30PM", s);
	}
	
	@Test
	public void testEditString() {
		Task t = new Task("buy snacks");
		t.setStartDateTime(new DateTime(2015, 5, 6, 11, 0));
		t.setEndDateTime(new DateTime(2016, 11, 30, 23, 59));
		String s = t.editString();
		assertNotEquals("", s);
	}

	@Test
	public void testGetTypeDeadline() {
		Task t = new Task();
		DateTime dt = new DateTime(2016, 5, 6, 22, 30);
		t.setStartDateTime(dt);
		t.setEndDateTime(dt);
		assertEquals(TaskType.DEADLINE, t.getType());
	}
	
	@Test
	public void testGetStateUpcoming() {
		Task t = new Task();
		DateTime dt = new DateTime(2016, 5, 6, 22, 30);
		t.setStartDateTime(dt);
		t.setEndDateTime(dt);
		assertEquals(TaskState.UPCOMING, t.getState());
	}
}
