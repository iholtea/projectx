package projectx.dataimport;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class StreetService implements ReadJsonFile {
	
	static class LocalData {
		private List<String> streets;
		public List<String> getStreets() {
			return streets;
		}
		public void setStreets(List<String> streets) {
			this.streets = streets;
		}
	}
	
	private static final String FILE_PATH = "import-data/streets.json";
	
	private LocalData localData;
	private Random rand = new Random();
	
	public StreetService() {}
	
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
	
	public List<String> getStreets() {
		return localData.getStreets();
	}
	
	public String getRandomStreet() {
		return localData.getStreets().get(rand.nextInt(localData.getStreets().size()));
	}
}
