package com.tasma;

import java.awt.CheckboxMenuItem;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.SystemTray;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;

public class TrayIcon {
	PopupMenu popupMenu;
	java.awt.TrayIcon trayIcon;
	SystemTray tray;
	
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
        MenuItem aboutItem = new MenuItem("About");
        CheckboxMenuItem cb1 = new CheckboxMenuItem("Set auto size");
        CheckboxMenuItem cb2 = new CheckboxMenuItem("Set tooltip");
        Menu displayMenu = new Menu("Display");
        MenuItem errorItem = new MenuItem("Error");
        MenuItem warningItem = new MenuItem("Warning");
        MenuItem infoItem = new MenuItem("Info");
        MenuItem noneItem = new MenuItem("None");
        MenuItem exitItem = new MenuItem("Exit");
         
        //Add components to popup menu
        popupMenu.add(aboutItem);
        popupMenu.addSeparator();
        popupMenu.add(cb1);
        popupMenu.add(cb2);
        popupMenu.addSeparator();
        popupMenu.add(displayMenu);
        displayMenu.add(errorItem);
        displayMenu.add(warningItem);
        displayMenu.add(infoItem);
        displayMenu.add(noneItem);
        popupMenu.add(exitItem);
        
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
