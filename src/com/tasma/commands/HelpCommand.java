/**
 * Tasma Task Manager
 */
//@author A0132763
package com.tasma.commands;

import java.util.List;

import com.tasma.HelpMessage;
import com.tasma.TaskCollection;
import com.tasma.TasmaUserInterface;

public class HelpCommand extends AbstractCommand {

	protected CommandType commandType;
	
	public HelpCommand(TasmaUserInterface userInterface,
			TaskCollection collection, String command) {
		super(userInterface, collection);
		this.commandType = AliasHandler.normalize(command);
	}

	@Override
	public void execute() throws Exception {
		String message = "";
		switch (commandType) {
			case ADD:
				message = HelpMessage.HELP_ADD;
				break;
			case SEARCH:
				break;
			case LIST:
				break;
			case MARK:
				break;
			case EDIT:
				break;
			case ARCHIVE:
				break;
			case SET:
				break;
			case HELP:
				break;
			case TUTORIAL:
				break;
			case EXIT:
				break;
			default:
				// probably an invalid command
				break;
		}
		String displayMessage = String.format(
				HelpMessage.HELP_TEMPLATE,
				commandType.toString(),
				message,
				listImplode(AliasHandler.aliases(commandType))
			);
		userInterface.displayMessage(displayMessage);
	}
	
	protected static String listImplode(List<String> list) {
		StringBuilder builder = new StringBuilder();
		builder.append(list.remove(0));
		for (String s: list) {
			builder.append(", ");
			builder.append(s);
		}
		return builder.toString();
	}

}
