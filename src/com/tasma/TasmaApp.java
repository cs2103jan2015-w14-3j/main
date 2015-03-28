/**
 * Tasma Task Manager
 */
package com.tasma;

import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.tasma.config.Config;

public class TasmaApp implements Runnable {
	private static final Logger logger = Log.getLogger( TasmaApp.class.getName() );
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new TasmaApp());
	}
	
	@Override
	public void run() {
		try {
			TrayIcon tray = new TrayIcon();
			tray.setup();
		} catch (Exception e) {
			
		}
		try {
			Controller controller = new Controller();
			Config config = Config.getInstance();
			
			logger.log(Level.FINE, "Initializing window & application");
			TasmaGUI frame = new TasmaGUI();
			frame.initialize(controller);
			controller.initialize();
			controller.executeInput("list");
			if (config.isFirstRun()) {
				controller.executeInput("tutorial");
			}
			
			logger.log(Level.FINE, "Showing window and requesting command box focus.");
			frame.setVisible(true);
			frame.requestCommandBoxFocus();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.toString(), e);
			e.printStackTrace();
		}
	}
}
