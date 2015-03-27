/**
 * Tasma Task Manager
 */
//@author A0132763
package com.tasma;

import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;

import com.fatboyindustrial.gsonjodatime.Converters;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.tasma.config.Config;

public class Storage {
	private static final Logger logger = Log.getLogger( Storage.class.getName() );
	
	public static final String FILENAME = "tasks.json";
	
	private String path;
	
	public Storage() throws Exception {
		this(Config.getInstance().getProperty("storage"));
	}
	
	public Storage(String path) throws Exception {
		this.path = path;
	}
	
	/**
	 * Saves the hashtable of tasks into the default storage file.
	 * @param tasks The hashtable of tasks to be saved
	 * @throws IOException Thrown when there is a problem trying to save data into the file.
	 */
	public void save(Hashtable<String, Task> tasks) throws IOException {
		logger.log(Level.FINE, "Performing a save on {0} tasks to file", tasks.size());
		final Gson gson = Converters.registerDateTime(new GsonBuilder()).create();
		final String json = gson.toJson(tasks);
    
	    try {
	    	File file = new File(path, FILENAME);
		    FileWriter writer = new FileWriter(file);  
		    writer.write(json);  
		    writer.close();
	    } catch (IOException e) {  
			throw e;
	    }
	}

	/**
	 * Loads the hashtable of tasks from the default storage file.
	 * @return Returns the hashtable of tasks that was loaded from the file
	 * @throws IOException Thrown when there is a problem trying to save data into the file.
	 */
	public Hashtable<String, Task> load() throws IOException {
		logger.log(Level.FINE, "Performing load from file");
		Hashtable<String, Task> tasks = new Hashtable<String, Task>();
		
		File file = new File(path, FILENAME);
		if (file.exists()) {
			try {
				FileReader reader = new FileReader(file);
				final Gson gson = Converters.registerDateTime(new GsonBuilder()).create();
				tasks = gson.fromJson(reader, new TypeToken<Hashtable<String, Task>>() {}.getType());
				reader.close();
			}catch(IOException e) {
				throw e;
			} 
		}
		return tasks;
	}
}
