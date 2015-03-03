/**
 * Tasma Task Manager
 */
package com.tasma;

/**
 * @author Yong Shan Xian <ysx@u.nus.edu>
 *
 */
public class TasmaController {
	
	public void initialize() {
		
	}
	
	public void execute(String input) {
		
	}
	
	private static String[] splitInput(String input) {
		int intSpacePos = input.indexOf(" ");
		if (intSpacePos == -1) {
			return new String[] {
				input.substring(0, intSpacePos),
				input.substring(intSpacePos + 1)
			};
		} else {
			return new String[] {
				input.trim(),
				""
			};
		}
	}
}
