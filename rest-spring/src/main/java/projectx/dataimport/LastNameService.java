package projectx.dataimport;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class LastNameService implements ReadJsonFile {
	
	static class LocalData {
		private List<String> lastNames;
		public List<String> getLastNames() {
			return lastNames;
		}
		public void setLastNames(List<String> lastNames) {
			this.lastNames = lastNames;
		}
	}
	
	private static final String FILE_PATH = "import-data/last-names.json";
	
	private LocalData localData;
	private Random rand = new Random();
	
	public LastNameService() {}
	
	@PostConstruct
	private void init() {
		try {
			byte[] jsonData = readJsonData(FILE_PATH);
			ObjectMapper objectMapper = new ObjectMapper();
			localData = objectMapper.readValue(jsonData, LocalData.class);
		} catch( IOException | URISyntaxException ex) {
			System.err.println(ex.getMessage());
		}
	}
	
	public List<String> getNames() {
		return localData.getLastNames();
	}
	
	public String getRandomName() {
		return localData.getLastNames().get(rand.nextInt(localData.getLastNames().size()));
	}
}
