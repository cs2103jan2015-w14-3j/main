package com.tasma.config.observers;

import java.util.HashMap;
import java.util.Map;

import com.tasma.config.ChangeObserverInterface;
import com.tasma.config.DefaultProviderInterface;
import com.tasma.ui.HotKeyHandler;

public class HotKeyObserver implements DefaultProviderInterface {
	
	public static final String DEFAULT_HOTKEY = "alt shift A";

	@Override
	public Map<String, String> defaults() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("hotkey", DEFAULT_HOTKEY);
		return map;
	}
	
}
