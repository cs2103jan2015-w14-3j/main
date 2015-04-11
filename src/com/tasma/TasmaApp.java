/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma;

import java.awt.EventQueue;
import java.awt.Toolkit;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.KeyStroke;

import com.sun.glass.events.KeyEvent;
import com.tasma.AppInstanceManager.AppActivateListener;
import com.tasma.config.Config;
import com.tasma.ui.TasmaConsoleUI;
import com.tasma.ui.TasmaGUI;
import com.tasma.ui.TasmaUserInterface;
import com.tasma.ui.TrayIcon;
import com.tulskiy.keymaster.common.HotKey;
import com.tulskiy.keymaster.common.HotKeyListener;
import com.tulskiy.keymaster.common.Provider;

/**
 * The main application class
 */
public class TasmaApp implements Runnable {
	private static final Logger logger = Log.getLogger( TasmaApp.class.getName() );
	
	private static final String APP_CLI_ARGUMENT = "cli";
	
	private static final String START_UP_DEFAULT_COMMAND = "list";
	private static final String START_UP_TUTORIAL_COMMAND = "tutorial"; 
	
	private Controller controller;
	private Config config;
	private TasmaUserInterface userInterface; 
	private TaskCollection collection;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		TasmaUserInterface userInterface;
		if (args.length == 1 && args[0].toLowerCase().equals(APP_CLI_ARGUMENT)) {
			userInterface = new TasmaConsoleUI();
		} else {
			userInterface = new TasmaGUI();
		}
		
		AppInstanceManager manager = new AppInstanceManager();
		manager.register(new TasmaApp(userInterface));
		manager.setAppActivateListener(new AppActivateListener() {

			@Override
			public void activate() {
				userInterface.show();
			}
			
		});
	}
	
	public TasmaApp() {
		this(new TasmaGUI());
	}
	
	public TasmaApp(TasmaUserInterface userInterface) {
		try {
			this.userInterface = userInterface;
			this.collection = new TaskCollection();
			this.controller = new Controller(collection);
			this.config = Config.getInstance();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Start running the app!
	 */
	//@author A0132763H
	@Override
	public void run() {
		try {
			logger.log(Level.FINE, "Initializing window & application");
			initialize();
			userInterface.show();

			registerHotKey();
			
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.toString(), e);
			e.printStackTrace();
		}
		
		// set up tray icon and balloon notification
		try {
			TrayIcon trayIcon = new TrayIcon(userInterface, controller);
			trayIcon.setup();
			BalloonNotification notification = new BalloonNotification(collection, trayIcon);
			notification.setup();
		} catch (Exception e) {
			
		}
	}
	
	protected void initialize() throws Exception {
		userInterface.initialize(controller);
		controller.initialize();
		controller.executeInput(START_UP_DEFAULT_COMMAND);
		if (config.isFirstRun()) { // run the tutorial if this is the first time!
			controller.executeInput(START_UP_TUTORIAL_COMMAND);
		}
	}
	
	protected void registerHotKey() throws Exception {
		Provider provider = Provider.getCurrentProvider(true);
		Config config = Config.getInstance();
		provider.register(KeyStroke.getKeyStroke(config.getProperty("hotkey")), new HotKeyListener() {

			@Override
			public void onHotKey(HotKey hotKey) {
				userInterface.show();
			}
        });
	}
}
