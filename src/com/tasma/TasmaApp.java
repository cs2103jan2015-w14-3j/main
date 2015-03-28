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
	
	private Controller controller;
	private Config config;
	private TasmaGUI frame; 
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new TasmaApp());
	}
	
	public TasmaApp() {
		try {
			controller = new Controller();
			config = Config.getInstance();
		} catch (Exception e) {
			
		}
	}
	
	@Override
	public void run() {
		try {
			logger.log(Level.FINE, "Initializing window & application");
			frame = new TasmaGUI();
			frame.initialize(controller);
			controller.initialize();
			controller.executeInput("list");
			if (config.isFirstRun()) {
				controller.executeInput("tutorial");
			}
			
			frame.setVisible(true);
			frame.requestCommandBoxFocus();
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.toString(), e);
			e.printStackTrace();
		}
		try {
			TrayIcon tray = new TrayIcon(frame);
			tray.setup();
		} catch (Exception e) {
			
		}
	}
}
