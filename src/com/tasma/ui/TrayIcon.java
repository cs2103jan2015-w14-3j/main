/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma.ui;

import java.awt.Image;
import java.awt.MenuItem;
import java.awt.SystemTray;
import java.awt.PopupMenu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

import javax.swing.ImageIcon;

import com.tasma.Controller;

/**
 * Provides support for system tray icon
 */
public class TrayIcon {
    
    private static final String LOGO_PATH = "/com/tasma/res/logo16.png";
    
    private PopupMenu popupMenu;
    private java.awt.TrayIcon trayIcon;
    private SystemTray tray;
    
    private TasmaUserInterface userInterface;
    
    public TrayIcon(TasmaUserInterface userInterface, Controller controller)
    		throws Exception {
        if (userInterface == null) {
            throw new Exception();
        }
        this.userInterface = userInterface;
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
        trayIcon = new java.awt.TrayIcon(createImage(LOGO_PATH, "tray icon"));
        tray = SystemTray.getSystemTray();
        buildMenu();
        trayIcon.setPopupMenu(popupMenu);
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
    
    //@author A0119434H
    public void displayInfo(String caption, String text) {
        trayIcon.displayMessage(caption, text, java.awt.TrayIcon.MessageType.INFO);
    }
}