package net.kikkirej.stundenplanmailer;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

import org.apache.log4j.Logger;

public class PropertiesSingleton {

	private Logger logger = Logger.getLogger(PropertiesSingleton.class);
	
	private static PropertiesSingleton instance;
	private Properties properties;
	
	public static PropertiesSingleton getInstance() {
		
		if (instance == null) {
			instance = new PropertiesSingleton();
		}
		return instance;
	}

	// nur innerhalb der Klasse verfügbar,
	// dadurch kann von aussen kein Objekt erzeugt werden
	private PropertiesSingleton() {
		properties = new Properties();
		File file = new File("stundenplan.properties");
		FileReader fileReader;
		try {
			fileReader = new FileReader(file);
			properties.load(fileReader);
		} catch (Exception e) {
			logger.error("Beim auslesen der Einstellungen ist ein Fehler aufgetreten", e);
		}
	}
	
	public String getProperty(String key) {
		return properties.getProperty(key);
	}
}
