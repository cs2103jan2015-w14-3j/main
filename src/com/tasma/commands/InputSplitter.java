/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma.commands;

public class InputSplitter {
	/**
	 * The magical space that splits between two arguments
	 */
	private static final String ARGUMENT_SPACE = " ";
	
	/**
	 * The value returned by indexOf when there is no more space
	 */
	private static final int ARGUMENT_SPACE_NOT_FOUND = -1;
	
	/**
	 * The input given
	 */
	private String input;

	public InputSplitter(String input) {
		this.input = input;
	}
	
	/**
	 * Fetch the next argument
	 * @return Returns the next argument in the input given
	 */
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
	
	/**
	 * Get all remaining text in the splitter
	 * @return Returns the remaining string in the splitter
	 */
	public String remainder() {
		return input;
	}
}
