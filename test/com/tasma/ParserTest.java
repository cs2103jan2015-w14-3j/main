package com.tasma;

import static org.junit.Assert.*;

import org.joda.time.DateTimeConstants;
import org.joda.time.DateTime;
import org.junit.Test;

public class ParserTest {
	
	@Test
	public void test1() {
		Parser caller = new Parser();
		Task parsedTask = new Task();
		
		try {
			parsedTask = caller.parse("do cs2105 on next mon");
		} catch (InvalidInputException e) {
			System.out.println(e.getMessage());
		}
		
		Task temp = new Task();
		
		temp.setDetails("do cs2105");
		
		DateTime d = initializeDateTime();
		d = d.plusWeeks(1);
		d = d.withDayOfWeek(DateTimeConstants.MONDAY);	
		temp.setEndDateTime(d);
		
		assertEquals(temp.getDetails(), parsedTask.getDetails());
		assertEquals(temp.getEndDateTime(), parsedTask.getEndDateTime());
		assertEquals(temp.getLocation(), parsedTask.getLocation());
	}
	
	@Test
	public void test2() {
		Parser caller = new Parser();
		Task parsedTask = new Task();
		
		try {
			parsedTask = caller.parse("do cs2105 on mon");
		} catch (InvalidInputException e) {
			System.out.println(e.getMessage());
		}
		
		Task temp = new Task();
		
		temp.setDetails("do cs2105");

		DateTime d = initializeDateTime();
		d = d.withDayOfWeek(DateTimeConstants.MONDAY);	
		
		if (d.isBefore(new DateTime())) {
			d = d.plusWeeks(1);
		}
		
		temp.setEndDateTime(d);
		
		assertEquals(temp.getDetails(), parsedTask.getDetails());
		assertEquals(temp.getEndDateTime(), parsedTask.getEndDateTime());
		assertEquals(temp.getLocation(), parsedTask.getLocation());
	}
	
	@Test
	public void test3() {
		Parser caller = new Parser();
		Task parsedTask = new Task();
		
		try {
			parsedTask = caller.parse("do cs2105 on next mon");
		} catch (InvalidInputException e) {
			System.out.println(e.getMessage());
		}
		
		Task temp = new Task();
		
		temp.setDetails("do cs2105");

		DateTime d = new DateTime();
		d = d.plusWeeks(1);
		d = d.withDayOfWeek(DateTimeConstants.MONDAY);	
		temp.setEndDateTime(d);
		
		temp.setLocation("ALL");
		
		assertEquals(temp.getDetails(), parsedTask.getDetails());
		assertEquals(temp.getEndDateTime(), parsedTask.getEndDateTime());
		assertEquals(temp.getLocation(), parsedTask.getLocation());
	}
	
	private DateTime initializeDateTime() {
		DateTime d = new DateTime();
		d = d.withHourOfDay(0);
		d = d.withMinuteOfHour(0);
		d = d.withMillisOfDay(0);
		return d;
	}
}
