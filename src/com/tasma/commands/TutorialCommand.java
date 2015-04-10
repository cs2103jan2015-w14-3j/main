/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma.commands;

import com.tasma.Palette;
import com.tasma.TaskCollection;
import com.tasma.ui.TasmaUserInterface;

public class TutorialCommand extends AbstractCommand {

	private static final String TUTORIAL_MESSAGE = "Hello there! Welcome to TASMA, your personal assistant managing your to-do and schedule.\n\n"
			+ "You can start creating new tasks by entering the 'add' command followed by details of your task. More information on the 'add' command can be found using the 'help add' command.\n\n"
			+ "The 'help' command will show you a list of commands and features available in Tasma.";
	
	public TutorialCommand(TasmaUserInterface userInterface,
			TaskCollection collection) {
		super(userInterface, collection);
	}

	@Override
	public void execute() throws Exception {
		userInterface.displayMessage(TUTORIAL_MESSAGE, Palette.MESSAGE_INFO);
	}

}
