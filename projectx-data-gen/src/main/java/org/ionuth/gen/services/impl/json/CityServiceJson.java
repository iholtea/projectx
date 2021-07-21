package org.ionuth.gen.services.impl.json;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.ionuth.gen.services.CityService;
import org.json.JSONArray;
import org.json.JSONObject;

public class CityServiceJson extends BaseServiceJson implements CityService {
	
	private static final String INPUT_PATH = "cities.json";
	
	private List<Pair<String,String>> cities = null;
	private Random rand = new Random();
	
	public CityServiceJson() {
		init();
	}
	
	public void init() {
						
		cities = new ArrayList<Pair<String,String>>();
		String jsonStr = readJsonFile(INPUT_PATH);
		JSONObject jsonObj = new JSONObject(jsonStr);
		JSONArray jsonCities = jsonObj.getJSONArray("cities");
		for(int i=0;i<jsonCities.length();i++) {
			JSONObject jsonCity = jsonCities.getJSONObject(i);
			String country = jsonCity.getString("country");
			String city = jsonCity.getString("city");
			Pair<String, String> cityPair = new ImmutablePair<String, String>(country, city);
			cities.add(cityPair);
		}
		
	}
	
	@Override
	public List<Pair<String,String>> getCities() {
		return cities;
	}

	@Override
	public Pair<String, String> getRandomCity() {
		return cities.get( rand.nextInt(cities.size()) );
	}
}
