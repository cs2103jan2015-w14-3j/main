package com.tasma;

/**
 * @author Manika Agarwal
 *
 */

import org.joda.time.LocalDate;
import org.joda.time.DateTimeConstants;
import org.joda.time.base.AbstractInstant;

public class Parser {
	private Task parsedTask;

	private static final String EMPTY_STRING = "";
	private static String taskDetails; 

	public Task parse(String details) {
		parsedTask = new Task();
		taskDetails = details;

		getWhat();

		getWhen();

		getWhere();

		return parsedTask;
	}

	private void getWhat() {
		final String[] keywords = {"on", "at", "in", "from", "by"};
		int index = 0; 

		for (int i = 0; i < keywords.length; i++) {
			if (taskDetails.toLowerCase().contains(keywords[i])) {
				if (index == 0 || taskDetails.toLowerCase().indexOf(keywords[i]) < index) {
					index = taskDetails.toLowerCase().indexOf(keywords[i]);
				}
			}
		}

		if (index != 0) {
			parsedTask.setDetails(taskDetails.substring(0, index));
		} else {
			parsedTask.setDetails(taskDetails);
			index = taskDetails.length();
		}

		taskDetails = taskDetails.substring(index); 
	}

	private void getWhen() {
		if (taskDetails != EMPTY_STRING) {
			final String[] keywords = {"on", "from", "at", "by"};

			if (taskDetails.toLowerCase().contains(keywords[0])) {  //date
				int indexOn = taskDetails.toLowerCase().indexOf(keywords[0]);
				taskDetails = taskDetails.substring(indexOn+3);

				int indexNext = 0, addWeek = 0;

				if (taskDetails.toLowerCase().contains("next")) {
					indexNext = taskDetails.toLowerCase().indexOf("next") + "next".length();
					addWeek = 1;
				}

				String day = taskDetails.substring(indexNext);

				LocalDate d = new LocalDate();
				d = d.plusWeeks(addWeek);
				d = d.withDayOfWeek(determineDay(day));

				if (d.isBefore(new LocalDate())) {
					System.out.println("before");
					d = d.plusWeeks(1);
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

	private void getWhere() {
		final String keyword = "at";
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
}
