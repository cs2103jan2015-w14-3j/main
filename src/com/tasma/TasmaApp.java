/**
 * Tasma Task Manager
 */
package com.tasma;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.KeyStroke;

import com.tasma.config.Config;
import com.tulskiy.keymaster.common.HotKey;
import com.tulskiy.keymaster.common.HotKeyListener;
import com.tulskiy.keymaster.common.Provider;

public class TasmaApp implements Runnable {
	private static final Logger logger = Log.getLogger( TasmaApp.class.getName() );
	
	private Controller controller;
	private Config config;
	private TasmaUserInterface frame; 
	
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
			
			frame.show();

			Provider provider = Provider.getCurrentProvider(true);
			provider.register(KeyStroke.getKeyStroke("alt shift X"), new HotKeyListener() {

				@Override
				public void onHotKey(HotKey hotKey) {
					frame.show();
				}
	        });
			
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
