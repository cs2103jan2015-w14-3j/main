/**
 * Tasma Task Manager
 */
//@author A0132763
package com.tasma;

import java.util.LinkedList;
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
	 * Saves the list of tasks into the default storage file.
	 * @param tasks The list of tasks to be saved
	 * @throws IOException Thrown when there is a problem trying to save data into the file.
	 */
	public void save(LinkedList<Task> tasks) throws IOException {
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
	 * Loads the list of tasks from the default storage file.
	 * @return Returns the list of tasks that was loaded from the file
	 * @throws IOException Thrown when there is a problem trying to save data into the file.
	 */
	public LinkedList<Task> load() throws IOException {
		logger.log(Level.FINE, "Performing load from file");
		LinkedList<Task> tasks = new LinkedList<Task>();
		
		File file = new File(path, FILENAME);
		if (file.exists()) {
			try {
				FileReader reader = new FileReader(file);
				final Gson gson = Converters.registerDateTime(new GsonBuilder()).create();
				tasks = gson.fromJson(reader, new TypeToken<LinkedList<Task>>() {}.getType());
				reader.close();
			}catch(IOException e) {
				throw e;
			} 
		}
		return tasks;
	}
}
