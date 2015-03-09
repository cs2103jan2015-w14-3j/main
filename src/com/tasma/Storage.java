package com.tasma;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import com.google.gson.Gson;

public class Storage {
	protected static final String FILENAME = "tasks.json";
	
	public void save(ArrayList<Task> tasks) {
		Gson gson = new Gson();
		String json = gson.toJson(tasks);
    
	    try {
		    FileWriter writer = new FileWriter(FILENAME);  
		    writer.write(json);  
		    writer.close();  
	    
	    } catch (IOException e) {  
		    e.printStackTrace();  
	    }
	}

	// TODO: to implement load method
	public ArrayList<Task> load() {
		ArrayList<Task> tasks = new ArrayList<Task>();
		File file = new File(FILENAME);
		if (file.exists()) {
			
		}
		return tasks;
	}
}
