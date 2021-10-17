package projectx.dataimport;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Random;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class CityService implements ReadJsonFile {
	
	static class LocalData {
		List<PojoCity> cities;
		public List<PojoCity> getCities() {
			return cities;
		}
		public void setCities(List<PojoCity> cities) {
			this.cities = cities;
		}
	}
	
	private static final String FILE_PATH = "import-data/cities.json";
	
	private LocalData localData;
	private Random rand = new Random();
	
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
	
	public List<PojoCity> getCities() {
		return localData.getCities();
	}
	
	public PojoCity getRandomCity() {
		return localData.getCities().get(rand.nextInt(localData.getCities().size()));
	}
	
}
