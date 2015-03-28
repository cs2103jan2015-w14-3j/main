package com.tasma;

import java.awt.Image;
import java.awt.MenuItem;
import java.awt.SystemTray;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;

public class TrayIcon {
	private PopupMenu popupMenu;
	private java.awt.TrayIcon trayIcon;
	private SystemTray tray;
	
	private TasmaGUI userInterface;
	
	public TrayIcon(TasmaGUI userInterface) throws Exception {
		if (userInterface == null) {
			throw new Exception();
		}
		this.userInterface = userInterface;
	}
	
	public void setup() throws UnsupportedOperationException {
        if (!SystemTray.isSupported()) {
            throw new UnsupportedOperationException();
        }
        
        popupMenu = new PopupMenu();
        trayIcon = new java.awt.TrayIcon(createImage("res/logo16.png", "tray icon"));
        tray = SystemTray.getSystemTray();
        buildMenu();
        trayIcon.setPopupMenu(popupMenu);

        try {
            tray.add(trayIcon);
        } catch (Exception e) {
        	
        }
	}
	
	protected void buildMenu() {
        MenuItem showItem = new MenuItem("Show Tasma");
        MenuItem aboutItem = new MenuItem("About");
        MenuItem exitItem = new MenuItem("Exit");
         
        //Add components to popup menu
        popupMenu.add(showItem);
        popupMenu.addSeparator();
        popupMenu.add(aboutItem);
        popupMenu.addSeparator();
        popupMenu.add(exitItem);
        
        showItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                userInterface.setVisible(true);
            }
        });
        
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                tray.remove(trayIcon);
                System.exit(0);
            }
        });
	}
	
    //Obtain the image URL
    protected static Image createImage(String path, String description) {
        URL imageURL = TrayIcon.class.getResource(path);
         
        if (imageURL == null) {
            System.err.println("Resource not found: " + path);
            return null;
        } else {
            return (new ImageIcon(imageURL, description)).getImage();
        }
    }
}
