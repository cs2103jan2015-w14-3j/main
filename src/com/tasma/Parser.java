/**
 * Tasma Task Manager
 */
package com.tasma;

/**
 * @author Manika Agarwal
 *
 */

import java.util.logging.Logger;
import java.util.logging.Level;
import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.joda.time.DateTimeConstants;

public class Parser {

	private static final Logger logger = Log.getLogger(Parser.class.getName() );
	private String str = null;
	private String taskDetails;

	public Task parse(String taskDetails) throws InvalidInputException{
		Task parsedTask = new Task();

		this.taskDetails = taskDetails; 
		str = taskDetails;

		try {
			getWhat(parsedTask);

			getWhen(parsedTask);

			getWhere(parsedTask);
		} catch (InvalidInputException e) {
			logger.log(Level.FINER, "InvalidInputException thrown: {0}", e.getMessage());
			throw e;
		}

		return parsedTask;
	}

	private void getWhat(Task parsedTask) throws InvalidInputException {
		logger.log(Level.FINE, "Getting what details from {0}", str);
		final String[] keywords = {" on ", " at ", " in ", " from ", " by "};
		int index = 0; 

		if (taskDetails.length() == 0) {
			throw new InvalidInputException();
		}

		for (int i = 0; i < keywords.length; i++) {
			if (taskDetails.toLowerCase().contains(keywords[i])) {
				if (index == 0 || taskDetails.toLowerCase().indexOf(keywords[i]) < index) {
					index = taskDetails.toLowerCase().indexOf(keywords[i]);
				}
			}
		}

		if (index != 0) {
			parsedTask.setDetails(taskDetails.substring(0, index).trim());
		} else {
			parsedTask.setDetails(taskDetails.trim());
			index = taskDetails.length();
		}

		taskDetails = taskDetails.substring(index).trim(); 
	}

	private void getWhen(Task parsedTask) throws InvalidInputException {
		//assert taskDetails.length() != 0;  //add -ea in VM arguments when running to turn on assertions 

		if (taskDetails.length() != 0) {
			logger.log(Level.FINE, "Getting when details from {0}", str);
			final String[] keywords = {"on", "from", "at", "by"};

			DateTime d = new DateTime();
			
			if (taskDetails.toLowerCase().contains(keywords[0])) {  //date
				d = parseTime(keywords[0]);

				if (isValidTime(taskDetails)) {
					d = getTime(taskDetails, d);
					taskDetails = taskDetails.substring("at".length() + 1);
					taskDetails = removeFirstWord(taskDetails);
				}
			} else if (taskDetails.toLowerCase().contains(keywords[2])) {
				if (isValidTime(taskDetails)) {
					d = getTime(taskDetails, d);
					taskDetails = taskDetails.substring("at".length() + 1);
					taskDetails = removeFirstWord(taskDetails);
				}
			} else if (taskDetails.toLowerCase().contains(keywords[3])) {
				d = parseTime(keywords[3]);
				
				if (isValidTime(taskDetails)) {
					d = getTime(taskDetails, d);
					taskDetails = taskDetails.substring("at".length() + 1);
					taskDetails = removeFirstWord(taskDetails);
				}
			}
			
			parsedTask.setEndDateTime(d);
		} 
		//}
		/*if (taskDetails.toLowerCase().contains(keywords[1])) {
					int indexFrom = taskDetails.toLowerCase().indexOf(keywords[1]);
					String dateOfTask = taskDetails.substring(indexOn + 3, indexFrom - 1);

					int indexNext = 0, addWeek = 0;

					if (dateOfTask.toLowerCase().contains("next")) {
						indexNext = taskDetails.toLowerCase().indexOf("next") + "next".length();
						addWeek = 1;
					}

					String date = taskDetails.substring(indexNext);
					System.out.println("when"+date);
					for (int i = 0; i < daysOfWeek.length; i++) {
						if (date.contains(daysOfWeek[i])) {
							DateTime d = new DateTime();
							d = d.plusWeeks(addWeek);
							d = d.withDayOfWeek(DateTimeConstants.FRIDAY);

							parsedTask.setEndDateTime(d);
						}
					}
				} else if (taskDetails.contains(keywords[2])) {  //at time
					int indexAt = taskDetails.indexOf(keywords[2]);

				}*/
		/*} else if (taskDetails.contains(keywords[1])) {

			} else if (taskDetails.contains(keywords[2])) {

			} else if (taskDetails.contains(keywords[3])) {

			} else {
			}*/
	}

	private DateTime parseTime(String keyword) throws InvalidInputException {
		int indexKeyword = taskDetails.toLowerCase().indexOf(keyword) + keyword.length();

		int indexNext = indexKeyword;

		DateTime d = initializeDateTime();

		if (taskDetails.toLowerCase().contains("next")) {
			indexNext = taskDetails.toLowerCase().indexOf("next", indexKeyword) + "next".length();	
			String day = getWord(taskDetails, indexNext);

			d = d.plusWeeks(1);
			int date = determineDay(day);

			if (date != -1) {
				d = d.withDayOfWeek(date);
			} else {
				throw new InvalidInputException("Invalid day");
			}
		} else if (determineDay(getWord(taskDetails, indexNext)) != -1) {
			d = d.withDayOfWeek(determineDay(getWord(taskDetails, indexNext)));
		} else if (isValidDate(getWord(taskDetails, indexNext))) {
			String date = getWord(taskDetails, indexNext);
			d = getDate(date);		
		} else {
			throw new InvalidInputException("Invalid date");
		}

		if (d.isBefore(new DateTime())) {
			d = d.plusWeeks(1);
		}

		taskDetails = taskDetails.substring(indexNext + 1);
		taskDetails = removeFirstWord(taskDetails);
		return d;
	}

	private void getWhere(Task parsedTask) {		
		if (taskDetails.length() != 0) {
			logger.log(Level.FINE, "Getting where details from {0}", str);
			final String keyword = "at";
			if (taskDetails.toLowerCase().contains(keyword)) {
				int indexAt = taskDetails.toLowerCase().indexOf(keyword);
				parsedTask.setLocation(taskDetails.substring(indexAt + 3).trim());
			}
		}
	}	

	private DateTime initializeDateTime() {
		DateTime d = new DateTime();
		d = d.withHourOfDay(0);
		d = d.withMinuteOfHour(0);
		d = d.withMillisOfDay(0);
		return d;
	}

	private int determineDay(String day) {
		final String[] daysOfWeek = {"mon", "tues", "wed", "thur", "fri", "sat", "sun"};

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
		} else {
			return -1;
		}
	}

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

	private DateTime getDate(String date) {
		DateTime d = initializeDateTime();
		d = d.withDayOfMonth(Integer.parseInt(date.substring(0, 2)));
		d = d.withMonthOfYear(Integer.parseInt(date.substring(3, 5)));
		d = d.withYear(2000 + Integer.parseInt(date.substring(6, 8)));
		return d;
	}

	private boolean isValidTime(String time) {
		if (getFirstWord(time).compareToIgnoreCase("at") == 0) {
			time = getWord(time, "at".length() + 1);
			final String regexTime = "^(([1-9]|[1][0-2]|[1-9][:.][0-5][\\d]|[1][0-2][:.][0-5][\\d])[aApP][mM])";
			final Pattern pattern = Pattern.compile(regexTime);

			if (!pattern.matcher(time).matches()) {
				return false;
			} else {
				return true;
			}
		} else {
			return false;
		}
	}

	private DateTime getTime(String taskDetails, DateTime d) {
		String date = getWord(taskDetails, "at".length() + 1);
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

	private String getFirstWord(String details) {
		String commandTypeString = details.trim().split("\\s+")[0];
		return commandTypeString;
	}

	private String getWord(String details, int index) {
		String commandTypeString = details.substring(index).trim().split("\\s+")[0];
		return commandTypeString;
	}

	private String removeFirstWord(String details) {
		return details.replace(getFirstWord(details), "").trim();
	}
}
