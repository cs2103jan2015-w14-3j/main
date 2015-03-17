package com.tasma;

import static org.junit.Assert.*;

import org.joda.time.DateTimeConstants;
import org.joda.time.LocalDate;
import org.junit.Test;

public class ParserTest {
	
	@Test
	public void test1() {
		Parser caller = new Parser();
		Task parsedTask = new Task();
		parsedTask = caller.parse("do cs2105 on next mon");
		Task temp = new Task();
		
		temp.setDetails("do cs2105");
		
		LocalDate d = new LocalDate();
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
		parsedTask = caller.parse("do cs2105");
		Task temp = new Task();
		
		temp.setDetails("do cs2105");

		assertEquals(temp.getEndDateTime(), parsedTask.getEndDateTime());
		assertEquals(temp.getDetails(), parsedTask.getDetails());
		assertEquals(temp.getLocation(), parsedTask.getLocation());
	}
}
