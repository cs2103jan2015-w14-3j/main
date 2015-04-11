/**
 * Tasma Task Manager
 */
//@author A0118888J
package com.tasma;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

public class Parser {

	/** For logging for Parser */
	private static final Logger logger = Log.getLogger(Parser.class.getName());

	/** Stores taskDetails for parsing */
	private String taskDetails;

	/** Keywords for parsing */
	private static final String[] KEYWORD_ARRAY_ALL = {"on", "at", "from", "by", 
		"tomorrow", "tmrw", "tmr", "today", "tdy", "next", "this"};
	private static final String[] KEYWORD_ARRAY_TOMORROW = {"tomorrow", "tmr", "tmrw"};
	private static final String[] KEYWORD_ARRAY_TODAY = {"today", "tdy"};

	private static final String KEYWORD_ON = "on";
	private static final String KEYWORD_AT = "at";
	private static final String KEYWORD_FROM = "from";
	private static final String KEYWORD_BY = "by";
	private static final String KEYWORD_NEXT = "next";
	private static final String KEYWORD_THIS = "this";
	private static final String KEYWORD_TO = "to";
	private static final String KEYWORD_WEEK = "week";
	private static final String KEYWORD_PM = "pm";

	/** Days of Week*/
	private static final String DAY_MONDAY = "mon";
	private static final String DAY_TUESDAY = "tue";
	private static final String DAY_WEDNESDAY = "wed";
	private static final String DAY_THURSDAY = "thu";
	private static final String DAY_FRIDAY = "fri";
	private static final String DAY_SATURDAY = "sat";
	private static final String DAY_SUNDAY = "sun";

	/** Regular expressions */
	private static final String REGEX_SPLIT = "([^\"]\\S*|\".+?\")\\s*";
	private static final String REGEX_DATE = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.]\\d\\d";
	private static final String REGEX_TIME = "^(([1-9]|[1][0-2]|[1-9][:.][0-5][\\d]|[1][0-2][:.][0-5][\\d])[aApP][mM])";

	/**
	 * Calls the relevant functions to parse the passed string.
	 * @param taskDetails Details of the task to be parsed.
	 * @return Instance of a Task which contains the parsed details.
	 */
	public Task parse(String taskDetails) {
		logger.log(Level.FINE, "Parsing \"{0}\"", taskDetails);
		Task parsedTask = new Task(taskDetails);

		this.taskDetails = taskDetails; 

		parsedTask = parseInput(parsedTask, taskDetails);

		return parsedTask;
	}

	/**
	 * Calls the relevant functions to parse the passed string.
	 * @param taskDetails Details of the task to be parsed.
	 * @param parsedTask Already existing 
	 * @return Instance of a Task which contains the parsed details.
	 */
	public Task parse(Task parsedTask, String taskDetails) {
		logger.log(Level.FINE, "Parsing \"{0}\"", taskDetails);

		this.taskDetails = taskDetails; 

		parsedTask = parseInput(parsedTask, taskDetails);

		return parsedTask;
	}

	/**
	 * Checks if date/time details exist in passed string. If so, calls parseDateTime().
	 * @param parsedTask Instance of Task in which parsed details are to be stored.
	 * @param taskDetails Task details to be parsed 
	 * @returns Task object with parsed information.
	 */
	private Task parseInput(Task parsedTask, String taskDetails) {
		logger.log(Level.FINE, "Searching for time in \"{0}\"", taskDetails);
		String[] param = tokenize(taskDetails);


		for (int i = 0; i < param.length; i++) {
			if (param[i].charAt(0) != ' ') {
				if(Arrays.asList(KEYWORD_ARRAY_ALL).contains(param[i]) || 
						isValidDayDate(param[i]) || isValidTime(param[i])) {
					parsedTask = parseDateTime(param, i, parsedTask);
					break;
				}
			}
		}

		return parsedTask;
	}

	/**
	 * Parses date and time from passed string array.
	 * @param param[] Tokenized form of taskDetails. 
	 * @param num Index in param[] of keyword.
	 * @param parsedTask Task object in which parsed details are to be stored.
	 * @returns Task object with parsed information.
	 */
	private Task parseDateTime(String[] param, int num, Task parsedTask) {
		logger.log(Level.FINE, "Parsing date and time in \"{0}\"", taskDetails);
		DateTime d;
		boolean isStartSet = false;

		d = setInitialEndTime(parsedTask);

		//int beginIndex = num, endIndex = 0;
		//boolean parsed = false;

		for (int i = num; i < param.length; i++) {
			if (param[i].charAt(0) != ' ') {
				if (isValidDayDate(param[i])) {
					d = parseDate(param[i], d);
					//parsed = true;
				} else if (isValidTime(param[i])) {
					d = getTime(param[i], d);
					//parsed = true;
				} else if (param[i].equals(KEYWORD_TO)) {
					parsedTask.setStartDateTime(d);
					isStartSet = true;
				} 
			}


			/*if (parsed == true && beginIndex == -1) {
				beginIndex = i - 1;
				endIndex = i - 1;
			} else if (parsed == true && beginIndex != -1) {
				endIndex++;
			}*/
		}

		parsedTask.setEndDateTime(d);

		setStartTime(parsedTask, d, isStartSet);

		setTaskDetails(param, num, parsedTask);

		return parsedTask;
	}

	/**
	 * Sets initial end date/time of passed Task object.
	 * @param parsedTask 	Task object in whose details are to be modified. 
	 * @returns Initialised DateTime object.
	 */
	private DateTime setInitialEndTime(Task parsedTask) {
		DateTime d;

		if (parsedTask.getEndDateTime() == null) {
			d = initializeDateTime();
		} else {
			d = parsedTask.getEndDateTime();
		}
		return d;
	}

	/**
	 * Sets start date/time of passed Task object.
	 * @param parsedTask 	Task object in whose details are to be modified. 
	 * @param d 			DateTime object to be assigned.
	 * @param boolean		Whether start date/time of parsedTask has been set. 
	 * @returns DateTime object with parsed information.
	 */
	private void setStartTime(Task parsedTask, DateTime d, boolean isStartSet) {
		if (!isStartSet) {
			parsedTask.setStartDateTime(d);
		} else if (parsedTask.getStartDateTime().isAfter(d)) {
			DateTime temp = d;
			parsedTask.setEndDateTime(parsedTask.getStartDateTime());
			parsedTask.setStartDateTime(temp);
		}

	}

	/**
	 * Sets task details of passed Task object.
	 * @param param[] 		String array from which details are to be extracted.
	 * @param num     		Index of first keyword in param[]. 
	 * @param parsedTask 	Task object in whose details are to be modified. 
	 * @returns DateTime object with parsed information.
	 */
	private void setTaskDetails(String[] param, int num, Task parsedTask) {
		if (num != 0) {
			String str = "";

			for (int i = 0; i < num; i++) {
				str += param[i] + " "; 
			}

			parsedTask.setDetails(str.trim());
		}
	}

	/**
	 * Parses the day/date from passed string.
	 * @param word String to be parsed.
	 * @param d DateTime object in which date/day details are to be stored. 
	 * @returns DateTime object with parsed information.
	 */
	private DateTime parseDate(String word, DateTime d) {
		if (word.equals(KEYWORD_NEXT)) {
			d = d.plusWeeks(1);
		} else if (determineDay(word) != -1) {
			d = d.withDayOfWeek(determineDay(word));
		} else if (isValidDate(word)) {
			d = getDate(word, d);		
		} else if (isWordToday(word)) {
			d = parseDay(d, 0);
		} else if (isWordTomorrow(word)) {
			d = parseDay(d, 1);

		}

		if (d.isBefore(initializeDateTime().minusDays(1))) {
			d = d.plusWeeks(1);
		}

		return d;
	}

	/**
	 * Checks if passed string is a valid day or date.
	 * @param word String to be checked.
	 * @returns true if word is a valid day/date, false otherwise.
	 */
	private boolean isValidDayDate(String word) {
		if (word.equals(KEYWORD_NEXT)) {
			return true;
		} else if (determineDay(word) != -1) {
			return true;
		} else if (isValidDate(word)) {
			return true;		
		} else if (isWordToday(word)) {
			return true;
		} else if (isWordTomorrow(word)) {
			return true;
		}

		return false;
	}

	/**
	 * Checks if passed string is "today".
	 * @param word String to be checked.
	 * @returns true if word can be matched to a form of "today", false otherwise.
	 */
	private boolean isWordToday(String word) {
		if (Arrays.asList(KEYWORD_ARRAY_TODAY).contains(word)) {
			return true;
		}

		return false;
	}

	/**
	 * Checks if passed string is "tomorrow".
	 * @param word String to be checked.
	 * @returns true if word can be matched to a form of "tomorrow", false otherwise.
	 */
	private boolean isWordTomorrow(String word) {
		if (Arrays.asList(KEYWORD_ARRAY_TOMORROW).contains(word)) {
			return true;
		}

		return false;
	}

	/**
	 * Parses the task's today/tomorrow details.
	 * @param days No. of days to be added from today.
	 * @return DateTime object containing the parsed day.
	 */
	private DateTime parseDay(DateTime d, int days) {
		d = d.withDayOfMonth(new DateTime().getDayOfMonth());
		d = d.withMonthOfYear(new DateTime().getMonthOfYear());
		d = d.withYear(new DateTime().getYear());

		d = d.plusDays(days);

		return d;
	}

	/**
	 * Initializes a DateTime object to current day and time 2359 and returns it.
	 * @return Initialized DateTime object.
	 */
	private DateTime initializeDateTime() {
		DateTime d = new DateTime();
		d = d.withHourOfDay(23);
		d = d.withMinuteOfHour(59);

		return d;
	}

	/**
	 * Determines which day of the week the passed string is.
	 * @param day String containing day to be parsed.
	 * @return Relevant DateTimeConstants if "day" is a valid day/week, -1 otherwise
	 */
	private int determineDay(String day) {
		if (day.toLowerCase().contains(DAY_MONDAY)) {
			return DateTimeConstants.MONDAY;
		} else if (day.toLowerCase().contains(DAY_TUESDAY)) {
			return DateTimeConstants.TUESDAY;
		} else if (day.toLowerCase().contains(DAY_WEDNESDAY)) {
			return DateTimeConstants.WEDNESDAY;
		} else if (day.toLowerCase().contains(DAY_THURSDAY)) {
			return DateTimeConstants.THURSDAY;
		} else if (day.toLowerCase().contains(DAY_FRIDAY)) {
			return DateTimeConstants.FRIDAY;
		} else if (day.toLowerCase().contains(DAY_SATURDAY)) {
			return DateTimeConstants.SATURDAY;
		} else if (day.toLowerCase().contains(DAY_SUNDAY)) {
			return DateTimeConstants.SUNDAY;
		} else if (day.toLowerCase().contains(KEYWORD_WEEK)) {
			return new DateTime().getDayOfWeek();
		} else {
			return -1;
		}
	}

	/**
	 * Checks if the passed string contains a valid date in dd/-.mm/-.yy form.
	 * @param word String to be checked.
	 * @return true if "date" contains a valid date, false otherwise.
	 */
	private boolean isValidDate(String word) {
		final Pattern pattern = Pattern.compile(REGEX_DATE);
		if (!pattern.matcher(word).matches()) {
			return false;
		}

		final int day = Integer.parseInt(word.substring(0, 2)), month = Integer.parseInt(word.substring(3, 5)),
				year = Integer.parseInt(word.substring(6, 8));
		if (day == 31 && (month == 4 || month == 6 || month == 9 || month == 11)) {
			return false; // 31st of a month with 30 days
		} else if (day >= 30 && month == 2) {
			return false; // February 30th or 31st
		} else if (month == 2 && day == 29 && !(year % 4 == 0 && (year % 100 != 0 || year % 400 == 0))) {
			return false; //February 29th outside a leap year
		} else {
			return true; //Valid date
		}
	}

	/**
	 * Gets date from passed string.
	 * @param word String containing date to be parsed.
	 * @param d	   DateTime object to be modified with parsed details.
	 * @return DateTime object containing the parsed date.
	 */
	private DateTime getDate(String word, DateTime d) {
		d = d.withDayOfMonth(Integer.parseInt(word.substring(0, 2)));
		d = d.withMonthOfYear(Integer.parseInt(word.substring(3, 5)));
		d = d.withYear(2000 + Integer.parseInt(word.substring(6, 8)));
		return d;
	}

	/**
	 * Checks if the passed string contains a valid time in 12 hour format.
	 * @param time String to be checked.
	 * @return true if "time" contains a valid time, false otherwise.
	 */
	private boolean isValidTime(String time) {
		final Pattern pattern = Pattern.compile(REGEX_TIME);

		if (!pattern.matcher(time).matches()) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Gets time from passed string.
	 * @param word String containing date to be parsed.
	 * @param d	   		  DateTime object to be modified with parsed details.
	 * @return DateTime object containing the parsed time.
	 */
	private DateTime getTime(String word, DateTime d) {
		int addHours = 0;

		if (word.substring(word.length()-2).compareToIgnoreCase(KEYWORD_PM) == 0) {
			addHours = 12;
		}

		int indexColon = word.indexOf(":"), hours, minutes = 0;

		if (indexColon == -1 ) {
			hours = Integer.parseInt(word.substring(0, word.length() - 2));
		} else {
			hours = Integer.parseInt(word.substring(0, indexColon));
			minutes = Integer.parseInt(word.substring(indexColon+1, word.length() - 2));
		}

		if (hours == 12 && addHours == 0) {
			hours = 0;  //12am
		} else if (hours == 12 & addHours == 12) {
			addHours = 0;  //12pm
		}

		d = d.withHourOfDay(hours + addHours);
		d = d.withMinuteOfHour(minutes);

		return d;
	}

	/**
	 * Splits passed string around spaces and ignores text in double quotes.
	 * @param taskDetails String to be tokenized.
	 * @return Tokenized string array.
	 */
	private String[] tokenize(String taskDetails) {
		ArrayList<String> list = new ArrayList<String>();

		Matcher m = Pattern.compile(REGEX_SPLIT).matcher(taskDetails);

		while (m.find()) {
			list.add(m.group(1).replace("\"", " ")); 
		}

		String[] param = new String[list.size()];
		param = list.toArray(param);

		return param;
	}

}
