package org.ionuth.gen.services.impl.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.ionuth.gen.services.FirstNameService;
import org.json.JSONArray;
import org.json.JSONObject;

public class FirstNameServiceJson extends BaseServiceJson implements FirstNameService {
	
	private static final String INPUT_PATH = "first-names.json";
	
	private List<String> names = null;
	private Random rand = new Random();
	
	public FirstNameServiceJson() {
		init();
	}
	
	public void init() {
		names = new ArrayList<String>();
		String jsonStr = readJsonFile(INPUT_PATH);
		JSONObject jsonObj = new JSONObject(jsonStr);
		JSONArray jsonNames = jsonObj.getJSONArray("firstNames");
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
