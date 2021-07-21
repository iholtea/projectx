package org.ionuth.gen.services;

import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

public interface CityService {
	
	List<Pair<String, String>> getCities();
	Pair<String, String> getRandomCity();
}
