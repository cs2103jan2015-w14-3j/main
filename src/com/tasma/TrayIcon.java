/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma;

import java.awt.Image;
import java.awt.MenuItem;
import java.awt.SystemTray;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.ImageIcon;

/**
 * Provides support for system tray icon
 */
public class TrayIcon {
	
	private static final long NOTIFICATION_INTERVAL = 3*1000; // in milliseconds
	
	private PopupMenu popupMenu;
	private java.awt.TrayIcon trayIcon;
	private SystemTray tray;
	private Timer timer;
	private TasmaUserInterface userInterface;
	
	private Controller controller;
	
	public TrayIcon(TasmaUserInterface userInterface, Controller controller) throws Exception {
		if (userInterface == null) {
			throw new Exception();
		}
		this.userInterface = userInterface;
		this.controller = controller;
	}
	
	/**
	 * Set up the tray icon
	 * @throws Exception Thrown when system tray is not supported.
	 */
	public void setup() throws Exception {
        if (!SystemTray.isSupported()) {
            throw new UnsupportedOperationException();
        }
        
        popupMenu = new PopupMenu();
        trayIcon = new java.awt.TrayIcon(createImage("res/logo16.png", "tray icon"));
        tray = SystemTray.getSystemTray();
        buildMenu();
        trayIcon.setPopupMenu(popupMenu);
        setupNotification();
        trayIcon.addMouseListener(new java.awt.event.MouseAdapter() {

            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) {
                	userInterface.show();
                }
            }

        });

        tray.add(trayIcon);
	}
	

	/**
	 * Build the context menu of the tray icon
	 */
	protected void buildMenu() {
        MenuItem showItem = new MenuItem("Show Tasma");
        MenuItem exitItem = new MenuItem("Exit");
         
        //Add components to popup menu
        popupMenu.add(showItem);
        popupMenu.addSeparator();
        popupMenu.add(exitItem);
        
        showItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userInterface.show();
            }
        });
        
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tray.remove(trayIcon);
                System.exit(0);
            }
        });
	}
	
    /**
     * Obtain the image URL
     * @param path Path to the image
     * @param description a description
     * @return Returns an Image resource
     */
    protected static Image createImage(String path, String description) {
        URL imageURL = TrayIcon.class.getResource(path);
         
        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }
    
    private void setupNotification() {
		timer = new Timer();
		
		// not used
		/* 
		TimerTask notification = new TimerTask () {
		    @Override
		    public void run () {
		    	int numTasksOverdue = controller.getNumOfUndoneTasksByState(TaskState.OVERDUE);
		    	int numTasksToday = controller.getNumOfUndoneTasksByState(TaskState.TODAY);
		    	String msg = "";
		    	if(numTasksOverdue >= 1){
			    	msg += "You have " + numTasksOverdue + " task(s) overdue. ";
		    	}
		    	if(numTasksToday>= 1){
			    	msg += "You have " + numTasksToday + " task(s) to do today. ";
		    	}
		    	if(!msg.equals("")){
			    	trayIcon.displayMessage("Tasma Notification",
			                msg,
			                java.awt.TrayIcon.MessageType.INFO);
		    	}
		    }
		};
		timer.schedule(notification, 0, NOTIFICATION_INTERVAL);
		*/
		
		
	}

}