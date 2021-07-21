package org.ionuth.gen.services.impl.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.ionuth.gen.services.StreetService;
import org.json.JSONArray;
import org.json.JSONObject;

public class StreetServiceJson extends BaseServiceJson implements StreetService {
	
	private static final String INPUT_PATH = "streets.json";
	
	private List<String> streets = null;
	private Random rand = new Random();
	
	public StreetServiceJson() {
		init();
	}
	
	public void init() {
		streets = new ArrayList<String>();
		String jsonStr = readJsonFile(INPUT_PATH);
		JSONObject jsonObj = new JSONObject(jsonStr);
		JSONArray jsonStreets = jsonObj.getJSONArray("streets");
		jsonStreets.forEach( e -> streets.add((String)e) );
	}
	
	@Override
	public List<String> getStreets() {
		return streets;
	}

	@Override
	public String getRandomStreet() {
		return streets.get( rand.nextInt(streets.size()));
	}
}
