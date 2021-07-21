package org.ionuth.gen.services.impl.json;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public abstract class BaseServiceJson {
	
	/*
	 * Reads a JSON file from resources folder into a string.
	 * This will not work if the JSON files are in a jar.
	 * When resources are part of a jar they can be read using ClassLoader.getResourceAsStream(fileName);
	 * Or create a distribution with the input files out of the jar in folder contained in classpath
	 */
	protected String readJsonFile(String relativePath)  {
		StringBuilder sb = new StringBuilder();
		try {
			ClassLoader classLoader = getClass().getClassLoader();
			URL resource = classLoader.getResource(relativePath);
			Path path = Paths.get(resource.toURI());
			Files.readAllLines(path).forEach(sb::append);
		} catch(IOException | URISyntaxException ex) {
			ex.printStackTrace();
		}
		return sb.toString();
	}
	
}
