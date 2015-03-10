/**
 * Tasma Task Manager
 */
package com.tasma;

import java.util.Hashtable;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

import com.fatboyindustrial.gsonjodatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class Storage {
	protected static final String FILENAME = "tasks.json";
	
	public void save(Hashtable<String, Task> tasks) throws IOException {
		final Gson gson = Converters.registerLocalDate(new GsonBuilder()).create();
		final String json = gson.toJson(tasks);
    
	    try {
		    FileWriter writer = new FileWriter(FILENAME);  
		    writer.write(json);  
		    writer.close();
	    } catch (IOException e) {  
			throw e;
	    }
	}

	public Hashtable<String, Task> load() throws IOException {
		Hashtable<String, Task> tasks = new Hashtable<String, Task>();
		File file = new File(FILENAME);
		if (file.exists()) {
			try {
				FileReader reader = new FileReader(file);
				final Gson gson = Converters.registerLocalDate(new GsonBuilder()).create();
				tasks = gson.fromJson(reader, new TypeToken<Hashtable<String, Task>>() {}.getType());
				reader.close();
			}catch(IOException e) {
				throw e;
			}
		}
		return tasks;
	}
}
