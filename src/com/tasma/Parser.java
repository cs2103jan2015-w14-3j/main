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

import org.joda.time.LocalDate;
import org.joda.time.DateTimeConstants;

public class Parser {
	
	private static final Logger logger = Log.getLogger(Parser.class.getName() );
	
	public Task parse(String details) {
		Task parsedTask = new Task();

		String taskDetails = details; 

		getWhat(parsedTask, taskDetails);

		getWhen(parsedTask, taskDetails);

		getWhere(parsedTask, taskDetails);

		return parsedTask;
	}

	private void getWhat(Task parsedTask, String taskDetails) {
		logger.log(Level.FINER, "Getting what details from {0}", taskDetails);
		final String[] keywords = {" on ", " at ", " in ", " from ", " by "};
		int index = 0; 

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

		taskDetails = taskDetails.substring(index); 
	}

	private void getWhen(Task parsedTask, String taskDetails) {
		assert taskDetails.length() != 0;  //add -ea in VM arguments when running to turn on assertions 
		if (taskDetails.length() != 0) {
			final String[] keywords = {" on ", " from ", " at ", " by "};

			if (taskDetails.toLowerCase().contains(keywords[0])) {  //date
				int indexOn = taskDetails.toLowerCase().indexOf(keywords[0]) + keywords[0].length();

				int indexNext = indexOn, addWeek = 0;

				if (taskDetails.toLowerCase().contains("next")) {
					indexNext = taskDetails.toLowerCase().indexOf("next", indexOn) + "next".length();	
					addWeek = 1;
				}

				//System.out.println("next word = "+getWord(taskDetails, indexNext));
				String day = taskDetails.substring(indexNext);

				LocalDate d = new LocalDate();
				d = d.plusWeeks(addWeek);
				d = d.withDayOfWeek(determineDay(day));

				if (d.isBefore(new LocalDate())) {
					d = d.plusWeeks(1);
				}

				parsedTask.setEndDateTime(d);
				taskDetails = taskDetails.substring(indexNext + 3).trim();
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
							LocalDate d = new LocalDate();
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
	}

	private void getWhere(Task parsedTask, String taskDetails) {		
		if (taskDetails.length() >= 2) {
			final String keyword = " at ";

			if (taskDetails.toLowerCase().contains(keyword)) {
				int indexAt = taskDetails.toLowerCase().indexOf(keyword);
				parsedTask.setLocation(taskDetails.substring(indexAt + 3).trim());
			}
		}
	}	

	private int determineDay(String day) {
		final String[] daysOfWeek = {"mon", "tues", "wed", "thur", "fri", "sat", "sun"};

		if (day.toLowerCase().contains(daysOfWeek[0])) {
			return DateTimeConstants.MONDAY;
		} else if (day.toLowerCase().contains(daysOfWeek[1])) {
			return DateTimeConstants.TUESDAY;
		} else if (day.toLowerCase().contains(daysOfWeek[1])) {
			return DateTimeConstants.WEDNESDAY;
		} else if (day.toLowerCase().contains(daysOfWeek[1])) {
			return DateTimeConstants.THURSDAY;
		} else if (day.toLowerCase().contains(daysOfWeek[1])) {
			return DateTimeConstants.FRIDAY;
		} else if (day.toLowerCase().contains(daysOfWeek[1])) {
			return DateTimeConstants.SATURDAY;
		} else if (day.toLowerCase().contains(daysOfWeek[1])) {
			return DateTimeConstants.SUNDAY;
		} else {
			return -1;
		}
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
