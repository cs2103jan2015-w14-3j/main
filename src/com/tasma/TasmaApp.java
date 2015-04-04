/**
 * Tasma Task Manager
 */
//@author A0132763
package com.tasma;

import java.awt.EventQueue;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.KeyStroke;
import com.tasma.config.Config;
import com.tulskiy.keymaster.common.HotKey;
import com.tulskiy.keymaster.common.HotKeyListener;
import com.tulskiy.keymaster.common.Provider;

/**
 * The main application class
 */
public class TasmaApp implements Runnable {
	private static final Logger logger = Log.getLogger( TasmaApp.class.getName() );
	private static final String APP_HOTKEY = "alt shift X";
	
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
			frame = new TasmaGUI();
			controller = new Controller();
			config = Config.getInstance();
		} catch (Exception e) {
			
		}
	}
	
	/**
	 * Start running the app!
	 */
	@Override
	public void run() {
		try {
			logger.log(Level.FINE, "Initializing window & application");
			frame.initialize(controller);
			controller.initialize();
			controller.executeInput("list");
			if (config.isFirstRun()) { // run the tutorial if this is the first time!
				controller.executeInput("tutorial");
			}
			
			frame.show();

			Provider provider = Provider.getCurrentProvider(true);
			provider.register(KeyStroke.getKeyStroke(APP_HOTKEY), new HotKeyListener() {

				@Override
				public void onHotKey(HotKey hotKey) {
					frame.show();
				}
	        });
			
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.toString(), e);
			e.printStackTrace();
		}
		
		// set up tray icon now
		try {
			TrayIcon tray = new TrayIcon(frame);
			tray.setup();
		} catch (Exception e) {
			
		}
	}
}
