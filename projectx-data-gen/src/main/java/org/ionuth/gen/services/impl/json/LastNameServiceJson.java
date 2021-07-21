package org.ionuth.gen.services.impl.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.ionuth.gen.services.LastNameService;
import org.json.JSONArray;
import org.json.JSONObject;

public class LastNameServiceJson extends BaseServiceJson implements LastNameService {
	
	private static final String INPUT_PATH = "last-names.json";
	
	private List<String> names;
	private Random rand = new Random();
	
	public LastNameServiceJson() {
		init();
	}
	
	public void init() {
		names = new ArrayList<String>();
		String jsonStr = readJsonFile(INPUT_PATH);
		JSONObject jsonObj = new JSONObject(jsonStr);
		JSONArray jsonNames = jsonObj.getJSONArray("lastNames");
		jsonNames.forEach( e -> names.add((String)e) );
	}
	
	@Override
	public List<String> getNames() {
		return names;
	}

	@Override
	public String getRandomName() {
		return names.get( rand.nextInt(names.size()) );
	}
	
}
