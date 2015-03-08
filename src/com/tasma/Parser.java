package com.tasma;

/**
 * @author Manika Agarwal
 *
 */

public class Parser {
	private Task parsedTask;
	
	private static final String EMPTY_STRING = "";
	
	public Task parse(String details) {
		parsedTask = new Task();
		
		getWhat(details);
		
		getWhere();
		
		getWho();
		
		return parsedTask;
	}


	private void getWhat(String details) {
		if (!details.contains("on") || !details.contains("at")) {
			parsedTask.setDetails(details);  //command does not contain when or where details
			details = EMPTY_STRING;
		} else {
			
		}
	}
	
	private void getWhere() {
		
	}

	private void getWho() {

	}
}
