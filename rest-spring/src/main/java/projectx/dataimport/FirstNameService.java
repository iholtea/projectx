package projectx.dataimport;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class FirstNameService implements ReadJsonFile {
	
	static class LocalData {
		private List<String> firstNames;
		public List<String> getFirstNames() {
			return firstNames;
		}
		public void setFirstNames(List<String> firstNames) {
			this.firstNames = firstNames;
		}
	}
	
	private static final String FILE_PATH = "import-data/first-names.json";
	
	private LocalData localData;
	private Random rand = new Random();
	
	public FirstNameService() {}
	
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
		return localData.getFirstNames();
	}
	
	public String getRandomName() {
		return localData.getFirstNames().get(rand.nextInt(localData.getFirstNames().size()));
	}
	
}
