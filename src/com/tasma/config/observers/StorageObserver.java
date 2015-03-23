package com.tasma.config.observers;

import java.util.HashMap;
import java.util.Map;

import com.tasma.config.ChangeObserverInterface;
import com.tasma.config.DefaultProviderInterface;

public class StorageObserver implements ChangeObserverInterface, DefaultProviderInterface {

	@Override
	public void notify(String key, String oldValue, String newValue) {
		// TODO to move the tasks.json file and update the storage path
		if (key.equals("storage")) {
			
		}
	}
	
	public Map<String, String> defaults() {
		Map<String, String> result = new HashMap<String, String>();
		result.put("storage", System.getProperty("user.dir"));
		return result;
	}

}
