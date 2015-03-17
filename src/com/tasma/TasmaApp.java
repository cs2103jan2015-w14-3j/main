/**
 * Tasma Task Manager
 */
package com.tasma;

import java.awt.EventQueue;

public class TasmaApp {
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					LoggerSetup.setup();
					TasmaGUI frame = new TasmaGUI();
					frame.initialize();
					frame.setVisible(true);
					frame.requestCommandBoxFocus();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
