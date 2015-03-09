package com.tasma;

import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

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
			// TODO: work on exception handling
	    }
	}

	public ArrayList<Task> load() {
		ArrayList<Task> tasks = new ArrayList<Task>();
		File file = new File(FILENAME);
		if (file.exists()) {
			try {
				FileReader reader = new FileReader(file);
				Gson gson = new Gson();
				tasks = gson.fromJson(reader, new TypeToken<ArrayList<Task>>() {}.getType());
				reader.close();
			}catch(IOException e) {
				// TODO: work on exception handling
			}
		}
		return tasks;
	}
}
