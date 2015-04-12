/**
 * Tasma Task Manager
 */
//@author A0118888J
package com.tasma;

import static org.junit.Assert.*;

import org.joda.time.DateTimeComparator;
import org.joda.time.DateTimeConstants;
import org.joda.time.DateTime;
import org.joda.time.DateTimeFieldType;
import org.junit.Before;
import org.junit.Test;

import com.tasma.ui.MockUserInterface;

public class ParserTest {

	Parser caller;
	Task parsedTask;
	DateTimeComparator comparator;
	DateTime d;

	@Before
	public void setUp() {
		caller = new Parser();
		parsedTask = new Task();
		d = initializeDateTime();
		comparator = DateTimeComparator.getInstance(DateTimeFieldType.minuteOfHour());	
	}

	@Test
	public void testWithIndicators() {
		parsedTask = caller.parse("do cs2105 on next mon at 2pm");

		d = d.plusWeeks(1);
		d = d.withDayOfWeek(DateTimeConstants.MONDAY);
		d = d.withHourOfDay(14);
		d = d.withMinuteOfHour(0);

		assertEquals("do cs2105", parsedTask.getDetails());
		assertEquals(comparator.compare(d, parsedTask.getEndDateTime()), 0);
		assertEquals(comparator.compare(d, parsedTask.getStartDateTime()), 0);
	}

	@Test
	public void testWithoutIndicators() {
		parsedTask = caller.parse("go to meeting next wed 2pm");

		d = d.plusWeeks(1);
		d = d.withDayOfWeek(DateTimeConstants.WEDNESDAY);
		d = d.withHourOfDay(14);
		d = d.withMinuteOfHour(0);

		assertEquals("go to meeting", parsedTask.getDetails());
		assertEquals(comparator.compare(d, parsedTask.getEndDateTime()), 0);
		assertEquals(comparator.compare(d, parsedTask.getStartDateTime()), 0);
	}

	@Test
	public void test3() {
		parsedTask = caller.parse("do cs2105 on next mon at ALL");

		Task temp = new Task();

		temp.setDetails("do cs2105");

		DateTime d = initializeDateTime();
		d = d.plusWeeks(1);
		d = d.withDayOfWeek(DateTimeConstants.MONDAY);	
		temp.setEndDateTime(d);

		assertEquals(temp.getDetails(), parsedTask.getDetails());
		assertEquals(temp.getEndDateTime(), parsedTask.getEndDateTime());
	}

	//Invalid case for date parsing
	@Test
	public void testInvalidDate() {
		parsedTask = caller.parse("\"work on garden\" 22-15-15 12pm");
			
		d = d.withHourOfDay(12);
		d = d.withMinuteOfHour(0);
		System.out.println(parsedTask.getDetails());
		System.out.println(d);
		System.out.println(parsedTask.getEndDateTime());
		System.out.println(parsedTask.getStartDateTime());
		assertEquals("work on garden 22-15-15", parsedTask.getDetails());
		assertEquals(0, comparator.compare(d, parsedTask.getEndDateTime()));
		assertEquals(0, comparator.compare(d, parsedTask.getStartDateTime()));

	}

	//Negative time - currently cannot detect, stores as place
	//from to
	//edit
	@Test
	public void testInvalidTime() {
		parsedTask = caller.parse("sign up for stan chart marathon 22-05-15 at -2pm");

		d = d.withDayOfMonth(22);
		d = d.withMonthOfYear(5);
		d = d.withYear(2015);

		assertEquals("sign up for stan chart marathon", parsedTask.getDetails());
		assertEquals(0, comparator.compare(d, parsedTask.getEndDateTime()));
		assertEquals(0, comparator.compare(d, parsedTask.getStartDateTime()));
	}

	private DateTime initializeDateTime() {
		DateTime d = new DateTime();
		d = d.withHourOfDay(23);
		d = d.withMinuteOfHour(59);
		return d;
	}

}
