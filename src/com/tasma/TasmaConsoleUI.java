/**
 * Tasma Task Manager
 */
package com.tasma;

import java.util.Collection;
import java.util.Scanner;

// This is an alternative UI for testing non-GUI stuff. 
public class TasmaConsoleUI implements TasmaUserInterface {

	private Controller controller = new Controller();
	
	public void initialize() throws Exception {
		controller.setUserInterface(this);
		controller.initialize();
	}

	@SuppressWarnings("resource")
	public void run() {
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.print("Command: ");
			String input = sc.nextLine();
			controller.executeInput(input);
		}
	}
	
	@Override
	public void displayTasks(Collection<Task> tasks) {
		// TODO Auto-generated method stub

	}

	@Override
	public void displayMessage(String message) {
		System.out.println(message);
	}
	
	public void editCmdDisplay (String task) {
		textCommand.setText(task);
	}
	
	public void helpCmdDisplay (String helpMsg) {
		textTasks.setText(helpMsg);
	}

}
