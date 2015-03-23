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
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Task temp = new Task();
		
		temp.setDetails("do cs2105");
		
		DateTime d = new DateTime();
		d = d.plusWeeks(1);
		d = d.withDayOfWeek(DateTimeConstants.MONDAY);	
		temp.setEndDateTime(d);
		
		assertEquals(temp.getEndDateTime(), parsedTask.getEndDateTime());
		assertEquals(temp.getDetails(), parsedTask.getDetails());
		assertEquals(temp.getLocation(), parsedTask.getLocation());
	}
	
	@Test
	public void test2() {
		Parser caller = new Parser();
		Task parsedTask = new Task();
		try {
			parsedTask = caller.parse("do cs2105 on next mon");
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Task temp = new Task();
		
		temp.setDetails("do cs2105");

		assertEquals(temp.getEndDateTime(), parsedTask.getEndDateTime());
		assertEquals(temp.getDetails(), parsedTask.getDetails());
		assertEquals(temp.getLocation(), parsedTask.getLocation());
	}
	
	@Test
	public void test3() {
		Parser caller = new Parser();
		Task parsedTask = new Task();
		try {
			parsedTask = caller.parse("do cs2105 on next mon");
		} catch (InvalidInputException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Task temp = new Task();
		
		temp.setDetails("do cs2105");

		DateTime d = new DateTime();
		d = d.plusWeeks(1);
		d = d.withDayOfWeek(DateTimeConstants.MONDAY);	
		temp.setEndDateTime(d);
		
		temp.setLocation("ALL");
		assertEquals(temp.getEndDateTime(), parsedTask.getEndDateTime());
		assertEquals(temp.getDetails(), parsedTask.getDetails());
		assertEquals(temp.getLocation(), parsedTask.getLocation());
	}
}
