package com.tasma;

import java.awt.EventQueue;

public class TasmaApp {
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		TasmaController controller = new TasmaController();
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TasmaUI frame = new TasmaUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
