package org.ionuth.gen.services.impl.json;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.ImmutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.ionuth.gen.services.BookService;
import org.json.JSONArray;
import org.json.JSONObject;

public class BookServiceJson extends BaseServiceJson implements BookService {
	
private static final String INPUT_PATH = "books.json";
	
	private List<Pair<String,String>> books = null;
	
	public BookServiceJson() {
		init();
	}
	
	public void init() {
						
		books = new ArrayList<Pair<String,String>>();
		String jsonStr = readJsonFile(INPUT_PATH);
		JSONObject jsonObj = new JSONObject(jsonStr);
		JSONArray jsonBooks = jsonObj.getJSONArray("books");
		for(int i=0;i<jsonBooks.length();i++) {
			JSONObject jsonCity = jsonBooks.getJSONObject(i);
			String title = jsonCity.getString("title");
			String author = jsonCity.getString("author");
			Pair<String, String> cityPair = new ImmutablePair<String, String>(title, author);
			books.add(cityPair);
		}
		
	}
	
	
	@Override
	public List<Pair<String, String>> getBooks() {
		return books;
	}
	
	
}
