/**
 * Tasma Task Manager
 */
package com.tasma;

import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TasmaApp {
	private static final Logger logger = Log.getLogger( TasmaApp.class.getName() );
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					logger.log(Level.FINE, "Initializing window & application");
					TasmaGUI frame = new TasmaGUI();
					frame.initialize();
					
					logger.log(Level.FINE, "Showing window and requesting command box focus.");
					frame.setVisible(true);
					frame.requestCommandBoxFocus();
				} catch (Exception e) {
					logger.log(Level.SEVERE, e.toString(), e);
					e.printStackTrace();
				}
			}
		});
	}
}
