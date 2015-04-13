/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Provides instance management to prevent multiple applications from running
 */
public class AppInstanceManager {

    private static final Logger logger = Log.getLogger( AppInstanceManager.class.getName() );
    
    /**
     * A randomly picked and agreed port number
     */
    private static final int PORT_NUMBER = 54765;
    
    public static final String SINGLE_INSTANCE_SHARED_KEY = "$$NewInstance$$51aBNQcYOEcjUzkAmF5ksDGfbdQvCkCkJ04vbXL6\n";
    
    /**
     * The listener to activate the app if a notification is received
     */
    private AppActivateListener listener;
    
    /**
     * Register an application to run if there are no other instances
     * @param application The application to run
     */
    @SuppressWarnings("resource")
    public void register(Runnable application) {
        logger.log(Level.FINE, "Registering application presence with AppInstanceManager");
        try {
            final ServerSocket socket = new ServerSocket(PORT_NUMBER, 5, InetAddress.getLocalHost());

            Thread instanceListenerThread = new Thread(new Runnable() {
                public void run() {
                    boolean socketClosed = false;
                    while (!socketClosed) {
                        if (socket.isClosed()) {
                            socketClosed = true;
                        } else {
                            try {
                                Socket client = socket.accept();
                                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                                String message = in.readLine();
                                if (SINGLE_INSTANCE_SHARED_KEY.trim().equals(message.trim())) {
                                    logger.log(Level.FINE, "Notification received. showing screen.");
                                    fireAppActivateListener();
                                }
                                in.close();
                                client.close();
                            } catch (IOException e) {
                                socketClosed = true;
                            }
                        }
                    }
                }
            });
            instanceListenerThread.start();
            logger.log(Level.FINE, "Running First-Instance of application");
            application.run();
        } catch (UnknownHostException e) {
        } catch (IOException e) {
            // port taken, app probably running. let's give a fistbump

            logger.log(Level.FINE, "Port taken, notifying first-instance app");
            try {
                Socket clientSocket = new Socket(InetAddress.getLocalHost(), PORT_NUMBER);
                OutputStream out = clientSocket.getOutputStream();
                out.write(SINGLE_INSTANCE_SHARED_KEY.getBytes());
                out.close();
                clientSocket.close();
            } catch (Exception ex) {
            }
            System.exit(0);
        }
    }
    
    public void setAppActivateListener(AppActivateListener listener) {
        this.listener = listener;
    }
    
    private void fireAppActivateListener() {
        if (listener != null) {
            listener.activate();
        }
    }
    
    public interface AppActivateListener {
        public void activate();
    }
}
