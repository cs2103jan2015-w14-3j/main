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
        assertEquals(comparator.compare(d, parsedTask.getStartDateTime()), 0);
        assertEquals(comparator.compare(d, parsedTask.getEndDateTime()), 0);
    }

    @Test
    public void testWithoutIndicators() {
        parsedTask = caller.parse("go to meeting next wed 2pm");

        d = d.plusWeeks(1);
        d = d.withDayOfWeek(DateTimeConstants.WEDNESDAY);
        d = d.withHourOfDay(14);
        d = d.withMinuteOfHour(0);

        assertEquals("go to meeting", parsedTask.getDetails());
        assertEquals(comparator.compare(d, parsedTask.getStartDateTime()), 0);
        assertEquals(comparator.compare(d, parsedTask.getEndDateTime()), 0);
    }

    //Invalid case for date parsing
    @Test
    public void testInvalidDate() {
        parsedTask = caller.parse("\"work on garden\" 22-15-15 12pm");

        d = d.withHourOfDay(12);
        d = d.withMinuteOfHour(0);

        assertEquals("work on garden 22-15-15", parsedTask.getDetails());
        assertEquals(0, comparator.compare(d, parsedTask.getStartDateTime()));
        assertEquals(0, comparator.compare(d, parsedTask.getEndDateTime()));


    }

    //Negative time - currently cannot detect, stores as place
    //from to
    //edit
    //search?
    @Test
    public void testInvalidTime() {
        parsedTask = caller.parse("sign up for stan chart marathon 22-05-15 at -2pm");

        d = d.withDayOfMonth(22);
        d = d.withMonthOfYear(5);
        d = d.withYear(2015);

        assertEquals("sign up for stan chart marathon", parsedTask.getDetails());
        assertEquals(0, comparator.compare(d, parsedTask.getStartDateTime()));
        assertEquals(0, comparator.compare(d, parsedTask.getEndDateTime()));

    }

    @Test
    public void testFromTo() {
        parsedTask = caller.parse("meeting with boss 22.05.15 from 2pm to 3:30pm");

        d = new DateTime(2015, 5, 22, 14, 0); 

        assertEquals("meeting with boss", parsedTask.getDetails());
        assertEquals(0, comparator.compare(d, parsedTask.getStartDateTime()));

        d = d.withHourOfDay(15);
        d = d.withMinuteOfHour(30);

        assertEquals(0, comparator.compare(d, parsedTask.getEndDateTime()));
    }

    @Test
    public void testEdit() {
        parsedTask = caller.parse("meeting with boss 22-05-15 from 2pm to 3:30pm");
        parsedTask = caller.parse(parsedTask, "tmr");

        d = d.plusDays(1);
        d = d.withHourOfDay(15);
        d = d.withMinuteOfHour(30);

        assertEquals("meeting with boss", parsedTask.getDetails());
        assertEquals(0, comparator.compare(d, parsedTask.getStartDateTime()));
        assertEquals(0, comparator.compare(d, parsedTask.getEndDateTime()));
    }

    @Test
    public void testDateSwap() {
        parsedTask = caller.parse("meet classmates for lunch next week 4pm to 3pm");

        d = d.plusWeeks(1);
        d = d.withHourOfDay(15);
        d = d.withMinuteOfHour(0);

        assertEquals("meet classmates for lunch", parsedTask.getDetails());
        assertEquals(0, comparator.compare(d, parsedTask.getStartDateTime()));

        d = d.withHourOfDay(16);
        d = d.withMinuteOfHour(0);

        assertEquals(0, comparator.compare(d, parsedTask.getEndDateTime()));
    }

    private DateTime initializeDateTime() {
        DateTime d = new DateTime();
        d = d.withHourOfDay(23);
        d = d.withMinuteOfHour(59);
        return d;
    }

}
