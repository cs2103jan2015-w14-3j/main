/**
 * Tasma Task Manager
 */
//@author A0132763H
package com.tasma.ui;

import javax.swing.KeyStroke;
import com.tasma.config.Config;
import com.tasma.config.observers.HotKeyObserver;
import com.tulskiy.keymaster.common.HotKey;
import com.tulskiy.keymaster.common.HotKeyListener;
import com.tulskiy.keymaster.common.Provider;

public class HotKeyHandler {
	private TasmaUserInterface userInterface;
	private static Provider provider = Provider.getCurrentProvider(true);
	
	public HotKeyHandler(TasmaUserInterface userInterface) {
		this.userInterface = userInterface;
	}
	
	public void setHotKey() throws Exception {
		provider.reset();
		Config config = Config.getInstance();
		KeyStroke key = KeyStroke.getKeyStroke(config.getProperty("hotkey"));
		if (key == null) {
			key = KeyStroke.getKeyStroke(HotKeyObserver.DEFAULT_HOTKEY);
		}
		provider.register(key, new HotKeyListener() {

			@Override
			public void onHotKey(HotKey hotKey) {
				userInterface.show();
			}
        });
	}
}
