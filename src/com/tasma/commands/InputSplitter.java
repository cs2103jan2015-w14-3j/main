/**
 * Tasma Task Manager
 */
//@author A0132763
package com.tasma.commands;

public class InputSplitter {
	private static final String ARGUMENT_SPACE = " ";
	private static final int ARGUMENT_SPACE_NOT_FOUND = -1;
	
	private String input;

	public InputSplitter(String input) {
		this.input = input;
	}
	
	public String next() {
		String result = "";
		int intSpacePos = input.indexOf(ARGUMENT_SPACE);
		if (intSpacePos == ARGUMENT_SPACE_NOT_FOUND) {
			result = input;
			input = "";
		} else {
			result = input.substring(0, intSpacePos);
			input = input.substring(intSpacePos + 1);
		}
		return result;
	}
	
	public String remainder() {
		return input;
	}
}
