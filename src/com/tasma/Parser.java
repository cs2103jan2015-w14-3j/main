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
		final String[] keywords = {"on", "at", "from", "by", "tomorrow", "tmrw", "tmr", "today", "tdy", "next"};

		for (int i = 0; i < param.length; i++) {
			if (param[i].charAt(0) != ' ') {
				if(Arrays.asList(keywords).contains(param[i])) {
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
		
		if (parsedTask.getEndDateTime() == null) {
			d = initializeDateTime();
		} else {
			d = parsedTask.getEndDateTime();
		}
		//int beginIndex = -1, endIndex = 0;
		//boolean parsed = false;

		for (int i = num; i < param.length; i++) {
			if (param[i].charAt(0) != ' ') {
				if (isValidDayDate(param[i])) {
					d = parseDate(param[i], d);
					//parsed = true;
				} else if (isValidTime(param[i])) {
					d = getTime(param[i], d);
					//parsed = true;
				} else if (param[i].equals("to")) {
					parsedTask.setStartDateTime(d);
				} else {
					//parsed = false;	
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
		
		if (parsedTask.getStartDateTime() == null) {
			parsedTask.setStartDateTime(d);
		}

		String str = "";
		for (int i = 0; i < num; i++) {
			str += param[i] + " "; 
		}
		parsedTask.setDetails(str.trim());

		return parsedTask;
	}

	/**
	 * Parses the day/date from passed string.
	 * @param word String to be parsed.
	 * @param d DateTime object in which date/day details are to be stored. 
	 * @returns DateTime object with parsed information.
	 */
	private DateTime parseDate(String word, DateTime d) {
		if (word.equals("next")) {
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
		
		if (d.isBefore(initializeDateTime())) {
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
		if (word.equals("next")) {
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
		if (word.equals("today") || word.equals("tdy")) {
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
		if (word.equals("tomorrow") || word.equals("tmr") || word.equals("tmrw")) {
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
		d = d.plusDays(days);

		taskDetails = removeFirstWord(taskDetails);
		return d;
	}

	/**
	 * Initializes and returns a DateTime object.
	 * @return Initialized DateTime object.
	 */
	private DateTime initializeDateTime() {
		DateTime d = new DateTime();
		d = d.withHourOfDay(0);
		d = d.withMinuteOfHour(0);
		d = d.withMillisOfDay(0);
		return d;
	}

	/**
	 * Determines which day of the week the passed string is.
	 * @param day String containing day to be parsed.
	 * @return Relevant DateTimeConstants if "day" is a valid day/week, -1 otherwise
	 */
	private int determineDay(String day) {
		final String[] daysOfWeek = {"mon", "tue", "wed", "thu", "fri", "sat", "sun", "week"};

		if (day.toLowerCase().contains(daysOfWeek[0])) {
			return DateTimeConstants.MONDAY;
		} else if (day.toLowerCase().contains(daysOfWeek[1])) {
			return DateTimeConstants.TUESDAY;
		} else if (day.toLowerCase().contains(daysOfWeek[2])) {
			return DateTimeConstants.WEDNESDAY;
		} else if (day.toLowerCase().contains(daysOfWeek[3])) {
			return DateTimeConstants.THURSDAY;
		} else if (day.toLowerCase().contains(daysOfWeek[4])) {
			return DateTimeConstants.FRIDAY;
		} else if (day.toLowerCase().contains(daysOfWeek[5])) {
			return DateTimeConstants.SATURDAY;
		} else if (day.toLowerCase().contains(daysOfWeek[6])) {
			return DateTimeConstants.SUNDAY;
		} else if (day.toLowerCase().contains(daysOfWeek[7])) {
			return new DateTime().getDayOfWeek();
		}else {
			return -1;
		}
	}

	/**
	 * Checks if the passed string contains a valid date in dd/-.mm/-.yy form.
	 * @param date String to be checked.
	 * @return true if "date" contains a valid date, false otherwise.
	 */
	private boolean isValidDate(String date) {
		final String regexDate = "^(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.]\\d\\d$";
		final Pattern pattern = Pattern.compile(regexDate);
		if (!pattern.matcher(date).matches()) {
			return false;
		}

		final int day = Integer.parseInt(date.substring(0, 2)), month = Integer.parseInt(date.substring(3, 5)),
				year = Integer.parseInt(date.substring(6, 8));
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
	 * @param date String containing date to be parsed.
	 * @param d	   DateTime object to be modified with parsed details.
	 * @return DateTime object containing the parsed date.
	 */
	private DateTime getDate(String date, DateTime d) {
		d = d.withDayOfMonth(Integer.parseInt(date.substring(0, 2)));
		d = d.withMonthOfYear(Integer.parseInt(date.substring(3, 5)));
		d = d.withYear(2000 + Integer.parseInt(date.substring(6, 8)));
		return d;
	}

	/**
	 * Checks if the passed string contains a valid time in 12 hour format.
	 * @param time String to be checked.
	 * @return true if "time" contains a valid time, false otherwise.
	 */
	private boolean isValidTime(String time) {
		/*if (getFirstWord(time).compareToIgnoreCase("at") == 0) {
			time = getWord(time, "at".length() + 1);
		} else {
			time = getFirstWord(time);
		}*/
		final String regexTime = "^(([1-9]|[1][0-2]|[1-9][:.][0-5][\\d]|[1][0-2][:.][0-5][\\d])[aApP][mM])";
		final Pattern pattern = Pattern.compile(regexTime);

		if (!pattern.matcher(time).matches()) {
			return false;
		} else {
			return true;
		}
	}

	/**
	 * Gets time from passed string.
	 * @param taskDetails String containing date to be parsed.
	 * @param d	   		  DateTime object to be modified with parsed details.
	 * @return DateTime object containing the parsed time.
	 */
	private DateTime getTime(String taskDetails, DateTime d) {
		String date;
		if (getFirstWord(taskDetails).compareToIgnoreCase("at") == 0) {
			date = getWord(taskDetails, "at".length() + 1);
		} else {
			date = getFirstWord(taskDetails);
		}

		int addHours = 0;

		if (date.substring(date.length()-2).compareToIgnoreCase("pm") == 0) {
			addHours = 12;
		}

		int indexColon = date.indexOf(":"), hours, minutes = 0;

		if (indexColon == -1 ) {
			hours = Integer.parseInt(date.substring(0, date.length() - 2));
		} else {
			hours = Integer.parseInt(date.substring(0, indexColon));
			minutes = Integer.parseInt(date.substring(indexColon+1, date.length() - 2));
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
		final String regexSplit = "([^\"]\\S*|\".+?\")\\s*";

		Matcher m = Pattern.compile(regexSplit).matcher(taskDetails);

		while (m.find()) {
			list.add(m.group(1).replace("\"", " ")); 
		}

		String[] param = new String[list.size()];
		param = list.toArray(param);

		return param;
	}

	/**
	 * Returns first word of passed string.
	 * @param details String from which first word is to be extracted.
	 * @return First word of passed string.
	 */
	private String getFirstWord(String details) {
		String commandTypeString = details.trim().split("\\s+")[0];
		return commandTypeString;
	}

	/**
	 * Returns word at passed index in passed string.
	 * @param details String from which word is to be extracted.
	 * @param index   Starting index in details from which word is to be extracted.
	 * @return Relevant word of passed string.
	 */
	private String getWord(String details, int index) {
		String commandTypeString = details.substring(index).trim().split("\\s+")[0];
		return commandTypeString;
	}

	/**
	 * Removes first word of passed string and returns the rest of the string.
	 * @param details String from which first word is to be removed.
	 * @return Passed string without first word.
	 */
	private String removeFirstWord(String details) {
		return details.replace(getFirstWord(details), "").trim();
	}
}
