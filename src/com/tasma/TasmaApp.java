package com.tasma;

import java.awt.EventQueue;

public class TasmaApp {
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		final Controller controller = new Controller();
		controller.initialize();
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					TasmaUI frame = new TasmaUI(controller);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
