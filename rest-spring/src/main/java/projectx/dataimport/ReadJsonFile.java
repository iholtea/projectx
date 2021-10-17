package projectx.dataimport;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public interface ReadJsonFile {
	
	default byte[] readJsonData(String relativePath) throws IOException, URISyntaxException {
		ClassLoader classLoader = getClass().getClassLoader();
		URL resource = classLoader.getResource(relativePath);
		return Files.readAllBytes(Paths.get(resource.toURI()));
	}
	
}
