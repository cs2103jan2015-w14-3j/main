/**
 * Tasma Task Manager
 */
package com.tasma;

import java.util.Collection;
import java.util.Scanner;

// This is an alternative UI for testing non-GUI stuff. 
public class TasmaConsoleUI implements TasmaUserInterface {

	private Controller controller = new Controller();
	
	public void initialize(Controller controller) throws Exception {
		this.controller = controller;
		this.controller.setUserInterface(this);
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
		
	}

	@Override
	public void displayMessage(String message) {
		System.out.println(message);
	}
	
	public void editCmdDisplay (String task) {
	}
	
	public void helpCmdDisplay (String helpMsg) {
		System.out.println(helpMsg);
	}

}
