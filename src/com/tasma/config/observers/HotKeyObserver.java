package com.tasma.config.observers;

import java.util.HashMap;
import java.util.Map;

import com.tasma.config.ChangeObserverInterface;
import com.tasma.config.DefaultProviderInterface;

public class HotKeyObserver implements ChangeObserverInterface, DefaultProviderInterface {
	
	public static final String DEFAULT_HOTKEY = "alt shift A";

	@Override
	public Map<String, String> defaults() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("hotkey", DEFAULT_HOTKEY);
		return map;
	}

	@Override
	public void notify(String key, String oldValue, String newValue) {
		// TODO Auto-generated method stub
		
	}

}
